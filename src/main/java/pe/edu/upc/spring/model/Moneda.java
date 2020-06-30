package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Moneda")
public class Moneda implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int idMoneda;
	
	@Column(name="tipoMoneda", nullable = false, length=25)
	public String tipoMoneda;
	
	@Column(name="valorMoneda", nullable = false, length=10)
	public String signoMoneda;
	

	public Moneda() {
		super();
	}

	public Moneda(int idMoneda,String tipoMoneda, String signoMoneda ) {
		super();
		this.idMoneda = idMoneda;
		this.tipoMoneda = tipoMoneda;
		this.signoMoneda = signoMoneda;
	}

	public int getIdMoneda() {
		return idMoneda;
	}

	public void setIdMoneda(int idMoneda) {
		this.idMoneda = idMoneda;
	}

	public String getTipoMoneda() {
		return tipoMoneda;
	}

	public void setTipoMoneda(String tipoMoneda) {
		this.tipoMoneda = tipoMoneda;
	}

	public String getSignoMoneda() {
		return signoMoneda;
	}

	public void setSignoMoneda(String signoMoneda) {
		this.signoMoneda = signoMoneda;
	}

	

	
	
	
}
