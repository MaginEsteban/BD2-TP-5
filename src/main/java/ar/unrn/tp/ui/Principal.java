package ar.unrn.tp.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

import ar.unrn.tp.jpa.services.ClientService;
import ar.unrn.tp.jpa.services.DiscountService;
import ar.unrn.tp.jpa.services.ProductService;
import ar.unrn.tp.jpa.services.SaleService;
import ar.unrn.tp.modelo.Product;
import ar.unrn.tp.modelo.Card;
import ar.unrn.tp.modelo.Cart;
import ar.unrn.tp.modelo.Client;
import ar.unrn.tp.modelo.Discount;

public class Principal extends JFrame {
	
	private JList<Product> listaProductos;
    private JList<Card> listaTarjetas;
    private JTextArea descuentosArea;
    private JButton botonPrecioTotal;
    private JButton botonComprar;

    public Principal(Long idCliente,String persistence_unit) {
    	ClientService c = new ClientService(persistence_unit);
        ProductService p = new ProductService(persistence_unit);
        DiscountService d = new DiscountService(persistence_unit);
        SaleService s = new SaleService(persistence_unit,c,p);
    	
    	Client cli;
    	// Configurar la ventana principal
        setTitle("Tienda Online");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear los componentes
        List<Product> productos = p.listarProductos();
        cli = c.buscarCliente(idCliente);
        List<Card> tarjetas = cli.getCardList();
        List<Discount> descuentos = d.descuentos();
        String text = "";
        
        for(Discount de: descuentos) {
        	if(
        		(	(de.getInicio().isEqual(LocalDate.now())) || (de.getInicio().isBefore(LocalDate.now()))  )
        		&& 
        		(   (de.getFin().isEqual(LocalDate.now()))    || (de.getFin().isAfter(LocalDate.now()))    )  
        																								) {
        		text = text.concat("Descuento en " + de.getMarca() + " del " + de.getDesc() + "% \n");
        	}
        	else {
        		descuentos.remove(de);
        	}
        }
        listaProductos = new JList<>(productos.toArray(new Product[0]));  // Lista de productos
        listaProductos.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        listaTarjetas = new JList<>(tarjetas.toArray(new Card[0]));  // Lista de tarjetas
        listaTarjetas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        descuentosArea = new JTextArea(text);
        descuentosArea.setEditable(false);

        botonPrecioTotal = new JButton("Ver Precio Total");
        botonComprar = new JButton("Realizar Compra");

        // Paneles para organizar componentes
        JPanel panelCentro = new JPanel(new GridLayout(1, 3));
        // Crear JScrollPane para productos y agregar título
        JScrollPane scrollProductos = new JScrollPane(listaProductos);
        scrollProductos.setBorder(new TitledBorder("Lista de productos"));
        panelCentro.add(scrollProductos);  // Agregamos los productos

        // Crear JScrollPane para descuentos y agregar título
        JScrollPane scrollDescuentos = new JScrollPane(descuentosArea);
        scrollDescuentos.setBorder(new TitledBorder("Descuentos disponibles"));
        panelCentro.add(scrollDescuentos);  // Agregamos los descuentos

        // Crear JScrollPane para tarjetas y agregar título
        JScrollPane scrollTarjetas = new JScrollPane(listaTarjetas);
        scrollTarjetas.setBorder(new TitledBorder("Tarjetas del cliente: " + cli.getName()));
        panelCentro.add(scrollTarjetas);

        JPanel panelSur = new JPanel();
        panelSur.add(botonPrecioTotal);  // Botón para calcular precio
        panelSur.add(botonComprar);      // Botón para realizar compra

        // Añadir paneles a la ventana
        add(panelCentro, BorderLayout.CENTER);
        add(panelSur, BorderLayout.SOUTH);

        // Mostrar la ventana
        setVisible(true);

        // Acción para calcular el precio total
        botonPrecioTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	Discount dis = null;
            	double tot = 0.0;
            	for (Discount d: descuentos){
                  if(d.getDesc() == 8L){
                	  dis = d;
                  }
                }
            	
            	try {
            	Cart c = new Cart(listaProductos.getSelectedValuesList(),dis);
            	
            	Card c2 = listaTarjetas.getSelectedValue();
            	
            	 tot = c.total()- c.calcularTodosLosDescuentos(c2,LocalDate.now());
            	JOptionPane.showMessageDialog(null, "Precio total actual: " + tot );
            	}catch(Exception ex){
            		JOptionPane.showMessageDialog(Principal.this,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            	}
           }
        });

        // Acción para realizar la compra
        botonComprar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Discount dis = null;
            	for (Discount d: descuentos){
                    if(d.getDesc() == 8L){
                  	  dis = d;
                    }
                  }
            	try{
            		
            	if(listaTarjetas.getSelectedValue() != null) {
            		s.realizarVenta(idCliente, listaProductos.getSelectedValuesList(), dis,listaTarjetas.getSelectedValue());
            		JOptionPane.showMessageDialog(null, "Compra realiza con exito!");
            	}
            	else {
            		//throw new Exception("Seleccione una tarjeta para continuar");
            	}
            	}catch(Exception ex2){
            		JOptionPane.showMessageDialog(Principal.this,ex2.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            		
            	}
            }
        });        
    

	

    }
}
