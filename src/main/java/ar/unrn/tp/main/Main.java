package ar.unrn.tp.main;

// ar.unrn.tp.jpa.services.DiscountService;

import ar.unrn.tp.cache.CacheService;
import ar.unrn.tp.jpa.services.*;
import ar.unrn.tp.modelo.*;
import ar.unrn.tp.ui.Cache;
import ar.unrn.tp.ui.ModificarProducto;
import ar.unrn.tp.ui.SeleccionString;

import java.time.LocalDate;

public class Main {
	public static void main(String[] args) {

    //String[] opciones = {"jpa-objectdb", "mysql-unit"};
	//SeleccionString selec = new SeleccionString(1L,opciones);
	//ModificarProducto m = new ModificarProducto(1L,"mysql-unit");
		ClientService c = new ClientService("mysql-unit");
		ProductService p = new ProductService("mysql-unit");
		PaymentService pay = new PaymentService("mysql-unit",p);
		SaleService s = new SaleService("mysql-unit",c,p,pay);
		//System.out.println(s.ventas());
		CacheService cs = new CacheService(s);
		Cache cac = new Cache(cs);
		//System.out.println(cs.getUltimasCompras());
	//ProductService p = new ProductService("mysql-unit");
	//DiscountService d = new DiscountService("mysql-unit");
	//Discount dis = d.buscarDescuento(1L);
	//Product pro = p.buscarProducto(1L);
	//p.modificarProducto(1L,333L,"Nike",1000.0,dis,pro.getCategory());
	/*
	ClientService c = new ClientService("mysql-unit");
	DiscountService d = new DiscountService("mysql-unit");
	ProductService p = new ProductService("mysql-unit");
	PaymentService pay = new PaymentService("mysql-unit",p);
	SaleService s = new SaleService("mysql-unit",c,p,pay);
	*/
	//System.out.println(pay.crearCarrito(p.listarProductos(),new Discount(8L,"Naranja",LocalDate.of(2024,10,10), LocalDate.of(2024,10,12))));
	//Long idCarrito = pay.crearCarrito(p.listarProductos(),new Discount(8L,"Naranja",LocalDate.of(2024,10,10), LocalDate.of(2024,10,12)));
	//Long idPago = pay.crearPago(2L,idCarrito);
	//s.realizarVenta(1L,idPago);
	//c.crearCliente(34L,"Magin","Esteban","ex@gmail.com");
	//c.agregarTarjeta(1L,new Card(2222L,"Naranja"));
	//c.agregarTarjeta(1L,new Card(2332L,"Visa"));
	//System.out.println(c.listarTarjetas(1L));
	//d.crearDescuento(8L,"Naranja",LocalDate.now(),LocalDate.of(2024,10,12));
	//p.crearProducto(333L,"Nike",1000.2,new Category("Zapatillas"),1L);
	//p.crearProducto(334L,"Nike",200.0,new Category("Campera"),1L);
	//p.crearProducto(335L,"Adidas",400.0,new Category("Remera"),1L);
	//p.crearProducto(336L,"Nike",15.3,new Category("Ojotas"),1L);
	//p.crearProducto(337L,"Adidas",1500.0,new Category("Zapatillas"),1L);
		// s.realizarVenta(1L,p.listarProductos(),di,ca);










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