package com.mgmoura.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mgmoura.entities.Obra;

@Repository
public interface ObraRepository extends JpaRepository<Obra, Integer>{
	
	@Query( "SELECT o FROM Obra o " +
			"JOIN FETCH o.autor a " +
			"WHERE o.dataPublicacao >= :dataInicial " +
			"AND o.dataExposicao <= :dataFinal " +
			"ORDER BY o.dataPublicacao DESC "	)
			
			List<Obra> findByDatas(
					@Param("dataInicial") Date dataMin,
					@Param("dataFinal") Date dataMax);
			
}



