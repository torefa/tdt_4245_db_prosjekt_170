package common;

import java.sql.Time;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import javafx.util.converter.PercentageStringConverter;

/**
 * Represents the corresponding table in the database.
 * Is abstract since there should only be either indoor or outdoor activities.
 *
 * @author Group 170
 */
public abstract class Treningsokt{
	public long trening_id;
	public Date dato;
	public Time tidspunkt;
	public long varighet;
	public int form;
	public int prestasjon;
	public String notat;
	public List<Ovelse> ovelser;
	
	/**
	* Creates an activity.
	*
	* @author Group 170
	* @param trening_id Unique key in database.
	* @param dato Starting date of workout.
	* @param tidspunkt Startin time of workout.
	* @param varighet Total time of the workout.
	* @param form Rating of the shape the person was in.
	* @param prestasjon Rating of how well the workout was done.
	* @param notat Option to add arbitrary notes to the workout.
	* @param ovelser List of all exercises collected in this workout.
	*/
	public Treningsokt(long trening_id,Date dato, Time tidspunkt, long varighet, int form,int prestasjon,String notat,List<Ovelse> ovelser) throws IndexOutOfBoundsException{

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
		this.ovelser = new ArrayList<Ovelse>(ovelser);
		this.notat=notat;
	}

	/**
	* Creates an activity.
	*
	* @author Group 170
	* @param trening_id Unique key in database.
	* @param dato Starting date of workout.
	* @param tidspunkt Startin time of workout.
	* @param varighet Total time of the workout.
	* @param form Rating of the shape the person was in.
	* @param prestasjon Rating of how well the workout was done.
	* @param notat Option to add arbitrary notes to the workout.
	*/
	public Treningsokt(long trening_id,Date dato, Time tidspunkt, long varighet, int form,int prestasjon,String notat) throws IndexOutOfBoundsException{
		this(trening_id,dato,tidspunkt,varighet,form,prestasjon,notat,new ArrayList<Ovelse>());
	}
	public void addOvelse(Ovelse o){
		this.ovelser.add(o);
	}
}	
