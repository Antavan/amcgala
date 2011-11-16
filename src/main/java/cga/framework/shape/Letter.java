/*
 * Copyright 2011 Cologne University of Applied Sciences Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package cga.framework.shape;

import cga.framework.camera.Camera;
import cga.framework.math.Matrix;
import cga.framework.renderer.Renderer;

/**
 * Ein Vektorbuchstabe, der für Textausgaben auf dem Bildschirm verwendet werden
 * kann.
 *
 * @author Robert Giacinto
 */
public class Letter extends Shape {

    private Line[] lines;
    private double width;
    private double height;
    private BresenhamLine2d[] letterLines;
    private static final double RATIO = 1.5;
    private static final Line[] A = {
        new Line(new Point2d(0, 0), new Point2d(0.4, 1.0)),
        new Line(new Point2d(0.4, 1.0), new Point2d(0.6, 1.0)),
        new Line(new Point2d(0.6, 1.0), new Point2d(1.0, 0)),
        new Line(new Point2d(0.2, 0.5), new Point2d(0.8, 0.5))
    };
    private static final Line[] B = {
        new Line(new Point2d(0, 0), new Point2d(0, 1)),
        new Line(new Point2d(0, 1), new Point2d(0.8, 1)),
        new Line(new Point2d(0.8, 1), new Point2d(1, 0.9)),
        new Line(new Point2d(1, 0.9), new Point2d(1, 0.7)),
        new Line(new Point2d(1, 0.7), new Point2d(0.8, 0.6)),
        new Line(new Point2d(0.8, 0.6), new Point2d(0.2, 0.6)),
        new Line(new Point2d(0.2, 0.6), new Point2d(0.2, 0.5)),
        new Line(new Point2d(0.2, 0.5), new Point2d(0.8, 0.5)),
        new Line(new Point2d(0.8, 0.5), new Point2d(1, 0.4)),
        new Line(new Point2d(1, 0.4), new Point2d(1.0, 0.2)),
        new Line(new Point2d(1.0, 0.2), new Point2d(0.8, 0)),
        new Line(new Point2d(0.8, 0), new Point2d(0, 0))
    };
    private static final Line[] C = {
        new Line(new Point2d(1, 0), new Point2d(0.3, 0)),
        new Line(new Point2d(0.3, 0), new Point2d(0, 0.3)),
        new Line(new Point2d(0, 0.3), new Point2d(0, 0.8)),
        new Line(new Point2d(0, 0.8), new Point2d(0.3, 1)),
        new Line(new Point2d(0.3, 1.0), new Point2d(1.0, 1.0))
    };
    private static final Line[] D = {
        new Line(new Point2d(0, 0), new Point2d(0, 1.0)),
        new Line(new Point2d(0, 1.0), new Point2d(0.7, 1.0)),
        new Line(new Point2d(0.7, 1.0), new Point2d(1.0, 0.7)),
        new Line(new Point2d(1.0, 0.7), new Point2d(1.0, 0.4)),
        new Line(new Point2d(1.0, 0.4), new Point2d(0.7, 0)),
        new Line(new Point2d(0.7, 0), new Point2d(0, 0))
    };
    private static final Line[] E = {
        new Line(new Point2d(1.0, 0), new Point2d(0, 0)),
        new Line(new Point2d(0, 0), new Point2d(0, 1.0)),
        new Line(new Point2d(0, 1.0), new Point2d(1.0, 1.0)),
        new Line(new Point2d(0, 0.5), new Point2d(0.6, 0.5))
    };
    private static final Line[] F = {
        new Line(new Point2d(0, 0), new Point2d(0, 1.0)),
        new Line(new Point2d(0, 1.0), new Point2d(1.0, 1.0)),
        new Line(new Point2d(0, 0.5), new Point2d(0.6, 0.5))
    };
    private static final Line[] G = {
        new Line(new Point2d(1.0, 0), new Point2d(0, 0)),
        new Line(new Point2d(0, 0), new Point2d(0, 1.0)),
        new Line(new Point2d(0, 1.0), new Point2d(1.0, 1.0)),
        new Line(new Point2d(1.0, 0), new Point2d(1.0, 0.5)),
        new Line(new Point2d(1.0, 0.5), new Point2d(0.5, 0.5))
    };
    private static final Line[] H = {
        new Line(new Point2d(1.0, 0), new Point2d(1.0, 1.0)),
        new Line(new Point2d(0, 0), new Point2d(0, 1.0)),
        new Line(new Point2d(0, 0.5), new Point2d(1.0, 0.5))
    };
    private static final Line[] I = {
        new Line(new Point2d(0.5, 0), new Point2d(0.5, 1.0)),
        new Line(new Point2d(0.3, 1.0), new Point2d(0.7, 1.0)),
        new Line(new Point2d(0.3, 0), new Point2d(0.7, 0))
    };
    private static final Line[] J = {
        new Line(new Point2d(0.3, 1.0), new Point2d(0.7, 1.0)),
        new Line(new Point2d(0.7, 1.0), new Point2d(0.5, 0)),
        new Line(new Point2d(0.5, 0), new Point2d(0.3, 0.1))
    };
    private static final Line[] K = {
        new Line(new Point2d(0.0, 0.0), new Point2d(0, 1.0)),
        new Line(new Point2d(1, 1.0), new Point2d(0, 0.2)),
        new Line(new Point2d(0.4, 0.5), new Point2d(1, 0))
    };
    private static final Line[] L = {
        new Line(new Point2d(0.0, 1.0), new Point2d(0, 0.0)),
        new Line(new Point2d(0, 0.0), new Point2d(1.0, 0))
    };
    private static final Line[] M = {
        new Line(new Point2d(0.0, 0.0), new Point2d(0.2, 1.0)),
        new Line(new Point2d(0.2, 1.0), new Point2d(0.5, 0.4)),
        new Line(new Point2d(0.5, 0.4), new Point2d(0.8, 1.0)),
        new Line(new Point2d(0.8, 1.0), new Point2d(1.0, 0.0))
    };
    private static final Line[] N = {
        new Line(new Point2d(0.0, 0.0), new Point2d(0.4, 1.0)),
        new Line(new Point2d(0.4, 1.0), new Point2d(0.6, 0.0)),
        new Line(new Point2d(0.6, 0.0), new Point2d(1.0, 1.0))
    };
    private static final Line[] O = {
        new Line(new Point2d(0.0, 0.0), new Point2d(0.0, 1.0)),
        new Line(new Point2d(0.0, 1.0), new Point2d(1.0, 1.0)),
        new Line(new Point2d(1.0, 1.0), new Point2d(1.0, 0.0)),
        new Line(new Point2d(1.0, 0.0), new Point2d(0.0, 0.0))
    };
    private static final Line[] P = {
        new Line(new Point2d(0.0, 0.0), new Point2d(0.0, 1.0)),
        new Line(new Point2d(0.0, 1.0), new Point2d(1.0, 1.0)),
        new Line(new Point2d(1.0, 1.0), new Point2d(1.0, 0.5)),
        new Line(new Point2d(1.0, 0.5), new Point2d(0.0, 0.5))
    };
    private static final Line[] Q = {
        new Line(new Point2d(0.0, 0.0), new Point2d(0.0, 1.0)),
        new Line(new Point2d(0.0, 1.0), new Point2d(1.0, 1.0)),
        new Line(new Point2d(1.0, 1.0), new Point2d(1.0, 0.0)),
        new Line(new Point2d(1.0, 0.0), new Point2d(0.0, 0.0)),
        new Line(new Point2d(0.5, 0.5), new Point2d(1.1, 0.0))
    };
    private static final Line[] R = {
        new Line(new Point2d(0.0, 0.0), new Point2d(0.0, 1.0)),
        new Line(new Point2d(0.0, 1.0), new Point2d(1.0, 1.0)),
        new Line(new Point2d(1.0, 1.0), new Point2d(1.0, 0.5)),
        new Line(new Point2d(1.0, 0.5), new Point2d(0.0, 0.5)),
        new Line(new Point2d(0.0, 0.5), new Point2d(1.0, 0.0))
    };
    private static final Line[] S = {
        new Line(new Point2d(1.0, 1.0), new Point2d(0.0, 1.0)),
        new Line(new Point2d(0.0, 1.0), new Point2d(0.0, 0.5)),
        new Line(new Point2d(0.0, 0.5), new Point2d(1.0, 0.5)),
        new Line(new Point2d(1.0, 0.5), new Point2d(1.0, 0.0)),
        new Line(new Point2d(1.0, 0.0), new Point2d(0.0, 0.0))
    };
    private static final Line[] T = {
        new Line(new Point2d(0.0, 1.0), new Point2d(1.0, 1.0)),
        new Line(new Point2d(0.5, 1.0), new Point2d(0.5, 0.0))
    };
    private static final Line[] U = {
        new Line(new Point2d(0.0, 1.0), new Point2d(0.0, 0.0)),
        new Line(new Point2d(0.0, 0.0), new Point2d(1.0, 0.0)),
        new Line(new Point2d(1.0, 0.0), new Point2d(1.0, 1.0))
    };
    private static final Line[] V = {
        new Line(new Point2d(0.0, 1.0), new Point2d(0.5, 0.0)),
        new Line(new Point2d(0.5, 0.0), new Point2d(1.0, 1.0))
    };
    private static final Line[] W = {
        new Line(new Point2d(0.0, 1.0), new Point2d(0.4, 0.0)),
        new Line(new Point2d(0.4, 0.0), new Point2d(0.5, 0.4)),
        new Line(new Point2d(0.5, 0.4), new Point2d(0.6, 0.0)),
        new Line(new Point2d(0.6, 0.0), new Point2d(1.0, 1.0))
    };
    private static final Line[] X = {
        new Line(new Point2d(0.0, 1.0), new Point2d(1.0, 0.0)),
        new Line(new Point2d(0.0, 0.0), new Point2d(1.0, 1.0))
    };
    private static final Line[] Y = {
        new Line(new Point2d(0.0, 0.0), new Point2d(0.5, 0.5)),
        new Line(new Point2d(0.5, 0.5), new Point2d(1.0, 1.0)),
        new Line(new Point2d(0.5, 0.5), new Point2d(0.5, 0.0))
    };
    private static final Line[] Z = {
        new Line(new Point2d(0.0, 0.0), new Point2d(1.0, 0.0)),
        new Line(new Point2d(0.0, 0.0), new Point2d(1.0, 1.0)),
        new Line(new Point2d(1.0, 1.0), new Point2d(0.0, 1.0))
    };
    private static final Line[] BLANK = {};

