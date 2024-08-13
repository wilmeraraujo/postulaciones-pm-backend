 package com.emss.postulacion_pm.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emss.postulacion_pm.entity.PagoSoportado;
import com.emss.postulacion_pm.service.PagoSoportadoService;


@RestController
@CrossOrigin(origins = "http://192.168.10.60:4200,http://localhost:4200")
@RequestMapping(value = "/api/v1/pago-soportado-suministro")
public class PagoSoportadoController {

	@Autowired
	private PagoSoportadoService pagoSoportadoService;
	
	@GetMapping
	public ResponseEntity<?> getAll(){
		return ResponseEntity.ok().body(pagoSoportadoService.findAllByOrderByIdAsc());
	}
	
	@GetMapping("/search-razon-social/{term}")
	public ResponseEntity<?> filter(@PathVariable String term){
		return ResponseEntity.ok(pagoSoportadoService.findByRazonSocial(term));
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody PagoSoportado pagoSoportado){
		return  ResponseEntity.status(HttpStatus.CREATED).body(pagoSoportadoService.save(pagoSoportado));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> edit(@RequestBody PagoSoportado pagoSoportado, @PathVariable(name = "id") Long id){
		
		Optional<PagoSoportado> objeto = pagoSoportadoService.findById(id);
		
		if(objeto.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		PagoSoportado pssDb = objeto.get();
		pssDb.setNit(pagoSoportado.getNit());
		pssDb.setHabilitado(pagoSoportado.getHabilitado());
		pssDb.setRazonSocial(pagoSoportado.getRazonSocial());
		pssDb.setValorPosiblePagoFactura(pagoSoportado.getValorPosiblePagoFactura());
		pssDb.setValorPosiblePagoSuministro(pagoSoportado.getValorPosiblePagoSuministro());
		pssDb.setValorPagarFactura(pagoSoportado.getValorPagarFactura());
		pssDb.setValorPagarSuministro(pagoSoportado.getValorPagarSuministro());
		
		try {
	        return ResponseEntity.status(HttpStatus.CREATED).body(pagoSoportadoService.save(pssDb));
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    }
	}
}
