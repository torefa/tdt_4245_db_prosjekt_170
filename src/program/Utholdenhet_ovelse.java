package program;

public class Utholdenhet_ovelse extends Ovelse{
	long distanse_km;
	long tid_min;
	
	public Utholdenhet_ovelse(long ovelse_id, String navn, String beskrivelse,long distanse_km,long tid_min){
		super(ovelse_id,navn,beskrivelse);
		this.distanse_km=distanse_km;
		this.tid_min=tid_min;
	}
}
