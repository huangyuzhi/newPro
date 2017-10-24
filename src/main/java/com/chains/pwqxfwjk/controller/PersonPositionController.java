package com.chains.pwqxfwjk.controller;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chains.core.util.ControllerSupport;
import com.chains.core.util.HandlerAction;
import com.chains.core.util.StringResult;
import com.chains.pwqxfwjk.model.PersonInfo;
import com.chains.pwqxfwjk.model.PersonInfoCollection;
import com.chains.pwqxfwjk.model.Serviceman;
import com.chains.pwqxfwjk.model.WqtMission;
import com.chains.pwqxfwjk.service.PersonPositionService;
import com.chains.pwqxfwjk.service.ServicemanService;
import com.chains.pwqxfwjk.service.WqtMissionService;
import com.chains.pwqxfwjk.util.PersonPositionQueryParam;
import com.chains.util.Json;

@Controller
@RequestMapping("/PersonPosition")
public class PersonPositionController extends ControllerSupport{
	@Autowired
	private PersonPositionService personPositionServiceImpl;
	
	@Autowired
	private WqtMissionService wqtMissionServiceImpl;
	
	@Autowired
	private ServicemanService servicemanServiceImpl;
	
	private Random random = new Random(47);
	@StringResult("权限不足")
	@ResponseBody
	@RequestMapping("/getPersonPositionWithJson")
	public PersonInfoCollection getPersonPositionWithJson(PersonPositionQueryParam param, HttpServletResponse response) {
		PersonInfoCollection result = null;
		try {
			result = personPositionServiceImpl.getPersonPosition(param);
		} catch (Exception e) {
			e.printStackTrace();
			result = new PersonInfoCollection(); //防止客户端异常
		}
		return result;
	}
	
	@StringResult("权限不足")
	@ResponseBody
	@RequestMapping("/assignMission")
	public Json assignMission(final WqtMission mission) {
		return handlerException(new HandlerAction() {
			@Override
			public void handler() throws Exception {
				wqtMissionServiceImpl.assignMission(mission);
			}
		});
	}
	
	@StringResult("权限不足")
	@ResponseBody
	@RequestMapping("/getmokeData")
	public PersonInfoCollection getmokeData(HttpServletRequest request) {
		PersonInfoCollection result = null;
		try {
			PersonPositionQueryParam personPositionQueryParam = new PersonPositionQueryParam();
			personPositionQueryParam.setPageSize(500);
			PersonInfoCollection personInfoCollection = personPositionServiceImpl.getPersonPosition(personPositionQueryParam);
			
			
			int randomIndex = random.nextInt(personInfoCollection.getResult().size());
			int randomIndex2 = random.nextInt(personInfoCollection.getResult().size());
			int min = randomIndex < randomIndex2 ? randomIndex : randomIndex2;
			int max = min < randomIndex ? randomIndex : randomIndex2;
			List<PersonInfo> subList = personInfoCollection.getResult().subList(min, max);
			for(PersonInfo person : subList) {
				PersonInfo randomPerson = personInfoCollection.getResult().get(random.nextInt(personInfoCollection.getResult().size()));
				person.setUserLng(randomPerson.getUserLng());
				person.setUserLat(randomPerson.getUserLat());
			}
			
			personInfoCollection.setResult(subList);
			result = personInfoCollection;
		} catch (Exception e) {
			e.printStackTrace();
			result = new PersonInfoCollection();	//防止客户端异常
		}
		return result;
	}
	
	/**
	 *  返回所有的抢修人员
	 * @return
	 */
	@StringResult("权限不足")
	@ResponseBody
	@RequestMapping("/getServiceman")
	public List<Serviceman> getServiceman() {
		return servicemanServiceImpl.findByExample(new Serviceman());
	}
	
	@StringResult("权限不足")
	@ResponseBody
	@RequestMapping("/getmokeData2")
	public PersonInfoCollection getmokeData2(HttpServletRequest request) {
		//只返回一条数据，但位置随机
		PersonPositionQueryParam personPositionQueryParam = new PersonPositionQueryParam();
		personPositionQueryParam.setPageSize(500);
		PersonInfoCollection personInfoCollection = personPositionServiceImpl.getPersonPosition(personPositionQueryParam);
		
		PersonInfo personInfo = personInfoCollection.getResult().get(0);
		PersonInfo randomPersonInfo = personInfoCollection.getResult().get(random.nextInt(personInfoCollection.getResult().size()));
		personInfo.setUserLng(randomPersonInfo.getUserLng());
		personInfo.setUserLat(randomPersonInfo.getUserLat());
		
		personInfoCollection.setResult(personInfoCollection.getResult().subList(0, 1));
		return personInfoCollection;
	}
	
	
}
