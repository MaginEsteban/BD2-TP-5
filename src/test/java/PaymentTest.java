import ar.unrn.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {

    @Test
    public void testValidarVenta(){
        ArrayList<Product> products = new ArrayList<>();
        ArrayList<Card> tarjetas = new ArrayList<>();
        tarjetas.add(new Card(1L,"Naranja"));
        products.add(new Product(1L,"Nike",new Category(1L,"Zapatillas"),134));
        Payment pago = new Payment(1L,new Card(1L,"Naranja"), new Cart(1L,products),new Client(1L,39867033L,"Magin","Esteban","estebanmagin@gmail.com",tarjetas));
        Sale s = pago.generarVenta();
        assertNotNull(s);
    }
}
