package program;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import common.*;


public class Database implements AutoCloseable {
	private Connection conn = null;
	private static String KEY_URL = DatabaseKey.KEY_URL;
	/*
	public static void main(String args[]){
		
		Database d = new Database();
		d.connect();
		for(Kategori i : d.getKategorier()){
			System.out.println(i.getFullName());
		}
		Utendor_aktivitet aktivitet = new Utendor_aktivitet(-1,new Date(2018,3,20),(Time) Time.valueOf("13:0:0"),10,10,10,"",20,"Sol");
		Ovelse test = new Styrke_ovelse(-1,"Test","", 10, 10, 10);
		d.insertTreningsOkt(aktivitet);
	}*/
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
	
	public int insertTreningsOkt(Treningsokt t){
		
		try{
			Statement st1 = conn.createStatement();
			Statement st2 = conn.createStatement();
			
			String query = "";
			MessageFormat treningTemplate = new MessageFormat(
				"insert into `trenings�kt`(`dato`,`varighet`,`prestasjon`,`notat`,`form`) values(\"{0}\",{1},{2},\"{3}\",\"{4}\");"
			);
			MessageFormat ovingTemplate = new MessageFormat(
				"insert into `trenings�kt_har_�ving` values(@i_id,{0});"
			);
			String datestring = new SimpleDateFormat("dd/mm/yyyy",Locale.ENGLISH).format(t.dato);
			query = treningTemplate.format(new Object[]{datestring,t.varighet,t.prestasjon,t.notat,t.form});
			/*For each �velse in treningsOkt*/
			if(st1.execute(query)){
				query = "SET i_id = LAST_INSERT_ID();";
				if(st2.execute(query)){
					query = "";
					for(Ovelse o : t.ovelser){
						query = ovingTemplate.format(new Object[]{o.ovelse_id});
						Statement st3 = conn.createStatement();
						if(!st3.execute(query)){break;}
					}
						
				}
				return 0;
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return 1;
	}
	public Kategori parseKat(){
		return null;
	}
	//Return a list of ovelser
	public List<Ovelse> getOvelser(){
		List<Ovelse> ret = new ArrayList<Ovelse>();
		try(Statement st = conn.createStatement()){
			
			String query1 = "select * from `�ving`";
			MessageFormat query2 = new MessageFormat("select * from `�velse_har_kategori` where `id_�velse` = {0}");
			MessageFormat query3 = new MessageFormat("select * from `syrke_kond_�velse` where `id_�velse` = {0}");
			MessageFormat query4 = new MessageFormat("select * from `utholdenhet_�velse` where `id_�velse` = {0}");
			if(st.execute(query1)){
				ResultSet set = st.getResultSet();
				while(set.next()){
					int o_id = set.getInt(1);
					String navn = set.getString(2);
					String beskrivelse = set.getString(3);
					Ovelse o = null;
					Statement st2 = conn.createStatement();
					Statement st3 = conn.createStatement();
					Statement st4 = conn.createStatement();
					if(st3.execute(query3.format(o_id))){
						ResultSet st_set = st3.getResultSet();
						if(! st_set.next()){
							int belastning = st_set.getInt(2);
							int repetisjoner= st_set.getInt(3);
							int sett = st_set.getInt(4);
							
							o = new Styrke_ovelse(o_id, navn, beskrivelse, belastning, repetisjoner, sett);
						}else if(st4.execute(query4.format(o_id))){
							st_set = st4.getResultSet();
							int distanse = st_set.getInt(2);
							int tid= st_set.getInt(3);
							
							o = new Utholdenhet_ovelse(o_id, navn, beskrivelse, distanse, tid);
						
						}
					}
					if(st2.execute(query2.format(o_id))){
						ResultSet katSet = st2.getResultSet();
						while(katSet.next()){
							int kat_id = katSet.getInt(2);
							o.kategori = kat_id;
							break;
						}
					}
					ret.add(o);
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return ret;
	}
	public List<Kategori> getKategorier(){
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