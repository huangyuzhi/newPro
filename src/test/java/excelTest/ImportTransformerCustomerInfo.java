package excelTest;

import java.io.File;
import java.io.FileFilter;
import java.util.List;

import com.chains.pwqxfwjk.util.file.FileUtil;
import com.chains.pwqxfwjk.util.excel.excelhandler.HandlerCustomerTransformerInfoExcelTemplate1;
import com.chains.pwqxfwjk.util.file.HandlerFile;

import org.junit.Test;
import support.Support;

public class ImportTransformerCustomerInfo extends Support {
	HandlerCustomerTransformerInfoExcelTemplate1 importer = context.getBean(HandlerCustomerTransformerInfoExcelTemplate1.class);

	@Test
	public void testImport() {
		List<File> files = FileUtil.searchFile(new File("C:\\Users\\Administrator\\Desktop\\乌当供电局配网抢修服务调度监控项目\\项目资料\\乌当供电局配网抢修服务调度监控项目\\客户数据\\客户变户信息普查\\客户变户信息普查\\新天供电所2016年客户信息核查表"),
				new FileFilter() {
					@Override
					public boolean accept(File pathname) {
						return pathname.isFile() ;//&& (pathname.getName().contains(".xls") || pathname.getName().contains(".xlsx"));
					}
				});
		for (File file :
				files) {
			importer.handler(file);
		}
	}
	/**
	 * 检查是不是有.xls和.xlsx以外的文件
	 */
	/*@Test*/
	public void checkFile() {
		FileUtil.iteratorFile(new File("C:\\Users\\Administrator\\Desktop\\乌当供电局配网抢修服务调度监控项目\\客户数据\\客户变户信息普查\\客户信息普-羊昌所"), new HandlerFile() {
			@Override
			public void handler(File file) {
				if(file.isFile()) {
					if(!(file.getName().endsWith(".xls") || file.getName().endsWith(".xlsx"))) {
						throw new RuntimeException("文件格式不对");
					}
				}
			}
		});
	}
}
