package io.zhpooer.estore.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class TransactionManager {
	public static ThreadLocal<Connection> t = new ThreadLocal<Connection>();
	private static String url;
	private static String user = "";
	private static String password = "";
	private static String driverClass;
	private static DataSource ds;

	public static DataSource getDataSource() {
		return ds;
	}

	static {
		Properties p = new Properties();
		try {
			InputStream conf = TransactionManager.class.getClassLoader()
			        .getResourceAsStream("jdbc.conf");
			p.load(conf);
			if (p.containsKey("debug")
			        && Boolean.parseBoolean(p.getProperty(("debug")))) {
				url = DBHelper.makeH2TempURL("sample");
				driverClass = "org.h2.Driver";
			} else {
				driverClass = p.getProperty(("driverClass"));
				url = p.getProperty("url");
				user = p.getProperty("user");
				password = p.getProperty("password");
			}
			ComboPooledDataSource cds = new ComboPooledDataSource();
			cds.setDriverClass(driverClass);
			cds.setJdbcUrl(url);
			cds.setUser(user);
			cds.setPassword(password);
			cds.setMaxPoolSize(40);
			cds.setMinPoolSize(5);
			cds.setInitialPoolSize(20);
			ds = cds;
			Class.forName(driverClass);
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}

		if (p.containsKey("sqlScript")) {
			String scriptPath = TransactionManager.class.getClassLoader()
			        .getResource(p.getProperty("sqlScript")).getPath();
			DBHelper.execSQL(scriptPath, driverClass, url, user, password);
		}
	}

	public static Connection getConnection() throws SQLException {
		Connection conn = t.get();
		if (conn == null) {
			conn = ds.getConnection();
			t.set(conn);
		}
		return conn;
	}

	public static void release() {
		try {
			Connection conn = getConnection();
			conn.close();
			t.remove();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static void commit() {
		try {
			Connection conn = getConnection();
			conn.commit();
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static void startTransaction() {
		try {
			Connection conn = getConnection();
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static void rollback() {
		try {
			Connection conn = getConnection();
			conn.rollback();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
