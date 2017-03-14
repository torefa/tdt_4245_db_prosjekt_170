package program;

import java.sql.Time;
import java.util.Date;

public class Utendor_aktivitet extends Treningsokt{
	long temperatur;
	String vaertype;
	
	public Utendor_aktivitet(int trening_id,Date dato, Time tidspunkt, long varighet, int form,int prestasjon,String notat,long temperatur,String vaertype){
		super(trening_id,dato,tidspunkt,varighet,form,prestasjon,notat);
		this.temperatur=temperatur;
		this.vaertype=vaertype;
	}
}
