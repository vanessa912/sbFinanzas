package pe.edu.upc.spring.service;

import java.util.List;

import pe.edu.upc.spring.model.Frecuencia;

public interface IFrecuenciaService {

	public boolean insertar(Frecuencia frecuencia);
	public boolean modificar(Frecuencia frecuencia);
	public void eliminar(int idFrecuencia);
	public List<Frecuencia> listar();
	public List<Frecuencia> buscarTipoFrecuencia(String tipoFrecuencia);

}
