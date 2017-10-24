package newPro;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import com.chains.pwqxfwjk.model.FaultStatisticsDetail;
import com.chains.pwqxfwjk.model.Serviceman;
import com.chains.pwqxfwjk.model.TransformerInfo;
import com.chains.pwqxfwjk.model.TroubleLine;
import com.chains.pwqxfwjk.model.WqtMission;
import com.chains.pwqxfwjk.representation.WqtBackfill;
import com.chains.pwqxfwjk.service.FaultStatisticsDetailService;
import com.chains.pwqxfwjk.service.ServicemanService;
import com.chains.pwqxfwjk.service.TransformerInfoService;
import com.chains.pwqxfwjk.service.TroubleLineService;
import com.chains.pwqxfwjk.service.WqtMissionService;
import com.chains.pwqxfwjk.util.TransformerInfoQueryParam;
import com.chains.util.QcRowBounds;

import support.Support;

/**
 * 抢修派工的测试
 * @author Administrator
 *
 */
public class TestFaultMission extends Support {
	
	private FaultStatisticsDetailService faultStatisticsDetailService = context.getBean(FaultStatisticsDetailService.class);
	
	private WqtMissionService wqtMissionService = context.getBean(WqtMissionService.class);
	
	private ServicemanService servicemanService = context.getBean(ServicemanService.class);
	
	private TransformerInfoService transformerInfoService = context.getBean(TransformerInfoService.class);
	
	private TroubleLineService troubleLineService = context.getBean(TroubleLineService.class);
	
	@Test
	public void assignMissionwithTrans() throws Exception {	//台变(变压器)故障工单
		WqtMission wqtMission = new WqtMission();
		FaultStatisticsDetail faultStatisticsDetail = new FaultStatisticsDetail();
		fillwqtMission(wqtMission);
		fillFaultStatisticsDetail(wqtMission, faultStatisticsDetail);
		String missionId = faultStatisticsDetailService.assignMissionAndStatistics(wqtMission, faultStatisticsDetail, new TroubleLine());
		
		assertComment(missionId);
		TransformerInfoQueryParam transQueryParam = new TransformerInfoQueryParam();
		transQueryParam.setId(wqtMission.getDestinationKey());
		List<TransformerInfo> trans = transformerInfoService.getData(transQueryParam, new QcRowBounds());
		Assert.assertTrue(trans.get(0).getIsFault());
	}

	@Test
	public void assignMissionwithTroubleLine() throws Exception  {
		WqtMission wqtMission = new WqtMission();
		FaultStatisticsDetail faultStatisticsDetail = new FaultStatisticsDetail();
		TroubleLine troubleLine = new TroubleLine();
		troubleLine.setLineQuality("10kV炬大线/10kV炬大线/master");
		fillwqtMission(wqtMission);
		wqtMission.setDestinationDeviceType("line");
		fillFaultStatisticsDetail(wqtMission, faultStatisticsDetail);
		String missionId = faultStatisticsDetailService.assignMissionAndStatistics(wqtMission, faultStatisticsDetail, troubleLine);
		
		assertComment(missionId);
		List<TroubleLine> tlines = troubleLineService.getTroubleLines();
		Assert.assertTrue(tlines.size() > 0);
		boolean isTarget = false;
		for (TroubleLine troubleLine2 : tlines) {
			if(troubleLine2.getLineQuality().equals(troubleLine.getLineQuality())) {
				isTarget = true;
			}
		}
		Assert.assertTrue(isTarget);
	}

	private void assertComment(String missionId) {
		Assert.assertTrue(wqtMissionService.findByExample(new WqtMission(missionId)).size() > 0);
		FaultStatisticsDetail queryParam = new FaultStatisticsDetail();
		queryParam.setMissionId(missionId);
		Assert.assertTrue(faultStatisticsDetailService.find(queryParam, new QcRowBounds()).size() > 0);
		List<WqtBackfill> backfills = wqtMissionService.findBackfill(missionId);
		Assert.assertTrue(backfills.size() > 0);
		Assert.assertTrue(backfills.get(0).getStatus().equals(2));
		Assert.assertTrue(servicemanService.findByExample(new Serviceman(missionId)).size() > 0);
	}
	
	private void fillwqtMission(WqtMission wqtMission) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_YEAR, 3);
		
		wqtMission.setTitle("82号公变下坝峨坡2号变 tes11t");
		wqtMission.setContent("82号公变下坝峨坡2号变 tes11t");
		wqtMission.setSetTime(calendar.getTime());
		wqtMission.setExeUser("ff8080815233c8cd0153302607ba081c");
		wqtMission.setIsHave("Y");
		
		wqtMission.setConsumeName("韦登发");
		wqtMission.setConsumePhone("15519579578");
		wqtMission.setServiceman("ff8080815233c8cd0153302607ba081c");
		wqtMission.setMasterServiceman("ff8080815233c8cd0153302607ba081c");
		wqtMission.setDestinationBdcoordX(106.8812044203);
		wqtMission.setDestinationBdcoordY(26.691459939308);
		wqtMission.setDestinationDeviceType("transformer");
		wqtMission.setDestinationKey(1000);
	}
	
	private void fillFaultStatisticsDetail(WqtMission wqtMission, FaultStatisticsDetail faultStatisticsDetail) {
		BeanUtils.copyProperties(wqtMission, faultStatisticsDetail);
		faultStatisticsDetail.setFaultDeviceVoltageLevel("低压");
		faultStatisticsDetail.setReportCustomerName("韦登发");
		faultStatisticsDetail.setReportCustomerNumber("15519579578");
		faultStatisticsDetail.setPerformer("备用");
		faultStatisticsDetail.setHandlerUser("wl_xlx");
		faultStatisticsDetail.setReportTime(new Date());
		faultStatisticsDetail.setSendMissionTime(new Date());
	}
}
