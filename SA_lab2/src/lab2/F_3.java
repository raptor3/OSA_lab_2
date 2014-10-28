/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2;

/**
 *
 * @author Vova
 */
public class F_3 implements FuncFromMatr {

    private Fi fi;
    private double x1[][];
    private double x2[][];
    private double x3[][];
    private double y[];
    private int i;

    public F_3(Fi fi, double[][] x1, double[][] x2, double[][] x3, double[] y, int i) {
        this.fi = fi;
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.y = y;
        this.i = i;
    }

    @Override
    public double func(Matr x) {
        double max = Double.NEGATIVE_INFINITY;
        for (int q = 0; q < x1[0].length; q++) {
            double[] x1q = new double[x1.length];
            for (int k = 0; k < x1.length; k++) {
                x1q[k] = x1[k][q];
            }
            double[] x2q = new double[x2.length];
            for (int k = 0; k < x2.length; k++) {
                x2q[k] = x2[k][q];
            }
            double[] x3q = new double[x3.length];
            for (int k = 0; k < x3.length; k++) {
                x3q[k] = x3[k][q];
            }
            double sum = x.getX1()[0][0] * fi.func(i, 1, x1q)
                    + x.getX2()[0][0] * fi.func(i, 2, x2q)
                    + x.getX3()[0][0] * fi.func(i, 3, x3q);
            sum -= y[q];
            if (max < sum) {
                max = sum;
            }
        }
        return max;
    }
}
