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
public class Matr {

    private double[][] x1;
    private double[][] x2;
    private double[][] x3;

    public Matr(int n, int m, int n2, int m2, int n3, int m3) {
        x1 = new double[n][m];
        x2 = new double[n2][m2];
        x3 = new double[n3][m3];
         for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                x1[i][j] = 1;
            }
        }
        for (int i = 0; i < n2; i++) {
            for (int j = 0; j < m2; j++) {
                x2[i][j] = 1;
            }
        }
        for (int i = 0; i < n3; i++) {
            for (int j = 0; j < m3; j++) {
                x3[i][j] = 1;
            }
        }
    }

    public Matr(int n, int m, int n2, int m2, int n3, int m3, double[] x) {
        x1 = new double[n][m];
        x2 = new double[n2][m2];
        x3 = new double[n3][m3];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                x1[i][j] = x[i * m + j];
            }
        }
        for (int i = 0; i < n2; i++) {
            for (int j = 0; j < m2; j++) {
                x2[i][j] = x[i * m2 + j + n * m];
            }
        }
        for (int i = 0; i < n3; i++) {
            for (int j = 0; j < m3; j++) {
                x3[i][j] = x[i * m3 + j + n * m + n2 * m2];
            }
        }
    }

    public Matr(double[][] x, double[][] y, double[][] z) {
        x1 = x;
        x2 = y;
        x3 = z;
    }

    public Matr add(Matr a) {
        double[][] x;
        if (x1.length != 0) {
            x = new double[x1.length][x1[0].length];
        } else {
            x = new double[0][0];
        }
        double[][] y;
        double[][] z;
        if (x2.length != 0) {
            y = new double[x2.length][x2[0].length];
        } else {
            y = new double[0][0];
        }
        if (x3.length != 0) {
            z = new double[x3.length][x3[0].length];
        } else {
            z = new double[0][0];
        }
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[0].length; j++) {
                x[i][j] = x1[i][j] + a.x1[i][j];
            }
        }
        for (int i = 0; i < x2.length; i++) {
            for (int j = 0; j < x2[0].length; j++) {
                y[i][j] = x2[i][j] + a.x2[i][j];
            }
        }
        for (int i = 0; i < x3.length; i++) {
            for (int j = 0; j < x3[0].length; j++) {
                z[i][j] = x3[i][j] + a.x3[i][j];
            }
        }
        return new Matr(x, y, z);
    }

    public Matr mult(double a) {
        double[][] x;
        if (x1.length != 0) {
            x = new double[x1.length][x1[0].length];
        } else {
            x = new double[0][0];
        }
        double[][] y;
        double[][] z;
        if (x2.length != 0) {
            y = new double[x2.length][x2[0].length];
        } else {
            y = new double[0][0];
        }
        if (x3.length != 0) {
            z = new double[x3.length][x3[0].length];
        } else {
            z = new double[0][0];
        }
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[0].length; j++) {
                x[i][j] = x1[i][j] * a;
            }
        }
        for (int i = 0; i < x2.length; i++) {
            for (int j = 0; j < x2[0].length; j++) {
                y[i][j] = x2[i][j] * a;
            }
        }
        for (int i = 0; i < x3.length; i++) {
            for (int j = 0; j < x3[0].length; j++) {
                z[i][j] = x3[i][j] * a;
            }
        }
        return new Matr(x, y, z);
    }

    public Matr addToX(double a, int xi, int xj) {
        double[][] x;
        if (x1.length != 0) {
            x = new double[x1.length][x1[0].length];
        } else {
            x = new double[0][0];
        }
        double[][] y;
        double[][] z;
        if (x2.length != 0) {
            y = new double[x2.length][x2[0].length];
        } else {
            y = new double[0][0];
        }
        if (x3.length != 0) {
            z = new double[x3.length][x3[0].length];
        } else {
            z = new double[0][0];
        }
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[0].length; j++) {
                x[i][j] = x1[i][j];
            }
        }
        for (int i = 0; i < x2.length; i++) {
            for (int j = 0; j < x2[0].length; j++) {
                y[i][j] = x2[i][j];
            }
        }
        for (int i = 0; i < x3.length; i++) {
            for (int j = 0; j < x3[0].length; j++) {
                z[i][j] = x3[i][j];
            }
        }
        if (xi < x.length) {
            x[xi][xj] += a;
        }
        return new Matr(x, y, z);
    }

    public Matr addToZ(double a, int xi, int xj) {
        double[][] x;
        if (x1.length != 0) {
            x = new double[x1.length][x1[0].length];
        } else {
            x = new double[0][0];
        }
        double[][] y;
        double[][] z;
        if (x2.length != 0) {
            y = new double[x2.length][x2[0].length];
        } else {
            y = new double[0][0];
        }
        if (x3.length != 0) {
            z = new double[x3.length][x3[0].length];
        } else {
            z = new double[0][0];
        }
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[0].length; j++) {
                x[i][j] = x1[i][j];
            }
        }
        for (int i = 0; i < x2.length; i++) {
            for (int j = 0; j < x2[0].length; j++) {
                y[i][j] = x2[i][j];
            }
        }
        for (int i = 0; i < x3.length; i++) {
            for (int j = 0; j < x3[0].length; j++) {
                z[i][j] = x3[i][j];
            }
        }
        if (xi < z.length) {
            z[xi][xj] += a;
        }
        return new Matr(x, y, z);
    }

    public Matr addToY(double a, int xi, int xj) {
        double[][] x;
        if (x1.length != 0) {
            x = new double[x1.length][x1[0].length];
        } else {
            x = new double[0][0];
        }
        double[][] y;
        double[][] z;
        if (x2.length != 0) {
            y = new double[x2.length][x2[0].length];
        } else {
            y = new double[0][0];
        }
        if (x3.length != 0) {
            z = new double[x3.length][x3[0].length];
        } else {
            z = new double[0][0];
        }
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[0].length; j++) {
                x[i][j] = x1[i][j];
            }
        }
        for (int i = 0; i < x2.length; i++) {
            for (int j = 0; j < x2[0].length; j++) {
                y[i][j] = x2[i][j];
            }
        }
        for (int i = 0; i < x3.length; i++) {
            for (int j = 0; j < x3[0].length; j++) {
                z[i][j] = x3[i][j];
            }
        }
        if (xi < y.length) {
            y[xi][xj] += a;
        }
        return new Matr(x, y, z);
    }

    public double[][] getX1() {
        return x1;
    }

    public void setX1(double[][] x1) {
        this.x1 = x1;
    }

    public double[][] getX2() {
        return x2;
    }

    public void setX2(double[][] x2) {
        this.x2 = x2;
    }

    public double[][] getX3() {
        return x3;
    }

    public void setX3(double[][] x3) {
        this.x3 = x3;
    }

    public double[][] getX(int s) throws Exception {
        switch (s) {
            case 1:
                return x1;
            case 2:
                return x2;
            case 3:
                return x3;
            default:
                throw new Exception();
        }
    }

    public double norm() {
        double norm = 0;
        for (int i = 0; i < x1.length; i++) {
            for (int j = 0; j < x1[0].length; j++) {
                norm += x1[i][j] * x1[i][j];
            }
        }
        for (int i = 0; i < x2.length; i++) {
            for (int j = 0; j < x2[0].length; j++) {
                norm += x2[i][j] * x2[i][j];
            }
        }
        for (int i = 0; i < x3.length; i++) {
            for (int j = 0; j < x3[0].length; j++) {
                norm += x3[i][j] * x3[i][j];
            }
        }
        return Math.sqrt(norm);
    }

    public String toString(String name) {
        StringBuilder str = new StringBuilder(name);
        for (int i = 1; i < 4; i++) {
            double[][] x = null;
            try {
                x = getX(i);
            } catch (Exception ex) {
                Logger.getLogger(Matr.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (int q = 0; q < x.length; q++) {
                for (int j = 0; j < x[0].length; j++) {
                    str.append(String.format("%.4f \t",x[q][j]));
                }
                str.append("\r\n");
            }
            str.append("\r\n");
        }
        return str.toString();
    }
}
