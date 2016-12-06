package co.h2a.fpatool.config;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.query.FreeformQuery;

public class Paramdb {

	private JDBCConnectionPool pool = null;

	public Paramdb() {
		try {
			pool = new SimpleJDBCConnectionPool("org.hsqldb.jdbc.JDBCDriver", "jdbc:hsqldb:file:fpatooldb", "SA", "", 2,
					5);

			if (!dbFileExists()) {
				initDatabase(pool);
				fillDatabase(pool);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean dbFileExists() {
		File file = new File("fpatooldb.script");
		System.out.println(file.getAbsolutePath());
		return file.exists();
	}

	public boolean isConnected() {
		return pool != null;
	}
	
	public String getConfigUserEmail() {
		FreeformQuery f = new FreeformQuery("SELECT EMAIL FROM CONFIG WHERE USERNAME = 'config'", pool);
		String email = null;
		try {
			f.beginTransaction();
			email = f.getResults(1, 1).getString(1); 
			f.commit();
		} catch (UnsupportedOperationException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return (email == null ? "<null>" : email);
	}

	private void initDatabase(JDBCConnectionPool connectionPool) {
		Connection conn = null;
		try {
			conn = connectionPool.reserveConnection();
			Statement statement = conn.createStatement();
			
			try {
				statement.executeUpdate("DROP TABLE CONFIG");
			} catch (SQLException e) {
			}
			
			statement.execute("CREATE TABLE CONFIG (ID INTEGER GENERATED ALWAYS AS IDENTITY, "
					+ "USERNAME VARCHAR(100), PASSWORD VARCHAR(100), NAME VARCHAR(100), EMAIL VARCHAR(200), "
					+ "PHONE VARCHAR(20), PRIMARY KEY(ID))");
			
			statement.close();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.releaseConnection(conn);
		}
	}

	private void fillDatabase(JDBCConnectionPool connectionPool) {
		Connection conn = null;
		try {
			conn = connectionPool.reserveConnection();
			Statement statement = conn.createStatement();
		    statement.executeUpdate("INSERT INTO CONFIG (USERNAME, PASSORD) VALUES( 'config', 'B79606FB3AFEA5BD1609ED40B622142F1C98125ABCFE89A76A661B0E8E343910' )");
			statement.close();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connectionPool.releaseConnection(conn);
		}
	}

}
