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
	* @author Group 
	* @param trening_id Unique key in database.
	* @param dato Starting date of workout.
	* @param tidspunkt Startin time of workout.
	* @param varighet Total time of the workout.
	* @param form Rating of the shape the person was in.
	* @param prestasjon Rating of how well the workout was done.
	* @param notat Option to add arbitrary notes to the workout.
	* @param temperatur Average temperature during the workout in celsius.
	* @param vaertype Weather condition during workout.
	*/
	public Utendor_aktivitet(int trening_id,Date dato, Time tidspunkt, long varighet, int form,int prestasjon,String notat,long temperatur,String vaertype){
		super(trening_id,dato,tidspunkt,varighet,form,prestasjon,notat);
		this.temperatur=temperatur;
		this.vaertype=vaertype;
	}
}
