package com.db2md.bean;

/**
 * @author huanghuapeng create at 2019/4/9 18:58
 * @version 1.0.0
 */
public class MarkdownSettingBean {
    /**
     * markdown文件位置
     */
    private String mdFilePath;

    /**
     * 表数组
     */
    private String[] sortedTableArray;

    public String getMdFilePath() {
        return mdFilePath;
    }

    public void setMdFilePath(String mdFilePath) {
        this.mdFilePath = mdFilePath;
    }

    public String[] getSortedTableArray() {
        return sortedTableArray;
    }

    public void setSortedTableArray(String[] sortedTableArray) {
        this.sortedTableArray = sortedTableArray;
    }
}
