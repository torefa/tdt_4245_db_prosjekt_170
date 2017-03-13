package program;

import java.util.Date;

public class Treningsokt{
	long trening_id;
	Date dato;
	Time tidspunkt;
	long varighet;
	String form;
	int prestasjon;
	String notat;
	
	public Treningsokt(long trening_id,Date dato, Time tidspunkt, long varighet, String form,int prestasjon,String notat) throws exeption{
		this.trening_id=trening_id;
		this.dato=dato;
		this.tidspunkt=tidspunkt;
		this.varighet=varighet;
		this.form=form;
		if(prestasjon<0 || prestasjon>10){
			throw IndexOutOfBoundsException;
		}else{
			this.prestasjon=prestasjon;
		}
		this.notat=notat;
	}
}
