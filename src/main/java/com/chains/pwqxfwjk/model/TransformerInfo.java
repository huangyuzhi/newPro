package com.chains.pwqxfwjk.model;

// 2015-11-5 13:58:52 3.2.2.GA

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TransformerInfo 
 *
 * @version	2015-11-5 13:58:52
 */
@Entity
@Table(name = "TRANSFORMER_INFO")
public class TransformerInfo implements java.io.Serializable, GPSCoords {

	/**
	 * 用一句话描述这个变量表示什么
	 */
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String transformerName;

	private String transformerNumber;

	private String assetNumber;

	private String belongBranch;

	private String runningNumber;

	private String runningName;

	private String deviceModel;

	private String firePoint;

	private String transformerType;

	private String boxPattern;

	private String specialCommonSign;

	private String electroNature;

	private String marketingNumber;

	private String windingWireMaterial;

	private String ironCorePattern;

	private String tankSealPattern;

	private String ratedVoltage;

	private String runningSplitGear;

	private String lineGroup;

	private String tappingRange;

	private String ratedCapacity;

	private String manufacturer;

	private String productionNumber;

	private String areaCharacter;

	private String consumerLevel;

	private String productionDate;

	private String runningDate;

	private String servicingUnit;

	private String propertyRightUnit;

	private String concentratorNumber;

	private String shapeDimension;

	private String standbyLineName;

	private String impedanceVoltage;

	private String loadLosses;

	private String noLoadLosses;

	private String noLoadCurrent;

	private String oilNumber;

	private String oilCategory;

	private String fixedAssetNumber;

	private String serviceLife;

	private String agent;

	private String installationSite;

	private String latitude;

	private String longitude;

	private String packMode;

	private String voltageRegulatingMode;

	private String highRatedCurrent;

	private String phaseNumber;

	private String oilTemperationAlarm;

	private String lowRatedCurrent;

	private String noiseValue;

	private String windingTemperationAlarm;

	private String insulatingHeatRating;

	private String coolingMode;

	private String belongLine;

	private String belongLineNumber;

	private String belongSubstation;

	private String belongSubstationNumber;

	private String powerBureauCode;

	private String powerBureauName;

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
	
	private Boolean isFault; 
	
	public TransformerInfo() {
	}

