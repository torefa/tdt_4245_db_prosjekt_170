package program;

public class Styrke_ovelse extends Ovelse{
	long belastning;
	long repetisjoner;
	long sett;
	
	public Styrke_ovelse(long ovelse_id, String navn, String beskrivelse,long belastning,long repetisjoner,long sett){
		super(ovelse_id,navn,beskrivelse);
		this.belastning=belastning;
		this.repetisjoner=repetisjoner;
		this.sett=sett;
	}
}
