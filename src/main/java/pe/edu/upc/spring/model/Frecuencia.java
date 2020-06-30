package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="Frecuencia")
public class Frecuencia implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFrecuencia;
    
    @Column(name="tipoFrecuencia", nullable = false, length=60)
    private String tipoFrecuencia;
    
    @Column(name="valorFrecuencia", nullable = false, length=60)
    private double valorFrecuencia;

	public Frecuencia() {
		super();
	}

	public Frecuencia(int idFrecuencia, String tipoFrecuencia, double valorFrecuencia) {
		super();
		this.idFrecuencia = idFrecuencia;
		this.tipoFrecuencia = tipoFrecuencia;
		this.valorFrecuencia = valorFrecuencia;
	}

	public int getIdFrecuencia() {
		return idFrecuencia;
	}

	public void setIdFrecuencia(int idFrecuencia) {
		this.idFrecuencia = idFrecuencia;
	}

	public String getTipoFrecuencia() {
		return tipoFrecuencia;
	}

	public void setTipoFrecuencia(String tipoFrecuencia) {
		this.tipoFrecuencia = tipoFrecuencia;
	}

	public double getValorFrecuencia() {
		return valorFrecuencia;
	}

	public void setValorFrecuencia(double valorFrecuencia) {
		this.valorFrecuencia = valorFrecuencia;
	}
    
}
