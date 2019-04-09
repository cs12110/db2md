# db2md

快速导出数据库信息为 markdown 类型的数据字典工具.

注意: **现版本只支持 mysql 数据库**.

---

## 使用教程

在 DicApp.java 里面修改相应的参数,并启动运行即可.

修改数据库连接地址和用户:

```java
/**
	* 创建mysql数据库连接信息实体类
	*
	* @return ConnectionInfoBean
	*/
private static MySqlInfoBean buildConnectionInfoBean() {
	return new MySqlInfoBean.Builder()
			// 设置数据库连接地址
			.setDbUrl("jdbc:mysql://47.98.104.252?useSSL=false")
			// 数据库连接用户
			.setDbUser("root")
			// 数据库连接密码
			.setDbPassword("****")
			// 数据库名称
			.setDbName("4fun_db")
			.build();
}
```

如果需要某一些数据表按照顺序生成,请填写(请确保数据表存在)

```java
/**
 * 排在数据字典前面的数据表
 */
private static String[] sortedTableArray = {"top_answer_t"};
```

---

## 生成文件

文件生成格式如下

表名称: `user`
中文描述:

| 字段名称 | 字段类型    | 自增 | 非空 | 默认值 | 主键 | 备注 |
| -------- | ----------- | ---- | :--: | ------ | :--: | ---- |
| id       | int[10]     | √    |  √   |        |  √   |      |
| username | varchar[20] |      |      |        |      |      |
| password | varchar[20] |      |      |        |      |      |
| sex      | varchar[10] |      |      |        |      |      |
| address  | varchar[20] |      |      |        |      |      |

---

## 其他

如有任何疑问,请联系: `cs12110@163.com`.
