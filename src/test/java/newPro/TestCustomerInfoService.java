package newPro;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.chains.pwqxfwjk.model.CustomerInfo;
import com.chains.pwqxfwjk.model.VCustomerInfo;
import com.chains.pwqxfwjk.service.CustomerInfoService;
import com.chains.pwqxfwjk.util.CustomerInfoQueryParam;
import com.chains.util.Page;
import com.chains.util.QcRowBounds;

import support.Support;

public class TestCustomerInfoService extends Support {
	/*public static void main(String[] args) {
		MD5FileUtil md5FileUtil = new MD5FileUtil();
		String pswd = md5FileUtil.encodePassword("123", "wdby2");
		System.out.println(pswd);
	}*/
	CustomerInfoService customerInfoService = context.getBean(CustomerInfoService.class);
	/**
	 * @throws Exception 
	 * 
	 */
	@Test
	public void testGetCustomerV() throws Exception {
		CustomerInfoQueryParam queryParam = new CustomerInfoQueryParam();
		queryParam.setSort(true);
		queryParam.setSortedBy("customer_number");
		queryParam.setOrder("desc");
		Page<VCustomerInfo> page = customerInfoService.getCustomerV(queryParam, new QcRowBounds(0, 10));
		consoleInfo(page);
	}
	
	@Test
	public void testFindchangeCustomer() throws Exception {
		CustomerInfoQueryParam queryParam = new CustomerInfoQueryParam();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		queryParam.setBeginDate(sdf.parse("2016-5-10 00:00:00"));
		queryParam.setEndDate(new Date());
		queryParam.setSort(true);
		queryParam.setSortedBy("customer_number");
		queryParam.setOrder("desc");
		Page<VCustomerInfo> page = customerInfoService.getCustomerV(queryParam, new QcRowBounds(0, 10));
		consoleInfo(page);
	}
	
	/*@Test*/
	public void testUpdateCustomer() {
		CustomerInfo customerInfo = new CustomerInfo();
		customerInfo.setCustomerNumber("0601080000518039");
		List<CustomerInfo> list = customerInfoService.findByExample(customerInfo);
		Assert.assertTrue(list.size() > 0);
		CustomerInfo orignalCusomter = list.get(0);
		orignalCusomter.setCustomerMobilePhone("112234");
		customerInfoService.update(orignalCusomter);
	}
	/*@Test*/
	public void testAdd() {
		CustomerInfo customerInfo = new CustomerInfo();
		customerInfo.setCustomerNumber("1110008");
		customerInfoService.add(customerInfo);
	}
	
}
