package com.db2md.service;

import com.db2md.bean.ConnectionInfoBean;
import com.db2md.bean.FieldBean;
import com.db2md.bean.MarkdownSettingBean;
import com.db2md.util.DbInfoUtil;
import com.db2md.util.JdbcUtil;
import com.db2md.util.MarkdownUtil;

import java.sql.Connection;
import java.util.*;

/**
 * 实现类
 *
 * @author cs12110 create at 2019/4/9 18:57
 * @version 1.0.0
 */
public class Db2MdServiceImpl implements Db2MdService {
    @Override
    public void execute(ConnectionInfoBean infoBean, MarkdownSettingBean settingBean) {
        Connection conn = JdbcUtil.getMySqlConn(infoBean.getDbUrl(), infoBean.getDbUser(), infoBean.getDbPassword());
        List<String> tableList = DbInfoUtil.getTableList(conn, infoBean.getDbName());
        Map<String, List<FieldBean>> infoMap = new LinkedHashMap<>();

        Set<String> alreadyWorkoutSet = new HashSet<>();

        // 处理排序的数据表
        for (String sorted : settingBean.getSortedTableArray()) {
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
        MarkdownUtil.buildMd(settingBean.getMdFilePath(), infoMap);
    }
}
