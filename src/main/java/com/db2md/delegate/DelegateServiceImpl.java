package com.db2md.delegate;

import com.db2md.bean.ConnectionInfoBean;
import com.db2md.bean.MarkdownSettingBean;
import com.db2md.service.Db2MdService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Supplier;

/**
 * 代理类
 *
 * @author cs12110 create at 2019/4/9 19:01
 * @version 1.0.0
 */
public class DelegateServiceImpl implements Db2MdService {

    private Db2MdService db2MdService;

    public DelegateServiceImpl(Db2MdService db2MdService) {
        this.db2MdService = db2MdService;
    }

    private long startTime = 0;

    @Override
    public void execute(ConnectionInfoBean infoBean, MarkdownSettingBean settingBean) {
        before(infoBean, settingBean);

        db2MdService.execute(infoBean, settingBean);

        after(infoBean, settingBean);
    }

    private void before(ConnectionInfoBean infoBean, MarkdownSettingBean settingBean) {

        System.out.println("------- 创建数据字典 -------");
        System.out.println("创建时间    :\t" + dateSupplier.get());
        System.out.println("作者        :\t `cs12110@163.com`");
        startTime = System.currentTimeMillis();

    }


    private static Supplier<String> dateSupplier = () -> {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    };

    private void after(ConnectionInfoBean info, MarkdownSettingBean settingBean) {
        System.out.println("数据库位置  : \t" + info.getDbUrl() + "/" + info.getDbName());
        System.out.println("数据字典位置: \t" + settingBean.getMdFilePath());
        System.out.println("生成字典耗时: \t" + (System.currentTimeMillis() - startTime) + " 毫秒");
    }
}
