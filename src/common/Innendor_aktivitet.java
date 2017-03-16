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
	* @param trening_id Unique key in database.
	* @param dato Starting date of workout.
	* @param tidspunkt Startin time of workout.
	* @param varighet Total time of the workout.
	* @param form Rating of the shape the person was in.
	* @param prestasjon Rating of how well the workout was done.
	* @param notat Option to add arbitrary notes to the workout.
	* @param publikum Number of people watched you doing the workout.
	* @param luft Climate conditions of the workout place.
	*/
	public Innendor_aktivitet(int trening_id,Date dato, Time tidspunkt, long varighet, int form,int prestasjon,String notat,long publikum,int luft){
		super(trening_id,dato,tidspunkt,varighet,form,prestasjon,notat);
		this.publikum = publikum;
		this.luft = luft;
	}
}