    /**
     * Gibt ein Vektor-A zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe A
     */
    public static Letter getA(double width) {
        return new Letter(width, A);
    }

    /**
     * Gibt ein Vektor-B zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe B
     */
    public static Letter getB(double width) {
        return new Letter(width, B);
    }

    /**
     * Gibt ein Vektor-C zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe C
     */
    public static Letter getC(double width) {
        return new Letter(width, C);
    }

    /**
     * Gibt ein Vektor-D zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe D
     */
    public static Letter getD(double width) {
        return new Letter(width, D);
    }

    /**
     * Gibt ein Vektor-E zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe E
     */
    public static Letter getE(double width) {
        return new Letter(width, E);
    }

    /**
     * Gibt ein Vektor-F zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe F
     */
    public static Letter getF(double width) {
        return new Letter(width, F);
    }

    /**
     * Gibt ein Vektor-G zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe G
     */
    public static Letter getG(double width) {
        return new Letter(width, G);
    }

    /**
     * Gibt ein Vektor-H zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe H
     */
    public static Letter getH(double width) {
        return new Letter(width, H);
    }

    /**
     * Gibt ein Vektor-I zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe I
     */
    public static Letter getI(double width) {
        return new Letter(width, I);
    }

    /**
     * Gibt ein Vektor-J zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe J
     */
    public static Letter getJ(double width) {
        return new Letter(width, J);
    }