	public TransformerInfo(Integer id) {
		super();
		this.id = id;
	}


	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "TRANSFORMER_NAME", length = 200)
	public String getTransformerName() {
		return this.transformerName;
	}

	public void setTransformerName(String transformerName) {
		this.transformerName = transformerName;
	}

	@Column(name = "TRANSFORMER_NUMBER", length = 200)
	public String getTransformerNumber() {
		return this.transformerNumber;
	}

	public void setTransformerNumber(String transformerNumber) {
		this.transformerNumber = transformerNumber;
	}

	@Column(name = "ASSET_NUMBER", length = 200)
	public String getAssetNumber() {
		return this.assetNumber;
	}

	public void setAssetNumber(String assetNumber) {
		this.assetNumber = assetNumber;
	}

	@Column(name = "BELONG_BRANCH", length = 200)
	public String getBelongBranch() {
		return this.belongBranch;
	}

	public void setBelongBranch(String belongBranch) {
		this.belongBranch = belongBranch;
	}

	@Column(name = "RUNNING_NUMBER", length = 200)
	public String getRunningNumber() {
		return this.runningNumber;
	}

	public void setRunningNumber(String runningNumber) {
		this.runningNumber = runningNumber;
	}

	@Column(name = "RUNNING_NAME", length = 200)
	public String getRunningName() {
		return this.runningName;
	}

	public void setRunningName(String runningName) {
		this.runningName = runningName;
	}

	@Column(name = "DEVICE_MODEL", length = 200)
	public String getDeviceModel() {
		return this.deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	@Column(name = "FIRE_POINT", length = 200)
	public String getFirePoint() {
		return this.firePoint;
	}

	public void setFirePoint(String firePoint) {
		this.firePoint = firePoint;
	}

	@Column(name = "TRANSFORMER_TYPE", length = 200)
	public String getTransformerType() {
		return this.transformerType;
	}

	public void setTransformerType(String transformerType) {
		this.transformerType = transformerType;
	}

	@Column(name = "BOX_PATTERN", length = 200)
	public String getBoxPattern() {
		return this.boxPattern;
	}

	public void setBoxPattern(String boxPattern) {
		this.boxPattern = boxPattern;
	}

	@Column(name = "SPECIAL_COMMON_SIGN", length = 200)
	public String getSpecialCommonSign() {
		return this.specialCommonSign;
	}

	public void setSpecialCommonSign(String specialCommonSign) {
		this.specialCommonSign = specialCommonSign;
	}

	@Column(name = "ELECTRO_NATURE", length = 200)
	public String getElectroNature() {
		return this.electroNature;
	}

	public void setElectroNature(String electroNature) {
		this.electroNature = electroNature;
	}

	@Column(name = "MARKETING_NUMBER", length = 200)
	public String getMarketingNumber() {
		return this.marketingNumber;
	}

	public void setMarketingNumber(String marketingNumber) {
		this.marketingNumber = marketingNumber;
	}

	@Column(name = "WINDING_WIRE_MATERIAL", length = 200)
	public String getWindingWireMaterial() {
		return this.windingWireMaterial;
	}

	public void setWindingWireMaterial(String windingWireMaterial) {
		this.windingWireMaterial = windingWireMaterial;
	}

	@Column(name = "IRON_CORE_PATTERN", length = 200)
	public String getIronCorePattern() {
		return this.ironCorePattern;
	}

	public void setIronCorePattern(String ironCorePattern) {
		this.ironCorePattern = ironCorePattern;
	}

	@Column(name = "TANK_SEAL_PATTERN", length = 200)
	public String getTankSealPattern() {
		return this.tankSealPattern;
	}

	public void setTankSealPattern(String tankSealPattern) {
		this.tankSealPattern = tankSealPattern;
	}

	@Column(name = "RATED_VOLTAGE", length = 200)
	public String getRatedVoltage() {
		return this.ratedVoltage;
	}

	public void setRatedVoltage(String ratedVoltage) {
		this.ratedVoltage = ratedVoltage;
	}

	@Column(name = "RUNNING_SPLIT_GEAR", length = 200)
	public String getRunningSplitGear() {
		return this.runningSplitGear;
	}

	public void setRunningSplitGear(String runningSplitGear) {
		this.runningSplitGear = runningSplitGear;
	}

	@Column(name = "LINE_GROUP", length = 200)
	public String getLineGroup() {
		return this.lineGroup;
	}

	public void setLineGroup(String lineGroup) {
		this.lineGroup = lineGroup;
	}

	@Column(name = "TAPPING_RANGE", length = 200)
	public String getTappingRange() {
		return this.tappingRange;
	}

	public void setTappingRange(String tappingRange) {
		this.tappingRange = tappingRange;
	}

	@Column(name = "RATED_CAPACITY", length = 200)
	public String getRatedCapacity() {
		return this.ratedCapacity;
	}

	public void setRatedCapacity(String ratedCapacity) {
		this.ratedCapacity = ratedCapacity;
	}

	@Column(name = "MANUFACTURER", length = 200)
	public String getManufacturer() {
		return this.manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	@Column(name = "PRODUCTION_NUMBER", length = 200)
	public String getProductionNumber() {
		return this.productionNumber;
	}

	public void setProductionNumber(String productionNumber) {
		this.productionNumber = productionNumber;
	}

	@Column(name = "AREA_CHARACTER", length = 200)
	public String getAreaCharacter() {
		return this.areaCharacter;
	}

	public void setAreaCharacter(String areaCharacter) {
		this.areaCharacter = areaCharacter;
	}

	@Column(name = "CONSUMER_LEVEL", length = 200)
	public String getConsumerLevel() {
		return this.consumerLevel;
	}

	public void setConsumerLevel(String consumerLevel) {
		this.consumerLevel = consumerLevel;
	}

	@Column(name = "PRODUCTION_DATE", length = 200)
	public String getProductionDate() {
		return this.productionDate;
	}

	public void setProductionDate(String productionDate) {
		this.productionDate = productionDate;
	}

	@Column(name = "RUNNING_DATE", length = 200)
	public String getRunningDate() {
		return this.runningDate;
	}

	public void setRunningDate(String runningDate) {
		this.runningDate = runningDate;
	}

	@Column(name = "SERVICING_UNIT", length = 200)
	public String getServicingUnit() {
		return this.servicingUnit;
	}

	public void setServicingUnit(String servicingUnit) {
		this.servicingUnit = servicingUnit;
	}

	@Column(name = "PROPERTY_RIGHT_UNIT", length = 200)
	public String getPropertyRightUnit() {
		return this.propertyRightUnit;
	}

	public void setPropertyRightUnit(String propertyRightUnit) {
		this.propertyRightUnit = propertyRightUnit;
	}

	@Column(name = "CONCENTRATOR_NUMBER", length = 200)
	public String getConcentratorNumber() {
		return this.concentratorNumber;
	}

	public void setConcentratorNumber(String concentratorNumber) {
		this.concentratorNumber = concentratorNumber;
	}

	@Column(name = "SHAPE_DIMENSION", length = 200)
	public String getShapeDimension() {
		return this.shapeDimension;
	}

	public void setShapeDimension(String shapeDimension) {
		this.shapeDimension = shapeDimension;
	}

	@Column(name = "STANDBY_LINE_NAME", length = 200)
	public String getStandbyLineName() {
		return this.standbyLineName;
	}

	public void setStandbyLineName(String standbyLineName) {
		this.standbyLineName = standbyLineName;
	}

	@Column(name = "IMPEDANCE_VOLTAGE", length = 200)
	public String getImpedanceVoltage() {
		return this.impedanceVoltage;
	}

	public void setImpedanceVoltage(String impedanceVoltage) {
		this.impedanceVoltage = impedanceVoltage;
	}

	@Column(name = "LOAD_LOSSES", length = 200)
	public String getLoadLosses() {
		return this.loadLosses;
	}

	public void setLoadLosses(String loadLosses) {
		this.loadLosses = loadLosses;
	}

	@Column(name = "NO_LOAD_LOSSES", length = 200)
	public String getNoLoadLosses() {
		return this.noLoadLosses;
	}

	public void setNoLoadLosses(String noLoadLosses) {
		this.noLoadLosses = noLoadLosses;
	}

	@Column(name = "NO_LOAD_CURRENT", length = 200)
	public String getNoLoadCurrent() {
		return this.noLoadCurrent;
	}

	public void setNoLoadCurrent(String noLoadCurrent) {
		this.noLoadCurrent = noLoadCurrent;
	}

	@Column(name = "OIL_NUMBER", length = 200)
	public String getOilNumber() {
		return this.oilNumber;
	}

	public void setOilNumber(String oilNumber) {
		this.oilNumber = oilNumber;
	}

	@Column(name = "OIL_CATEGORY", length = 200)
	public String getOilCategory() {
		return this.oilCategory;
	}

	public void setOilCategory(String oilCategory) {
		this.oilCategory = oilCategory;
	}

	@Column(name = "FIXED_ASSET_NUMBER", length = 200)
	public String getFixedAssetNumber() {
		return this.fixedAssetNumber;
	}

	public void setFixedAssetNumber(String fixedAssetNumber) {
		this.fixedAssetNumber = fixedAssetNumber;
	}

	@Column(name = "SERVICE_LIFE", length = 200)
	public String getServiceLife() {
		return this.serviceLife;
	}

	public void setServiceLife(String serviceLife) {
		this.serviceLife = serviceLife;
	}

	@Column(name = "AGENT", length = 200)
	public String getAgent() {
		return this.agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	@Column(name = "INSTALLATION_SITE", length = 200)
	public String getInstallationSite() {
		return this.installationSite;
	}

	public void setInstallationSite(String installationSite) {
		this.installationSite = installationSite;
	}

	@Column(name = "LATITUDE", length = 200)
	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	@Column(name = "LONGITUDE", length = 200)
	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Column(name = "PACK_MODE", length = 200)
	public String getPackMode() {
		return this.packMode;
	}

	public void setPackMode(String packMode) {
		this.packMode = packMode;
	}

	@Column(name = "VOLTAGE_REGULATING_MODE", length = 200)
	public String getVoltageRegulatingMode() {
		return this.voltageRegulatingMode;
	}

	public void setVoltageRegulatingMode(String voltageRegulatingMode) {
		this.voltageRegulatingMode = voltageRegulatingMode;
	}

	@Column(name = "HIGH_RATED_CURRENT", length = 200)
	public String getHighRatedCurrent() {
		return this.highRatedCurrent;
	}

	public void setHighRatedCurrent(String highRatedCurrent) {
		this.highRatedCurrent = highRatedCurrent;
	}

	@Column(name = "PHASE_NUMBER", length = 200)
	public String getPhaseNumber() {
		return this.phaseNumber;
	}

	public void setPhaseNumber(String phaseNumber) {
		this.phaseNumber = phaseNumber;
	}

	@Column(name = "OIL_TEMPERATION_ALARM", length = 200)
	public String getOilTemperationAlarm() {
		return this.oilTemperationAlarm;
	}

	public void setOilTemperationAlarm(String oilTemperationAlarm) {
		this.oilTemperationAlarm = oilTemperationAlarm;
	}

	@Column(name = "LOW_RATED_CURRENT", length = 200)
	public String getLowRatedCurrent() {
		return this.lowRatedCurrent;
	}

	public void setLowRatedCurrent(String lowRatedCurrent) {
		this.lowRatedCurrent = lowRatedCurrent;
	}

	@Column(name = "NOISE_VALUE", length = 200)
	public String getNoiseValue() {
		return this.noiseValue;
	}

	public void setNoiseValue(String noiseValue) {
		this.noiseValue = noiseValue;
	}

	@Column(name = "WINDING_TEMPERATION_ALARM", length = 200)
	public String getWindingTemperationAlarm() {
		return this.windingTemperationAlarm;
	}

	public void setWindingTemperationAlarm(String windingTemperationAlarm) {
		this.windingTemperationAlarm = windingTemperationAlarm;
	}

	@Column(name = "INSULATING_HEAT_RATING", length = 200)
	public String getInsulatingHeatRating() {
		return this.insulatingHeatRating;
	}

	public void setInsulatingHeatRating(String insulatingHeatRating) {
		this.insulatingHeatRating = insulatingHeatRating;
	}

	@Column(name = "COOLING_MODE", length = 200)
	public String getCoolingMode() {
		return this.coolingMode;
	}

	public void setCoolingMode(String coolingMode) {
		this.coolingMode = coolingMode;
	}

	@Column(name = "BELONG_LINE", length = 200)
	public String getBelongLine() {
		return this.belongLine;
	}

	public void setBelongLine(String belongLine) {
		this.belongLine = belongLine;
	}

	@Column(name = "BELONG_LINE_NUMBER", length = 200)
	public String getBelongLineNumber() {
		return this.belongLineNumber;
	}

	public void setBelongLineNumber(String belongLineNumber) {
		this.belongLineNumber = belongLineNumber;
	}

	@Column(name = "BELONG_SUBSTATION", length = 200)
	public String getBelongSubstation() {
		return this.belongSubstation;
	}

	public void setBelongSubstation(String belongSubstation) {
		this.belongSubstation = belongSubstation;
	}

	@Column(name = "BELONG_SUBSTATION_NUMBER", length = 200)
	public String getBelongSubstationNumber() {
		return this.belongSubstationNumber;
	}

	public void setBelongSubstationNumber(String belongSubstationNumber) {
		this.belongSubstationNumber = belongSubstationNumber;
	}

	@Column(name = "POWER_BUREAU_CODE", length = 200)
	public String getPowerBureauCode() {
		return this.powerBureauCode;
	}

	public void setPowerBureauCode(String powerBureauCode) {
		this.powerBureauCode = powerBureauCode;
	}

	@Column(name = "POWER_BUREAU_NAME", length = 200)
	public String getPowerBureauName() {
		return this.powerBureauName;
	}

	public void setPowerBureauName(String powerBureauName) {
		this.powerBureauName = powerBureauName;
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

	@Column(name = "ENABLED", length = 100)
	public String getEnabled() {
		return this.enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	@Column(name = "DELETE_STATUS", length = 100)
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



	public void setBdCoorsX(Double bdCoorsX) {
		this.bdCoorsX = bdCoorsX;
	}

	@Column(name = "BDCOORS_Y")
	public Double getBdCoorsY() {
		return bdCoorsY;
	}



	public void setBdCoorsY(Double bdCoorsY) {
		this.bdCoorsY = bdCoorsY;
	}

	@Column(name="IS_FAULT", length = 1)
	@org.hibernate.annotations.Type(type="yes_no")
	public Boolean getIsFault() {
		return isFault;
	}

	public void setIsFault(Boolean isFault) {
		this.isFault = isFault;
	}
}
