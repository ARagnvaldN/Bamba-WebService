package se.kth.nylun.bamba.DB;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionPool {
	
	private final int MAX_CONNECTIONS = 5;
	private final int MAX_WAITTIME = 10000;
	
	private final Semaphore sem = 
			new Semaphore(MAX_CONNECTIONS, true);
	
	private final Queue<Connection> connections = 
			new ConcurrentLinkedQueue<Connection>();
	
	private String url, user, pass;
	
	public ConnectionPool(String url, String user, String pass){
		this.url = url;
		this.user = user;
		this.pass = pass;
	}
	
	private Connection createConnection(){
	Connection conn = null;
		try {
			
	          Class.forName("com.mysql.jdbc.Driver");
	          conn = DriverManager.getConnection(url, "root", "delfin");
	          
		  } catch(Exception e){
			  
		  } 
		
		return conn;
	}
	
	public Connection getConnection(){
		try {
			sem.tryAcquire(MAX_WAITTIME, TimeUnit.MILLISECONDS);
		
		
		//Get an existing connection
		Connection conn = connections.poll();
		if(conn != null){
			return conn;
		}
		
		//Otherwise create a new connection
		try{
			return createConnection();
			
		}catch(Exception e){
			
			sem.release();
		}
		
		} catch (InterruptedException e) {

		}
		
		return null;
		
	}
	
	public void releaseConnection(Connection conn){
		connections.add(conn);
		sem.release();
	}

}
