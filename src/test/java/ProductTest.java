import ar.unrn.Category;
import ar.unrn.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    @Test
    public void testValidarAltaProducto(){
        assertDoesNotThrow(()-> { Product p  = new Product(1L,"Nike",new Category(1L,"Zapatillas"),1.0); });
    }

}
