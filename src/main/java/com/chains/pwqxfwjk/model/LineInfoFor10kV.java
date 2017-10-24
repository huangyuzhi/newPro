package com.chains.pwqxfwjk.model;

// 2015-11-5 13:58:52 3.2.2.GA

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 10kVLineInfo
 * 
 * @author
 * @version 2015-11-5 13:58:52
 */
@Entity
@Table(name = "10kV_LINE_INFO")
public class LineInfoFor10kV implements java.io.Serializable, GPSCoords {

	/**
	 * 用一句话描述这个变量表示什么
	 */
	
	private static final long serialVersionUID = 1L;

	private Integer id;

	/**
	 * 所属变电站.
	 */
	private String belongSubstation;

	/**
	 * 所属线路.
	 */
	private String belongLine;

	/**
	 * 所属分支.
	 */
	private String belongBranch;

	/**
	 * 线路编号.
	 */
	private String lineNumber;

	/**
	 * 杆塔编号.
	 */
	private String towerNumber;

	/**
	 * 杆塔类别.
	 */
	private String towerCategory;

	/**
	 * 运行状态.
	 */
	private String runningState;

	/**
	 * 直径.
	 */
	private String endDiameter;

	/**
	 * 杆塔全高(m).
	 */
	private String towerHeight;

	/**
	 * 呼称高.
	 */
	private String towerEndHeight;

	/**
	 * 海拔.
	 */
	private String altitude;

	/**
	 * 前档距(m).
	 */
	private String frontSpanLength;

	/**
	 * 杆塔类型.
	 */
	private String towerType;

	/**
	 * 杆塔型式.
	 */
	private String towerPattern;

	/**
	 * 铁塔材质.
	 */
	private String ironTowerMaterial;

	/**
	 * 电杆形状.
	 */
	private String poleShape;

	/**
	 * 回路数.
	 */
	private String loopNumber;

	/**
	 * 生产厂家.
	 */
	private String manufacturer;

	/**
	 * 出厂日期.
	 */
	private String productionDate;

	/**
	 * 投运日期.
	 */
	private String runningDate;

	/**
	 * 维护单位.
	 */
	private String servicingUnit;

	/**
	 * 产权单位.
	 */
	private String propertyRightUnit;

	/**
	 * 地形.
	 */
	private String terrain;

	/**
	 * 是否存在交叉跨越.
	 */
	private String ifCrossOver;

	/**
	 * 拉线数.
	 */
	private String cableNumber;

	/**
	 * 设备供应商(代理商).
	 */
	private String agent;

	/**
	 * 使用寿命(年).
	 */
	private String serviceLife;

	/**
	 * 纬度.
	 */
	private String latitude;

	/**
	 * 经度.
	 */
	private String longitude;

	/**
	 * 转角度数.
	 */
	private String rotationDegree;

	/**
	 * 角铁横担.
	 */
	private String angleIronCrossarm;

	/**
	 * 进出线横担.
	 */
	private String inletOutletCrossarm;

	/**
	 * 针式瓷瓶.
	 */
	private String pinTypeInsulator;

	/**
	 * 避雷器.
	 */
	private String arrester;

	/**
	 * 悬式瓷瓶.
	 */
	private String suspensionTypeInsulator;

	/**
	 * 顶套抱箍.
	 */
	private String topHoop;

	private String ceramicCrossarm;

	private String guideHoop;

	private String remarks;

	private String orderId;

	private String enabled;

	private String deleteStatus;

	private String createdBy;

	private String creationTime;

	private String creationDept;

	private String modifiedBy;

	private String modificationTime;

	private String modificationDept;

	private String deletedBy;

	private String deletedTime;

	private String deletedDept;
	
	private Double bdCoorsX;
	
	private Double bdCoorsY;

	private Double towerSort;
	
	private Integer secondarySort;
	
	private String secondaryClass;
	
	public LineInfoFor10kV() {
		super();
	}

