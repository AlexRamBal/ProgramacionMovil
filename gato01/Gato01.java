/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gato01;

/**
 *
 * @author alexr
 */
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Gato01 extends JFrame implements ActionListener {
    JButton botones[] = new JButton[9];
    JButton btnReinciar;
    boolean turnoX = true;
    Font fuente = new Font("Arial", 1, 60);
    JPanel pJuego, pOpciones;
    int contadorMovimientos = 0;
    
    //Matriz de combinaciones ganadoras como constante
    private static final int[][] COMBINACIONES_GANADORAS = {
        {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Filas
        {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Columnas
        {0, 4, 8}, {2, 4, 6}              // Diagonales
    };
    

    private int ultimaPosicion = -1;
    private String ultimoJugador = "";

    public Gato01() {
        setTitle("Gato");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pJuego = new JPanel();
        pJuego.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < botones.length; i++) {
            botones[i] = new JButton("");
            botones[i].setFont(fuente);
            botones[i].addActionListener(this);
            pJuego.add(botones[i]);
        }

        add(pJuego, BorderLayout.CENTER);

        btnReinciar = new JButton("Reiniciar Juego");
        pOpciones = new JPanel();

        btnReinciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ex) {
                reiniciarJuego();
            }
        });

        pOpciones.add(btnReinciar);
        add(pOpciones, BorderLayout.SOUTH);
    }

    public static void main(String a[]) {
        Gato01 g01 = new Gato01();
        g01.setVisible(true);
    }

    public void actionPerformed(ActionEvent btnApretado) {
        // Optimización: Determinar jugador y posición en una sola pasada
        for (int i = 0; i < botones.length; i++) {
            if (btnApretado.getSource() == botones[i] && botones[i].getText().equals("")) {
                // Guardar en cache para la validación
                ultimaPosicion = i;
                ultimoJugador = turnoX ? "X" : "O";
                
                // Actualizar botón
                botones[i].setText(ultimoJugador);
                botones[i].setEnabled(false);
                contadorMovimientos++;
                
                // Cambiar turno
                turnoX = !turnoX;
                
                if (hayGanadorOptimizado()) {
                    JOptionPane.showMessageDialog(this,
                            "¡El jugador " + ultimoJugador + " ha ganado!",
                            "Fin del juego",
                            JOptionPane.INFORMATION_MESSAGE);
                    deshabilitarBotones();
                } else if (contadorMovimientos == 9) {
                    JOptionPane.showMessageDialog(this,
                            "¡Empate!",
                            "Fin del juego",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                break;
            }
        }
    }
  // Alejandro Ramírez Balderas.
    
    private boolean hayGanadorOptimizado() {
        int fila = ultimaPosicion / 3;
        int columna = ultimaPosicion % 3;
        
        // Verificar fila completa
        if (botones[fila * 3].getText().equals(ultimoJugador) &&
            botones[fila * 3 + 1].getText().equals(ultimoJugador) &&
            botones[fila * 3 + 2].getText().equals(ultimoJugador)) {
            return true;
        }
        
        // Verificar columna completa
        if (botones[columna].getText().equals(ultimoJugador) &&
            botones[columna + 3].getText().equals(ultimoJugador) &&
            botones[columna + 6].getText().equals(ultimoJugador)) {
            return true; 
        }
        
        // Verificar diagonal principal (solo si está en ella)
        if (ultimaPosicion % 4 == 0) { // Posiciones 0,4,8
            if (botones[0].getText().equals(ultimoJugador) &&
                botones[4].getText().equals(ultimoJugador) &&
                botones[8].getText().equals(ultimoJugador)) {
                return true;
            }
        }
        
        // Verificar diagonal inversa (solo si está en ella)
        if (ultimaPosicion == 2 || ultimaPosicion == 4 || ultimaPosicion == 6) {
            if (botones[2].getText().equals(ultimoJugador) &&
                botones[4].getText().equals(ultimoJugador) &&
                botones[6].getText().equals(ultimoJugador)) {
                return true;
            }
        }
        
        return false;
    }

    private void deshabilitarBotones() {
        for (int i = 0; i < botones.length; i++) {
            botones[i].setEnabled(false);
        }
    }

    private void reiniciarJuego() {
        for (int i = 0; i < botones.length; i++) {
            botones[i].setText("");
            botones[i].setEnabled(true);
        }
        turnoX = true;
        contadorMovimientos = 0;
        ultimaPosicion = -1;
        ultimoJugador = "";
    }
}