package pe.edu.upc.spring.service;

import java.util.List;
import pe.edu.upc.spring.model.Tasa;

public interface ITasaService {

	public boolean insertar(Tasa tasa);
	public boolean modificar(Tasa tasa);
	public void eliminar(int idTasa);
	public List<Tasa> listar();
	public List<Tasa> buscarTipoTasa(String tipoTasa);

}
