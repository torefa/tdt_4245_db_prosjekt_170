public class ovelse{
	long ovelse_id;
	String navn;
	String beskrivelse;
}

public class kondisjon_ovelse extends ovelse{
	long belastning;
	long repetisjoner;
	long sett;
}

public class styrke_ovelse extends ovelse{
	long belastning;
	long repetisjoner;
	long sett;
}

public class utholdenhet_ovelse extends ovelse{
	long distanse_km;
	long tid_min;
}
