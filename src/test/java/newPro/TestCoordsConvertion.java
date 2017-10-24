package newPro;

import com.chains.pwqxfwjk.model.TransformerInfo;
import com.chains.pwqxfwjk.other.TransServiceModel;
import com.chains.pwqxfwjk.service.CoordsConvertionService;
import com.chains.pwqxfwjk.service.TransformerInfoService;
import com.chains.pwqxfwjk.util.TransformerInfoQueryParam;
import com.chains.util.QcRowBounds;
import org.junit.Test;
import support.Support;
import java.util.List;
import static org.junit.Assert.*;

public class TestCoordsConvertion extends Support{
    private CoordsConvertionService coordsConvertionService = context.getBean(CoordsConvertionService.class);
    private TransformerInfoService transformerInfoService = context.getBean(TransformerInfoService.class);

//    @Test
    public void test(){
        TransformerInfo transformerInfo = new TransformerInfo();
        transformerInfo.setLongitude("114.21892734521");
        transformerInfo.setLatitude("29.575429778924");
        TransServiceModel model = coordsConvertionService.convert(transformerInfo);
        consoleInfo(model);
        assertTrue(model.getStatus() == 0);
    }

//    @Test
    public void testUpdateCoords() {
        List<TransformerInfo> list = transformerInfoService.getData(new TransformerInfoQueryParam(), new QcRowBounds(0, 100));
        TransServiceModel model = coordsConvertionService.convert(list);
        consoleInfo(model);
        assertTrue(model.getStatus() == 0 && model.getResult().size() == 100);
    }
}
