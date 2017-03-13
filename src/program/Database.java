package program;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class Database implements AutoCloseable {
	private Connection conn = null;
	private static String KEY_URL = DatabaseKey.KEY_URL;
	
	
	
	public boolean connect() {
		// Class.forName("com.mysql.jdbc.Driver");  Not needed if Eclipse?
		try {
			conn = DriverManager.getConnection(KEY_URL);
		}
		catch (SQLException ex) {
			System.out.println("SQLEcxeption: " + ex.getMessage());
			return false;
		}
		return true;
	}
	
	public int insertTreningsOkt(){
		String type = "";
		
		try (Statement st = conn.createStatement()){
			String query = "";
			MessageFormat treningTemplate = new MessageFormat(
				"insert into `treningsøkt` values({0},{1},{2},{3},{4}); SET i_id = LAST_INSERT_ID();"
			);
			MessageFormat ovingTemplate = new MessageFormat(
				"insert into `treningsøkt_har_øving` values(t_id,{0});"
			);
			
			query += treningTemplate.format(new Object[]{null,null,null,null,null});
			/*For each øvelse in treningsOkt*/
			query += ovingTemplate.format(new Object[]{0});
			
			if(st.execute(query)){
				return 0;
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return 0;
	}
	
	
	@Override
	public void close() {
		try {
			if (conn != null)
			conn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
	}
}