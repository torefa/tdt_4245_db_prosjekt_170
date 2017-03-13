package program;

import java.util.Date;

public class Innendor_aktivitet{
	long temperatur;
	long vaertype;
	
	public Innendor_aktivitet(long trening_id,Date dato, Time tidspunkt, long varighet, String form,int prestasjon,String notat,long ventilation,long antall_tilskuere){
		Super(trening_id,dato,tidspunkt,varighet,form,prestasjon,notat);
		this.ventilation=ventilation;
		this.antall_tilskuere=antall_tilskuere;
	}
}
