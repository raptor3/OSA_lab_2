/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vova
 */
class Fi_1 {
    private Psi[] psi;
    private int[] n;
    private Matr a;

    public Fi_1(int n1, int n2, int n3, Psi[] psi, Matr a) {
        int[] n = {n1,n2,n3};
        this.n = n;
        this.psi = psi;
        this.a = a;
    }
    
    public double func(int i, int s, double[] x) {
        double sum = 0;
        for (int js = 0; js < n[s - 1]; js++) {
            try {
                sum += Math.log(1+a.getX(s)[i][js] *  psi[i].psi_s_js(s, js, x[js]));
            } catch (Exception ex) {
                Logger.getLogger(Fi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return Math.exp(sum) - 1;
    }
    
     public double ln_func_1(int i, int s, double[] x) {
        double sum = 0;
        for (int js = 0; js < n[s - 1]; js++) {
            try {
                sum += Math.log(1+a.getX(s)[i][js] *  psi[i].psi_s_js(s, js, x[js]));
            } catch (Exception ex) {
                Logger.getLogger(Fi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return sum;
    }
    
    public String toString(double number,int i,int s) {
        StringBuilder str = new StringBuilder();
        for (int js = 0; js < n[s - 1]; js++) {
            try {
                str.append(psi[i].toString
        (number*a.getX(s)[i][js], s, js));
            } catch (Exception ex) {
                Logger.getLogger(Psi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return str.toString();
    }
    
    public String toStringNenorm(double number,int i,int s) {
        StringBuilder str = new StringBuilder();
        for (int js = 0; js < n[s - 1]; js++) {
            try {
                str.append(psi[i].toStringNenorm
        (number*a.getX(s)[i][js], s, js));
            } catch (Exception ex) {
                Logger.getLogger(Psi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return str.toString();
    }
    public String toStringNorm(double number,int i,int s) {
        StringBuilder str = new StringBuilder();
        for (int js = 0; js < n[s - 1]; js++) {
            try {
                str.append(psi[i].toStringNorm
        (number*a.getX(s)[i][js], s, js));
            } catch (Exception ex) {
                Logger.getLogger(Psi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return str.toString();
    }
    
}
