package common;

import java.util.List;

/**
 * Represents the corresponding table in the database. 
 * Is abstract since it is not ment to have a not specified exercise
 *
 * @author Group 170
 */
public abstract class Ovelse{
	public long ovelse_id;
	public String navn;
	public String beskrivelse;
	public int kategori;
	/**
	* Creates a new exercise.
	*
	* @author Group 170
	* @param distanse_km Distance of exercise in km.
	* @param tid_min Time of exercise in minutes.
	*/
	public Ovelse(long ovelse_id, String navn, String beskrivelse, int kat){
		this.ovelse_id=ovelse_id;
		this.navn=navn;
		this.beskrivelse=beskrivelse;
		this.kategori = kat;
	}
	public Ovelse(long ovelse_id, String navn, String beskrivelse){
		this(ovelse_id,navn,beskrivelse,-1);
	}
}
