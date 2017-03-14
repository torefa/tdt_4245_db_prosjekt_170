package program;

import java.sql.Time;
import java.util.Date;

/**
 * Represents the corresponding table in the database.
 * Is abstract since there should only be either indoor or outdoor activities.
 *
 * @author Group 170
 */
public abstract class Treningsokt{
	long trening_id;
	Date dato;
	Time tidspunkt;
	long varighet;
	int form;
	int prestasjon;
	String notat;
	
	/**
	* Creates an activity.
	*
	* @author Group 170
	* @param trening_id Unic number in database.
	* @param dato Starting date of workout.
	* @param tidspunkt Startin time of workout.
	* @param varighet Total time of the workout.
	* @param form Rating of the shape the person was in.
	* @param prestasjon Rating of how well the workout was done.
	* @param notat Option to add arbitrary notes to the workout.
	*/
	public Treningsokt(int trening_id,Date dato, Time tidspunkt, long varighet, int form,int prestasjon,String notat) throws IndexOutOfBoundsException{
		this.trening_id=trening_id;
		this.dato=dato;
		this.tidspunkt=tidspunkt;
		this.varighet=varighet;
		this.form=form;
		if(prestasjon<0 || prestasjon>10){
			throw new IndexOutOfBoundsException();
		}else{
			this.prestasjon=prestasjon;
		}
		this.notat=notat;
	}
}
