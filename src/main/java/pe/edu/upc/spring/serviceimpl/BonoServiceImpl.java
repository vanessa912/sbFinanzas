package pe.edu.upc.spring.serviceimpl;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Bono;
import pe.edu.upc.spring.repository.IBonoRepository;
import pe.edu.upc.spring.service.IBonoService;

@Service
public class BonoServiceImpl implements IBonoService {

	@Autowired
	private IBonoRepository dBono;

	
	@Override
	@Transactional
	public boolean insertar(Bono bono) {
		Bono objBono = dBono.save(bono);
		if( objBono == null)
		    return false;
		else
			return true;
	}

	@Override
	@Transactional
	public boolean modificar(Bono bono) {
		boolean flag = false;
		try {
			dBono.save(bono);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Ocurrio un error");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idBono) {
		dBono.deleteById(idBono);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Bono> listarId(int idBono) {
		return dBono.findById(idBono);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Bono> listar() {
		return dBono.findAll();
	}

	@Override
	@Transactional
	public List<Bono> buscarId(int idBono) {
		return dBono.buscarId(idBono);
	}

	@Override
	@Transactional
	public List<Bono> BuscarEmpresa(String nombreEmpresa) {
		return dBono.buscarEmpresa(nombreEmpresa);
	}

	@Override
	@Transactional
	public List<Bono> BuscarFrecuencia(String tipoFrecuencia) {
		return dBono.buscarFrecuencia(tipoFrecuencia);
	}
	
	@Override
	@Transactional
	public List<Bono> BuscarTasa(String tipoTasa) {
		return dBono.buscarTasa(tipoTasa);
	}
	
	@Override
	@Transactional
	public List<Bono> BuscarMoneda(String tipoMoneda) {
		return dBono.buscarMoneda(tipoMoneda);
	}

}
