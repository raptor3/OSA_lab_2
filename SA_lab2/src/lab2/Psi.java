/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vova
 */
public class Psi {

    private int n1;
    private int n2;
    private int n3;
    private int[] p;
    private int[] n;
    private Polinom pol;
    private Matr lambda;
    private double[][][] polinomNorm;
    private double[][][] polinomNenorm;

    public Psi(int n1, int n2, int n3, int p1, int p2, int p3,
            Polinom pol, Matr lambda, double[][] maxx, double[][] minx) {
        polinomNorm = new double[3][][];
        polinomNenorm = new double[3][][];
        int[] n = {n1, n2, n3};
        int[] p = {p1, p2, p3};
        this.n = n;
        for (int s = 0; s < n.length; s++) {
            polinomNorm[s] = new double[n[s]][p[s]+1];
            polinomNenorm[s] = new double[n[s]][p[s]+1];
            double[][] polin = pol.polinom(p[s]);
            for (int js = 0; js < n[s]; js++) {
                for (int pi = 0; pi <= p[s]; pi++) {
                    for (int p4 = 0; p4 <= p[s]; p4++) {
                        try {
                            polinomNorm[s][js][pi] += 
                                    lambda.getX(s + 1)[js][pi] 
                                    * polin[p4][pi];
                        } catch (Exception ex) {
                            Logger.getLogger(Psi.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
            
            for (int js = 0; js < n[s]; js++) {
                polin = polinom(p[s],maxx[s][js],minx[s][js]);
                for (int pi = 0; pi <= p[s]; pi++) {
                    for (int p4 = 0; p4 <= p[s]; p4++) {
                        try {
                            polinomNenorm[s][js][pi] += polinomNorm[s][js][pi] * polin[p4][pi];
                        } catch (Exception ex) {
                            Logger.getLogger(Psi.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }
        this.n1 = n1;
        this.n2 = n2;
        this.n3 = n3;

        this.p = p;
        this.pol = pol;
        this.lambda = lambda;

    }

    public double psi_s_js(int s, int js, double x) {
        double sum = 0;

        for (int i = 0; i <= p[s - 1]; i++) {
            try {
                sum += lambda.getX(s)[js][i] * pol.func(i, x);
            } catch (Exception ex) {
                Logger.getLogger(Psi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return sum;
    }

    public String toString(double number, int s, int js) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i <= p[s - 1]; i++) {
            try {
                str.append("+(" + number * lambda.getX(s)[js][i] + ")" + "T" + i + "(x" + s + js + ")");
            } catch (Exception ex) {
                Logger.getLogger(Psi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return str.toString();
    }

    public String toStringNenorm(double number, int s, int js) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i <= p[s - 1]; i++) {
            try {
                str.append("+(" + number * polinomNenorm[s-1][js][i] + ")" + "(x" + s + (js+1) + "^"+i+")");
            } catch (Exception ex) {
                Logger.getLogger(Psi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return str.toString();
    }

    public String toStringNorm(double number, int s, int js) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i <= p[s - 1]; i++) {
            try {
                str.append("+(" + number * polinomNorm[s-1][js][i] + ")" + "(x" + s + (js+1) + "^"+i+")");
            } catch (Exception ex) {
                Logger.getLogger(Psi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return str.toString();
    }
    
    
    private double[][] polinom(int power, double max,double min) {
        double[][] pol = new double[power + 1][power + 1];
        pol[0][0] = 1;
        if (power > 0) {
            pol[1][0] = -min/(max-min);
            pol[1][1] = 1/(max-min);
        }
        for (int i = 2; i < power + 1; i++) {
            pol[i][0] = -min/(max-min) * pol[i - 1][0];
            for (int j = 1; j < power + 1; j++) {
                pol[i][j] = pol[i - 1][j - 1]/(max-min) - min/(max-min) * pol[i - 1][j];
            }
        }

        return pol;
    }
}
