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
package cga.example.ships;

import cga.framework.camera.AbstractCamera;
import cga.framework.camera.Camera;
import cga.framework.math.Matrix;
import cga.framework.renderer.Renderer;
import cga.framework.shape.Shape;

/**
 * Das Spielbrett.
 *
 * @author Robert Giacinto
 */
public class Board extends Shape {

    private int size;
    private double x;
    private double y;
    private double width;
    private double height;
    private double distance;
    private BoardCell[][] boardArray;
    private int cursorX, cursorY;

    /**
     * Erstellt ein Standardboard für das Spiel Schiffe Versenken. Größe 6,
     * Feldbreite 35 und Feldabstand von 2 Pixel.
     */
    public Board() {
        this(0, 0, 6, 35, 35, 2);
    }

    /**
     * Erstellt ein Standardboard an der Position (x,y)
     *
     * @param x die x-Position des Spielfeldes
     * @param y die y-Position des Spielfeldes
     */
    public Board(double x, double y) {
        this(x, y, 6, 35, 35, 2);
    }

    /**
     * Erstellt ein Spielfeld für das Spiel Schiffe Versenken.
     *
     * @param x die x-Koordinate der Spielfeldposition
     * @param y die y-Koordinate der Spielfeldposition
     * @param size die Größe des quadratischen Spielfelds
     * @param width die Breite einer Zelle
     * @param height die Höhe einer Zelle
     * @param distance der Abstand zwischen den Zellen
     */
    public Board(double x, double y, int size, double width, double height, double distance) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.width = width;
        this.height = height;
        this.distance = distance;
        initBoard(size, x, width, distance, y, height);
        placeShips(width, height);
    }

    /**
     *  /**
     * Initialisiert das Spielfeld.
     *
     * @param size die Anzahl der Zellen in einer Reihe des quadratischen
     * Spielfeldes
     * @param x die x-Position des Spielfeldes
     * @param width die Breite einer Spielfeldzelle
     * @param distance der Abstand zwischen den Zellen
     * @param y die y-Position des Spielfeldes
     * @param height die Höhe einer Spielfeldzelle
     */
    private void initBoard(int size, double x, double width, double distance, double y, double height) {
        boardArray = new BoardCell[size][size];
        for (int i = 0; i < boardArray.length; i++) {
            for (int k = 0; k < boardArray[0].length; k++) {
                boardArray[i][k] = new BoardCell(x + i * (width + distance), y + k * (height + distance), width, height);
            }
        }
    }

    /**
     * Platziert Schiffe auf dem Spielfeld.
     *
     * @param width die Breite einer Zelle
     * @param height die Höhe einer Zelle
     */
    private void placeShips(double width, double height) {
        Ship[] ship = ShipFactory.createShip2(width - 10, height, Orientation.VERTICAL);
        boardArray[2][1].setShip(ship[0]);
        boardArray[2][0].setShip(ship[1]);
    }

    @Override
    public void render(Matrix transformation, Camera camera, Renderer renderer) {
        for (int i = 0; i < boardArray.length; i++) {
            for (int k = 0; k < boardArray[0].length; k++) {
                boardArray[i][k].render(transformation, camera, renderer);
            }
        }
    }

    public void moveRight() {
        if (cursorX + 1 < size) {
            cursorX++;
        }
        boardArray[cursorX][cursorY].setState(BoardCell.State.SELECTED);
        if (cursorX > 0) {
            boardArray[cursorX - 1][cursorY].recoverPreviousState();
        }
    }

    public void moveLeft() {
        if (cursorX - 1 > -1) {
            cursorX--;
        }
        boardArray[cursorX][cursorY].setState(BoardCell.State.SELECTED);
        if (cursorX < size - 1) {
            boardArray[cursorX + 1][cursorY].recoverPreviousState();
        }
    }
}
