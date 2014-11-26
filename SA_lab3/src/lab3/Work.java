/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

import Jama.Matrix;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import org.math.plot.Plot2DPanel;

/**
 *
 * @author Vova
 */
public class Work {

    public static final double EPS = 1e-3;

    private double[][][] x;
    private double[][][] xN;
    private double[][] y;
    private double[][] yN;
     private double[][] ln_yN_1;
    private double[][] bq0;
    private int m;
    private int length;
    private int[] p;
    private int[] n;
    private double[] xG;
    private double[][] yG;
    private double[][] yNG;
    private double[] nevyazka;
    private Polinom pol;
    private File openFile;
    private File resultFile;
    private int chooseBQ0;
    private double[][] maxX;
    private double[][] minX;
    private int chooseSolveOfLambda;
    private StringBuilder result;

    public Work(File openFile, File outputFile, int k, int length, int m,
            int[] n, int[] p, Polinom pol, int chooseBQ0, int chooseSolveOfLambda) {
        result = new StringBuilder();
        x = new double[k][][];
        xN = new double[k][][];
        y = new double[m][length];
        for (int i = 0; i < k; i++) {
            x[i] = new double[n[i]][length];
        }
        this.length = length;
        this.m = m;
        this.p = p;
        this.n = n;
        this.pol = pol;
        this.openFile = openFile;
        this.resultFile = outputFile;
        this.chooseBQ0 = chooseBQ0;
        this.chooseSolveOfLambda = chooseSolveOfLambda;
        xG = new double[length];
        for (int i = 0; i < length; i++) {
            xG[i] = i;
        }
    }

    public void drawGraphNorm(int index) {
        JFrame frame = new JFrame("Y" + (index + 1));
        Plot2DPanel plot = new Plot2DPanel("South");
        double[][] z, b;

        z = yNG;
        b = yN;

        plot.addLinePlot("Fi" + (index + 1), xG, z[index]);
        plot.addLinePlot("Y" + (index + 1), xG, b[index]);

        double[] a = {0, 0};
        plot.addLinePlot("Невязка = " + Double.toString(nevyazka[index]), a, a);
        frame.add(plot);
        frame.setVisible(true);
        frame.pack();
        frame.setBounds(0, 0, 800, 600);
    }

    public void drawGraphNenorm(int index) {
        JFrame frame = new JFrame("Y" + (index + 1));
        Plot2DPanel plot = new Plot2DPanel("South");
        double[][] z, b;

        z = yG;
        b = y;

        plot.addLinePlot("Fi" + (index + 1), xG, z[index]);
        plot.addLinePlot("Y" + (index + 1), xG, b[index]);

        double[] a = {0, 0};
        plot.addLinePlot("Невязка = " + Double.toString(nevyazka[index]), a, a);
        frame.add(plot);
        frame.setVisible(true);
        frame.pack();
        frame.setBounds(0, 0, 800, 600);
    }

    public String getResult() {
        return result.toString();
    }

