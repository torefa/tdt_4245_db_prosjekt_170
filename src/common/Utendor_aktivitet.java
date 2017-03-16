package common;

import java.sql.Time;
import java.sql.Date;

/**
 * Represents the corresponding table in the database.
 *
 * @author Group 170
 */
public class Utendor_aktivitet extends Treningsokt{
	public long temperatur;
	public String vaertype;
	
	/**
	* Creates a outdoor activity.
	*
	* @author Group 170
	* @param temperatur Average temperature during the workout in celsius.
	* @param vaertype Weather condition during workout.
	*/
	public Utendor_aktivitet(int trening_id,Date dato, Time tidspunkt, long varighet, int form,int prestasjon,String notat,long temperatur,String vaertype){
		super(trening_id,dato,tidspunkt,varighet,form,prestasjon,notat);
		this.temperatur=temperatur;
		this.vaertype=vaertype;
	}
}
