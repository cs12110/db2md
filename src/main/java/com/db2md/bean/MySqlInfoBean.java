package com.db2md.bean;

import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * mysql连接信息实体类
 *
 * @author cs12110 create at 2019/4/9 18:06
 * @version 1.0.0
 */
@Data
public class MySqlInfoBean {
    /**
     * 数据库连接地址
     */
    private String dbUrl;

    /**
     * 数据库用户
     */
    private String dbUser;

    /**
     * 数据库密码
     */
    private String dbPassword;

    /**
     * 数据库名称
     */
    private String dbName;

    private MySqlInfoBean(Builder builder) {

        this.dbUrl = builder.dbUrl;
        this.dbUser = builder.dbUser;
        this.dbPassword = builder.dbPassword;
        this.dbName = builder.dbName;

    }

    public static class Builder {
        String dbUrl;
        String dbUser;
        String dbPassword;
        String dbName;

        public Builder setDbUrl(String dbUrl) {
            this.dbUrl = dbUrl;
            return this;
        }

        public Builder setDbUser(String dbUser) {
            this.dbUser = dbUser;
            return this;
        }

        public Builder setDbPassword(String dbPassword) {
            this.dbPassword = dbPassword;
            return this;
        }

        public Builder setDbName(String dbName) {
            this.dbName = dbName;
            return this;
        }

        public MySqlInfoBean build() {
            return new MySqlInfoBean(this);
        }
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
