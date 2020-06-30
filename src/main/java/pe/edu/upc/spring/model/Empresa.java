package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Empresa")
public class Empresa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int idEmpresa;
	
	@Column(name="nombreEmpresa", nullable = false, length=25)
	public String nombreEmpresa;
	
	@Column(name="rucEmpresa", nullable = false, length=11)
	public String rucEmpresa;
	
	@Column(name="prima", nullable = false, length=10)
	public double prima;
	
	@Column(name="colocacion", nullable = false, length=10)
	public double colocacion;
	
	@Column(name="estructuracion", nullable = false, length=10)
	public double estructuracion;
	
	@Column(name="flotacion", nullable = false, length=10)
	public double flotacion;
	
	@Column(name="cavali", nullable = false, length=10)
	public double cavali;
	
	/*
	@ManyToOne
	@JoinColumn(name="idTasa", nullable = false)
	public Tasa tasa;
	
	
	@ManyToOne
	@JoinColumn(name="idMoneda", nullable = false)
	public Moneda moneda;
*/
	public Empresa() {
		super();
	}

	public Empresa(int idEmpresa,String nombreEmpresa,String rucEmpresa, double prima, double colocacion, double estructuracion, double flotacion, double cavali) {
		super();
		this.idEmpresa = idEmpresa;
		this.nombreEmpresa = nombreEmpresa;
		this.rucEmpresa = rucEmpresa;
		this.prima = prima;
		this.colocacion = colocacion;
		this.estructuracion = estructuracion;
		this.flotacion = flotacion;
		this.cavali = cavali;
	//	this.moneda = moneda;
	}

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public double getPrima() {
		return prima;
	}

	public void setPrima(double prima) {
		this.prima = prima;
	}

	public double getColocacion() {
		return colocacion;
	}

	public void setColocacion(double colocacion) {
		this.colocacion = colocacion;
	}

	public double getEstructuracion() {
		return estructuracion;
	}

	public void setEstructuracion(double estructuracion) {
		this.estructuracion = estructuracion;
	}

	public double getFlotacion() {
		return flotacion;
	}

	public void setFlotacion(double flotacion) {
		this.flotacion = flotacion;
	}

	public double getCavali() {
		return cavali;
	}

	public void setCavali(double cavali) {
		this.cavali = cavali;
	}

	public String getRucEmpresa() {
		return rucEmpresa;
	}

	public void setRucEmpresa(String rucEmpresa) {
		this.rucEmpresa = rucEmpresa;
	}
	
	
/*
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
*/
	
	
	
}
