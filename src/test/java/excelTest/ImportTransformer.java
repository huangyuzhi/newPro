package excelTest;

import com.chains.pwqxfwjk.util.excel.excelhandler.TransformerHandler2;
import com.chains.pwqxfwjk.util.file.FileUtil;
import org.junit.Test;
import support.Support;

import java.io.File;
import java.io.FileFilter;
import java.util.List;

public class ImportTransformer extends Support {
    private TransformerHandler2 transformerHandler2 = context.getBean(TransformerHandler2.class);
    @Test
    public void test() {
        /*List<File> files = FileUtil.searchFile(new File("C:\\Users\\Administrator\\Desktop\\乌当供电局配网抢修服务调度监控项目\\201606gis导出数据\\贵阳供电局乌当分局"),
                new FileFilter() {
                    @Override
                    public boolean accept(File pathname) {
                        return pathname.getName().contains("美式箱变（组合式）.xls") || pathname.getName().contains("欧式箱变（预装式）.xls");
                    }
                });
        for (File file :
                files) {
            transformerHandler2.handler(file);
        }*/
    }
}
