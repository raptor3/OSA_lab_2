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
public class Ermit implements Polinom {

    @Override
    public double func(int power, double x) {
        switch (power) {
            case 0:
                return 1.0;
            case 1:
                return 2 * x;
            default:
                return (2.0 * x * func(power - 1, x)
                        - 2 * (power - 1) * func(power - 2, x));
        }
    } //To change body of generated methods, choose Tools | Templates.

    public double[][] polinom(int power) {
        double[][] pol = new double[power + 1][power + 1];
        pol[0][0] = 1;
        if (power > 0) {
            pol[1][0] = 0;
            pol[1][1] = 2;
        }
        for (int i = 2; i < power + 1; i++) {
            pol[i][0] = -pol[i - 2][0]*2*(i-1);
            for (int j = 1; j < power + 1; j++) {
                pol[i][j] = 2 * pol[i - 1][j - 1] - pol[i - 2][j]*2*(i-1);
            }
        }

        return pol;
    }
}
