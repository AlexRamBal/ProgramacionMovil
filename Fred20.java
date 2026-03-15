/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.fred20;

/**
 *
 * @author alexrambal
 */
//Ramírez Balderas Alejandro

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Fred20 extends JFrame {

    JButton casillas[] = new JButton[4];
    List<Integer> secuencia = new ArrayList<>();
    List<Integer> secuenciaJugador = new ArrayList<>();
    Random r = new Random();
    
    // Colores específicos para cada botón cuando están encendidos
    private final Color[] COLORES_ENCENDIDOS = {
        Color.RED,      // btn1 - Rojo
        Color.GREEN,    // btn2 - Verde
        Color.BLUE,     // btn3 - Azul
        Color.YELLOW    // btn4 - Amarillo
    };
    
    
    private final Color COLOR_APAGADO = Color.LIGHT_GRAY;
    
    private int nivelActual = 1;
    private boolean esperandoJugador = false;
    private boolean juegoActivo = true;

    public Fred20() {
        setTitle("Fred20 - Nivel 1");
        setSize(300, 300);
        setDefaultCloseOperation(3);
        setLayout(new GridLayout(2, 2));

        for (int i = 0; i < casillas.length; i++) {
            final int indice = i;
            casillas[i] = new JButton();
            casillas[i].setBackground(COLOR_APAGADO);
            
            casillas[i].addActionListener((ActionEvent e) -> {
                if (esperandoJugador && juegoActivo) {
                    procesarJugada(indice);
                }
            });
            
            add(casillas[i]);
        }

        iniciarNivel();
    }

    public void iniciarNivel() {
        // Limpiar secuencia del jugador
        secuenciaJugador.clear();
        
        // Agregar un nuevo número a la secuencia
        secuencia.add(r.nextInt(4));
        
        System.out.println("Nivel " + nivelActual + " - Secuencia: " + secuencia);
        
        // Mostrar la secuencia completa
        mostrarSecuencia();
    }
    
    public void mostrarSecuencia() {
        esperandoJugador = false;
        
        Thread hilo = new Thread(() -> {
            try {
                // Pequeña pausa antes de comenzar
                Thread.sleep(500);
                
                for (int i = 0; i < secuencia.size(); i++) {
                    int indice = secuencia.get(i);
                    
                    // Encender el botón con su color específico
                    casillas[indice].setBackground(COLORES_ENCENDIDOS[indice]);
                    Thread.sleep(600);
                    
                    // Apagar el botón (volver a gris)
                    casillas[indice].setBackground(COLOR_APAGADO);
                    Thread.sleep(200);
                }
                
                // Indicar que ahora es el turno del jugador
                esperandoJugador = true;
                System.out.println("Turno del jugador. Repite la secuencia...");
                
            } catch (Exception e) {
            }
        });
        hilo.start();
    }
    
    private void procesarJugada(int indice) {
        // Encender el botón que presionó el jugador
        encenderBoton(indice);
        
        // Agregar a la secuencia del jugador
        secuenciaJugador.add(indice);
        
        // Verificar si la jugada es correcta
        int posicionActual = secuenciaJugador.size() - 1;
        
        if (secuenciaJugador.get(posicionActual).equals(secuencia.get(posicionActual))) {
            // La jugada es correcta
            System.out.println("¡Correcto! Llevas " + (posicionActual + 1) + " de " + secuencia.size());
            
            // Verificar si completó toda la secuencia
            if (secuenciaJugador.size() == secuencia.size()) {
                nivelCompletado();
            }
        } else {
            // La jugada es incorrecta - PERDIÓ
            juegoPerdido();
        }
    }
    
    private void nivelCompletado() {
        esperandoJugador = false;
        nivelActual++;
        setTitle("Fred20 - Nivel " + nivelActual);
        
        System.out.println("¡Nivel completado! Pasando al nivel " + nivelActual);
        
        // Mostrar mensaje de nivel completado
        JOptionPane.showMessageDialog(this, 
            "¡Nivel " + (nivelActual - 1) + " completado!\nPasando al nivel " + nivelActual,
            "¡Bien hecho!",
            JOptionPane.INFORMATION_MESSAGE);
        
        // Iniciar el siguiente nivel
        iniciarNivel();
    }
    
    private void juegoPerdido() {
        juegoActivo = false;
        esperandoJugador = false;
        
        int nivelesCompletados = nivelActual - 1;
        
        System.out.println("¡Perdiste! Completaste " + nivelesCompletados + " niveles");
        
        // Mostrar mensaje con los niveles completados
        String mensaje = "¡Perdiste!\n\n";
        mensaje += "Niveles completados: " + nivelesCompletados + "\n\n";
        mensaje += "¿Quieres jugar de nuevo?";
        
        int opcion = JOptionPane.showConfirmDialog(this, 
            mensaje,
            "Game Over",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.ERROR_MESSAGE);
        
        if (opcion == JOptionPane.YES_OPTION) {
            reiniciarJuego();
        } else {
            System.exit(0);
        }
    }
    
    private void reiniciarJuego() {
        // Reiniciar todas las variables
        secuencia.clear();
        secuenciaJugador.clear();
        nivelActual = 1;
        juegoActivo = true;
        setTitle("Fred20 - Nivel 1");
        
        System.out.println("\n=== NUEVA PARTIDA ===");
        
        // Iniciar el primer nivel
        iniciarNivel();
    }
    
    private void encenderBoton(int indice) {
        
        Thread hiloUsuario = new Thread(() -> {
            try {
                // Encender el botón con su color específico
                casillas[indice].setBackground(COLORES_ENCENDIDOS[indice]);
                Thread.sleep(300);
                // Apagar el botón
                casillas[indice].setBackground(COLOR_APAGADO);
            } catch (Exception ex) {
            }
        });
        hiloUsuario.start();
    }

    public static void main(String[] args) {
        Fred20 f = new Fred20();
        f.setVisible(true);
    }
}