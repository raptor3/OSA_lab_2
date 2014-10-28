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
public class F_2 implements FuncFromMatr {

    private Psi[] psi;
    private double[][] xi;
    private double[] yi;
    private int i;
    private int s;

    @Override
    public double func(Matr x) {
        double max = Double.NEGATIVE_INFINITY;
        //To change body of generated methods, choose Tools | Templates.
        for (int i = 0; i < yi.length; i++) {
            double sum = 0;
            for (int js = 0; js < xi.length; js++) {
                sum += x.getX1()[0][js] * psi[i].psi_s_js(s, js, xi[0][i]);
            }
            sum -= yi[i];
            sum = Math.abs(sum);
            if (max < sum) {
                max = sum;
            }
        }
        return max;
    }

    public F_2(Psi[] psi, double[][] xi, double[] yi, int s, int i) {
        this.psi = psi;
        this.xi = xi;
        this.yi = yi;
        this.s = s;
        this.i = i;
    }

}