    public void work() {

        Scanner sc = null;
        PrintWriter pw = null;
        try {
            sc = new Scanner(openFile);
            sc.useLocale(Locale.US);
            for (int i = 0; i < length; i++) {
                for (int k = 0; k < x.length; k++) {
                    for (int j = 0; j < x[k].length; j++) {
                        x[k][j][i] = sc.nextDouble();
                    }
                }
                for (int j = 0; j < y.length; j++) {
                    y[j][i] = sc.nextDouble();
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            sc.close();
        }

        //Normalization
        for (int i = 0; i < x.length; i++) {
            xN[i] = norm(x[i]);
        }
        yN = norm(y);
        ln_yN_1 = ln_y_1(yN);
        // Definition of bq0
//        if (chooseBQ0 == 1) {
//            //

            bq0 = ln_yN_1;
//        }
//        if (chooseBQ0 == 2) {
//            bq0 = bq0(0, yN);
//
//        }
//        if (chooseBQ0 == 3) {
//            bq0 = bq01(0, yN);
//
//        }

        maxX = max(x);
        minX = min(x);

        Psi[] psi = new Psi[m];
        // Find lambda
        Matr lambda = null;
         Matr v = null;
        try {
            pw = new PrintWriter(resultFile);

            for (int i = 0; i < m; i++) {
//                    double[] lambda_i_1 = conjGradMethod(f1_1(pol, new int[]{n[0], 0, 0}, new int[]{p[0], 0, 0}, xN[0], xN[1], xN[2]), bq0[i]);                
//                    double[] lambda_i_2 = conjGradMethod(f1_1(pol, new int[]{0, n[1], 0}, new int[]{0, p[1], 0}, xN[0], xN[1], xN[2]), bq0[i]);
//                    double[] lambda_i_3 = conjGradMethod(f1_1(pol, new int[]{0, 0, n[2]}, new int[]{0, 0, p[2]}, xN[0], xN[1], xN[2]), bq0[i]);
//                    double[] lambda_i = new double[lambda_i_1.length + lambda_i_2.length + lambda_i_3.length];
//                    System.arraycopy(lambda_i_1, 0, lambda_i, 0, lambda_i_1.length);
//                    System.arraycopy(lambda_i_2, 0, lambda_i, lambda_i_1.length, lambda_i_2.length);
//                    System.arraycopy(lambda_i_3, 0, lambda_i, lambda_i_1.length + lambda_i_2.length, lambda_i_3.length);
                    lambda = new Matr(n[0], p[0] + 1, n[1], p[1] + 1, n[2], p[2] + 1);//, lambda_i);
                    
                    double[] v_i_1 = conjGradMethod(f1(pol,lambda, new int[]{n[0], 0, 0}, new int[]{p[0], 0, 0}, xN[0], xN[1], xN[2]), bq0[i]);                
                    double[] v_i_2 = conjGradMethod(f1(pol, lambda,new int[]{0, n[1], 0}, new int[]{0, p[1], 0}, xN[0], xN[1], xN[2]), bq0[i]);
                    double[] v_i_3 = conjGradMethod(f1(pol,lambda, new int[]{0, 0, n[2]}, new int[]{0, 0, p[2]}, xN[0], xN[1], xN[2]), bq0[i]);
                    double[] v_i = new double[v_i_1.length + v_i_2.length + v_i_3.length];
                    System.arraycopy(v_i_1, 0, v_i, 0, v_i_1.length);
                    System.arraycopy(v_i_2, 0, v_i, v_i_1.length, v_i_2.length);
                    System.arraycopy(v_i_3, 0, v_i, v_i_1.length + v_i_2.length, v_i_3.length);
                    v = new Matr(n[0], p[0] + 1, n[1], p[1] + 1, n[2], p[2] + 1, v_i);
                    
                    psi[i] = new Psi(n[0], n[1], n[2], p[0], p[1], p[2], pol, lambda, v,  maxX, minX);
                    result.append(v.toString("lambda " + i + ":\r\n"));
                    pw.println(v.toString("lambda " + i + ":\r\n"));
                
            }
            double[][] a1 = new double[m][];
            double[][] a2 = new double[m][];
            double[][] a3 = new double[m][];
            for (int i = 0; i < m; i++) {

                a1[i] = conjGradMethod(f2(psi, xN[0], 1, i), ln_yN_1[i]);
                a2[i] = conjGradMethod(f2(psi, xN[1], 2, i), ln_yN_1[i]);
                a3[i] = conjGradMethod(f2(psi, xN[2], 3, i), ln_yN_1[i]);}

            Matr a = new Matr(a1, a2, a3);
            result.append(a.toString("a\r\n"));
            pw.println(a.toString("a\r\n"));
            Fi fi = new Fi(n[0], n[1], n[2], psi, a);
            double[][] c1 = new double[1][m];
            double[][] c2 = new double[1][m];
            double[][] c3 = new double[1][m];
            for (int i = 0; i < m; i++) {
                double[] c = conjGradMethod(f3(fi, xN[0], xN[1], xN[2], i), ln_yN_1[i]);
                c1[0][i] = c[0];
                c2[0][i] = c[1];
                c3[0][i] = c[2];
            }
            Matr c = new Matr(c1, c2, c3);
            result.append(c.toString("c\r\n"));
            pw.println(c.toString("c\r\n"));
            Fi_i finalFunction = new Fi_i(fi, c);
            double[] maxj = new double[m];
            double[] minj = new double[m];
            for (int i = 0; i < y.length; i++) {
                maxj[i] = y[i][0];
                minj[i] = y[i][0];
                for (int j = 1; j < y[i].length; j++) {
                    if (maxj[i] < y[i][j]) {
                        maxj[i] = y[i][j];
                    } else if (minj[i] > y[i][j]) {
                        minj[i] = y[i][j];
                    }
                }

            }
            for (int i = 0; i < m; i++) {
                for (int s = 1; s < 4; s++) {
                    for (int js = 0; js < n[s - 1]; js++) {
                        result.append("psi" + i + s + js + "=" + psi[i].toString(1, s, js) + "\n");
                        pw.println("psi" + i + s + js + "=" + psi[i].toString(1, s, js));
                    }
                }
            }
            for (int i = 0; i < m; i++) {
                for (int s = 1; s < 4; s++) {
                    result.append("Ф" + i + s + "=" + fi.toString(1, i, s) + "\n");
                    pw.println("Ф" + i + s + "=" + fi.toString(1, i, s));
                }
            }
            for (int i = 0; i < m; i++) {
                result.append("Ф" + i + "=" + finalFunction.toString(i) + "\n");
                pw.println("Ф" + i + "=" + finalFunction.toString(i));

            }
            pw.println("В нормированном виде");
//            for (int i = 0; i < m; i++) {
//                pw.println("Ф" + i + "=" + finalFunction.toStringNorm(i));
//
//            }
//            pw.println("В ненормированном виде");
//            for (int i = 0; i < m; i++) {
//                pw.println("Ф" + i + "=" + finalFunction.toStringNenorm(i, maxj[i] - minj[i]) + "+" + minj);
//
//            }
            double[][] xForCalc = new double[x.length][];
            for (int i = 0; i < x.length; i++) {
                xForCalc[i] = new double[n[i]];
            }
            yG = new double[m][length];
            yNG = new double[m][length];
            nevyazka = new double[m];
            pw.println("нормированные");
            for (int j = 0; j < m; j++) {
                pw.print("Y" + j + "\t\t");
            }
            pw.println();

            for (int i = 0; i < length; i++) {
                for (int k = 0; k < x.length; k++) {
                    for (int j = 0; j < n[k]; j++) {
                        xForCalc[k][j] = xN[k][j][i];
                    }
                }

                for (int j = 0; j < m; j++) {

                    yNG[j][i] = finalFunction.func(j, xForCalc[0], xForCalc[1], xForCalc[2]);

                    if (nevyazka[j] < Math.abs(yNG[j][i] - yN[j][i])) {
                        nevyazka[j] = Math.abs(yNG[j][i] - yN[j][i]);
                    };
                    pw.print(yNG[j][i] + "\t\t");

                }
                pw.println();
            }

            pw.println("без нормировки");
            for (int j = 0; j < m; j++) {
                pw.print("Y" + j + "\t\t");
            }
            pw.println();

            for (int j = 0; j < y[0].length; j++) {
                for (int i = 0; i < y.length; i++) {
                    yG[i][j] = yNG[i][j] * (maxj[i] - minj[i]) + minj[i];
                    pw.print(yG[i][j] + "\t\t");
                }
                pw.println();
            }
            pw.println();
            pw.println("Невязка");

            for (int j = 0; j < y[0].length; j++) {
                for (int i = 0; i < y.length; i++) {

                    pw.print(Math.abs(yNG[i][j] - yN[i][j]) + "\t\t");
                }
                pw.println();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pw.close();
        }
    }

    public void work1() {

        Scanner sc = null;
        PrintWriter pw = null;
        try {
            sc = new Scanner(openFile);
            sc.useLocale(Locale.US);
            for (int i = 0; i < length; i++) {
                for (int k = 0; k < x.length; k++) {
                    for (int j = 0; j < x[k].length; j++) {
                        x[k][j][i] = sc.nextDouble();
                    }
                }
                for (int j = 0; j < y.length; j++) {
                    y[j][i] = sc.nextDouble();
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            sc.close();
        }

        //Normalization
        for (int i = 0; i < x.length; i++) {
            xN[i] = norm(x[i]);
        }
        yN = norm(y);
        ln_yN_1 = ln_f_y_1(yN);
        // Definition of bq0
      //  if (chooseBQ0 == 1) {
            //

            bq0 = ln_yN_1;
      //  }
//        if (chooseBQ0 == 2) {
//            bq0 = bq0(0, yN);
//
//        }
//        if (chooseBQ0 == 3) {
//            bq0 = bq01(0, yN);
//
//        }

        maxX = max(x);
        minX = min(x);

        PsiOur[] psi = new PsiOur[m];
        // Find lambda
        Matr lambda = null;
         Matr v = null;
        try {
            pw = new PrintWriter(resultFile);

            for (int i = 0; i < m; i++) {
//                    double[] lambda_i_1 = conjGradMethod(f1_1(pol, new int[]{n[0], 0, 0}, new int[]{p[0], 0, 0}, xN[0], xN[1], xN[2]), bq0[i]);                
//                    double[] lambda_i_2 = conjGradMethod(f1_1(pol, new int[]{0, n[1], 0}, new int[]{0, p[1], 0}, xN[0], xN[1], xN[2]), bq0[i]);
//                    double[] lambda_i_3 = conjGradMethod(f1_1(pol, new int[]{0, 0, n[2]}, new int[]{0, 0, p[2]}, xN[0], xN[1], xN[2]), bq0[i]);
//                    double[] lambda_i = new double[lambda_i_1.length + lambda_i_2.length + lambda_i_3.length];
//                    System.arraycopy(lambda_i_1, 0, lambda_i, 0, lambda_i_1.length);
//                    System.arraycopy(lambda_i_2, 0, lambda_i, lambda_i_1.length, lambda_i_2.length);
//                    System.arraycopy(lambda_i_3, 0, lambda_i, lambda_i_1.length + lambda_i_2.length, lambda_i_3.length);
                    lambda = new Matr(n[0], p[0] + 1, n[1], p[1] + 1, n[2], p[2] + 1);//, lambda_i);
                    
                    double[] v_i_1 = conjGradMethod(f1_our(pol, new int[]{n[0], 0, 0}, new int[]{p[0], 0, 0}, xN[0], xN[1], xN[2]), bq0[i]);                
                    double[] v_i_2 = conjGradMethod(f1_our(pol,new int[]{0, n[1], 0}, new int[]{0, p[1], 0}, xN[0], xN[1], xN[2]), bq0[i]);
                    double[] v_i_3 = conjGradMethod(f1_our(pol, new int[]{0, 0, n[2]}, new int[]{0, 0, p[2]}, xN[0], xN[1], xN[2]), bq0[i]);
                    double[] v_i = new double[v_i_1.length + v_i_2.length + v_i_3.length];
                    System.arraycopy(v_i_1, 0, v_i, 0, v_i_1.length);
                    System.arraycopy(v_i_2, 0, v_i, v_i_1.length, v_i_2.length);
                    System.arraycopy(v_i_3, 0, v_i, v_i_1.length + v_i_2.length, v_i_3.length);
                    v = new Matr(n[0], p[0] + 1, n[1], p[1] + 1, n[2], p[2] + 1, v_i);
                    
                    psi[i] = new PsiOur(n[0], n[1], n[2], p[0], p[1], p[2], pol, lambda, v,  maxX, minX);
                    result.append(v.toString("lambda " + i + ":\r\n"));
                    pw.println(v.toString("lambda " + i + ":\r\n"));
                
            }
            double[][] a1 = new double[m][];
            double[][] a2 = new double[m][];
            double[][] a3 = new double[m][];
            for (int i = 0; i < m; i++) {

                a1[i] = conjGradMethod(f2_our(psi, xN[0], 1, i), ln_yN_1[i]);
                a2[i] = conjGradMethod(f2_our(psi, xN[1], 2, i), ln_yN_1[i]);
                a3[i] = conjGradMethod(f2_our(psi, xN[2], 3, i), ln_yN_1[i]);}

            Matr a = new Matr(a1, a2, a3);
            result.append(a.toString("a\r\n"));
            pw.println(a.toString("a\r\n"));
            FiOur fi = new FiOur(n[0], n[1], n[2], psi, a);
            double[][] c1 = new double[1][m];
            double[][] c2 = new double[1][m];
            double[][] c3 = new double[1][m];
            for (int i = 0; i < m; i++) {
                double[] c = conjGradMethod(f3_our(fi, xN[0], xN[1], xN[2], i), ln_yN_1[i]);
                c1[0][i] = c[0];
                c2[0][i] = c[1];
                c3[0][i] = c[2];
            }
            Matr c = new Matr(c1, c2, c3);
            result.append(c.toString("c\r\n"));
            pw.println(c.toString("c\r\n"));
            Fi_iOur finalFunction = new Fi_iOur(fi, c);
            double[] maxj = new double[m];
            double[] minj = new double[m];
            for (int i = 0; i < y.length; i++) {
                maxj[i] = y[i][0];
                minj[i] = y[i][0];
                for (int j = 1; j < y[i].length; j++) {
                    if (maxj[i] < y[i][j]) {
                        maxj[i] = y[i][j];
                    } else if (minj[i] > y[i][j]) {
                        minj[i] = y[i][j];
                    }
                }

            }
            for (int i = 0; i < m; i++) {
                for (int s = 1; s < 4; s++) {
                    for (int js = 0; js < n[s - 1]; js++) {
                        result.append("psi" + i + s + js + "=" + psi[i].toString(1, s, js) + "\n");
                        pw.println("psi" + i + s + js + "=" + psi[i].toString(1, s, js));
                    }
                }
            }
            for (int i = 0; i < m; i++) {
                for (int s = 1; s < 4; s++) {
                    result.append("Ф" + i + s + "=" + fi.toString(1, i, s) + "\n");
                    pw.println("Ф" + i + s + "=" + fi.toString(1, i, s));
                }
            }
            for (int i = 0; i < m; i++) {
                result.append("Ф" + i + "=" + finalFunction.toString(i) + "\n");
                pw.println("Ф" + i + "=" + finalFunction.toString(i));

            }
            pw.println("В нормированном виде");
//            for (int i = 0; i < m; i++) {
//                pw.println("Ф" + i + "=" + finalFunction.toStringNorm(i));
//
//            }
//            pw.println("В ненормированном виде");
//            for (int i = 0; i < m; i++) {
//                pw.println("Ф" + i + "=" + finalFunction.toStringNenorm(i, maxj[i] - minj[i]) + "+" + minj);
//
//            }
            double[][] xForCalc = new double[x.length][];
            for (int i = 0; i < x.length; i++) {
                xForCalc[i] = new double[n[i]];
            }
            yG = new double[m][length];
            yNG = new double[m][length];
            nevyazka = new double[m];
            pw.println("нормированные");
            for (int j = 0; j < m; j++) {
                pw.print("Y" + j + "\t\t");
            }
            pw.println();

            for (int i = 0; i < length; i++) {
                for (int k = 0; k < x.length; k++) {
                    for (int j = 0; j < n[k]; j++) {
                        xForCalc[k][j] = xN[k][j][i];
                    }
                }

                for (int j = 0; j < m; j++) {

                    yNG[j][i] = finalFunction.func(j, xForCalc[0], xForCalc[1], xForCalc[2]);

                    if (nevyazka[j] < Math.abs(yNG[j][i] - yN[j][i])) {
                        nevyazka[j] = Math.abs(yNG[j][i] - yN[j][i]);
                    };
                    pw.print(yNG[j][i] + "\t\t");

                }
                pw.println();
            }

            pw.println("без нормировки");
            for (int j = 0; j < m; j++) {
                pw.print("Y" + j + "\t\t");
            }
            pw.println();

            for (int j = 0; j < y[0].length; j++) {
                for (int i = 0; i < y.length; i++) {
                    yG[i][j] = yNG[i][j] * (maxj[i] - minj[i]) + minj[i];
                    pw.print(yG[i][j] + "\t\t");
                }
                pw.println();
            }
            pw.println();
            pw.println("Невязка");

            for (int j = 0; j < y[0].length; j++) {
                for (int i = 0; i < y.length; i++) {

                    pw.print(Math.abs(yNG[i][j] - yN[i][j]) + "\t\t");
                }
                pw.println();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pw.close();
        }
    }
    public double[][] norm(double[][] x) {
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

    private double[] maxXi(double[][] x) {
        double[] max = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            max[i] = x[i][0];
            for (int j = 1; j < x[i].length; j++) {
                if (max[i] < x[i][j]) {
                    max[i] = x[i][j];
                }
            }
        }
        return max;
    }

    public double[][] max(double[][][] x) {
        double[][] max = new double[x.length][];
        for (int i = 0; i < x.length; i++) {
            max[i] = maxXi(x[i]);
        }
        return max;
    }

    private double[] minXi(double[][] x) {
        double[] min = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            min[i] = x[i][0];
            for (int j = 1; j < x[i].length; j++) {
                if (min[i] > x[i][j]) {
                    min[i] = x[i][j];
                }
            }
        }
        return min;
    }

    public double[][] min(double[][][] x) {
        double[][] min = new double[x.length][];
        for (int i = 0; i < x.length; i++) {
            min[i] = minXi(x[i]);
        }
        return min;
    }

    public double[][] bq0(int index, double[][] y) {
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
                bq0[j][i] = Math.log(1 + (maxj + minj) / 2.0);
            }

        }
        return bq0;
    }

    public double[][] bq01(int index, double[][] y) {
        double[][] bq0 = new double[y.length][y[0].length];
        for (int i = 0; i < bq0[0].length; i++) {

            for (int j = 0; j < y.length; j++) {
                bq0[j][i] = Math.log(1 + y[j][i]);
            }

        }
        return bq0;
    }

    public double[] conjGradMethod(double[][] matrix, double[] vector) {
        Matrix a = new Matrix(matrix);//, F.length, F[0].length);
        Matrix b = new Matrix(vector, vector.length);
        Matrix x = new Matrix(matrix.length, 1);
        a = a.transpose();
//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix[0].length; j++) {
//                //if ((Double.isNaN(matrix[i][j]))) {
//                if ((Double.isNaN(a.get(j, i)))) {
//                    System.out.println("hana " + i + " " + j);
//                }
//            }
//        }
        //if (Double.isNaN(a.norm2())) System.out.println("hana");
//        if (Double.isNaN(b.norm2())) {
//            System.out.println("hana");
//        }
        double rsNew = 0;
        Matrix aTa = (a.transpose().times(a));
        Matrix aTb = (a.transpose().times(b));
//        if (Double.isNaN(aTb.norm2())) {
//            System.out.println("hana");
//        }
        Matrix r = aTb.copy();//aTb.minus(aTa.times(x)).copy();
        Matrix p = r.copy();
        double rsOld = (r.transpose().times(r)).get(0, 0);
        Matrix min = x;
        double minNorm = a.times(min).minus(b).normInf();
        for (int i = 0; i < 10e3; i++) {
            Matrix Ap = aTa.times(p);
            if ((p.transpose().times(Ap).get(0, 0)) == 0) {
                System.out.println("asdasfdasfsfgdfg");
            };
            double alpha = rsOld / (p.transpose().times(Ap).get(0, 0));

            //System.out.println((p.transpose().times(Ap).get(0, 0)));
            if (Double.isNaN(alpha)) {
                
                System.out.println(i + " " + rsOld + " " + (p.transpose().times(Ap).get(0, 0)));
                break;
            }
            // if (Double.isNaN(alpha)) System.out.println("asdasfdasfsfgdfg");;
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
        double[] xRes = new double[matrix.length];
        for (int i = 0; i < xRes.length; i++) {
            xRes[i] = min.get(i, 0);
        }
        System.out.println("nevyazka " + minNorm);
        return xRes;
    }

    public double[][] f1(Polinom pol, Matr lambda,int[] n, int[] p, double[][] x1, double[][] x2, double[][] x3) {
        double[][] f1 = new double[n[0] * p[0] + n[1] * p[1] + n[2] * p[2] + n[0] + n[1] + n[2]][x1[0].length];
        int offset = 0;

        for (int j = 0; j < n[0]; j++) {
            for (int k = 0; k <= p[0]; k++) {
                for (int q = 0; q < x1[0].length; q++) {
                    double temp = 1 + lambda.getX1()[j][k]*pol.func(k, x1[j][q]);
                    if (temp < 0) {
                        System.out.println(temp);
                        System.out.println("");
                    }
                    f1[offset][q] = Math.log(Math.abs(1 + lambda.getX1()[j][k]*pol.func(k, x1[j][q]))+10e-6);
                }
                offset++;
            }
        }
        for (int j = 0; j < n[1]; j++) {
            for (int k = 0; k <= p[1]; k++) {
                for (int q = 0; q < x2[0].length; q++) {
                    f1[offset][q] = Math.log(Math.abs(1 + lambda.getX2()[j][k]*pol.func(k, x2[j][q]))+10e-6);
                }
                offset++;
            }
        }
        for (int j = 0; j < n[2]; j++) {
            for (int k = 0; k <= p[2]; k++) {
                for (int q = 0; q < x3[0].length; q++) {
                    f1[offset][q] = Math.log(Math.abs(1 + lambda.getX3()[j][k]*pol.func(k, x3[j][q]))+10e-6);
                }
                offset++;
            }
        }
        return f1;
    }

    public double[][] f1_1(Polinom pol, int[] n, int[] p, double[][] x1, double[][] x2, double[][] x3) {
        double[][] f1 = new double[n[0] * p[0] + n[1] * p[1] + n[2] * p[2] + n[0] + n[1] + n[2]][x1[0].length];
        int offset = 0;

        for (int j = 0; j < n[0]; j++) {
            for (int k = 0; k <= p[0]; k++) {
                for (int q = 0; q < x1[0].length; q++) {
                    //double temp = 1 + pol.func(k, x1[j][q]);
                    //if (temp < 0) {
                     //   System.out.println("dad");
                    //}
                    f1[offset][q] = pol.func(k, x1[j][q]);
                }
                offset++;
            }
        }
        for (int j = 0; j < n[1]; j++) {
            for (int k = 0; k <= p[1]; k++) {
                for (int q = 0; q < x2[0].length; q++) {
                    f1[offset][q] = pol.func(k, x2[j][q]);
                }
                offset++;
            }
        }
        for (int j = 0; j < n[2]; j++) {
            for (int k = 0; k <= p[2]; k++) {
                for (int q = 0; q < x3[0].length; q++) {
                    f1[offset][q] = pol.func(k, x3[j][q]);
                }
                offset++;
            }
        }
        return f1;
    }
    
    public double[][] f2(Psi[] psi, double[][] xi, int s, int i) {
        double[][] f2 = new double[xi.length][xi[0].length];
        for (int k = 0; k < xi.length; k++) {
            for (int j = 0; j < xi[0].length; j++) {
                f2[k][j] = psi[i].ln_psi_s_js_1(s, k, xi[k][j]);
//                if (Double.isInfinite(f2[k][j])) {
//                    System.out.println(f2[k][j]);
//                    System.out.println(psi[i].psi_s_js(s, k, xi[k][j]));
//                    System.out.println("");
//                }
            }
        }
        return f2;
    }

    public double[][] f3(Fi fi, double[][] x1, double[][] x2, double[][] x3, int i) {
        double[][] f3 = new double[3][x1[0].length];
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
            f3[0][q] = fi.ln_func_1(i, 1, x1q);
            f3[1][q] = fi.ln_func_1(i, 2, x2q);
            f3[2][q] = fi.ln_func_1(i, 3, x3q);
        }
        return f3;
    }

    private double[][] ln_y_1(double[][] y) {
        double[][] bq0 = new double[y.length][y[0].length];
        for (int i = 0; i < bq0[0].length; i++) {

            for (int j = 0; j < y.length; j++) {
                bq0[j][i] = Math.log(1 + (y[j][i]));
            }

        }
        return bq0;
    }
private double[][] ln_f_y_1(double[][] y) {
        double[][] bq0 = new double[y.length][y[0].length];
        for (int i = 0; i < bq0[0].length; i++) {

            for (int j = 0; j < y.length; j++) {
                bq0[j][i] = Math.log(1 + Func.func(y[j][i]));
            }

        }
        return bq0;
    }
    
   public double[][] f1_our(Polinom pol, int[] n, int[] p, double[][] x1, double[][] x2, double[][] x3) {
        double[][] f1 = new double[n[0] * p[0] + n[1] * p[1] + n[2] * p[2] + n[0] + n[1] + n[2]][x1[0].length];
        int offset = 0;

        for (int j = 0; j < n[0]; j++) {
            for (int k = 0; k <= p[0]; k++) {
                for (int q = 0; q < x1[0].length; q++) {
                    f1[offset][q] =Math.log(Math.abs(1+ Func.func(pol.func(k, x1[j][q])))+10e-6);
                }
                offset++;
            }
        }
        for (int j = 0; j < n[1]; j++) {
            for (int k = 0; k <= p[1]; k++) {
                for (int q = 0; q < x2[0].length; q++) {
                    f1[offset][q] = Math.log(Math.abs(1+ Func.func(pol.func(k, x2[j][q])))+10e-6);
                    if (!Double.isFinite(f1[offset][q])) {
                        System.out.println(k + " " + x2[j][q]);
                        double y = pol.func(k, x2[j][q]);
                        System.out.println(y                             
                        );
                         System.out.println(Math.asin(pol.func(k, x2[j][q])));
                        throw new IndexOutOfBoundsException();
                    }
                }
                offset++;
            }
        }
        for (int j = 0; j < n[2]; j++) {
            for (int k = 0; k <= p[2]; k++) {
                for (int q = 0; q < x3[0].length; q++) {
                    f1[offset][q] =Math.log(Math.abs(1+  Func.func(pol.func(k, x3[j][q])))+10e-6);
                }
                offset++;
            }
        }
        return f1;
    }

    public double[][] f2_our(PsiOur[] psi, double[][] xi, int s, int i) {
        double[][] f2 = new double[xi.length][xi[0].length];
        for (int k = 0; k < xi.length; k++) {
            for (int j = 0; j < xi[0].length; j++) {
                f2[k][j] = psi[i].ln_psi_s_js_1(s, k, xi[k][j]);
            }
        }
        return f2;
    }

    public double[][] f3_our(FiOur fi, double[][] x1, double[][] x2, double[][] x3, int i) {
        double[][] f3 = new double[3][x1[0].length];
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
            f3[0][q] = fi.ln_func_1(i, 1, x1q);
            f3[1][q] = fi.ln_func_1(i, 2, x2q);
            f3[2][q] = fi.ln_func_1(i, 3, x3q);
        }
        return f3;
    }

}
