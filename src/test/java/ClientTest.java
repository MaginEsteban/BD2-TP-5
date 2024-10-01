import ar.unrn.tp.modelo.Card;
import ar.unrn.tp.modelo.Client;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ClientTest {
    @Test
    public void testValidarAltaCliente(){
        assertDoesNotThrow(()-> {
            List<Card> card = null;// = new List<>();
            card.add(new Card(1L,"Naranja"));
            Client c  = new Client(1L,39867033L,"Magin","Esteban","email@ex.com",card);
        });
    }
}
