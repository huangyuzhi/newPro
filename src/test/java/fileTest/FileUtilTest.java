package fileTest;

import com.chains.pwqxfwjk.util.file.FileUtil;
import org.junit.Test;
import support.Support;

import java.io.File;
import java.io.FileFilter;
import java.util.List;

public class FileUtilTest extends Support{

    @Test
    public void test() {
       /* List<File> files = FileUtil.searchFile(new File("C:\\Users\\Administrator\\Desktop\\乌当供电局配网抢修服务调度监控项目\\201606gis导出数据\\贵阳供电局乌当分局"),
                new FileFilter() {
                    @Override
                    public boolean accept(File pathname) {
                        return pathname.getName().contains("杆塔") ;
                    }
                });
        consoleInfo(files.size());
        for (File file :
                files) {
            try {
                System.out.println(file.getAbsolutePath());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }*/
    }

    /**
     * 统计乌当局新天所的户变数据
     * */
    @Test
    public void countFiles() {
        List<File> files = FileUtil.searchFile(new File("C:\\Users\\Administrator\\Desktop\\乌当供电局配网抢修服务调度监控项目\\项目资料\\乌当供电局配网抢修服务调度监控项目\\客户数据\\客户变户信息普查\\客户变户信息普查\\新天供电所2016年客户信息核查表"),
                new FileFilter() {
                    @Override
                    public boolean accept(File pathname) {
                        return true;
                    }
                });
        consoleInfo(files.size());
        for (File file :
                files) {
            try {
                System.out.println(file.getAbsolutePath());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
