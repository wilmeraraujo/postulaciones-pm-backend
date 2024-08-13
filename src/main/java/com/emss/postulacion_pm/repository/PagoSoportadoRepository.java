package com.emss.postulacion_pm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.emss.postulacion_pm.entity.PagoSoportado;

public interface PagoSoportadoRepository extends JpaRepository<PagoSoportado, Long> {
	
	@Query("select ps from PagoSoportado ps where upper(ps.razonSocial) like upper(concat('%', ?1, '%')) or nit like upper(concat('%', ?1, '%'))")
	public List<PagoSoportado> findByRazonSocial(String term);
	
	public List<PagoSoportado> findAllByOrderByIdAsc();
}
