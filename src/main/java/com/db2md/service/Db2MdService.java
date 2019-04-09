package com.db2md.service;

import com.db2md.bean.ConnectionInfoBean;
import com.db2md.bean.MarkdownSettingBean;

/**
 * 数据库转换数据字典接口
 *
 * @author cs12110 create at 2019/4/9 18:55
 * @version 1.0.0
 */
public interface Db2MdService {

    /**
     * 执行
     *
     * @param infoBean    数据库连接信息
     * @param settingBean markdown文件配置
     */
    void execute(ConnectionInfoBean infoBean, MarkdownSettingBean settingBean);
}