    /**
     * Gibt ein Vektor-K zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe K
     */
    public static Letter getK(double width) {
        return new Letter(width, K);
    }

    /**
     * Gibt ein Vektor-L zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe L
     */
    public static Letter getL(double width) {
        return new Letter(width, L);
    }

    /**
     * Gibt ein Vektor-M zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe M
     */
    public static Letter getM(double width) {
        return new Letter(width, M);
    }

    /**
     * Gibt ein Vektor-N zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe N
     */
    public static Letter getN(double width) {
        return new Letter(width, N);
    }

    /**
     * Gibt ein Vektor-O zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe O
     */
    public static Letter getO(double width) {
        return new Letter(width, O);
    }

    /**
     * Gibt ein Vektor-P zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe P
     */
    public static Letter getP(double width) {
        return new Letter(width, P);
    }

    /**
     * Gibt ein Vektor-Q zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe Q
     */
    public static Letter getQ(double width) {
        return new Letter(width, Q);
    }

    /**
     * Gibt ein Vektor-R zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe R
     */
    public static Letter getR(double width) {
        return new Letter(width, R);
    }

    /**
     * Gibt ein Vektor-S zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe S
     */
    public static Letter getS(double width) {
        return new Letter(width, S);
    }

    /**
     * Gibt ein Vektor-T zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe T
     */
    public static Letter getT(double width) {
        return new Letter(width, T);
    }

