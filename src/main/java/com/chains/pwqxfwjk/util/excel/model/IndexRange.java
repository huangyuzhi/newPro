package com.chains.pwqxfwjk.util.excel.model;

/**
 * 辅助类， 表示索引范围。
 */
public class IndexRange {

    private int beginIndex = 0;
    private int endIndex = 0;

    public IndexRange() {
    }

    public IndexRange(int endIndex, int beginIndex) {
        this.endIndex = endIndex;
        this.beginIndex = beginIndex;
    }

    public int getBeginIndex() {
        return beginIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    /**
     * 增加索引范围
     */
    public void increaseRange() {
        endIndex ++;
    }

    public void increaseRange(int offset) {
        endIndex += offset;
    }
}
