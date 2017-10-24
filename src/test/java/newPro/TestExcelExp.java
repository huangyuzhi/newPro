package newPro;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.chains.pwqxfwjk.model.FaultStatisticsDetail;
import com.chains.pwqxfwjk.model.VCustomerInfo;
import com.chains.pwqxfwjk.service.CustomerInfoService;
import com.chains.pwqxfwjk.service.FaultStatisticsDetailService;
import com.chains.pwqxfwjk.service.impl.CustomerInfoExcel;
import com.chains.pwqxfwjk.service.impl.FaultStatisticsDetailExcel;
import com.chains.pwqxfwjk.util.CustomerInfoQueryParam;
import com.chains.util.QcRowBounds;

import support.Support;

public class TestExcelExp extends Support {
	private CustomerInfoService customerInfoService = context.getBean(CustomerInfoService.class);
	
	private FaultStatisticsDetailService faultStatisticsDetailService = context.getBean(FaultStatisticsDetailService.class);
	
	/*@Test*/
	public void test() throws Exception {
		List<VCustomerInfo> list = customerInfoService.getCustomerVList(new CustomerInfoQueryParam(), new QcRowBounds(0, 1));
		byte[] excel = new CustomerInfoExcel().majorFlow(list);
		FileUtils.writeByteArrayToFile(new File("客户变更记录.xlsx"), excel);
	}
	
	/*@Test*/
	public void testFaultStatisticsDetailExp() throws Exception {
		FaultStatisticsDetail faultStatisticsDetail = new FaultStatisticsDetail();
		List<FaultStatisticsDetail> list = faultStatisticsDetailService.find(faultStatisticsDetail, new QcRowBounds());
		byte[] excel = new FaultStatisticsDetailExcel().majorFlow(list);
		FileUtils.writeByteArrayToFile(new File("故障统计细节表.xlsx"), excel);
	}
}
