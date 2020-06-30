package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Moneda;

public interface IMonedaService {

	public boolean insertar(Moneda moneda);
	public boolean modificar(Moneda moneda);
	public void eliminar(int idMoneda);
	public Optional<Moneda> listarId(int idMoneda);
	List<Moneda> listar();
	List<Moneda> buscarTipo(String tipoMoneda);
}
