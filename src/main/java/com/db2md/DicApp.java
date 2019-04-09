package com.db2md;

import java.sql.Connection;
import java.util.*;

import com.db2md.bean.FieldBean;
import com.db2md.bean.MySqlInfoBean;
import com.db2md.util.DbInfoUtil;
import com.db2md.util.JdbcUtil;
import com.db2md.util.MarkdownUtil;

/**
 * 数据字典
 *
 *
 * <p>
 *
 * @author cs12110 2018年11月13日
 * @since 1.0
 */
public class DicApp {

    /**
     * 排在数据字典前面的数据表
     */
    private static String[] sortedTableArray = {"top_answer_t"};

    public static void main(String[] args) {
        // 创建数据库连接信息实体类
        MySqlInfoBean info = buildConnectionInfoBean();
        // 数据字典存放位置
        String mdPath = "d://数据字典-latest.md";

        long start = System.currentTimeMillis();

        execute(info, mdPath);

        long end = System.currentTimeMillis();
        display(info, mdPath, (end - start));
    }

    /**
     * 创建mysql数据库连接信息实体类
     *
     * @return MySqlInfoBean
     */
    private static MySqlInfoBean buildConnectionInfoBean() {
        return new MySqlInfoBean.Builder()
                // 设置数据库连接地址
                .setDbUrl("jdbc:mysql://47.98.104.252?useSSL=false")
                // 数据库连接用户
                .setDbUser("root")
                // 数据库连接密码
                .setDbPassword("*******")
                // 数据库名称
                .setDbName("4fun_db")
                .build();
    }


    /**
     * 创建md文件
     *
     * @param infoBean 数据库地址信息实体类
     * @param mdPath   markdown文件保存路径
     */
    private static void execute(MySqlInfoBean infoBean, String mdPath) {
        Connection conn = JdbcUtil.getMySqlConn(infoBean.getDbUrl(), infoBean.getDbUser(), infoBean.getDbPassword());
        List<String> tableList = DbInfoUtil.getTableList(conn, infoBean.getDbName());
        Map<String, List<FieldBean>> infoMap = new LinkedHashMap<>();

        Set<String> alreadyWorkoutSet = new HashSet<>();

        // 处理排序的数据表
        for (String sorted : sortedTableArray) {
            if (!tableList.contains(sorted)) {
                System.err.println("error: [" + sorted + "] doesn't exists on:[" + infoBean.getDbName() + "]");
                continue;
            }
            List<FieldBean> fieldList = DbInfoUtil.getFieldFromTable(conn, infoBean.getDbName(), sorted);
            infoMap.put(sorted, fieldList);
            alreadyWorkoutSet.add(sorted);
        }
        // 处理剩下的数据表
        for (String tableName : tableList) {
            if (alreadyWorkoutSet.add(tableName)) {
                List<FieldBean> fieldList = DbInfoUtil.getFieldFromTable(conn, infoBean.getDbName(), tableName);
                infoMap.put(tableName, fieldList);
            }
        }
        JdbcUtil.close(conn);
        MarkdownUtil.buildMd(mdPath, infoMap);
    }


    /**
     * 打印信息
     *
     * @param info   info
     * @param mdPath md文件位置
     * @param spend  消耗时间
     */
    private static void display(MySqlInfoBean info, String mdPath, long spend) {
        System.out.println("数据库位置  : \t" + info.getDbUrl() + "/" + info.getDbName());
        System.out.println("数据字典位置: \t" + mdPath);
        System.out.println("生成字典耗时: \t" + spend + " 毫秒");
    }
}
