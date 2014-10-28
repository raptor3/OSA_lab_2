/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2;

import Jama.Matrix;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author Vova
 */
public class Work {

    public static final double EPS = 1e-3;

    public static double[][] norm(double[][] x) {
        double[][] xn = new double[x.length][x[0].length];
        for (int i = 0; i < x.length; i++) {
            double maxj = x[i][0];
            double minj = x[i][0];
            for (int j = 1; j < x[i].length; j++) {
                if (maxj < x[i][j]) {
                    maxj = x[i][j];
                } else if (minj > x[i][j]) {
                    minj = x[i][j];
                }
            }
            for (int j = 0; j < x[i].length; j++) {
                xn[i][j] = (x[i][j] - minj) / (maxj - minj);
            }
        }
        return xn;
    }

    public static double[][] max(double[][] x1, double[][] x2, double[][] x3) {
        double[][] max = new double[3][];
        max[0] = new double[x1.length];
        max[1] = new double[x2.length];
        max[2] = new double[x3.length];
        for (int i = 0; i < x1.length; i++) {
            max[0][i] = x1[i][0];
            for (int j = 1; j < x1[i].length; j++) {
                if (max[0][i] < x1[i][j]) {
                    max[0][i] = x1[i][j];
                }
            }
        }
        for (int i = 0; i < x2.length; i++) {
            max[1][i] = x2[i][0];
            for (int j = 1; j < x2[i].length; j++) {
                if (max[1][i] < x2[i][j]) {
                    max[1][i] = x2[i][j];
                }
            }
        }
        for (int i = 0; i < x3.length; i++) {
            max[2][i] = x3[i][0];
            for (int j = 1; j < x3[i].length; j++) {
                if (max[2][i] < x3[i][j]) {
                    max[2][i] = x3[i][j];
                }
            }
        }
        return max;
    }

    public static double[][] min(double[][] x1, double[][] x2, double[][] x3) {
        double[][] max = new double[3][];
        max[0] = new double[x1.length];
        max[1] = new double[x2.length];
        max[2] = new double[x3.length];
        for (int i = 0; i < x1.length; i++) {
            max[0][i] = x1[i][0];
            for (int j = 1; j < x1[i].length; j++) {
                if (max[0][i] > x1[i][j]) {
                    max[0][i] = x1[i][j];
                }
            }
        }
        for (int i = 0; i < x2.length; i++) {
            max[1][i] = x2[i][0];
            for (int j = 1; j < x2[i].length; j++) {
                if (max[1][i] > x2[i][j]) {
                    max[1][i] = x2[i][j];
                }
            }
        }
        for (int i = 0; i < x3.length; i++) {
            max[2][i] = x3[i][0];
            for (int j = 1; j < x3[i].length; j++) {
                if (max[2][i] > x3[i][j]) {
                    max[2][i] = x3[i][j];
                }
            }
        }
        return max;
    }

    public static double[][] bq0(int index, double[][] y) {
        double[][] bq0 = new double[y.length][y[0].length];
        for (int i = 0; i < bq0[0].length; i++) {
            double maxj = y[0][i];
            double minj = y[0][i];
            for (int j = 0; j < y.length; j++) {
                if (maxj < y[j][i]) {
                    maxj = y[j][i];
                } else if (minj > y[j][i]) {
                    minj = y[j][i];
                }
            }
            for (int j = 0; j < bq0.length; j++) {
                bq0[j][i] = (maxj + minj) / 2;
            }

        }
        return bq0;
    }

    public static double[][] bq01(int index, double[][] y) {
        double[][] bq0 = new double[y.length][y[0].length];
        for (int i = 0; i < bq0.length; i++) {
            double mid = 0;
            for (int j = 0; j < y.length; j++) {
                mid += y[j][i];
            }
            for (int j = 0; j < bq0.length; j++) {
                bq0[j][i] = mid / y.length;
            }

        }
        return bq0;
    }

    public static Matr minimization(FuncFromMatr func, Matr x) {
        //ArrayList<Matr> points = new ArrayList<Matr>();
        Matr x_k = x;
        Matr x_k1;
        Matr diff;
        Matr direct = x_k;
        double beta = 0;
        int k = 0;
        Matr minusGrad1 = gradient(func, x_k).mult(-1);
        do {
            Matr minusGrad = minusGrad1;
            direct = minusGrad.add(direct.mult(beta));
            x_k1 = next(direct, x_k, func);
            diff = x_k1.add(x_k.mult(-1));
            x_k = x_k1;
            minusGrad1 = gradient(func, x_k).mult(-1);
            k++;
            if (k % 2 == 0) {
                beta = 0;
            } else {
                beta = minusGrad1.norm() / minusGrad.norm();
            }

        } while (diff.norm() >= EPS); //|| Math.abs(func.f(x_k1.getX(), x_k1.getY()) - func.f(x_k.getX(), x_k.getY())) >= EPS);
        return x_k1;
    }

