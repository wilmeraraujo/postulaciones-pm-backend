package com.emss.postulacion_pm.service;

import java.util.List;
import java.util.Optional;

import com.emss.postulacion_pm.entity.PagoSoportado;

public interface PagoSoportadoService {
	
	//public List<PagoSoportadoSuministro> findAll();
	public Optional<PagoSoportado> findById(Long id);
	public PagoSoportado save(PagoSoportado pagoSoportado);
	public List<PagoSoportado> findByRazonSocial(String term);
	public List<PagoSoportado> findAllByOrderByIdAsc();
}
