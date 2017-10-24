package com.chains.dataImport.filehandle;

import java.io.File;
import java.io.FilenameFilter;

public class FileHandle {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\Administrator\\Desktop\\项目资料\\乌当供电局配网抢修服务调度监控项目\\客户数据\\客户变户信息普查\\客户变户信息普查\\新天供电所2016年客户信息核查表");
        String[] files = file.list(new SuffixNameFilter("xls", "xlsx", "et"));
        System.out.println(files.length);
    }
}
