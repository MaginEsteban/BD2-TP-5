package ar.unrn.tp.api;

import ar.unrn.tp.modelo.Card;
import ar.unrn.tp.modelo.Client;


import java.util.List;

public interface ClienteInterfaz {
   // validar que el dni no se repita

    void crearCliente(Long dni,String nombre, String apellido, String email);

    // validar que sea un cliente existente

    void modificarCliente(Long idCliente,Long dni, String nombre,String apellido,String email);

    // validar que sea un cliente existente
    void agregarTarjeta(Long idCliente,Long nro, String marca);

    //Devuelve las tarjetas de un cliente específico
    List<Card> listarTarjetas(Long idCliente);

    //busca un cliente
    Client buscarCliente(Long idCliente);
}
