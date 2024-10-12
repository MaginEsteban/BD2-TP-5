package ar.unrn.tp.ui;

import ar.unrn.tp.jpa.services.DiscountService;
import ar.unrn.tp.jpa.services.ProductService;
import ar.unrn.tp.modelo.Category;
import ar.unrn.tp.modelo.Product;
import org.hibernate.dialect.lock.OptimisticEntityLockException;

import javax.persistence.OptimisticLockException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class ModificarProducto extends JFrame {

    // Componentes
    private JLabel lblProductId;
    private JTextField txtProductName, txtProductPrice, txtProductBrand;
    private JComboBox<String> cmbProductCategory;
    private JButton btnSave;
    private ProductService p;
    private DiscountService d;
    private Product prod_aux;
    private Long idP;


    public ModificarProducto(Long idProduct,String persistence_unit) {
        p = new ProductService(persistence_unit);
        d = new DiscountService(persistence_unit);
        idP = idProduct;
        List<String> strings_cat = p.listarCategorias();
        String[] categories = strings_cat.toArray(new String[0]);
        prod_aux = p.buscarProducto(1L);
        // Configurar la ventana
        setTitle("Modificar producto");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());  // Usamos GridBagLayout para mayor control
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);  // Márgenes entre los componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;  // Para que los componentes se estiren horizontalmente

        // Crear componentes
        lblProductId = new JLabel(prod_aux.getId().toString());  // Ejemplo de ID no editable
        txtProductName = new JTextField(prod_aux.getCategory().getNameCategory());
        txtProductPrice = new JTextField(String.valueOf(prod_aux.getPrice()));
        txtProductBrand = new JTextField(prod_aux.getDescription());

        cmbProductCategory = new JComboBox<>(categories);

        // Crear el botón de Guardar
        btnSave = new JButton("Guardar");

        // Añadir un ActionListener al botón
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Category c = prod_aux.getCategory();
                    c.setNameCategory((String) cmbProductCategory.getSelectedItem());
                    p.modificarProducto(idP,prod_aux.getCode(),txtProductBrand.getText(),Double.parseDouble(txtProductPrice.getText()),prod_aux.getPromo(),c);
                }catch (OptimisticLockException ex2){
                    JOptionPane.showMessageDialog(null,ex2.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                }
                catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        // Añadir componentes a la ventana con posiciones específicas

        // ID del Producto (Label)
        gbc.gridx = 0;
        gbc.gridy = 0;  // Primera columna, primera fila
        add(new JLabel("ID del Producto:"), gbc);

        gbc.gridx = 1;  // Segunda columna, misma fila
        add(lblProductId, gbc);

        // Nombre del Producto
        gbc.gridx = 0;
        gbc.gridy = 1;  // Primera columna, segunda fila
        add(new JLabel("Nombre del Producto:"), gbc);

        gbc.gridx = 1;  // Segunda columna, misma fila
        add(txtProductName, gbc);

        // Precio del Producto
        gbc.gridx = 0;
        gbc.gridy = 2;  // Primera columna, tercera fila
        add(new JLabel("Precio:"), gbc);

        gbc.gridx = 1;  // Segunda columna, misma fila
        add(txtProductPrice, gbc);

        // Marca del Producto
        gbc.gridx = 0;
        gbc.gridy = 3;  // Primera columna, cuarta fila
        add(new JLabel("Marca:"), gbc);

        gbc.gridx = 1;  // Segunda columna, misma fila
        add(txtProductBrand, gbc);

        // Categoría del Producto
        gbc.gridx = 0;
        gbc.gridy = 4;  // Primera columna, quinta fila
        add(new JLabel("Categoría:"), gbc);

        gbc.gridx = 1;  // Segunda columna, misma fila
        add(cmbProductCategory, gbc);

        // Botón Guardar
        gbc.gridx = 1;
        gbc.gridy = 5;  // Segunda columna, sexta fila
        gbc.anchor = GridBagConstraints.CENTER;  // Centrar el botón
        add(btnSave, gbc);

        // Hacer visible la ventana
        setVisible(true);
    }
}

