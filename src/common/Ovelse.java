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
	* @param ovelse_id Unique key in database.
	* @param navn One word description of exercise.
	* @param beskrivelse Detailed description of exercise.
	* @param kategori Kategori of workout. Compare {@link Kategori}.
	*/
	public Ovelse(long ovelse_id, String navn, String beskrivelse, int kategori){
		this.ovelse_id=ovelse_id;
		this.navn=navn;
		this.beskrivelse=beskrivelse;
		this.kategori = kategori;
	}
	
	/**
	* Creates a new exercise without category.
	*
	* @author Group 170
	* @param ovelse_id Unique key in database.
	* @param navn One word description of exercise.
	* @param beskrivelse Detailed description of exercise.
	*/
	public Ovelse(long ovelse_id, String navn, String beskrivelse){
		this(ovelse_id,navn,beskrivelse,-1);
	}
}
