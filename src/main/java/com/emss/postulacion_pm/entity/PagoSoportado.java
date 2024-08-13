package com.emss.postulacion_pm.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@Table(name = "pago_soportado",schema = "pre_max_anexo_01")
@AllArgsConstructor
@NoArgsConstructor
public class PagoSoportado {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nit;
	
	@Column(name = "razon_social", columnDefinition = "varchar(1200)")
	private String razonSocial;
	
	@Column(name = "habilitado", columnDefinition = "varchar(50)")
	private String habilitado;
	
	@Column(name = "valor_posible_pago_factura")
	private BigDecimal valorPosiblePagoFactura;
	
	@Column(name = "valor_posible_pago_suministro")
	private BigDecimal valorPosiblePagoSuministro;
	
	@Column(name = "valor_pagar_factura")
	private BigDecimal valorPagarFactura;
	
	@Column(name = "valor_pagar_suministro")
	private BigDecimal valorPagarSuministro;
}
//@AssertTrue