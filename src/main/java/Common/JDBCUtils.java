package Common;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils {

	final static BasicDataSource ds;

	static {
		Properties prop = new Properties();
		try {
			prop.load(JDBCUtils.class.getResourceAsStream("/dbcp2.properties"));
			String driverClassName = prop.getProperty("driverClassName");
			String url = prop.getProperty("url");
			String username = prop.getProperty("username");
			String password = prop.getProperty("password");
			ds = new BasicDataSource();
			ds.setDriverClassName(driverClassName);
			ds.setUrl(url);
			ds.setUsername(username);
			ds.setPassword(password);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void closeQuietly(AutoCloseable c) {
		if (c != null) {
			try {
				c.close();
			} catch (Exception e) {

			}
		}
	}

	public static void closeAll(ResultSet rs) {
		if (rs==null)
		{
			return;
		}
		try {
			Statement stmt = rs.getStatement();
			Connection conn = stmt.getConnection();
			closeQuietly(rs);
			closeQuietly(stmt);
			closeQuietly(conn);
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	public static void main(String[] args) {
		try {

			// executeNonQuery("insert into T_IdNames(TypeName,Name,IsDeleted)
			// values(?,?,0)", "test1", "name1");
			 long id = executeInsert("INSERT INTO `t_order_child` VALUES ('201908291014222351100', '32', '闲时', '200', '60', '160', '136', '320', '272', '1566987545000', '1566997545000', '0');");
			 System.out.println(id);
//			String  obj = (String) querySingle("SELECT operator_id FROM t_partners WHERE id=\"30\"");
//			System.out.println(obj);
//			ResultSet rs = executeQuery("SELECT * FROM t_partners WHERE id=\"30\"");
//			while(rs.next()) {
//				System.out.println(rs.getString("id")); }
//			JDBCUtils.closeAll(rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException
	{
		return ds.getConnection();
	}

	// 执行非查询代码
	public static void executeNonQuery(Connection conn, String sql, Object... params) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			ps.execute();
		} finally {
			closeQuietly(ps);
		}
	}

	// 执行非查询代码
	public static void executeNonQuery(String sql, Object... params) throws SQLException {
		Connection conn = ds.getConnection();
		try {
			executeNonQuery(conn, sql, params);
		} finally {
			closeQuietly(conn);
		}
	}

	public static ResultSet executeQuery(String sql, Object... params) throws SQLException {
		Connection conn = ds.getConnection();
		try {
			return executeQuery(conn, sql, params);
		} catch (SQLException ex) {
			closeQuietly(conn);
			throw ex;
		}
	}
	
	public static ResultSet executeQuery(Connection conn, String sql, Object... params) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			return ps.executeQuery();
		} catch (SQLException ex) {
			closeQuietly(ps);
			throw ex;
		}
	}



	public static long executeInsert(Connection conn, String sql, Object... params) throws SQLException {
		PreparedStatement psInsert = null;
		PreparedStatement psLastInsertId = null;
		ResultSet rs = null;
		try {
			psInsert = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				psInsert.setObject(i + 1, params[i]);
			}
			psInsert.execute();
			// 必须在同一个连接中执行
			psLastInsertId = conn.prepareStatement("select Last_insert_id()");
			rs = psLastInsertId.executeQuery();
			if (rs.next()) {
				return rs.getLong(1);
			} else {
				throw new RuntimeException("没有查到自增字段的值");
			}
		} finally {
			closeQuietly(rs);
			closeQuietly(psLastInsertId);
			closeQuietly(psInsert);
		}
	}

	/**
	 * 专门用于执行插入数据的方法，可以获得自增字段的值
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public static long executeInsert(String sql, Object... params) throws SQLException {
		Connection conn = ds.getConnection();
		try {
			return executeInsert(conn, sql, params);
		} finally {
			closeQuietly(conn);
		}
	}
	
	public static Object querySingle(Connection conn, String sql, Object... params) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
//				System.out.println(ps);
			}
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getObject(1);
			} else {
				return null;
			}
		} catch (SQLException ex) {
			closeQuietly(rs);
			closeQuietly(ps);
			throw ex;
		}
	}

	/**
	 * 执行查询，并且返回结果集中第一行、第一列的值，如果没有值，则返回null selec count(*) from t
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public static Object querySingle(String sql, Object... params) throws SQLException {
		Connection conn = ds.getConnection();
		try
		{

			return querySingle(conn, sql, params);
		}
		finally
		{
			closeQuietly(conn);
		}
	}
	
	public static void rollback(Connection conn)
	{
		if(conn!=null)
		{
			try {
				conn.rollback();
			} catch (SQLException e) {
				
			}
		}
	}
}
