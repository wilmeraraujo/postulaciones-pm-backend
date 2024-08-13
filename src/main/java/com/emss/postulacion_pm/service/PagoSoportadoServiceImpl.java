package com.emss.postulacion_pm.service;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emss.postulacion_pm.entity.PagoSoportado;
import com.emss.postulacion_pm.repository.PagoSoportadoRepository;

import jakarta.transaction.Transactional;

@Service
public class PagoSoportadoServiceImpl implements PagoSoportadoService {

	@Autowired
	private PagoSoportadoRepository pagoSoportadoRepository;
	/*
	@Override
	public List<PagoSoportadoSuministro> findAll() {
		return pagoSoportadoSuministroRepository.findAll() ;
	}*/

	@Override
	public Optional<PagoSoportado> findById(Long id) {
		return pagoSoportadoRepository.findById(id);
	}
	
	@Override
	@Transactional
	public PagoSoportado save(PagoSoportado pagoSoportado) {
		BigDecimal minValue = new BigDecimal("1000000");
		
		if (pagoSoportado.getValorPagarFactura().compareTo(minValue) < 0 && pagoSoportado.getValorPagarFactura().compareTo(BigDecimal.ZERO) != 0) {
	        throw new IllegalArgumentException("El valor a pagar no puede ser inferior a 1'000,000");
	    }
		
		if (pagoSoportado.getValorPagarSuministro().compareTo(minValue) < 0 && pagoSoportado.getValorPagarSuministro().compareTo(BigDecimal.ZERO) != 0) {
	        throw new IllegalArgumentException("El valor a pagar no puede ser inferior a 1'000,000");
	    }
		
		if (pagoSoportado.getValorPagarFactura().compareTo(pagoSoportado.getValorPosiblePagoFactura()) > 0) {
	        throw new IllegalArgumentException("El valor a pagar nivel factura no puede ser mayor que el valor probable de pago nivel factura");
	    }
		
		if (pagoSoportado.getValorPagarSuministro().compareTo(pagoSoportado.getValorPosiblePagoSuministro()) > 0) {
	        throw new IllegalArgumentException("El valor a pagar nivel suministro no puede ser mayor que el valor probable de pago nivel suministro");
	    }
		
		return pagoSoportadoRepository.save(pagoSoportado);
	}
	
	@Override
	public List<PagoSoportado> findByRazonSocial(String term) {
		return pagoSoportadoRepository.findByRazonSocial(term);
	}

	@Override
	public List<PagoSoportado> findAllByOrderByIdAsc() {
		return pagoSoportadoRepository.findAllByOrderByIdAsc();
	}

	

	

}
