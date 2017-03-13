package program;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.util.converter.PercentageStringConverter;
public class Treningsokt{
	long trening_id;
	Date dato;
	Time tidspunkt;
	long varighet;
	String form;
	int prestasjon;
	String notat;
	List<Ovelse> ovelser;
	
	public Treningsokt(long trening_id,Date dato, Time tidspunkt, long varighet, String form,int prestasjon,String notat,List<Ovelse> ovelser) throws IndexOutOfBoundsException{
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
	public Treningsokt(long trening_id,Date dato, Time tidspunkt, long varighet, String form,int prestasjon,String notat) throws IndexOutOfBoundsException{
		this(trening_id,dato,tidspunkt,varighet,form,prestasjon,notat,new ArrayList<Ovelse>());
	}
	public void addOvelse(Ovelse o){
		this.ovelser.add(o);
	}
}	
