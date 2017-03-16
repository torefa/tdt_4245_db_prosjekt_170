package common;

/**
 * Represents the corresponding table in the database.
 *
 * @author Group 170
 */

public class Utholdenhet_ovelse extends Ovelse{
	public long distanse_km;
	public long tid_min;
	
	/**
	* Creates a new kondition exercise.
	*
	* @author Group 170
	* @param ovelse_id Unique key in database.
	* @param navn One word description of exercise.
	* @param beskrivelse Detailed description of exercise.
	* @param distanse_km Distance of exercise in km.
	* @param tid_min Time of exercise in minutes.
	*/
	public Utholdenhet_ovelse(long ovelse_id, String navn, String beskrivelse,long distanse_km,long tid_min){
		super(ovelse_id,navn,beskrivelse);
		this.distanse_km=distanse_km;
		this.tid_min=tid_min;
	}
}
