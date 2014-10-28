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
public class F_1 implements FuncFromMatr {

    private int n1;
    private int n2;
    private int n3;
    private int p1;
    private int p2;
    private int p3;
    private Polinom pol;
    private double[][] x1;
    private double[][] x2;
    private double[][] x3;
    private double[] bq0;

    public F_1(int n1, int n2, int n3, int p1, int p2, int p3, Polinom pol,
            double[][] x1, double[][] x2, double[][] x3, double[] bq0) {
        this.n1 = n1;
        this.n2 = n2;
        this.n3 = n3;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.pol = pol;
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.bq0 = bq0;
    }

    @Override
    public double func(Matr x) {
        double max = Double.NEGATIVE_INFINITY;
        if (bq0 == null) System.out.println("12312");
        for (int i = 0; i < bq0.length; i++) {
            double s = 0;
            for (int j = 0; j < n1; j++) {
                for (int p = 0; p < p1; p++) {
                    s+=x.getX1()[j][p]*pol.func(p, x1[j][i]);
                }
            }
            for (int j = 0; j < n2; j++) {
                for (int p = 0; p < p2; p++) {
                    s+=x.getX2()[j][p]*pol.func(p, x2[j][i]);
                }
            }
            for (int j = 0; j < n3; j++) {
                for (int p = 0; p < p3; p++) {
                    s+=x.getX3()[j][p]*pol.func(p, x3[j][i]);
                }
            }
            s -= bq0[i];
            s = Math.abs(s);
            if (max < s) {
                max = s;
            }
        }
        return max;
    }

}
