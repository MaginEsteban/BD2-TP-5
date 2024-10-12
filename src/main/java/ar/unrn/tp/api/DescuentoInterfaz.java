package ar.unrn.tp.api;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import ar.unrn.tp.modelo.Discount;

public interface DescuentoInterfaz {
	// validar que las fechas no se superpongan
	void crearDescuentoSobreTotal(String marcaTarjeta, LocalDate fechaDesde, LocalDate fechaHasta, float porcentaje);

	 // validar que las fechas no se superpongan
	void crearDescuento(Long porcentaje, String marcaProducto, LocalDate fechaDesde, LocalDate fechaHasta);
	
	List<Discount> descuentos();

	Discount buscarDescuento(Long idDescuento);

}
