package pe.edu.upc.spring.model;

import java.io.Serializable;

import java.lang.Math;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="Bono")
public class Bono implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int idBono;
	
	@Column(name="valornominal", nullable = false, length=12)
	public double valornominal;
	
	@Column(name="valorcomercial", nullable = false, length=12)
	public double valorcomercial;
	
	@Column(name="plazo", nullable = false, length=3)
	public double plazo;
	
	@Column(name="tasaCupon", nullable = false, length=7)
	public double tasaCupon;
	
	@Column(name="tasaMercado", nullable = false, length=7)
	public double tasaMercado;
	
	@ManyToOne
	@JoinColumn(name="idEmpresa", nullable = false)
	public Empresa empresa;
	
	@ManyToOne
	@JoinColumn(name="idFrecuencia", nullable = false)
	public Frecuencia frecuencia;
	
	@ManyToOne
	@JoinColumn(name="idTasa", nullable = false)
	public Tasa tasa;
	
	@ManyToOne
	@JoinColumn(name="idMoneda", nullable = false)
	public Moneda moneda;

	 
	
	public Bono() {
		super();
	}

	public Bono(int idBono,double valornominal, double valorcomercial, double plazo, double tasaCupon, double tasaMercado, Empresa empresa, Frecuencia frecuencia, Tasa tasa, Moneda moneda) {
		super();
		this.idBono = idBono;
		this.valornominal = valornominal;
		this.valorcomercial = valorcomercial;
		this.plazo = plazo;
		this.tasaCupon = tasaCupon;
		this.tasaMercado = tasaMercado;
		this.frecuencia = frecuencia;
		this.empresa = empresa;
		this.tasa = tasa;
		this.moneda = moneda;
	}
	
	//ALEMAN CALCS
	public double vanfrances() {
		int i=1;
		double newsuma=0;
		double suma=0;
		while(i<=numperiodo()) {
			suma= suma + cuotafrances()/Math.pow(1+tepcomercial()/100,i);
			i++;
		}
		newsuma=suma+(saldofrances(numperiodo())*empresa.getPrima()/100)/Math.pow(1+tepcomercial()/100,numperiodo());
		return newsuma;	
	}
	
	public double vanaleman() {
		int i=1;
		double suma=0;
		double newsuma=0;
		while(i<=numperiodo()) {
			suma= suma + cuota(i)/Math.pow(1+tepcomercial()/100,i);
			i++;
		}
		newsuma=suma + (saldo(numperiodo())*empresa.getPrima()/100)/Math.pow(1+tepcomercial()/100,numperiodo());
		return newsuma;	
		
		
		
	}
	
	public double vanamericano() {
		int i=1;
		double suma=0;
		double newsuma=0;
		while(i<=numperiodo()) {
			suma= suma + (valornominal*tep()/100)/Math.pow(1+tepcomercial()/100,i);
			i++;
		}
		newsuma=suma+(valornominal+valornominal*empresa.getPrima()/100)/Math.pow(1+tepcomercial()/100,numperiodo());
		return newsuma;
	}
	
	
	public double valor(int periodo) {
		return ((amortizacion()+valornominal*tep()/100)/(Math.pow(1+tepcomercial()/100, periodo)));
		
	}
	
	public double cuponal(int periodo) {
		return saldo(periodo)*tep()/100;
	}
	
	public double saldo(int periodo) {
		return (valornominal-amortizacion()*(periodo-1));
		
	}
	
	public double cuota(int periodo) {
		return cuponal(periodo)+amortizacion();
	}
	
	public double amortizacion() {
		return valornominal/numperiodo();
	}
	
	// FRANCES CALCS ----------------------------------------------------------------------
	
	public double sumamorfrances(int numperiodo) {
		int i=1;
		double suma=0;
		while(i<=numperiodo) {
			suma= suma + amortizacionfrances(i);
			i++;
		}
		return suma;
	}
	
	public double sumcuotafrances() {		
		return cuotafrances()*numperiodo();
	}
	
	
	public double suminteresfrances(int numperiodo) {
		int i=1;
		double suma=0;
		while(i<=numperiodo) {
			suma= suma + cuponfrances(i);
			i++;
		}
		return suma;
	}
	
	
	public double cuponfrances(int periodo) {
		return saldofrances(periodo)*tep()/100;
	}
	
	public double prima(int periodo) {
		return saldofrances(periodo)*empresa.getPrima()/100;
	}
	
	public double primaal(int periodo) {
		return saldo(periodo)*empresa.getPrima()/100;
	}
	
	public double amortizacionfrances(int periodo) {
		return cuotafrances()-cuponfrances(periodo);
	}
	
	public double saldofrances(int periodo) {
			double sumamor=0;
			int i=1;
			double retorno=0;
			while(i<periodo) {
				sumamor= sumamor + amortizacionfrances(i);
				i++;
			}
			retorno=valornominal-sumamor;
			if(retorno<=0)
				return 0;
			else
				return retorno;
	}
	
	
	
	
	public double cuotafrances() {
		return valornominal/((1-Math.pow(1+tep()/100, numperiodo()*-1))/(tep()/100));
	}
	
	public double ultimocuotafrances(int periodo) {
		return cuotafrances()+saldofrances(periodo)*empresa.getPrima()/100;
	}
	
	public double ultimocuotaamericano() {
		return (valornominal*tep()/100)+ valornominal*empresa.getPrima()/100+valornominal;
	}
	
	public double ultimocuotaaleman(int periodo) {
		return cuota(periodo)+saldo(periodo)*empresa.getPrima()/100;
	}
	
	//---------------------------------------------------------------------------------
	
	public double suminteresam() {
		int i=1;
		double suma=0;
		while(i<=numperiodo()) {
			suma= suma + valornominal*tep()/100*i;
			i++;
		}
		return suma;
	}
	
	public double sumcuota(int numperiodo) {
		int i=1;
		double suma=0;
		while(i<=numperiodo) {
			suma= suma + cuota(i);
			i++;
		}
		return suma;
	}
	
	public double sumamortizacion() {
		return amortizacion()*numperiodo();
	}
	
	public double sumintereses(int numperiodo) {
		int i=1;
		double suma=0;
		while(i<=numperiodo) {
			suma= suma + cuponal(i);
			i++;
		}
		return suma;
	}
	
	
	
	
	public double tep() {
		if(tasa.getIdTasa()==1)
			return ((Math.pow((1+tasaCupon/100),(frecuencia.getValorFrecuencia()/360)))-1)*100;
		else
			return ((Math.pow(1+tasaCupon/36000,frecuencia.getValorFrecuencia()))-1)*100;
	}
	
	public double tepcomercial() {
		return ((Math.pow((1+tasaMercado/100),(frecuencia.getValorFrecuencia()/360)))-1)*100;
	}
	
	public double gastosbonista() {
		return empresa.getFlotacion()+empresa.getCavali();
		
	}
	
	public double gastosemisor() {
		return empresa.getEstructuracion()+empresa.getColocacion()+empresa.getFlotacion()+empresa.getCavali();
		
	}
	
	public int numperiodo() {
		
		double periodo=(plazo*360)/frecuencia.getValorFrecuencia();
		int iperiodo=(int)periodo;
		return iperiodo;
		
	}
	
	public double tceaemamericano() {
		
		double costo=((valorcomercial*tep()/100)*numperiodo()+(valorcomercial*(gastosemisor())/100))/valorcomercial;
		double tceacal=(Math.pow((1+costo/100),12)-1)*100;
		return tceacal;	
	}
	
	public double tceabonamericano() {
		
		double costo=((valornominal*tep()/100)*numperiodo()+(valornominal*(gastosbonista())/100))/valornominal;
		double tceacal=(Math.pow((1+costo/100),12)-1)*100;
		return tceacal;	
	}
	
	
	public double tceaemal() {
		double costo=((sumintereses(numperiodo()))+(valorcomercial*(gastosemisor())/100))/valorcomercial;
		double tceacal=(Math.pow((1+costo/100),12)-1)*100;
		return tceacal;
	}
	
	public double tceabonal() {
		double costo=((sumintereses(numperiodo()))+(valornominal*(gastosbonista())/100))/valornominal;
		double tceacal=(Math.pow((1+costo/100),12)-1)*100;
		return tceacal;
	}
	
	public double tceabonfrances() {
		double costo=((suminteresfrances(numperiodo()))+(valornominal*(gastosbonista())/100))/valornominal;
		double tceacala=(Math.pow((1+costo/100),12)-1)*100;
		return tceacala;	
	}
	
	public double tceaemfrances() {
		double costo=((suminteresfrances(numperiodo()))+(valorcomercial*(gastosemisor())/100))/valorcomercial;
		double tceacala=(Math.pow((1+costo/100),12)-1)*100;
		return tceacala;	
	}
	
	
	
	
	
	

	public int getIdBono() {
		return idBono;
	}

	public void setIdBono(int idBono) {
		this.idBono = idBono;
	}

	public double getValornominal() {
		return valornominal;
	}

	public void setValornominal(double valornominal) {
		this.valornominal = valornominal;
	}

	public double getValorcomercial() {
		return valorcomercial;
	}

	public void setValorcomercial(double valorcomercial) {
		this.valorcomercial = valorcomercial;
	}

	public double getPlazo() {
		return plazo;
	}

	public void setPlazo(double plazo) {
		this.plazo = plazo;
	}

	public double getTasaCupon() {
		return tasaCupon;
	}

	public void setTasaCupon(double tasaCupon) {
		this.tasaCupon = tasaCupon;
	}

	public double getTasaMercado() {
		return tasaMercado;
	}

	public void setTasaMercado(double tasaMercado) {
		this.tasaMercado = tasaMercado;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Frecuencia getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(Frecuencia frecuencia) {
		this.frecuencia = frecuencia;
	}

	public Tasa getTasa() {
		return tasa;
	}

	public void setTasa(Tasa tasa) {
		this.tasa = tasa;
	}

	public Moneda getMoneda() {
		return moneda;
	}

	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}

	
	

	
	
	
}
