package newPro;

import org.junit.Test;

import com.chains.pwqxfwjk.model.TroubleLine;
import com.chains.pwqxfwjk.service.TroubleLineService;

import support.Support;

public class TestTroubleLineService extends Support{
	
	private TroubleLineService troubleLineServiceImpl = context.getBean(TroubleLineService.class);
	/*@Test*/
	public void test() {
		TroubleLine troubleLine = new TroubleLine();
		troubleLine.setLineQuality("10kV万化线/10kV万化线/master");
		troubleLineServiceImpl.addTroubleLine(troubleLine);
	}
}
