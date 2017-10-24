package newPro;

import java.util.List;

import org.junit.Test;

import com.chains.pwqxfwjk.model.Serviceman;
import com.chains.pwqxfwjk.service.ServicemanService;
import com.chains.util.StringUtil;

import support.Support;

public class TestServiceman extends Support{
	public ServicemanService servicemanServiceImpl = context.getBean(ServicemanService.class);
	
	/*@Test*/
	public void test() {
		List<String> ids = StringUtil.getIdsToList("ff808081533a2048015494d943180d22,ff808081533ff1dd015494e7b5c911ae");
		servicemanServiceImpl.assignMission(ids, "ff8080815233c8cd0153302607ba081c");
	}

	@Test
	public void testInitWqtMember() {

	}
}
