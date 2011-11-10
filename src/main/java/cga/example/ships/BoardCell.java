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

import cga.framework.camera.Camera;
import cga.framework.math.Matrix;
import cga.framework.renderer.Renderer;
import cga.framework.shape.Rectangle2d;
import cga.framework.shape.Shape;

/**
 * Ein Feld auf dem Spielbrett. Es speichert den aktuellen Zustand für diese
 * Position auf dem Feld.
 *
 * @author Robert Giacinto
 */
public class BoardCell extends Shape {

    private double x;
    private double y;
    private double width;
    private double height;
    /**
     * Speichert das Schiff, das sich auf diesem Feld befinden kann.
     */
    private Ship ship;
    protected Rectangle2d bounds;

    /**
     * Ein Feld auf dem Spielbrett.
     * @param x die x-Koordinate dieses Feldes
     * @param y die y-Koordinate dieses Feldes
     * @param width die Breite des Feldes
     * @param height die Höhe des Feldes
     */
    public BoardCell(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.bounds = new Rectangle2d(x, y, width, height);
    }

    /**
     * Setzt ein Schiffsteil auf dieses Feld.
     * @param ship das Schiffsteil
     */
    public void setShip(Ship ship) {
        this.ship = ship;
        double diff = (width - ship.width) / 2;
        this.ship.setX(x + diff);
        this.ship.setY(y);
    }

    @Override
    public void render(Matrix transformation, Camera camera, Renderer renderer) {
        bounds.render(transformation, camera, renderer);
        if (ship != null) {
            ship.render(transformation, camera, renderer);
        }
    }
}
