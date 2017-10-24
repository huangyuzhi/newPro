package com.chains.weixin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.chains.pwqxfwjk.model.CustomerChangePhoneInfo;
import com.chains.pwqxfwjk.model.CustomerComplianInfo;
import com.chains.pwqxfwjk.model.PowerInterruptionNotificationInfo;
import com.chains.pwqxfwjk.service.CustomerInfoService;
import com.chains.pwqxfwjk.service.PowerInterruptionNotificationService;
import com.chains.pwqxfwjk.util.ConvertBean;
import com.chains.pwqxfwjk.util.Exception.UnformattedChangePhoneInfoException;
import com.chains.pwqxfwjk.util.Exception.UnformattedComplianInfoException;
import com.github.sd4324530.fastweixin.handle.EventHandle;
import com.github.sd4324530.fastweixin.handle.MessageHandle;
import com.github.sd4324530.fastweixin.message.BaseMsg;
import com.github.sd4324530.fastweixin.message.TextMsg;
import com.github.sd4324530.fastweixin.message.req.MenuEvent;
import com.github.sd4324530.fastweixin.message.req.TextReqMsg;
import com.github.sd4324530.fastweixin.servlet.WeixinControllerSupport;

@Controller
@RequestMapping("/weixin")
public class WeixinController extends WeixinControllerSupport {
        private static final Logger log = LoggerFactory.getLogger(WeixinController.class);
        private static final String TOKEN = "push.lanyxge.com";
        @Autowired
        private CustomerInfoService customerInfoServiceImpl; 
        @Autowired
        private PowerInterruptionNotificationService powerInterruptionNotificationServiceImpl;
        //设置TOKEN，用于绑定微信服务器
        @Override
        protected String getToken() {
            return TOKEN;
        }
        //使用安全模式时设置：APPID
        @Override
        protected String getAppId() {
            return null;
        }
        //使用安全模式时设置：密钥
        @Override
        protected String getAESKey() {
            return null;
        }
        //重写父类方法，处理对应的微信消息
        @Override
        protected BaseMsg handleTextMsg(TextReqMsg msg) {
            String content = msg.getContent();
            log.debug("用户发送到服务器的内容:{}", content);
            if(content.matches("^#([^#]*#){2}[^#]*#$")) {
            	CustomerChangePhoneInfo info =  ConvertBean.convertFrom(extractChangePhoneInfo(content), CustomerChangePhoneInfo.class);
            	customerInfoServiceImpl.changePhoneNumber(info);
            	return new TextMsg("已提交修改号码的申请，请等待人工处理结果");
            }
            if(content.matches("^(#[^#]+)(#[^#]+)")) {
            	CustomerComplianInfo info = ConvertBean.convertFrom(extractComplianInfo(content), CustomerComplianInfo.class);
            	customerInfoServiceImpl.customerComplianInfo(info);
            	return new TextMsg("感谢您的意见及建议，我们会尽快回复");
            }
            return new TextMsg("请点击'客户沟通'选择相应的业务，回复信息将指示正确的输入格式");
        }
        
        @Override
        protected BaseMsg handleMenuClickEvent(MenuEvent event) {
        	log.info(event.getFromUserName() + "点击了" + event.getEventKey() + "菜单");
        	BaseMsg msg = null;
        	String eventKey = event.getEventKey();
        	if("suggest".equals(eventKey)) {
        		msg = new TextMsg("欢迎您对乌当供电局提出宝贵意见和建议，请按以下格式回复您的留言——#区域#内容");
        	}else if("chgPhoneNum".equals(eventKey)) {
        		msg = new TextMsg("客户电话号码是供电企业与您沟通的主要途径，为了可以为您提供更好的服务，您可以按以下格式对我们系统内留存的电话号码进行更改——#供电户主姓名#用电地址#更改后的电话#");
        	}else if("notification".equals(eventKey)) {
        		String content = "";
        		List<PowerInterruptionNotificationInfo> list = powerInterruptionNotificationServiceImpl.getRecentNotification(7);
        		if(list.size() > 0) {
        			content = "最近一周有" + list.size() + "条停电通知:\n";
        		}else {
        			content = "最近一周没有停电通知";
        		}
        		for(PowerInterruptionNotificationInfo info : list) {
        			content += info.toString() + "\n\n";
        		}
        		msg = new TextMsg(content);
        	}else {
        		msg = new TextMsg("处理事件");
        	}
        	return msg;
        }
        /*1.1版本新增，重写父类方法，加入自定义微信消息处理器
         *不是必须的，上面的方法是统一处理所有的文本消息，如果业务觉复杂，上面的会显得比较乱
         *这个机制就是为了应对这种情况，每个MessageHandle就是一个业务，只处理指定的那部分消息
         */
        @Override
        protected List<MessageHandle> initMessageHandles() {
                List<MessageHandle> handles = new ArrayList<MessageHandle>();
             //   handles.add(new MyMessageHandle());
                return handles;
        }
        //1.1版本新增，重写父类方法，加入自定义微信事件处理器，同上
        @Override
        protected List<EventHandle> initEventHandles() {
                List<EventHandle> handles = new ArrayList<EventHandle>();
              //  handles.add(new MyEventHandle());
                return handles;
        }
      public static void main(String[] args) {
    	  CustomerChangePhoneInfo info =  ConvertBean.convertFrom(extractChangePhoneInfo("#供电户主姓名#用电地址#更改后的电话#"), CustomerChangePhoneInfo.class);
    	  System.out.println(JSON.toJSONString(info));;
	}
    /**
     * 方法名称: extractChangePhoneInfo<br>
     * 方法描述: 从固定格式的字符串中抽取用户改号信息,如果字符串的格式不正确则抛出异常                    <br>
     * @return
     * 返回类型:
     * Map<String,String>
     * @exception
    */
    private static Map<String, String> extractChangePhoneInfo(String info) {
    	String changePhonePattern = "^(#[^#]+)(#[^#]+)(#[^#]+)#$";
    	Pattern pattern = Pattern.compile(changePhonePattern);
    	Matcher matcher = pattern.matcher(info);
    	Map<String, String> result = new HashMap<String, String>();
    	if(matcher.find()) {
			result.put("householderName", matcher.group(1).substring(1));
			result.put("electricityAddress", matcher.group(2).substring(1));
			result.put("newPhoneNumber", matcher.group(3).substring(1));
		}else {
    		throw new UnformattedChangePhoneInfoException("改变用户手机号码的请求格式不正确");
    	}
		return result;
    }
    
    private static Map<String, String> extractComplianInfo(String info) {
    	String complianInfoPattern = "^(#[^#]+)(#[^#]+)";
    	Pattern pattern = Pattern.compile(complianInfoPattern);
    	Matcher matcher = pattern.matcher(info);
    	Map<String, String> result = new HashMap<String, String>();
    	if(matcher.find()) {
    		result.put("complainUnit", matcher.group(1).substring(1));
    		result.put("complainContent", matcher.group(2).substring(1));
		}else {
    		throw new UnformattedComplianInfoException("客户投诉建议请求的格式不正确");
    	}
    	return result;
    }
}