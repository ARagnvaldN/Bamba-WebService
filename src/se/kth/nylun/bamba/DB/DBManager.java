package se.kth.nylun.bamba.DB;

import java.sql.*;

public class DBManager {

  //private static final String url = "jdbc:mysql://192.168.0.105:3306/BambaDB";
  private static final String url = "jdbc:mysql://127.0.0.1:3306/BambaDB";
  private static final String user = "root";
  private static final String pass = "delfin";
  
  private static DBManager INSTANCE;
  private static ConnectionPool connPool = null;
  
  
  public DBManager(){
	  connPool = new ConnectionPool(url, user, pass);
	  
  }
  
  public synchronized static Connection getConnection(){
	  
	  if(INSTANCE == null){
		  INSTANCE = new DBManager();
	  }
	  
	  return connPool.getConnection();
  }

public static void releaseConnection(Connection conn) {
	connPool.releaseConnection(conn);
}
  
  
}