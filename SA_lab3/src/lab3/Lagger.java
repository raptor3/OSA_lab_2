/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

/**
 *
 * @author Vova
 */
public class Lagger implements Polinom {

    public double func(int power, double x) {
        switch (power) {
            case 0:
                return 1.0;
            case 1:
                return -x + 1;
            default:
                return ((2.0 * (power - 1) + 1 - x) * func(power - 1, x)
                        -  (power - 1) * func(power - 2, x))/(power);
        }
    }

    public double[][] polinom(int power) {
        double[][] pol = new double[power + 1][power + 1];
        pol[0][0] = 1;
        if (power > 0) {
            pol[1][0] = 1;
            pol[1][1] = -1;
        }
        for (int i = 2; i < power + 1; i++) {
            pol[i][0] = -(i - 1) * (i - 1) * pol[i - 2][0] + (2 * i - 1) * pol[i - 1][0];
            for (int j = 1; j < power + 1; j++) {
                pol[i][j] = -pol[i - 1][j - 1] - (i - 1) * (i - 1) * pol[i - 2][j] + (2 * i - 1) * pol[i - 1][j];
            }
        }

        return pol;
    }
}