    private static Matr next(Matr direct, Matr x_k, FuncFromMatr func) {
        double EPS = 1e-7;
        double left = 0;
        double right = 100;
        double phi = (1.0 + Math.sqrt(5)) / 2.0;
        do {
            double x1 = right - (right - left) / phi;
            double x2 = left + (right - left) / phi;
            double y1 = func.func(x_k.add(direct.mult(x1)));
            double y2 = func.func(x_k.add(direct.mult(x2)));
            if (y1 >= y2) {
                left = x1;
            } else {
                right = x2;
            }
        } while (Math.abs(right - left) > EPS);
        left = (left + right) / 2.0;
        return x_k.add(direct.mult(left));
    }

    private static Matr gradient(FuncFromMatr function, Matr x_p) {
        double EPS = 1e-7;
        double[][] x;
        if (x_p.getX1().length != 0) {
            x = new double[x_p.getX1().length][x_p.getX1()[0].length];
        } else {
            x = new double[0][0];
        }
        double[][] y;
        double[][] z;
        if (x_p.getX2().length != 0) {
            y = new double[x_p.getX2().length][x_p.getX2()[0].length];
        } else {
            y = new double[0][0];
        }
        if (x_p.getX3().length != 0) {
            z = new double[x_p.getX3().length][x_p.getX3()[0].length];
        } else {
            z = new double[0][0];
        }

        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[0].length; j++) {
                x[i][j] = (function.func(x_p.addToX(EPS, i, j))
                        - function.func(x_p.addToX(-EPS, i, j))) / (2 * EPS);
            }
        }
        for (int i = 0; i < y.length; i++) {
            for (int j = 0; j < y[0].length; j++) {
                y[i][j] = (function.func(x_p.addToY(EPS, i, j))
                        - function.func(x_p.addToY(-EPS, i, j))) / (2 * EPS);
            }
        }
        for (int i = 0; i < z.length; i++) {
            for (int j = 0; j < z[0].length; j++) {
                z[i][j] = (function.func(x_p.addToZ(EPS, i, j))
                        - function.func(x_p.addToZ(-EPS, i, j))) / (2 * EPS);
            }
        }
        return new Matr(x, y, z);
    }

    public static double[] conjGradMethod(double[][] matrix, double[] vector) {
        Matrix a = new Matrix(matrix);//, F.length, F[0].length);
        Matrix b = new Matrix(vector, vector.length);
        Matrix x = new Matrix(matrix.length, 1);
        a = a.transpose();

        double rsNew = 0;
        Matrix aTa = (a.transpose().times(a));
        Matrix aTb = (a.transpose().times(b));

        Matrix r = aTb.minus(aTa.times(x)).copy();
        Matrix p = r.copy();
        double rsOld = (r.transpose().times(r)).get(0, 0);
        Matrix min = x;
        double minNorm = a.times(min).minus(b).normInf();
        for (int i = 0; i < 10e3; i++) {
            Matrix Ap = aTa.times(p);
            double alpha = rsOld / (p.transpose().times(Ap).get(0, 0));
            x = p.times(alpha).plus(x);
            r = r.minus(Ap.times(alpha));
            rsNew = (r.transpose().times(r)).get(0, 0);
            double norm = a.times(x).minus(b).normInf();
            if (norm < minNorm) {
                min = x;
                minNorm = norm;
            }
            if (Math.sqrt(rsNew) < 1e-10) {

                break;
            }

            p = p.times(rsNew / rsOld).plus(r);
            rsOld = rsNew;
        }
        // System.out.println(A.times(x).minus(b).norm1());
        double[] xRes = new double[matrix.length];
        for (int i = 0; i < xRes.length; i++) {
            xRes[i] = min.get(i, 0);
        }
        System.out.println("nevyazka " + minNorm);
        return xRes;
    }

    public static double[][] f_1(Polinom pol, int[] n, int[] p, double[][] x1, double[][] x2, double[][] x3) {
        double[][] f_1 = new double[n[0] * p[0] + n[1] * p[1] + n[2] * p[2] + n[0] + n[1] + n[2]][x1[0].length];
        int offset = 0;

        for (int j = 0; j < n[0]; j++) {
            for (int k = 0; k <= p[0]; k++) {
                for (int q = 0; q < x1[0].length; q++) {
                    f_1[offset][q] = pol.func(k, x1[j][q]);
                }
                offset++;
            }
        }
        for (int j = 0; j < n[1]; j++) {
            for (int k = 0; k <= p[1]; k++) {
                for (int q = 0; q < x2[0].length; q++) {
                    f_1[offset][q] = pol.func(k, x2[j][q]);
                }
                offset++;
            }
        }
        for (int j = 0; j < n[2]; j++) {
            for (int k = 0; k <= p[2]; k++) {
                for (int q = 0; q < x3[0].length; q++) {
                    f_1[offset][q] = pol.func(k, x3[j][q]);
                }
                offset++;
            }
        }
        return f_1;
    }

    public static double[][] f_2(Psi[] psi, double[][] xi, int s, int i) {
        double[][] f_2 = new double[xi.length][xi[0].length];
        for (int k = 0; k < xi.length; k++) {
            for (int j = 0; j < xi[0].length; j++) {
                f_2[k][j] = psi[i].psi_s_js(s, k, xi[k][j]);
            }
        }
        return f_2;
    }

    static double[][] F_3(Fi fi, double[][] x1, double[][] x2, double[][] x3, int i) {
        double[][] F_3 = new double[3][x1[0].length];
        double[] x1q = new double[x1.length];
        double[] x2q = new double[x2.length];
        double[] x3q = new double[x3.length];
        for (int q = 0; q < x1[0].length; q++) {
            for (int k = 0; k < x1.length; k++) {
                x1q[k] = x1[k][q];
            }

            for (int k = 0; k < x2.length; k++) {
                x2q[k] = x2[k][q];
            }

            for (int k = 0; k < x3.length; k++) {
                x3q[k] = x3[k][q];
            }
            F_3[0][q] = fi.func(i, 1, x1q);
            F_3[1][q] = fi.func(i, 2, x2q);
            F_3[2][q] = fi.func(i, 3, x3q);
        }
        return F_3;
    }

    public static double[] Conjugate_gradient_method1(double[][] F, double[] l) {
        Matrix A = new Matrix(F);//, F.length, F[0].length);
        Matrix b = new Matrix(l, l.length);
        Matrix x = new Matrix(F.length, 1);
        A = A.transpose();
        Matrix direct_k = new Matrix(F.length, 1);
        Matrix ATA = (A.transpose().times(A)).copy();
        Matrix ATb = (A.transpose().times(b)).copy();
        x = ATb;
        //ATA.set(0, 0, ATA.get(0, 0) + 700);
        //System.out.println(ATA.det());
        //x = ATA.inverse().times(ATb);
        Matrix min = x;
        double minNorm = A.times(min).minus(b).norm1();
        for (int i = 0; i < F.length * 10; i++) {
            Matrix x_prev = x;
            Matrix direct_k_1 = direct_k;
            Matrix grad = ATA.times(x).minus(ATb);
            double betta_k = grad.transpose().times(ATA.times(direct_k_1)).get(0, 0) / direct_k_1.transpose().times(ATA.times(direct_k_1)).get(0, 0);
            if (i == 0) {
                betta_k = 0;
            }
            direct_k = grad.times(-1).plus(direct_k_1.times(betta_k));
            x = next(ATA, ATb, x, direct_k);
            //System.out.println(Math.sqrt(rsnew));
            //if (A.times(x))
            double norm = A.times(x).minus(b).norm1();
            if (norm < minNorm) {
                min = x;
                minNorm = norm;
            }
            if (Math.sqrt(x.minus(x_prev).norm1()) < 1e-10) {

                break;
            }

        }
        // System.out.println(A.times(x).minus(b).norm1());
        double[] xRes = new double[F.length];
        for (int i = 0; i < xRes.length; i++) {
            xRes[i] = min.get(i, 0);
        }
        return xRes;
    }

    private static Matrix next(Matrix A, Matrix b, Matrix x, Matrix direct) {
        double a = 0;
        double bb = 1;
        double phi = (1 + Math.sqrt(5)) / 2;
        double x1 = 0, x2 = 0, y1, y2;

        while (Math.abs(bb - a) > 1e-10) {
            x1 = bb - (bb - a) / phi;
            x2 = a + (bb - a) / phi;
            Matrix x_1 = x.plus(direct.times(x1));
            Matrix x_2 = x.plus(direct.times(x2));
            y1 = A.times(x_1).transpose().times(x_1).minus(b.transpose().times(x_1)).get(0, 0);
            y2 = A.times(x_2).transpose().times(x_2).minus(b.transpose().times(x_2)).get(0, 0);
            if (y1 >= y2) {
                a = x1;
            } else {
                bb = x2;
            }

        }
        return x.plus(direct.times((x1 + x2) / 2.0));
    }

    static void nevyazka(Psi psi, double[] bq0, double[][] x1, double[][] x2, double[][] x3) {

        double max = 0;
        for (int q = 0; q < bq0.length; q++) {
            double sum = 0;
            for (int js = 0; js < x1.length; js++) {
                sum += psi.psi_s_js(1, js, x1[js][q]);
            }
            for (int js = 0; js < x2.length; js++) {
                sum += psi.psi_s_js(2, js, x2[js][q]);
            }
            for (int js = 0; js < x3.length; js++) {
                sum += psi.psi_s_js(3, js, x3[js][q]);
            }
            double nev = Math.abs(sum - bq0[q]);
            if (max < nev) {
                max = nev;
            }
        }
        System.out.println(max);
    }
}
