package program;

import java.sql.Time;
import java.util.Date;

public class Innendor_aktivitet extends Treningsokt{
	long temperatur;
	long vaertype;
	
	
	public Innendor_aktivitet(long trening_id,Date dato, Time tidspunkt, long varighet, String form,int prestasjon,String notat,long temperatur,long vaertype){
		super(trening_id,dato,tidspunkt,varighet,form,prestasjon,notat);
		this.temperatur=temperatur;
		this.vaertype=vaertype;
	}
}
