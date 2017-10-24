package com.chains.pwqxfwjk.representation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Administrator
 *外勤通回填信息
 */
public class WqtBackfill {
	private String issUserByPubUser;
	private String issUserByExcuteUser;
	private String title;
	private String content;
	private Date finishTime;
	private String isHave;
	private Date  pubTaskTime;
	private Date  setTime;
	private Integer status;
	private String replyContent;
	private String ecId;
	private String  picPath;
	private Date  createTime;
	
	private String faultDevice;
	
	private String repairContent;
	
	private String faultCategory;
	
	private Date performerArriveTime;
	
	private Date repairEndtime;
	
	private Boolean customerDevice;
	
	public String getIssUserByPubUser() {
		return issUserByPubUser;
	}
	public void setIssUserByPubUser(String issUserByPubUser) {
		this.issUserByPubUser = issUserByPubUser;
	}
	public String getIssUserByExcuteUser() {
		return issUserByExcuteUser;
	}
	public void setIssUserByExcuteUser(String issUserByExcuteUser) {
		this.issUserByExcuteUser = issUserByExcuteUser;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	public String getIsHave() {
		return isHave;
	}
	public void setIsHave(String isHave) {
		this.isHave = isHave;
	}
	public Date getPubTaskTime() {
		return pubTaskTime;
	}
	public void setPubTaskTime(Date pubTaskTime) {
		this.pubTaskTime = pubTaskTime;
	}
	public Date getSetTime() {
		return setTime;
	}
	public void setSetTime(Date setTime) {
		this.setTime = setTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String getEcId() {
		return ecId;
	}
	public void setEcId(String ecId) {
		this.ecId = ecId;
	}
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public void parse() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
		if(replyContent != null) {
			String[] strs = replyContent.split("\n");
			if(strs.length > 4) {
				faultDevice = strs[0];
				repairContent = strs[1];
				faultCategory = strs[2];
				try {
					performerArriveTime = sdf.parse(strs[3]);
				} catch (ParseException e) {
					throw new RuntimeException(e);
				}
				customerDevice = strs[4].equals("是") ? true : false;
				repairEndtime = finishTime;
			}
		}
	}
	
	public String getFaultDevice() {
		return faultDevice;
	}
	public String getRepairContent() {
		return repairContent;
	}
	public String getFaultCategory() {
		return faultCategory;
	}
	public Date getPerformerArriveTime() {
		return performerArriveTime;
	}
	public Date getRepairEndtime() {
		return repairEndtime;
	}
	public Boolean getCustomerDevice() {
		return customerDevice;
	}
}
