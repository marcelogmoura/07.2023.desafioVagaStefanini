package com.mgmoura.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mgmoura.entities.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Integer>{
	
	@Query("SELECT a FROM Autor a ORDER BY a.nome")
	List<Autor> findAll();
	
	@Query("SELECT COUNT(o) FROM Obra o WHERE o.autor.idAutor = :idAutor")
	Integer countByObra(@Param("idAutor") Integer idAutor );


}
