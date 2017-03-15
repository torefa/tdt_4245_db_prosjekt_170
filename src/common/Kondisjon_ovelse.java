package common;

/**
 * Represents the corresponding table in the database.
 *
 * @author Group 170
 */
public class Kondisjon_ovelse extends Ovelse{
	long belastning;
	long repetisjoner;
	long sett;
	
	/**
	* Creates a new kondition exercise.
	*
	* @author Group 170
	* @param belastning Weight that was used for this exercise.
	* @param repetisjoner Number of repetitions of the exercise per {@link sett}.
	* @param sett Number of sets of the exercise.
	*/
	
	public Kondisjon_ovelse(long ovelse_id, String navn, String beskrivelse,long belastning,long repetisjoner,long sett){
		super(ovelse_id,navn,beskrivelse);
		this.belastning=belastning;
		this.repetisjoner=repetisjoner;
		this.sett=sett;
	}
}
