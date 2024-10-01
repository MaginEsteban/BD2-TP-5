package ar.unrn.tp.main;

import ar.unrn.tp.jpa.services.ClientService;
import ar.unrn.tp.jpa.services.DiscountService;
// ar.unrn.tp.jpa.services.DiscountService;
import ar.unrn.tp.jpa.services.ProductService;
import ar.unrn.tp.jpa.services.SaleService;
import ar.unrn.tp.modelo.*;
import ar.unrn.tp.ui.Principal;
import ar.unrn.tp.ui.SeleccionString;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
    String[] opciones = {"jpa-objectdb", "mysql-unit"};
	SeleccionString s = new SeleccionString(1L,opciones);













    //ClientService c = new ClientService(s2);
    //DiscountService d = new DiscountService(s2);
    //d.crearDescuento(4L,"Adidas",LocalDate.of(2024,9,29),LocalDate.of(2024,9,30));
    //c.crearCliente(22L,"Magin","Esteban","estebanmagin@gmail.com");
    // DiscountService d = new DiscountService(em);
    // ProductService p = new ProductService(em);
    //d.crearDescuento(8L,"Visa",LocalDate.of(2024,9,28),LocalDate.of(2024,9,29));
    //d.crearDescuento(5L,"Adidas",LocalDate.of(2024,9,28),LocalDate.of(2024,9,29));
    //d.crearDescuento(8L,"Visa",LocalDate.of(2024,9,26),LocalDate.of(2024,9,27));
    //d.crearDescuento(5L,"Nike",LocalDate.of(2024,9,26),LocalDate.of(2024,9,27));
   
    //p.crearProducto(333L,"Nike",1000.2,new Category("Zapatillas"),12L);
    //p.crearProducto(334L,"Nike",200.0,new Category("Campera"),12L);
    //p.crearProducto(335L,"Adidas",400.0,new Category("Remera"),12L);
    //p.crearProducto(336L,"Nike",15.3,new Category("Ojotas"),12L);
    //p.crearProducto(337L,"Adidas",1500.0,new Category("Zapatillas"),12L);








	/*

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-objectdb");
        EntityManager em = emf.createEntityManager();
        ClientService c = new ClientService(em);
        ProductService p = new ProductService(em);
        SaleService s = new SaleService(em,c,p);
        //c.crearCliente(12121212L,"Test","TEST","test@gmail.com");

        //c.agregarTarjeta(1L,12566666L,"Naranja");
        //c.modificarCliente(1L,39877033L,"Magin","Esteban","estebanelmagin@gmail.com");
        //List<Card> lis;
        //lis = c.listarTarjetas(1L);
        //System.out.println(lis);
        //p.crearProducto(333L,"Producto1",1000.2,new Category("Categoria 1"),null);
        //p.modificarProducto(7L,334L,"Producto1-mod",1000.3,new Discount(4L,"nike", LocalDate.of(2024,9,27),LocalDate.of(2024,9,28)));
        //System.out.println(p.listarProductos());
        //List<Product> lis2 = new ArrayList<>();
        //lis2.add(p.buscarProducto(7L));

        //s.realizarVenta(1L,lis2,new Discount(5L,"Visa",LocalDate.of(2024,9,26),LocalDate.of(2024,9,28)));
        //System.out.println(s.ventas());
        System.out.println(s.calcularMonto(9L,LocalDate.now(),2L));


    */
	}
}