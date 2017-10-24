package newPro;

import java.io.File;
import java.io.FileFilter;
import java.util.List;

import com.chains.pwqxfwjk.util.TransformerInfoQueryParam;
import com.chains.pwqxfwjk.util.excel.excelhandler.TransformerHandler2;
import com.chains.util.QcRowBounds;
import org.junit.Test;

import com.chains.pwqxfwjk.model.TransformerInfo;
import com.chains.pwqxfwjk.service.TransformerInfoService;
import com.chains.pwqxfwjk.util.file.FileUtil;
import com.chains.pwqxfwjk.util.excel.excelhandler.TransformerHandler;

import support.Support;

public class TestTransformerService extends Support{
	
	private TransformerInfoService transformerInfoService = context.getBean(TransformerInfoService.class);

	private TransformerHandler2 transformerHandler2 = context.getBean(TransformerHandler2.class);

	@Test
	public void test() {
		TransformerInfo transformerInfo = new TransformerInfo();
		transformerInfo.setIsFault(true);
		List<TransformerInfo> list = transformerInfoService.findByExample(transformerInfo);
		consoleInfo(list);
	}

    @Test
	public void testSelect() {
        List<TransformerInfo> list = transformerInfoService.getData(new TransformerInfoQueryParam(), new QcRowBounds());
        consoleInfo(list);
    }

	@Test
	public void testGetTransformerByCustomerName() throws Exception {
		List<TransformerInfo> transformerInfos = transformerInfoService.getTransformerByCustomerName("林盼");
		consoleInfo(transformerInfos);
	}

	@Test
    public void testCount() {
        TransformerInfoQueryParam queryParam = new TransformerInfoQueryParam();
        queryParam.setId(10);
        Long count = transformerInfoService.count(queryParam);
        consoleInfo(count);
    }

	@Test
	public void testImp() {
		/*FileUtil.iteratorFile(new File("C:\\Users\\Administrator\\Desktop\\乌当供电局配网抢修服务调度监控项目\\项目资料\\乌当供电局配网抢修服务调度监控项目\\201606gis导出数据\\贵阳供电局乌当分局"),new FileFilter() {
			@Override
			public boolean accept(File file) {
				return file.getName().contains("美式箱变（组合式）") || file.getName().contains("欧式箱变（预装式）") ;
//				return file.getName().contains("配电变压器.xls");
			}
		}, transformerHandler2);*/
	}

	@Test
	public void testUpdateBdCoords() {
		transformerInfoService.updateBdCoords(new TransformerInfo());
	}
}
