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
public class Fi_iOur {

    private FiOur fi;
    private Matr c;

    public Fi_iOur(FiOur fi, Matr c) {
        this.fi = fi;
        this.c = c;
    }

    public double func(int i, double[] x1, double[] x2, double[] x3) {
        return Func_1.func(Math.exp(c.getX1()[0][i] * fi.ln_func_1(i, 1, x1)
                + c.getX2()[0][i] * fi.ln_func_1(i, 2, x2)
                + c.getX3()[0][i] * fi.ln_func_1(i, 3, x3)) - 1);
    /*    return Math.pow(1+Func.func(fi.func(i, 1, x1)), c.getX1()[0][i]) * 
                Math.pow(1+Func.func(fi.func(i, 2, x2)), c.getX2()[0][i]) *                
                Math.pow(1+Func.func(fi.func(i, 3, x3)), c.getX3()[0][i]) - 1;
*/
    }

    public double ln_func_1(int i, double[] x1, double[] x2, double[] x3) {
        return c.getX1()[0][i] * fi.ln_func_1(i, 1, x1)
                + c.getX2()[0][i] * fi.ln_func_1(i, 2, x2)
                + c.getX3()[0][i] * fi.ln_func_1(i, 3, x3);

    }

    public String toString(int i) {
        StringBuilder str = new StringBuilder("sin(exp(");
        for (int s = 1; s < 4; s++) {
            try {
                str.append("+"+c.getX(s)[0][i]+"*ln(1+"+fi.toString(1, i, s)+")");
            } catch (Exception ex) {
                Logger.getLogger(Psi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        str.append(")-1)");
        return str.toString();
    }

    public String toStringNenorm(int i, double q) {
        StringBuilder str = new StringBuilder();
        for (int s = 1; s < 4; s++) {
            try {
                str.append(fi.toStringNenorm(q * c.getX(s)[0][i], i, s));
            } catch (Exception ex) {
                Logger.getLogger(Psi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return str.toString();
    }

    public String toStringNorm(int i) {
        StringBuilder str = new StringBuilder();
        for (int s = 1; s < 4; s++) {
            try {
                str.append(fi.toStringNorm(c.getX(s)[0][i], i, s));
            } catch (Exception ex) {
                Logger.getLogger(Psi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return str.toString();
    }

}
