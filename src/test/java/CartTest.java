import ar.unrn.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

public class CartTest {

    @Test
    public void testMontoTotal(){
        ArrayList<Product> list = new ArrayList<>();
        LocalDate inicio = LocalDate.of(2024,8,21);
        LocalDate fin = LocalDate.of(2024,8,22);
        list.add(new Product(1L,"Nike",new Category(1L,"Zapatillas"),134,new Discount(1L,5L,"Nike",inicio,fin)));
        Cart carrito = new Cart(1L,list,new Discount(2L,8L,"Naranja",inicio,fin));
        double res = carrito.calcularTodosLosDescuentos(new Card(1L,"Naranja"),LocalDate.now());
        Assertions.assertEquals(17.42, res);
    }

    @Test
    public void testMontoProductoVigente(){
        ArrayList<Product> list = new ArrayList<>();
        LocalDate inicio = LocalDate.of(2024,8,21);
        LocalDate fin = LocalDate.of(2024,8,22);
        list.add(new Product(1L,"Nike",new Category(1L,"Zapatillas"),134,new Discount(1L,5L,"Nike",inicio,fin)));
        Cart carrito = new Cart(1L,list);
        double res = carrito.calcularDescuentoMarca(LocalDate.now());
        Assertions.assertEquals(6.7, res);
    }

    @Test
    public void testMontoCompraVigente(){
        ArrayList<Product> list = new ArrayList<>();
        LocalDate inicio = LocalDate.of(2024,8,21);
        LocalDate fin = LocalDate.of(2024,8,22);
        list.add(new Product(1L,"Nike",new Category(1L,"Zapatillas"),134));
        Cart carrito = new Cart(1L,list,new Discount(2L,8L,"Naranja",inicio,fin));
        double res = carrito.calcularTodosLosDescuentos(new Card(1L,"Naranja"),LocalDate.now());
        Assertions.assertEquals(10.72, res);
    }

    @Test
    public void testMontoTotalCaducados(){
        ArrayList<Product> list = new ArrayList<>();
        LocalDate inicio = LocalDate.of(2024,8,20);
        LocalDate fin = LocalDate.of(2024,8,20);
        list.add(new Product(1L,"Nike",new Category(1L,"Zapatillas"),134,new Discount(1L,5L,"Nike",inicio,fin)));
        Cart carrito = new Cart(1L,list,new Discount(2L,8L,"Naranja",inicio,fin));
        double res = carrito.total() - carrito.calcularTodosLosDescuentos(new Card(1L,"Naranja"),LocalDate.now());
        Assertions.assertEquals(134, res);
    }
}
