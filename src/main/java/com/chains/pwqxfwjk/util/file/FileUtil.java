package com.chains.pwqxfwjk.util.file;

import com.chains.pwqxfwjk.util.excel.excelhandler.TransformerHandler2;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FileUtil {
	public static void main(String[] args) {
		iteratorFile(new File("C:\\Users\\Administrator\\Desktop\\乌当供电局配网抢修服务调度监控项目\\客户数据\\客户变户信息普查\\客户信息普-羊昌所"), new TransformerHandler2());
	}
	
	private static final ThreadLocal<IterateContext<String, Object>> context = new ThreadLocal<>();
	
	public static void setContext(IterateContext<String, Object> iterateContext) {
		context.set(iterateContext);
	}
	
	public static IterateContext<String, Object> getContext() {
		return context.get();
	}
	/**
	 * 方法名称:iteratorFile<br>
	 * 方法描述:                     <br>
	 * @param file
	 * @throws Exception
	 * 返回类型:
	 * void
	 * @exception
	*/
	public static void iteratorFile(File file, HandlerFile handlerFile) {
		if(file.isDirectory()) {
			File[] files = file.listFiles();
			for(File childFile : files) {
				iteratorFile(childFile, handlerFile);
			}
		}else {
			//handlerFile
			handlerFile.handler(file);
		}
	}

	public static List<File> searchFile(File root, FileFilter filter) {
        List<File> files = new ArrayList<>();
        searchFile(root, filter, files);
        return files;
    }

	public static String getFileSuffix(File file, boolean hasDot) {
		String suffix = file.getName().substring(file.getName().lastIndexOf("."));
		return hasDot ? suffix : suffix.substring(1, suffix.length());
	}

    private static void searchFile(File root, FileFilter filter, List<File> result) {
        File subRoot = root;
        if(filter == null || filter.accept(subRoot)) {
            result.add(subRoot);
        }
        if(subRoot.isDirectory()) {
            File[] files = subRoot.listFiles();
            for (File file :
                    files) {
                searchFile(file, filter, result);
            }
        }
    }


	public static void iteratorFile(File file, FileFilter filter, HandlerFile handlerFile) {
		if(file.isDirectory()) {
			File[] files = file.listFiles();
			for(File childFile : files) {
				iteratorFile(childFile, filter, handlerFile);
			}
		}else {
			//handlerFile
			if(filter.accept(file)) {
				handlerFile.handler(file);
			}
		}
	}
	
	private static class IterateContext<K, V> extends HashMap<K, V>{
		private static final long serialVersionUID = 1L;
	}
}
