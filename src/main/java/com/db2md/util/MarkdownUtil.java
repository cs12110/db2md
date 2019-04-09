package com.db2md.util;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.db2md.bean.FieldBean;

/**
 * markdown工具类
 *
 *
 * <p>
 *
 * @author cs12110 2018年11月13日
 * @since 1.0
 */
public class MarkdownUtil {
    /**
     * 创建md文件
     *
     * @param path    路径
     * @param infoMap 信息集合
     */
    public static void buildMd(String path, Map<String, List<FieldBean>> infoMap) {
        StringBuilder md = new StringBuilder();

        md.append("# 数据库字典");
        md.append(System.lineSeparator());
        md.append(System.lineSeparator());
        md.append("本文档主要用于说明数据库的各个表结构.");
        md.append(System.lineSeparator());
        md.append(System.lineSeparator());
        md.append("---");
        md.append(System.lineSeparator());


        for (Map.Entry<String, List<FieldBean>> entry : infoMap.entrySet()) {
            md.append(buildEachTableMdContent(entry.getKey(), entry.getValue()));
            md.append(System.lineSeparator());
            md.append(System.lineSeparator());
        }

        write2File(path, md.toString());
    }

    /**
     * 构建每一个数据表的markdown内容
     *
     * @param tableName 表名称
     * @param list      属性列表
     * @return String
     */
    private static String buildEachTableMdContent(String tableName, List<FieldBean> list) {
        StringBuilder table = new StringBuilder();

        table.append("### ").append(tableName);
        table.append(System.lineSeparator());
        table.append(System.lineSeparator());
        table.append(System.lineSeparator());
        table.append("表名称: `").append(tableName).append("`");
        table.append(System.lineSeparator());
        table.append("中文描述: ").append(" ");
        table.append(System.lineSeparator());
        table.append(System.lineSeparator());

        table.append("|字段名称|字段类型|自增|非空|默认值|主键|备注|");
        table.append(System.lineSeparator());
        table.append("|---|---|---|:---:|---|:---:|---|");
        table.append(System.lineSeparator());
        for (FieldBean bean : list) {
            table.append("| ");
            table.append(bean.getName()).append(" | ");
            table.append(bean.getType().toLowerCase()).append("[").append(bean.getLength()).append("]").append(" | ");
            table.append(fixYes(bean.getAutoIncre())).append(" | ");
            table.append(fixNotNo(bean.getNullable())).append(" | ");
            table.append(fixNull(bean.getDefValue())).append(" | ");
            table.append(fixYes(bean.getIsKey())).append(" | ");
            table.append(bean.getDesc()).append(" | ");
            table.append(System.lineSeparator());
        }

        table.append(System.lineSeparator());
        table.append(System.lineSeparator());
        table.append("---");

        return table.toString();
    }

    /**
     * 修复空显示
     *
     * @param str 字符串
     * @return String
     */
    private static String fixNull(String str) {
        return null == str || "null".equals(str) ? "" : str;
    }

    /**
     * 是则返回y,否则返回""
     *
     * @param val 值
     * @return String
     */
    private static String fixYes(String val) {
        return "YES".equals(val) ? "√" : "";
    }

    /**
     * 不为NO,是则返回y,否则返回""
     *
     * @param val 值
     * @return String
     */
    private static String fixNotNo(String val) {
        return "NO".equals(val) ? "√" : "";
    }

    /**
     * 内容写入文件
     *
     * @param path    文件路径
     * @param content 文件内容
     */
    private static void write2File(String path, String content) {
        try {
            FileOutputStream fout = new FileOutputStream(path);
            fout.write(content.getBytes());
            fout.flush();
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
