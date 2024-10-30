package ar.unrn.tp.ui;
import ar.unrn.tp.cache.CacheService;

import javax.swing.*;
import java.awt.*;

public class Cache extends JFrame {

    public Cache(CacheService cs) {
        // Configuración de la ventana
        setTitle("Ultimas compras");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300); // Tamaño de la ventana
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        // Crear un panel y establecer el layout como GridBagLayout
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        // Crear una lista de strings
        String[] datos = cs.getUltimasCompras().toArray(new String[0]);
        JList<String> lista = new JList<>(datos);
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Permitir solo una selección

        // Agregar la lista dentro de un JScrollPane
        JScrollPane scrollPane = new JScrollPane(lista);
        scrollPane.setPreferredSize(new Dimension(200, 150)); // Tamaño del JScrollPane

        // Configurar restricciones para centrar la lista en el panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Margen
        panel.add(scrollPane, gbc); // Agregar el JScrollPane al panel

        // Agregar el panel al marco
        add(panel);
        setVisible(true);
    }
}
