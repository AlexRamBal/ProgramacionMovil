/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.calculadoragrafica;

/**
 *
 * @author alexrambal
 */

//Alejandro Ramírez Balderas

public class Calculadora {
   
    public double sumar(double n1, double n2){
        return n1 + n2;
    }
   
    public double restar(double n1, double n2){
        return n1 - n2;  // Cambiado para devolver valor
    }
   
    public static double multiplicar(double n1, double n2){
        return n1 * n2;
    }
   
    public static double dividir(double n1, double n2){
        if (n2 != 0) {
            return n1 / n2;  // Cambiado para devolver valor
        } else {
            throw new ArithmeticException("División por cero");
        }
    }
}