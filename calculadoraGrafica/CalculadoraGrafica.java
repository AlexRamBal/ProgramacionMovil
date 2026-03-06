/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.calculadoragrafica;

/**
 *
 * @author alexrambal
 */

//Alejandro Ramírez Balderas

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalculadoraGrafica extends JFrame{
    JTextField n1, n2;
    JButton btnSum, btnRes, btnDiv, btnMul;
    JLabel txtRes;
    Font fuente = new Font("Courier",1,60);
    Calculadora calc = new Calculadora();
   
    public CalculadoraGrafica(){
        setTitle("casio");
        setSize(400,400);
        setDefaultCloseOperation(3);
        setLayout(new GridLayout(4,1));
       
        n1 = new JTextField();
        n1.setFont(fuente);
        n2 = new JTextField();
        n2.setFont(fuente);
       
        JPanel pOpciones = new JPanel(new GridLayout(1,4));
        btnSum = new JButton("+");
        btnSum.setFont(fuente);
        btnRes = new JButton("-");
        btnRes.setFont(fuente);
        btnMul = new JButton("*");
        btnMul.setFont(fuente);
        btnDiv = new JButton("/");
        btnDiv.setFont(fuente);
       
        pOpciones.add(btnSum);        
        pOpciones.add(btnRes);
        pOpciones.add(btnMul);
        pOpciones.add(btnDiv);
               
        txtRes = new JLabel("0");
        txtRes.setFont(fuente);
       
        // Todos con lambda usando la clase Calculadora
        btnSum.addActionListener(e -> {
            double num1 = Double.parseDouble(n1.getText());
            double num2 = Double.parseDouble(n2.getText());
            txtRes.setText(String.valueOf(calc.sumar(num1, num2)));
        });    
       
        btnMul.addActionListener(e -> {
            double num1 = Double.parseDouble(n1.getText());
            double num2 = Double.parseDouble(n2.getText());
            txtRes.setText(String.valueOf(Calculadora.multiplicar(num1, num2)));
        });
       
        btnRes.addActionListener(e -> {
            double num1 = Double.parseDouble(n1.getText());
            double num2 = Double.parseDouble(n2.getText());
            txtRes.setText(String.valueOf(calc.restar(num1, num2)));
        });
       
        btnDiv.addActionListener(e -> {
            double num1 = Double.parseDouble(n1.getText());
            double num2 = Double.parseDouble(n2.getText());
            if (num2 != 0) {
                txtRes.setText(String.valueOf(Calculadora.dividir(num1, num2)));
            } else {
                txtRes.setText("Error: Div/0");
            }
        });
       
        add(n1);
        add(n2);
        add(pOpciones);
        add(txtRes);        
    }
   
    public static void main(String[] args) {
        CalculadoraGrafica cc = new CalculadoraGrafica();
        cc.setVisible(true);
    }  
}