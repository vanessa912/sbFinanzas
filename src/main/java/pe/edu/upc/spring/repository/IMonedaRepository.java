package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Moneda;


@Repository
public interface IMonedaRepository extends JpaRepository <Moneda, Integer> {

	@Query("from Moneda m where m.tipoMoneda like %:tipoMoneda%")
	  List<Moneda> buscarTipo(@Param ("tipoMoneda") String tipoMoneda);
}
