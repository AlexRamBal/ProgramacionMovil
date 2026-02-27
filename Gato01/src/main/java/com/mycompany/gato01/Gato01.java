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
import javax.swing.JPanel;

public class Gato01 extends JFrame implements ActionListener {
    JButton botones [] = new JButton[9];
    JButton btnReinciar;
    boolean turnoX=true;
    Font fuente = new Font("Arial",1,60);  
    JPanel pJuego, pOpciones;
    String letra;
   
    public Gato01(){
        setTitle("Gato");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        //pJuego = new JPanel(new GridLayout(3,3));
        pJuego = new JPanel();
        pJuego.setLayout(new GridLayout(3,3));
       
        //botones[0] = new JButton("");
        //pJuego.add(botones[0]);
       
        for(int i=0;i<botones.length;i++){
            botones[i] = new JButton("");
            botones[i].setFont(fuente);
            botones[i].addActionListener(this);
            pJuego.add(botones[i]);            
        }
       
        add(pJuego, BorderLayout.CENTER);
       
       
        btnReinciar = new JButton("Reiniciar Juego");
        pOpciones = new JPanel();
       
        btnReinciar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ex){
                  for(int i=0;i<botones.length;i++){
                        botones[i].setText("");  
                        botones[i].setEnabled(true);                      
                  }
                  turnoX=true;                  
            }            
        });
       
        pOpciones.add(btnReinciar);
        add(pOpciones, BorderLayout.SOUTH);
    }
   
    public static void main(String a []){
        Gato01 g01 = new Gato01();
        g01.setVisible(true);
    }
   
    public void actionPerformed(ActionEvent btnApretado){
        if(turnoX){
           letra="X";
           turnoX=false;
        }else{
            letra="O";
            turnoX=true;
        }
       
        for(int i=0;i<botones.length;i++){
            if(btnApretado.getSource()==botones[i]){
                botones[i].setText(letra);  
                botones[i].setEnabled(false);
            }    
        }
    }
}

