import ar.unrn.Discount;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class DiscountTest {
    @Test
    public void testValidarAltaDescuento(){
        assertDoesNotThrow(()-> {Discount d  = new Discount(1L,8L,"Naranja", LocalDate.of(2024,8,21),LocalDate.of(2024,8,22));});
    }
}
