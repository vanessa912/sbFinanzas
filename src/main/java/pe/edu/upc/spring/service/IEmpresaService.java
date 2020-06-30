package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Empresa;

public interface IEmpresaService {

	public boolean insertar(Empresa empresa);
	public boolean modificar(Empresa empresa);
	public void eliminar(int idEmpresa);
	public Optional<Empresa> listarId(int idEmpresa);
	List<Empresa> listar();
	List<Empresa> buscarNombre(String nombreEmpresa);
}
