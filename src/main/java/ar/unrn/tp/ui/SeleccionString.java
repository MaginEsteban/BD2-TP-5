package ar.unrn.tp.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SeleccionString extends JFrame {
    private JComboBox<String> comboBox;
    private JButton botonConfirmar;

    public SeleccionString(Long l, String[] opciones) {
        // Configuración de la ventana
        setTitle("Seleccion de la unidad de persistencia");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Opciones del JComboBox
        comboBox = new JComboBox<>(opciones);

        // Botón para confirmar
        botonConfirmar = new JButton("Selecciónar unidad");
        // Añadir los componentes a la ventana
        add(comboBox);
        add(botonConfirmar);
        setVisible(true);
        // Acción del botón
        botonConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el valor seleccionado
                String seleccion = (String) comboBox.getSelectedItem();

                // Crear una nueva instancia de la pantalla principal y pasarle la selección
                Principal p = new Principal(l,seleccion);
                p.setVisible(true);

                // Cerrar la esta pantalla
                dispose();
            }
        });


    }
}

