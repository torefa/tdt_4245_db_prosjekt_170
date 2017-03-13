package program;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Database implements AutoCloseable {
	private Connection conn = null;
	private static String KEY_URL = DatabaseKey.KEY_URL;
	
	public static void main(String args[]){
		
		Database d = new Database();
		d.connect();
		for(Kategori i : d.getKategorier()){
			System.out.println(i.getFullName());
		}
	}
	
	public boolean connect() {
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
	
	public List<Kategori> getKategorier(){
		//Might want to return 
		List<Kategori> ret = new ArrayList<Kategori>();
		//Id, kat map
		Map<Integer,Kategori> idmap = new HashMap<Integer,Kategori>();
		try (Statement st = conn.createStatement()){
			String query = "select * from `kategori`";
			if(st.execute(query)){
				ResultSet res = st.getResultSet();
				while(res.next()){
					Kategori kat = new Kategori(
							res.getInt(1),
							res.getString(2),
							//Create a fake parent
							new Kategori(res.getInt(3),"",null));
					idmap.put(res.getInt(1), kat);
					ret.add(kat);
				}
				for(Kategori kat : ret){
					int pid = kat.getParent().getId();
					if(idmap.containsKey(pid)){
						kat.setParent(idmap.get(pid));
					}else{
						kat.setParent(null);
					}
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		
		return ret;
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