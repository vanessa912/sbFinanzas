package pe.edu.upc.spring.serviceimpl;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.spring.model.Empresa;
import pe.edu.upc.spring.repository.IEmpresaRepository;
import pe.edu.upc.spring.service.IEmpresaService;

@Service
public class EmpresaServiceImpl implements IEmpresaService {

	@Autowired
	private IEmpresaRepository dEmpresa;

	
	@Override
	@Transactional
	public boolean insertar(Empresa empresa) {
		Empresa objEmpresa = dEmpresa.save(empresa);
		if( objEmpresa == null)
		    return false;
		else
			return true;
	}

	@Override
	@Transactional
	public boolean modificar(Empresa empresa) {
		boolean flag = false;
		try {
			dEmpresa.save(empresa);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Ocurrio un error");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idEmpresa) {
		dEmpresa.deleteById(idEmpresa);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Empresa> listarId(int idEmpresa) {
		return dEmpresa.findById(idEmpresa);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Empresa> listar() {
		return dEmpresa.findAll();
	}

	@Override
	@Transactional
	public List<Empresa> buscarNombre(String nombreEmpresa) {
		return dEmpresa.buscarNombre(nombreEmpresa);
	}

}
