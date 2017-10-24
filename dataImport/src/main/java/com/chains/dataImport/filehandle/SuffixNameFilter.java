package com.chains.dataImport.filehandle;

import java.io.File;
import java.io.FilenameFilter;

public class SuffixNameFilter implements FilenameFilter {

    private String[] suffixNames = new String[0];

    public SuffixNameFilter() {}

    public SuffixNameFilter(String ...suffixNames) {
        this.suffixNames = suffixNames;
    }

    @Override
    public boolean accept(File dir, String name) {
        String fileSuffix = name.substring(name.lastIndexOf(".") + 1, name.length());
        boolean match = false;
        for (int i = 0; i < suffixNames.length; i++) {
            if(suffixNames[i].equals(fileSuffix)) {
                match = true;
                break;
            }
        }
        return match;
    }
}
