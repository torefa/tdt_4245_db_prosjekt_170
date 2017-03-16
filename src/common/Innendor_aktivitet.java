package common;

import java.sql.Time;
import java.sql.Date;

/**
 * Represents the corresponding table in the database.
 *
 * @author Group 170
 */
public class Innendor_aktivitet extends Treningsokt{
	public long publikum;
	public int luft;
	
	/**
	* Creates a indoor activity.
	*
	* @author Group 170
	* @param publikum Number of people watched you doing the workout.
	* @param luft Climate conditions of the workout place.
	*/
	public Innendor_aktivitet(int trening_id,Date dato, Time tidspunkt, long varighet, int form,int prestasjon,String notat,long publikum,int luft){
		super(trening_id,dato,tidspunkt,varighet,form,prestasjon,notat);
		this.publikum = publikum;
		this.luft = luft;
	}
}
