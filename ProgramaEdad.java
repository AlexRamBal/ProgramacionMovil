/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
// Alejandro Ramírez Balderas.
package com.mycompany.programaedad;

/**
 *
 * @author alexr
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class ProgramaEdad extends JFrame implements ActionListener {
    // Declaración de componentes
    JTextField txtEdad;
    JButton btnVerificar;
    JPanel panel01;
    JLabel etiqueta;
    
    public ProgramaEdad() {
        // Configuración de la ventana
        setTitle("Verificador de Edad");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana
        
        // Inicializar componentes
        etiqueta = new JLabel("Ingrese su edad:");
        txtEdad = new JTextField(10);
        btnVerificar = new JButton("Verificar");
        
        // Agregar ActionListener al botón
        btnVerificar.addActionListener(this);
        
        // Crear y configurar panel
        panel01 = new JPanel();
        panel01.add(etiqueta);
        panel01.add(txtEdad);
        panel01.add(btnVerificar);
        
        // Agregar panel a la ventana
        add(panel01);
        
        // Hacer visible la ventana
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // Verificar que el evento viene del botón
        if (e.getSource() == btnVerificar) {
            try {
                // Recuperar la edad del campo de texto
                String edadTexto = txtEdad.getText();
                
                // Verificar si el campo está vacío
                if (edadTexto.isEmpty()) {
                    JOptionPane.showMessageDialog(null, 
                        "Por favor ingrese una edad");
                    return;
                }
                
                // Alejandro Ramírez Balderas
                // Convertir a número
                int edad = Integer.parseInt(edadTexto);
                
                // Validar que la edad sea positiva
                if (edad < 0) {
                    JOptionPane.showMessageDialog(null, 
                        "La edad no puede ser negativa");
                } 
                else if (edad >= 18) {
                    JOptionPane.showMessageDialog(null, 
                        "MAYOR DE EDAD\n" +
                        "Edad ingresada: " + edad + " años");
                } 
                else {
                    JOptionPane.showMessageDialog(null, 
                        "MENOR DE EDAD\n" +
                        "Edad ingresada: " + edad + " años");
                }
                
                // Limpiar el campo después de verificar
                txtEdad.setText("");
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, 
                    "Error: Debe ingresar un número válido");
            }
        }
    }
    
    public static void main(String[] args) {
        // Crear una instancia de la ventana
        ProgramaEdad programa = new ProgramaEdad();
    }
}