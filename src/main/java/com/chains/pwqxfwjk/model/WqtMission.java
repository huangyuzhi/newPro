package com.chains.pwqxfwjk.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Administrator
 * 外勤通任务实体类
 */
@Entity
@Table(name = "WQT_MISSION")
public class WqtMission implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String exeUser;
	private Date setTime;
	private String title;
	private String content;
	private String consumeName;
	private String consumePhone;
	private String isHave;
	private String missionId;
	private Date publishTime;
	private Integer status;
	private String replyContent;
	private String picPath;
	private Double destinationBdcoordX;
	private Double destinationBdcoordY;
	private String serviceman;
	private String masterServiceman;
	private Integer destinationKey;
	private String destinationDeviceType;
	private Boolean customerDevice;
	private String faultCategory;
	private String faultDevice;
	private Date performerArriveTime;
	private String repairContent;
	private Date repairEndtime;

	
	public WqtMission() {
	}
	
	public WqtMission(String missionId) {
		this.missionId = missionId;
	}

	public WqtMission(Integer id, String exeUser, Date setTime, String title, String content, String consumeName,
			String consumePhone, String isHave, String missionId, Date publishTime, Integer status, String replyContent,
			String picPath, Double destinationBdcoordX, Double destinationBdcoordY, String serviceman,
			String masterServiceman, Integer destinationKey, Boolean customerDevice, String faultCategory,
			String faultDevice, Date performerArriveTime, String repairContent, Date repairEndtime) {
		super();
		this.id = id;
		this.exeUser = exeUser;
		this.setTime = setTime;
		this.title = title;
		this.content = content;
		this.consumeName = consumeName;
		this.consumePhone = consumePhone;
		this.isHave = isHave;
		this.missionId = missionId;
		this.publishTime = publishTime;
		this.status = status;
		this.replyContent = replyContent;
		this.picPath = picPath;
		this.destinationBdcoordX = destinationBdcoordX;
		this.destinationBdcoordY = destinationBdcoordY;
		this.serviceman = serviceman;
		this.masterServiceman = masterServiceman;
		this.destinationKey = destinationKey;
		this.customerDevice = customerDevice;
		this.faultCategory = faultCategory;
		this.faultDevice = faultDevice;
		this.performerArriveTime = performerArriveTime;
		this.repairContent = repairContent;
		this.repairEndtime = repairEndtime;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "exe_user", length = 200)
	public String getExeUser() {
		return this.exeUser;
	}

	public void setExeUser(String exeUser) {
		this.exeUser = exeUser;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "set_time", length = 19)
	public Date getSetTime() {
		return this.setTime;
	}

	public void setSetTime(Date setTime) {
		this.setTime = setTime;
	}

	@Column(name = "title", length = 200)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content", length = 200)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "consume_name", length = 30)
	public String getConsumeName() {
		return this.consumeName;
	}

	public void setConsumeName(String consumeName) {
		this.consumeName = consumeName;
	}

	@Column(name = "consume_phone", length = 40)
	public String getConsumePhone() {
		return this.consumePhone;
	}

	public void setConsumePhone(String consumePhone) {
		this.consumePhone = consumePhone;
	}

	@Column(name = "is_have", length = 1)
	public String getIsHave() {
		return this.isHave;
	}

	public void setIsHave(String isHave) {
		this.isHave = isHave;
	}

	@Column(name = "mission_id", length = 40)
	public String getMissionId() {
		return this.missionId;
	}

	public void setMissionId(String missionId) {
		this.missionId = missionId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "publish_time", length = 19)
	public Date getPublishTime() {
		return this.publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "reply_content", length = 400)
	public String getReplyContent() {
		return this.replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	@Column(name = "pic_path", length = 300)
	public String getPicPath() {
		return this.picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	@Column(name = "destination_bdcoord_x", precision = 30, scale = 12)
	public Double getDestinationBdcoordX() {
		return this.destinationBdcoordX;
	}

	public void setDestinationBdcoordX(Double destinationBdcoordX) {
		this.destinationBdcoordX = destinationBdcoordX;
	}

	@Column(name = "destination_bdcoord_y", precision = 30, scale = 12)
	public Double getDestinationBdcoordY() {
		return this.destinationBdcoordY;
	}

	public void setDestinationBdcoordY(Double destinationBdcoordY) {
		this.destinationBdcoordY = destinationBdcoordY;
	}

	@Column(name = "serviceman", length = 400)
	public String getServiceman() {
		return this.serviceman;
	}

	public void setServiceman(String serviceman) {
		this.serviceman = serviceman;
	}

	@Column(name = "master_serviceman", length = 400)
	public String getMasterServiceman() {
		return this.masterServiceman;
	}

	public void setMasterServiceman(String masterServiceman) {
		this.masterServiceman = masterServiceman;
	}

	@Column(name = "destination_key")
	public Integer getDestinationKey() {
		return this.destinationKey;
	}

	public void setDestinationKey(Integer destinationKey) {
		this.destinationKey = destinationKey;
	}

	@Column(name = "destination_device_type", length = 20)
	public String getDestinationDeviceType() {
		return destinationDeviceType;
	}

	public void setDestinationDeviceType(String destinationDeviceType) {
		this.destinationDeviceType = destinationDeviceType;
	}

	@Column(name = "customer_device")
	@org.hibernate.annotations.Type(type="yes_no")
	public Boolean getCustomerDevice() {
		return this.customerDevice;
	}

	public void setCustomerDevice(Boolean customerDevice) {
		this.customerDevice = customerDevice;
	}

	@Column(name = "fault_category")
	public String getFaultCategory() {
		return this.faultCategory;
	}

	public void setFaultCategory(String faultCategory) {
		this.faultCategory = faultCategory;
	}

	@Column(name = "fault_device")
	public String getFaultDevice() {
		return this.faultDevice;
	}

	public void setFaultDevice(String faultDevice) {
		this.faultDevice = faultDevice;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "performer_arrive_time", length = 19)
	public Date getPerformerArriveTime() {
		return this.performerArriveTime;
	}

	public void setPerformerArriveTime(Date performerArriveTime) {
		this.performerArriveTime = performerArriveTime;
	}

	@Column(name = "repair_content")
	public String getRepairContent() {
		return this.repairContent;
	}

	public void setRepairContent(String repairContent) {
		this.repairContent = repairContent;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "repair_endtime", length = 19)
	public Date getRepairEndtime() {
		return this.repairEndtime;
	}

	public void setRepairEndtime(Date repairEndtime) {
		this.repairEndtime = repairEndtime;
	}

}
