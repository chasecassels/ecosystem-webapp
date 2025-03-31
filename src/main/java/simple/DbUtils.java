package simple;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.nfis.db.TomcatConnectionManager;
import org.tinylog.Logger;

public class DbUtils {
	
	protected TomcatConnectionManager tcm;
	
	//initialize a connection pool
	public DbUtils() {
		
		try {
			tcm = new TomcatConnectionManager("java:/comp/env", "jdbc/dbcp");
		} catch (NamingException e) {
			System.err.println(e.getExplanation());
		}
	}
    
	/** Gets a connection from the pool
	 * 
	 * @return
	 * @throws SQLException
	 * @throws NamingException
	 */
    protected Connection createConnection() throws SQLException {
    	Connection conn = null;
    	Logger.debug("Connecting...");
		conn = tcm.getConnection();
    	Logger.info("Connected to:" + conn);
    	return conn;
    }
    
    /**
     * releases the specified connection back into the connection pool
     * 
     * @param conn
     * @throws SQLException
     */
    protected void releaseConnection(Connection conn) throws SQLException {
    	if (conn != null) {
    		tcm.closeConnection(conn); 	
    		Logger.info("Closed connection to:" + conn);
    	}
    }
}
