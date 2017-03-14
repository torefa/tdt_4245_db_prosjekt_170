package program;

import java.sql.Time;
import java.util.Date;

public class Innendor_aktivitet extends Treningsokt{
	long publikum;
	int luft;
	
	
	public Innendor_aktivitet(int trening_id,Date dato, Time tidspunkt, long varighet, int form,int prestasjon,String notat,long publikum,int luft){
		super(trening_id,dato,tidspunkt,varighet,form,prestasjon,notat);
		this.publikum = publikum;
		this.luft = luft;
	}
}
