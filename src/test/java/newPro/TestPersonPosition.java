package newPro;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.chains.pwqxfwjk.model.PersonInfoCollection;
import com.chains.pwqxfwjk.model.WqtMission;
import com.chains.pwqxfwjk.representation.WqtBackfill;
import com.chains.pwqxfwjk.service.PersonPositionService;
import com.chains.pwqxfwjk.service.WqtMissionService;
import com.chains.pwqxfwjk.util.PersonPositionQueryParam;

import support.Support;

public class TestPersonPosition extends Support {
	private WqtMissionService wqtMissionServiceImpl = context.getBean(WqtMissionService.class);
	private PersonPositionService personPositionServiceImpl = context.getBean(PersonPositionService.class);
	
	@Test
	public void testSearchPerson() {
		PersonPositionQueryParam queryParam = new PersonPositionQueryParam();
//		queryParam.setPhone("15519003476");
		queryParam.setPageSize(Integer.MAX_VALUE);
		PersonInfoCollection personInfoCollection =  personPositionServiceImpl.getPersonPosition(queryParam);
		consoleInfo(personInfoCollection);
	}
	
	/*@Test*/
	public void testAssignMission() throws Exception {
		WqtMission mission = new WqtMission();
		mission.setConsumeName("客户姓名");
		mission.setConsumePhone("客户电话");
		mission.setContent("day day up 777"); 
		mission.setExeUser("ff8080815233c8cd0153302607ba081c");
		mission.setServiceman("ff8080815233c8cd0153302607ba081c");
		mission.setMasterServiceman("ff8080815233c8cd0153302607ba081c");
		mission.setIsHave("Y");
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MINUTE, 1);
		mission.setSetTime(calendar.getTime());
		
		mission.setTitle("test");
		consoleInfo(wqtMissionServiceImpl.assignMission(mission));
	}
	
	/**
	 * 查询回填内容
	 * @throws Exception 
	 */
	@Test
	public void testFindbackfill() throws Exception {
		List<WqtBackfill> list = wqtMissionServiceImpl.findBackfill("ff808081554039f2015548d837f60355");
		WqtBackfill wqtBackfill = list.get(0);
		String replyContent = wqtBackfill.getReplyContent();
		String[] strs = replyContent.split("\n");
		for(String str : strs) {
			if(str.matches("^\\d")) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时ss分");
				Date date = sdf.parse(str);
				consoleInfo(date);
			}
		}
		consoleInfo(list);
	}
}
