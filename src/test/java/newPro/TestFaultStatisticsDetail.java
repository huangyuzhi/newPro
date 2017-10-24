package newPro;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import com.chains.pwqxfwjk.model.FaultStatisticsDetail;
import com.chains.pwqxfwjk.service.FaultStatisticsDetailService;
import com.chains.pwqxfwjk.util.FaultStatisticsDetailQueryParam;
import com.chains.pwqxfwjk.util.QueryParam;
import com.chains.util.QcRowBounds;

import support.Support;

public class TestFaultStatisticsDetail extends Support {
	
	private FaultStatisticsDetailService faultStatisticsDetailService = context.getBean(FaultStatisticsDetailService.class);
	
	/**
	 * 测试
	 * @throws Exception 
	 */
	@Test
	public void testFindByexampleAndSort() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		FaultStatisticsDetail faultStatisticsDetail = new FaultStatisticsDetail();
		FaultStatisticsDetailQueryParam queryParam = new FaultStatisticsDetailQueryParam();
		queryParam.setBeginDate(sdf.parse("2016-5-1 00:00:00"));
		queryParam.setEndDate(new Date());
		queryParam.setSort(true);
		queryParam.setOrder("desc");
		queryParam.setSortedBy("sendMissionTime");
		List<FaultStatisticsDetail> list = faultStatisticsDetailService.
				find(faultStatisticsDetail, new QcRowBounds(5, 5), queryParam);
		consoleInfo(list);
	}
	@Test
	public void testFindByExample() throws Exception {
		FaultStatisticsDetail faultStatisticsDetail = new FaultStatisticsDetail();
		List<FaultStatisticsDetail> list = faultStatisticsDetailService.find(faultStatisticsDetail, new QcRowBounds(0, 10));
		consoleInfo(list);
	}
	
	@Test
	public void testCountByExample() {
		FaultStatisticsDetail faultStatisticsDetail = new FaultStatisticsDetail();
		faultStatisticsDetail.setArriveTimeout(false);
		Long count = faultStatisticsDetailService.count(faultStatisticsDetail);
		consoleInfo(count);
	}

	/**
	 * 导出故障报修的统计表
	 */
	@Test
	public void testStatisticsExcel() {
		byte[] bytes = faultStatisticsDetailService.staticExcel(2016, 6, Arrays.asList("category","faultCategory", "faultDevice", "faultDeviceVoltageLevel"));
//		byte[] bytes = faultStatisticsDetailService.staticExcel(2016, 6, Arrays.asList("category", "faultDevice", "faultCategory", "faultDeviceVoltageLevel"));
		try {
			FileUtils.writeByteArrayToFile(new File("excel.xlsx"), bytes);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/*@Test*/
	public void testDel() {
		faultStatisticsDetailService.del(19);
	}
}
