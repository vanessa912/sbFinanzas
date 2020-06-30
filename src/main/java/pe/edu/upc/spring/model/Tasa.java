package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="Tasa")
public class Tasa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTasa;
    
    @Column(name="tipoTasa", nullable = false, length=12)
    private String tipoTasa;
 

	public Tasa() {
		super();
	}

	public Tasa(int idTasa, String tipoTasa) {
		super();
		this.idTasa = idTasa;
		this.tipoTasa = tipoTasa;
	}

	public int getIdTasa() {
		return idTasa;
	}

	public void setIdTasa(int idTasa) {
		this.idTasa = idTasa;
	}

	public String getTipoTasa() {
		return tipoTasa;
	}

	public void setTipoTasa(String tipoTasa) {
		this.tipoTasa = tipoTasa;
	}
	
	
}
