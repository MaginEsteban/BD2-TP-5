package ar.unrn.tp.api;

import ar.unrn.tp.modelo.Card;
import ar.unrn.tp.modelo.Cart;
import ar.unrn.tp.modelo.Discount;
import ar.unrn.tp.modelo.Product;

import java.util.List;

public interface PaymentInterfaz {

    Long crearPago(Long idCard,Long idCart);

    Long crearCarrito(List<Product> lis, Discount dis);

    Cart buscarCarrito(Long id);

}
