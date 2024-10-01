package ar.unrn.tp.api;
import ar.unrn.tp.modelo.Card;
import ar.unrn.tp.modelo.Discount;
import ar.unrn.tp.modelo.Product;
import ar.unrn.tp.modelo.Sale;

import java.time.LocalDate;
import java.util.List;

public interface VentaInterfaz {
    //Crea una venta. El monto se calcula aplicando los descuentos a la fecha
    // validaciones:
    // - debe ser un cliente existente
    // - la lista de productos no debe estar vacía
    // - La tarjeta debe pertenecer al cliente
    void realizarVenta(Long idCliente, List<Product> productos, Discount descuento,Card d);

    //Devuelve el monto total aplicando los descuentos al día de la fecha
    // validar que no llegue una lista vacía y la tarjeta exista
    double calcularMonto(Long idSale, LocalDate l,Long idTarjeta);

    //Devuelve todas las ventas realizadas
    List<Sale> ventas();

    Sale buscarVenta(Long idSale);
}