    /**
     * Gibt ein Vektor-U zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe U
     */
    public static Letter getU(double width) {
        return new Letter(width, U);
    }

    /**
     * Gibt ein Vektor-V zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe V
     */
    public static Letter getV(double width) {
        return new Letter(width, V);
    }

    /**
     * Gibt ein Vektor-W zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe W
     */
    public static Letter getW(double width) {
        return new Letter(width, W);
    }

    /**
     * Gibt ein Vektor-X zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe X
     */
    public static Letter getX(double width) {
        return new Letter(width, X);
    }

    /**
     * Gibt ein Vektor-Y zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe Y
     */
    public static Letter getY(double width) {
        return new Letter(width, Y);
    }

    /**
     * Gibt ein Vektor-Z zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe Z
     */
    public static Letter getZ(double width) {
        return new Letter(width, Z);
    }

    /**
     * Gibt ein Leerzeichen zurück.
     *
     * @param width die Breite des Buchstaben
     * @return das Leerzeichen
     */
    public static Letter getBlank(double width) {
        return new Letter(width, BLANK);
    }

    /**
     * Gibt ein Vektor-A zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe A
     */
    public static Letter getA(double x, double y, double width) {
        return new Letter(x, y, width, A);
    }

    /**
     * Gibt ein Vektor-B zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe B
     */
    public static Letter getB(double x, double y, double width) {
        return new Letter(x, y, width, B);
    }

    /**
     * Gibt ein Vektor-C zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe C
     */
    public static Letter getC(double x, double y, double width) {
        return new Letter(x, y, width, C);
    }

    /**
     * Gibt ein Vektor-D zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe D
     */
    public static Letter getD(double x, double y, double width) {
        return new Letter(x, y, width, D);
    }

    /**
     * Gibt ein Vektor-E zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe E
     */
    public static Letter getE(double x, double y, double width) {
        return new Letter(x, y, width, E);
    }

    /**
     * Gibt ein Vektor-F zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe F
     */
    public static Letter getF(double x, double y, double width) {
        return new Letter(x, y, width, F);
    }

    /**
     * Gibt ein Vektor-G zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe G
     */
    public static Letter getG(double x, double y, double width) {
        return new Letter(x, y, width, G);
    }

    /**
     * Gibt ein Vektor-H zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe H
     */
    public static Letter getH(double x, double y, double width) {
        return new Letter(x, y, width, H);
    }

    /**
     * Gibt ein Vektor-I zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe I
     */
    public static Letter getI(double x, double y, double width) {
        return new Letter(x, y, width, I);
    }

