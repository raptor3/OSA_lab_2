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
public class FiOur {

    private PsiOur[] psi;
    private int[] n;
    private Matr a;

    public FiOur(int n1, int n2, int n3, PsiOur[] psi, Matr a) {
        int[] n = {n1,n2,n3};
        this.n = n;
        this.psi = psi;
        this.a = a;
    }
    
    public double func(int i, int s, double[] x) {
        //double sum = 0;
        double sum = 1;
        for (int js = 0; js < n[s - 1]; js++) {
            try {
               // sum += a.getX(s)[i][js] *  Math.log(1+Func.func(psi[i].psi_s_js(s, js, x[js])));
                sum *= Math.pow(1+Func.func(psi[i].psi_s_js(s, js, x[js])), a.getX(s)[i][js]);
            } catch (Exception ex) {
                Logger.getLogger(FiOur.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //return Math.exp(sum) - 1;
        return sum - 1;
    }
    
     public double ln_func_1(int i, int s, double[] x) {
        double sum = 0;
        for (int js = 0; js < n[s - 1]; js++) {
            try {
                sum += a.getX(s)[i][js] * psi[i].ln_psi_s_js_1(s, js, x[js]);
            } catch (Exception ex) {
                Logger.getLogger(FiOur.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return sum;
    }
    
    public String toString(double number,int i,int s) {
        StringBuilder str = new StringBuilder("+exp(");
        for (int js = 0; js < n[s - 1]; js++) {
            try {
                str.append("+"+a.getX(s)[i][js]+"*ln(1+"+psi[i].toString
        (1, s, js)+")");
            } catch (Exception ex) {
                Logger.getLogger(Psi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        str.append(")-1");
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
