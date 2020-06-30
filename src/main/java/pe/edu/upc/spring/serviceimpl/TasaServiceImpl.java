package pe.edu.upc.spring.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Tasa;
import pe.edu.upc.spring.repository.ITasaRepository;
import pe.edu.upc.spring.service.ITasaService;

@Service
public class TasaServiceImpl implements ITasaService {

	@Autowired
	private ITasaRepository dTasa;
	
	@Override
	@Transactional
	public boolean insertar(Tasa tasa) {
		Tasa objTasa = dTasa.save(tasa);
		if(objTasa != null)
			return true;
		else
			return false;
	}

	@Override
	@Transactional
	public boolean modificar(Tasa tasa) {
		boolean flag = false;
		try {
			dTasa.save(tasa);
			flag = true;
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminar(int idTasa) {
		dTasa.deleteById(idTasa);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Tasa> listar() {
		return dTasa.findAll();
	}

	@Override
	@Transactional
	public List<Tasa> buscarTipoTasa(String tipoTasa) {
		return dTasa.buscarTipoTasa(tipoTasa);
	}

}
