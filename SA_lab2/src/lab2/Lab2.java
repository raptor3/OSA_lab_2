/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2;

import Jama.Matrix;

/**
 *
 * @author Vova
 */
public class Lab2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        double[][] a = {{1,2,3},
//                        {4,5,6}};
//        a = new double[2][3];
        double[] b = {11,-12,13};
//        Matrix am = new Matrix(a);
        Matrix b1 = new Matrix(b,3);
        System.out.println(b1.normInf());
//        am = am.times(b1);
//        System.out.println(am);
        new MainFrame().setVisible(true);
    }
    
}
