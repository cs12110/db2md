package com.db2md;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.db2md.bean.FieldBean;
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
 * @see
 * @since 1.0
 */
public class DicApp {
	public static void main(String[] args) {
		// 数据库连接地址
		String dbUrl = "jdbc:mysql://10.10.2.233";
		// 数据库用户
		String dbUser = "root";
		// 数据库密码
		String dbPassword = "Rojao@123";
		// 数据库名称
		String dbName = "ups_web";
		// 数据字典存放位置
		String mdPath = "d://ups-web.md";

		System.out.println("------ 开始 -------");
		execute(dbUrl, dbUser, dbPassword, dbName, mdPath);
		System.out.println("------ 结束 -------");
	}

	/**
	 * 创建md文件
	 *
	 * @param url
	 *            数据库地址
	 * @param user
	 *            用户
	 * @param password
	 *            密码
	 * @param dbName
	 *            数据库名称
	 * @param mdPath
	 *            markdown文件保存路径
	 */
	private static void execute(String url, String user, String password, String dbName, String mdPath) {
		Connection conn = JdbcUtil.getMySqlConn(url, user, password);
		List<String> tableList = DbInfoUtil.getTableList(conn, dbName);
		Map<String, List<FieldBean>> infoMap = new HashMap<>();

		for (String tableName : tableList) {
			List<FieldBean> fieldList = DbInfoUtil.getFieldFromTable(conn, dbName, tableName);
			infoMap.put(tableName, fieldList);
		}
		JdbcUtil.fuckoff(conn, null, null);
		MarkdownUtil.buildMd(mdPath, infoMap);
	}
}