	public LineInfoFor10kV(Integer id) {
		super();
		this.id = id;
	}

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 所属变电站.
	 */

	@Column(name = "BELONG_SUBSTATION", length = 200)
	public String getBelongSubstation() {
		return this.belongSubstation;
	}

	/**
	 * 所属变电站.
	 */
	public void setBelongSubstation(String belongSubstation) {
		this.belongSubstation = belongSubstation;
	}

	/**
	 * 所属线路.
	 */

	@Column(name = "BELONG_LINE", length = 200)
	public String getBelongLine() {
		return this.belongLine;
	}

	/**
	 * 所属线路.
	 */
	public void setBelongLine(String belongLine) {
		this.belongLine = belongLine;
	}

	/**
	 * 所属分支.
	 */

	@Column(name = "BELONG_BRANCH", length = 200)
	public String getBelongBranch() {
		return this.belongBranch;
	}

	/**
	 * 所属分支.
	 */
	public void setBelongBranch(String belongBranch) {
		this.belongBranch = belongBranch;
	}

	/**
	 * 线路编号.
	 */

	@Column(name = "LINE_NUMBER", length = 100)
	public String getLineNumber() {
		return this.lineNumber;
	}

	/**
	 * 线路编号.
	 */
	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}

	/**
	 * 杆塔编号.
	 */

	@Column(name = "TOWER_NUMBER", length = 200)
	public String getTowerNumber() {
		return this.towerNumber;
	}

	/**
	 * 杆塔编号.
	 */
	public void setTowerNumber(String towerNumber) {
		this.towerNumber = towerNumber;
	}

	/**
	 * 杆塔类别.
	 */

	@Column(name = "TOWER_CATEGORY", length = 200)
	public String getTowerCategory() {
		return this.towerCategory;
	}

	/**
	 * 杆塔类别.
	 */
	public void setTowerCategory(String towerCategory) {
		this.towerCategory = towerCategory;
	}

	/**
	 * 运行状态.
	 */

	@Column(name = "RUNNING_STATE", length = 200)
	public String getRunningState() {
		return this.runningState;
	}

	/**
	 * 运行状态.
	 */
	public void setRunningState(String runningState) {
		this.runningState = runningState;
	}

	/**
	 * 直径.
	 */

	@Column(name = "END_DIAMETER", length = 200)
	public String getEndDiameter() {
		return this.endDiameter;
	}

	/**
	 * 直径.
	 */
	public void setEndDiameter(String endDiameter) {
		this.endDiameter = endDiameter;
	}

	/**
	 * 杆塔全高(m).
	 */

	@Column(name = "TOWER_HEIGHT", length = 200)
	public String getTowerHeight() {
		return this.towerHeight;
	}

	/**
	 * 杆塔全高(m).
	 */
	public void setTowerHeight(String towerHeight) {
		this.towerHeight = towerHeight;
	}

	/**
	 * 呼称高.
	 */

	@Column(name = "TOWER_END_HEIGHT", length = 200)
	public String getTowerEndHeight() {
		return this.towerEndHeight;
	}

	/**
	 * 呼称高.
	 */
	public void setTowerEndHeight(String towerEndHeight) {
		this.towerEndHeight = towerEndHeight;
	}

	/**
	 * 海拔.
	 */

	@Column(name = "ALTITUDE", length = 200)
	public String getAltitude() {
		return this.altitude;
	}

	/**
	 * 海拔.
	 */
	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}

	/**
	 * 前档距(m).
	 */

	@Column(name = "FRONT_SPAN_LENGTH", length = 200)
	public String getFrontSpanLength() {
		return this.frontSpanLength;
	}

	/**
	 * 前档距(m).
	 */
	public void setFrontSpanLength(String frontSpanLength) {
		this.frontSpanLength = frontSpanLength;
	}

	/**
	 * 杆塔类型.
	 */

	@Column(name = "TOWER_TYPE", length = 200)
	public String getTowerType() {
		return this.towerType;
	}

	/**
	 * 杆塔类型.
	 */
	public void setTowerType(String towerType) {
		this.towerType = towerType;
	}

	/**
	 * 杆塔型式.
	 */

	@Column(name = "TOWER_PATTERN", length = 200)
	public String getTowerPattern() {
		return this.towerPattern;
	}

	/**
	 * 杆塔型式.
	 */
	public void setTowerPattern(String towerPattern) {
		this.towerPattern = towerPattern;
	}

	/**
	 * 铁塔材质.
	 */

	@Column(name = "IRON_TOWER_MATERIAL", length = 200)
	public String getIronTowerMaterial() {
		return this.ironTowerMaterial;
	}

	/**
	 * 铁塔材质.
	 */
	public void setIronTowerMaterial(String ironTowerMaterial) {
		this.ironTowerMaterial = ironTowerMaterial;
	}

	/**
	 * 电杆形状.
	 */

	@Column(name = "POLE_SHAPE", length = 200)
	public String getPoleShape() {
		return this.poleShape;
	}

	/**
	 * 电杆形状.
	 */
	public void setPoleShape(String poleShape) {
		this.poleShape = poleShape;
	}

	/**
	 * 回路数.
	 */

	@Column(name = "LOOP_NUMBER", length = 200)
	public String getLoopNumber() {
		return this.loopNumber;
	}

	/**
	 * 回路数.
	 */
	public void setLoopNumber(String loopNumber) {
		this.loopNumber = loopNumber;
	}

	/**
	 * 生产厂家.
	 */

	@Column(name = "MANUFACTURER", length = 200)
	public String getManufacturer() {
		return this.manufacturer;
	}

	/**
	 * 生产厂家.
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * 出厂日期.
	 */

	@Column(name = "PRODUCTION_DATE", length = 200)
	public String getProductionDate() {
		return this.productionDate;
	}

	/**
	 * 出厂日期.
	 */
	public void setProductionDate(String productionDate) {
		this.productionDate = productionDate;
	}

	/**
	 * 投运日期.
	 */

	@Column(name = "RUNNING_DATE", length = 200)
	public String getRunningDate() {
		return this.runningDate;
	}

	/**
	 * 投运日期.
	 */
	public void setRunningDate(String runningDate) {
		this.runningDate = runningDate;
	}

	/**
	 * 维护单位.
	 */

	@Column(name = "SERVICING_UNIT", length = 200)
	public String getServicingUnit() {
		return this.servicingUnit;
	}

	/**
	 * 维护单位.
	 */
	public void setServicingUnit(String servicingUnit) {
		this.servicingUnit = servicingUnit;
	}

	/**
	 * 产权单位.
	 */

	@Column(name = "PROPERTY_RIGHT_UNIT", length = 200)
	public String getPropertyRightUnit() {
		return this.propertyRightUnit;
	}

	/**
	 * 产权单位.
	 */
	public void setPropertyRightUnit(String propertyRightUnit) {
		this.propertyRightUnit = propertyRightUnit;
	}

	/**
	 * 地形.
	 */

	@Column(name = "TERRAIN", length = 200)
	public String getTerrain() {
		return this.terrain;
	}

	/**
	 * 地形.
	 */
	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}

	/**
	 * 是否存在交叉跨越.
	 */

	@Column(name = "IF_CROSS_OVER", length = 200)
	public String getIfCrossOver() {
		return this.ifCrossOver;
	}

	/**
	 * 是否存在交叉跨越.
	 */
	public void setIfCrossOver(String ifCrossOver) {
		this.ifCrossOver = ifCrossOver;
	}

	/**
	 * 拉线数.
	 */

	@Column(name = "CABLE_NUMBER", length = 200)
	public String getCableNumber() {
		return this.cableNumber;
	}

	/**
	 * 拉线数.
	 */
	public void setCableNumber(String cableNumber) {
		this.cableNumber = cableNumber;
	}

	/**
	 * 设备供应商(代理商).
	 */

	@Column(name = "AGENT", length = 200)
	public String getAgent() {
		return this.agent;
	}

	/**
	 * 设备供应商(代理商).
	 */
	public void setAgent(String agent) {
		this.agent = agent;
	}

	/**
	 * 使用寿命(年).
	 */

	@Column(name = "SERVICE_LIFE", length = 200)
	public String getServiceLife() {
		return this.serviceLife;
	}

	/**
	 * 使用寿命(年).
	 */
	public void setServiceLife(String serviceLife) {
		this.serviceLife = serviceLife;
	}

	/**
	 * 纬度.
	 */

	@Column(name = "LATITUDE", length = 200)
	public String getLatitude() {
		return this.latitude;
	}

	/**
	 * 纬度.
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * 经度.
	 */

	@Column(name = "LONGITUDE", length = 200)
	public String getLongitude() {
		return this.longitude;
	}

	/**
	 * 经度.
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	/**
	 * 转角度数.
	 */

	@Column(name = "ROTATION_DEGREE", length = 200)
	public String getRotationDegree() {
		return this.rotationDegree;
	}

	/**
	 * 转角度数.
	 */
	public void setRotationDegree(String rotationDegree) {
		this.rotationDegree = rotationDegree;
	}

	/**
	 * 角铁横担.
	 */

	@Column(name = "ANGLE_IRON_CROSSARM", length = 200)
	public String getAngleIronCrossarm() {
		return this.angleIronCrossarm;
	}

	/**
	 * 角铁横担.
	 */
	public void setAngleIronCrossarm(String angleIronCrossarm) {
		this.angleIronCrossarm = angleIronCrossarm;
	}

	/**
	 * 进出线横担.
	 */

	@Column(name = "INLET_OUTLET_CROSSARM", length = 200)
	public String getInletOutletCrossarm() {
		return this.inletOutletCrossarm;
	}

	/**
	 * 进出线横担.
	 */
	public void setInletOutletCrossarm(String inletOutletCrossarm) {
		this.inletOutletCrossarm = inletOutletCrossarm;
	}

	/**
	 * 针式瓷瓶.
	 */

	@Column(name = "PIN_TYPE_INSULATOR", length = 200)
	public String getPinTypeInsulator() {
		return this.pinTypeInsulator;
	}

	/**
	 * 针式瓷瓶.
	 */
	public void setPinTypeInsulator(String pinTypeInsulator) {
		this.pinTypeInsulator = pinTypeInsulator;
	}

	/**
	 * 避雷器.
	 */

	@Column(name = "ARRESTER", length = 200)
	public String getArrester() {
		return this.arrester;
	}

	/**
	 * 避雷器.
	 */
	public void setArrester(String arrester) {
		this.arrester = arrester;
	}

	/**
	 * 悬式瓷瓶.
	 */

	@Column(name = "SUSPENSION_TYPE_INSULATOR", length = 200)
	public String getSuspensionTypeInsulator() {
		return this.suspensionTypeInsulator;
	}

	/**
	 * 悬式瓷瓶.
	 */
	public void setSuspensionTypeInsulator(String suspensionTypeInsulator) {
		this.suspensionTypeInsulator = suspensionTypeInsulator;
	}

	/**
	 * 顶套抱箍.
	 */

	@Column(name = "TOP_HOOP", length = 200)
	public String getTopHoop() {
		return this.topHoop;
	}

	/**
	 * 顶套抱箍.
	 */
	public void setTopHoop(String topHoop) {
		this.topHoop = topHoop;
	}

	@Column(name = "CERAMIC_CROSSARM", length = 200)
	public String getCeramicCrossarm() {
		return this.ceramicCrossarm;
	}

	public void setCeramicCrossarm(String ceramicCrossarm) {
		this.ceramicCrossarm = ceramicCrossarm;
	}

	@Column(name = "GUIDE_HOOP", length = 200)
	public String getGuideHoop() {
		return this.guideHoop;
	}

	public void setGuideHoop(String guideHoop) {
		this.guideHoop = guideHoop;
	}

	@Column(name = "REMARKS", length = 1000)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "ORDER_ID", length = 100)
	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Column(name = "ENABLED", length = 10)
	public String getEnabled() {
		return this.enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	@Column(name = "DELETE_STATUS", length = 10)
	public String getDeleteStatus() {
		return this.deleteStatus;
	}

	public void setDeleteStatus(String deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	@Column(name = "CREATED_BY", length = 100)
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "CREATION_TIME", length = 100)
	public String getCreationTime() {
		return this.creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}

	@Column(name = "CREATION_DEPT", length = 100)
	public String getCreationDept() {
		return this.creationDept;
	}

	public void setCreationDept(String creationDept) {
		this.creationDept = creationDept;
	}

	@Column(name = "MODIFIED_BY", length = 100)
	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Column(name = "MODIFICATION_TIME", length = 100)
	public String getModificationTime() {
		return this.modificationTime;
	}

	public void setModificationTime(String modificationTime) {
		this.modificationTime = modificationTime;
	}

	@Column(name = "MODIFICATION_DEPT", length = 100)
	public String getModificationDept() {
		return this.modificationDept;
	}

	public void setModificationDept(String modificationDept) {
		this.modificationDept = modificationDept;
	}

	@Column(name = "DELETED_BY", length = 100)
	public String getDeletedBy() {
		return this.deletedBy;
	}

	public void setDeletedBy(String deletedBy) {
		this.deletedBy = deletedBy;
	}

	@Column(name = "DELETED_TIME", length = 100)
	public String getDeletedTime() {
		return this.deletedTime;
	}

	public void setDeletedTime(String deletedTime) {
		this.deletedTime = deletedTime;
	}

	@Column(name = "DELETED_DEPT", length = 100)
	public String getDeletedDept() {
		return this.deletedDept;
	}

	public void setDeletedDept(String deletedDept) {
		this.deletedDept = deletedDept;
	}

	/**
	 * @since bdCoorsX
	 * @return Double
	 */
	@Column(name = "BDCOORS_X")
	public Double getBdCoorsX() {
		return bdCoorsX;
	}

	/**
	 * @since bdCoorsX
	 * @param bdCoorsX
	 * @return void
	 */
	
	public void setBdCoorsX(Double bdCoorsX) {
		this.bdCoorsX = bdCoorsX;
	}

	/**
	 * @since bdCoorsY
	 * @return Double
	 */
	@Column(name = "BDCOORS_Y")
	public Double getBdCoorsY() {
		return bdCoorsY;
	}

	/**
	 * @since bdCoorsY
	 * @param bdCoorsY
	 * @return void
	 */
	
	public void setBdCoorsY(Double bdCoorsY) {
		this.bdCoorsY = bdCoorsY;
	}

	/**
	 * @since towerSort
	 * @return String
	 */
	@Column(name="TOWER_SORT")
	public Double getTowerSort() {
		return towerSort;
	}

	/**
	 * @since towerSort
	 * @param towerSort
	 * @return void
	 */
	
	public void setTowerSort(Double towerSort) {
		this.towerSort = towerSort;
	}

	/**
	 * @since secondarySort
	 * @return Integer
	 */
	@Column(name="SECONDARY_SORT")
	public Integer getSecondarySort() {
		return secondarySort;
	}

	/**
	 * @since secondarySort
	 * @param secondarySort
	 * @return void
	 */
	
	public void setSecondarySort(Integer secondarySort) {
		this.secondarySort = secondarySort;
	}

	/**
	 * @since secondaryClass
	 * @return String
	 */
	@Column(name="secondary_class")
	public String getSecondaryClass() {
		return secondaryClass;
	}

	/**
	 * @since secondaryClass
	 * @param secondaryClass
	 * @return void
	 */
	
	public void setSecondaryClass(String secondaryClass) {
		this.secondaryClass = secondaryClass;
	}
}
