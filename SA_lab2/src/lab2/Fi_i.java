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
public class Fi_i {
    private Fi fi;
    private Matr c;

    public Fi_i(Fi fi, Matr c) {
        this.fi = fi;
        this.c = c;
    }
    
    public double func(int i, double[] x1, double[] x2, double[] x3) {
        return c.getX1()[0][i]*fi.func(i, 1, x1) +
               c.getX2()[0][i]*fi.func(i, 2, x2) +
               c.getX3()[0][i]*fi.func(i, 3, x3);
    }
    
    public String toString(int i) {
        StringBuilder str = new StringBuilder();
        for (int s = 1; s < 4; s++) {
            try {
                str.append(fi.toString(c.getX(s)[0][i], i, s));
            } catch (Exception ex) {
                Logger.getLogger(Psi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return str.toString();
    }
    
    public String toStringNenorm(int i, double q) {
        StringBuilder str = new StringBuilder();
        for (int s = 1; s < 4; s++) {
            try {
                str.append(fi.toStringNenorm(q*c.getX(s)[0][i], i, s));
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
