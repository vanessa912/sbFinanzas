package pe.edu.upc.spring.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Frecuencia;
import pe.edu.upc.spring.repository.IFrecuenciaRepository;
import pe.edu.upc.spring.service.IFrecuenciaService;

@Service
public class FrecuenciaServiceImpl implements IFrecuenciaService {

	@Autowired
	private IFrecuenciaRepository dFrecuencia;
	
	@Override
	@Transactional
	public boolean insertar(Frecuencia frecuencia) {
		Frecuencia objFrecuencia = dFrecuencia.save(frecuencia);
		if(objFrecuencia != null)
			return true;
		else
			return false;
	}

	@Override
	@Transactional
	public boolean modificar(Frecuencia frecuencia) {
		boolean flag = false;
		try {
			dFrecuencia.save(frecuencia);
			flag = true;
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idFrecuencia) {
		dFrecuencia.deleteById(idFrecuencia);
		
	}

	@Override
	@Transactional(readOnly=true)
	public List<Frecuencia> listar() {
		return dFrecuencia.findAll();
	}

	@Override
	@Transactional
	public List<Frecuencia> buscarTipoFrecuencia(String tipoFrecuencia) {
		return dFrecuencia.buscarTipoFrecuencia(tipoFrecuencia);
	}

}
