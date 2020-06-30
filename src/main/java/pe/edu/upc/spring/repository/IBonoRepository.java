package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Bono;



@Repository
public interface IBonoRepository extends JpaRepository <Bono, Integer> {

	@Query("from Bono b where b.idBono like %:idBono%")
	  List<Bono> buscarId(@Param ("idBono") int idBono);
	
	@Query("from Bono b where b.empresa.nombreEmpresa like %:nombreEmpresa%")
	List<Bono> buscarEmpresa(@Param("nombreEmpresa") String nombreEmpresa);
	
	@Query("from Bono b where b.frecuencia.tipoFrecuencia like %:tipoFrecuencia%")
	List<Bono> buscarFrecuencia(@Param("tipoFrecuencia") String tipoFrecuencia);
	
	@Query("from Bono b where b.tasa.tipoTasa like %:tipoTasa%")
	List<Bono> buscarTasa(@Param("tipoTasa") String tipoTasa);
	
	@Query("from Bono b where b.moneda.tipoMoneda like %:tipoMoneda%")
	List<Bono> buscarMoneda(@Param("tipoMoneda") String tipoMoneda);
}
