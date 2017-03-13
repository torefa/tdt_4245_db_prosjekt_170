package program;

import java.util.Date;

public class Utendor_aktivitet{
	long temperatur;
	String vaertype;
	
	public Utendor_aktivitet(long trening_id,Date dato, Time tidspunkt, long varighet, String form,int prestasjon,String notat,long temperatur,String vaertype){
		Super(trening_id,dato,tidspunkt,varighet,form,prestasjon,notat);
		this.temperatur=temperatur;
		this.vaertype=vaertype;
	}
}
