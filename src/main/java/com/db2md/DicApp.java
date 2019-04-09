package com.db2md;

import com.db2md.bean.ConnectionInfoBean;
import com.db2md.bean.MarkdownSettingBean;
import com.db2md.service.Db2MdServiceImpl;
import com.db2md.delegate.DelegateServiceImpl;

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
    private static final String[] SORTED_TABLE_ARRAY = {};

    /**
     * 数据字典文件位置
     */
    private static final String MARKDOWN_FILE_PATH = "d://数据字典-latest.md";

    public static void main(String[] args) {
        // 创建数据库连接信息实体类
        ConnectionInfoBean infoBean = buildConnectionInfoBean();
        MarkdownSettingBean settingBean = buildMdSetting();
        // 数据字典存放位置

        DelegateServiceImpl proxy = new DelegateServiceImpl(new Db2MdServiceImpl());
        proxy.execute(infoBean, settingBean);
    }

    /**
     * 创建mysql数据库连接信息实体类
     *
     * @return ConnectionInfoBean
     */
    private static ConnectionInfoBean buildConnectionInfoBean() {
        return new ConnectionInfoBean.Builder()
                // 设置数据库连接地址
                .setDbUrl("jdbc:mysql://47.98.104.252?useSSL=false")
                // 数据库连接用户
                .setDbUser("root")
                // 数据库连接密码
                .setDbPassword("*****")
                // 数据库名称
                .setDbName("4fun_db")
                .build();
    }

    /**
     * markdown 文件设置类
     *
     * @return MarkdownSettingBean
     */
    private static MarkdownSettingBean buildMdSetting() {
        MarkdownSettingBean settingBean = new MarkdownSettingBean();
        settingBean.setSortedTableArray(SORTED_TABLE_ARRAY);
        settingBean.setMdFilePath(MARKDOWN_FILE_PATH);
        return settingBean;
    }


}
