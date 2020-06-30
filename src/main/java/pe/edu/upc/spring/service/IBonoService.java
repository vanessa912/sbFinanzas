package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Bono;
public interface IBonoService {

	public boolean insertar(Bono bono);
	public boolean modificar(Bono bono);
	public void eliminar(int idBono);
	public Optional<Bono> listarId(int idBono);
	List<Bono> listar();
	List<Bono> buscarId(int idBono);
	
	public List<Bono> BuscarEmpresa (String nombreEmpresa);
	public List<Bono> BuscarFrecuencia (String tipoFrecuencia);
	public List<Bono> BuscarTasa (String tipoTasa);
	public List<Bono> BuscarMoneda (String tipoMoneda);
	
}
