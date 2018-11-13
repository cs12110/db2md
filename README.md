# db2md

快速导出数据库信息为markdown类型的数据字典工具.

注意: **现版本只支持mysql数据库**.

----

## 使用教程

在DicApp.java里面修改相应的参数,并启动运行即可.

```java
public static void main(String[] args) {
		// 数据库连接地址
		String dbUrl = "jdbc:mysql://10.10.2.233?useSSL=false";
		// 数据库用户
		String dbUser = "root";
		// 数据库密码
		String dbPassword = "Rojao@123";
		// 数据库名称
		String dbName = "ups_web";
		// 数据字典存放位置
		String mdPath = "d://数据字典-latest.md";

		System.out.println("------ 开始 -------");

		long start = System.currentTimeMillis();

		execute(dbUrl, dbUser, dbPassword, dbName, mdPath);

		long end = System.currentTimeMillis();

		System.out.println("数据库位置    : \t" + dbUrl + "/" + dbName);
		System.out.println("数据字典位置: \t" + mdPath);
		System.out.println("生成字典耗时:  \t" + (end - start) + " 毫秒");

		System.out.println("------ 结束 -------");
}
```

----

## 生成文件

文件生成格式如下



表名称: `user`
中文描述: 

| 字段名称 | 字段类型    | 自增 | 非空  | 默认值 | 主键  | 备注 |
| -------- | ----------- | ---- | :---: | ------ | :---: | ---- |
| id       | int[10]     | √    | √     |        | √     |      |
| username | varchar[20] |      |       |        |       |      |
| password | varchar[20] |      |       |        |       |      |
| sex      | varchar[10] |      |       |        |       |      |
| address  | varchar[20] |      |       |        |       |      |

----

## 其他

如有任何疑问,请联系: `cs12110@163.com`.