    /**
     * Gibt ein Vektor-J zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe J
     */
    public static Letter getJ(double x, double y, double width) {
        return new Letter(x, y, width, J);
    }

    /**
     * Gibt ein Vektor-K zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe K
     */
    public static Letter getK(double x, double y, double width) {
        return new Letter(x, y, width, K);
    }

    /**
     * Gibt ein Vektor-L zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe L
     */
    public static Letter getL(double x, double y, double width) {
        return new Letter(x, y, width, L);
    }

    /**
     * Gibt ein Vektor-M zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe M
     */
    public static Letter getM(double x, double y, double width) {
        return new Letter(x, y, width, M);
    }

    /**
     * Gibt ein Vektor-N zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe N
     */
    public static Letter getN(double x, double y, double width) {
        return new Letter(x, y, width, N);
    }

    /**
     * Gibt ein Vektor-O zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe O
     */
    public static Letter getO(double x, double y, double width) {
        return new Letter(x, y, width, O);
    }

    /**
     * Gibt ein Vektor-P zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe P
     */
    public static Letter getP(double x, double y, double width) {
        return new Letter(x, y, width, P);
    }

    /**
     * Gibt ein Vektor-Q zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe Q
     */
    public static Letter getQ(double x, double y, double width) {
        return new Letter(x, y, width, Q);
    }

    /**
     * Gibt ein Vektor-R zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe R
     */
    public static Letter getR(double x, double y, double width) {
        return new Letter(x, y, width, R);
    }

    /**
     * Gibt ein Vektor-S zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe S
     */
    public static Letter getS(double x, double y, double width) {
        return new Letter(x, y, width, S);
    }

    /**
     * Gibt ein Vektor-T zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe T
     */
    public static Letter getT(double x, double y, double width) {
        return new Letter(x, y, width, T);
    }

    /**
     * Gibt ein Vektor-U zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe U
     */
    public static Letter getU(double x, double y, double width) {
        return new Letter(x, y, width, U);
    }

    /**
     * Gibt ein Vektor-V zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe V
     */
    public static Letter getV(double x, double y, double width) {
        return new Letter(x, y, width, V);
    }

    /**
     * Gibt ein Vektor-W zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe W
     */
    public static Letter getW(double x, double y, double width) {
        return new Letter(x, y, width, W);
    }

    /**
     * Gibt ein Vektor-X zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe X
     */
    public static Letter getX(double x, double y, double width) {
        return new Letter(x, y, width, X);
    }

    /**
     * Gibt ein Vektor-Y zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe Y
     */
    public static Letter getY(double x, double y, double width) {
        return new Letter(x, y, width, Y);
    }

    /**
     * Gibt ein Vektor-Z zurück.
     *
     * @param width die Breite des Buchstaben
     * @return der Buchstabe Z
     */
    public static Letter getZ(double x, double y, double width) {
        return new Letter(x, y, width, Z);
    }

    /**
     * Gibt ein Leerzeichen zurück.
     *
     * @param width die Breite des Buchstaben
     * @return das Leerzeichen
     */
    public static Letter getBlank(double x, double y, double width) {
        return new Letter(x, y, width, BLANK);
    }

    private Letter() {
    }

    private Letter(double width, Line... lines) {
        this.lines = lines;
        this.width = width;
        this.height = RATIO * width;
        letterLines = new BresenhamLine2d[lines.length];

        for (int i = 0; i < lines.length; i++) {
            letterLines[i] = lines[i].getLine(width, height);
        }
    }

    private Letter(double x, double y, double width, Line... lines) {
        this.lines = lines;
        this.width = width;
        this.height = RATIO * width;
        letterLines = new BresenhamLine2d[lines.length];

        for (int i = 0; i < lines.length; i++) {
            letterLines[i] = lines[i].getLine(x, y, width, height);
        }
    }

    @Override
    public void render(Matrix transformation, Camera camera, Renderer renderer) {
        for (BresenhamLine2d bl : letterLines) {
            bl.render(transformation, camera, renderer);
        }
    }
}
