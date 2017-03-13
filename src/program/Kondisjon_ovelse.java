package program;

public class Kondisjon_ovelse extends Ovelse{
	long belastning;
	long repetisjoner;
	long sett;
	
	public Kondisjon_ovelse(long ovelse_id, String navn, String beskrivelse,long belastning,long repetisjoner,long sett){
		Super(ovelse_id,navn,beskrivelse);
		this.belastning=belastning;
		this.repetisjoner=repetisjoner;
		this.sett=sett;
	}
}
