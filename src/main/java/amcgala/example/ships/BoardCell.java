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
package amcgala.example.ships;

import amcgala.framework.camera.AbstractCamera;
import amcgala.framework.camera.Camera;
import amcgala.framework.math.Matrix;
import amcgala.framework.renderer.Color;
import amcgala.framework.renderer.Renderer;
import amcgala.framework.shape.Rectangle2d;
import amcgala.framework.shape.Shape;

/**
 * Ein Feld auf dem Spielbrett. Es speichert den aktuellen Zustand für diese
 * Position auf dem Feld.
 *
 * @author Robert Giacinto
 */
public class BoardCell extends Shape {

    /**
     * Enum mit Zuständen, die eine Zelle annehmen kann.
     */
    public static enum State {

        SELECTED,
        MISS,
        HIT,
        NOTHING
    }
    private double x;
    private double y;
    private double width;
    private double height;
    private State state, previousState;
    /**
     * Speichert das Schiff, das sich auf diesem Feld befinden kann.
     */
    private Ship ship;
    /**
     * Die Grenzen einer Zelle, die gezeichnet werden sollen.
     */
    protected Rectangle2d bounds;

    /**
     * Ein Feld auf dem Spielbrett.
     *
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
        this.state = State.NOTHING;
        this.previousState = State.NOTHING;
    }

    /**
     * Setzt ein Schiffsteil auf dieses Feld.
     *
     * @param ship das Schiffsteil
     */
    public void setShip(Ship ship) {
        this.ship = ship;
        // Zentrieren des Schiffs, falls es kleiner als die Breite einer Zelle sein sollte.
        double diff = (width - ship.width) / 2;
        this.ship.setX(x + diff);
        this.ship.setY(y);
    }

    /**
     * Ändert den Zustand der Zelle. Eine Zelle kann folgende Zustände
     * einnehmen: Miss, Hit, Selected und Nothing. Die Zustände kommen aus dem
     * enum BoardCell.State
     *
     * @param state der neue Zustand
     */
    public void setState(State state) {
        previousState = this.state;
        
        this.state = state;
        if (state == State.HIT) {
            bounds.color = Color.RED;
        } else if (state == State.MISS) {
            bounds.color = Color.BLUE;
        } else if (state == State.SELECTED) {
            bounds.color = Color.GREEN;
        } else if (state == State.NOTHING) {
            bounds.color = Color.BLACK;
        }
    }

    public void recoverPreviousState() {
        this.state = previousState;
        
        if (state == State.HIT) {
            bounds.color = Color.RED;
        } else if (state == State.MISS) {
            bounds.color = Color.BLUE;
        } else if (state == State.SELECTED) {
            bounds.color = Color.GREEN;
        } else if (state == State.NOTHING) {
            bounds.color = Color.BLACK;
        }
    }

    @Override
    public void render(Matrix transformation, Camera camera, Renderer renderer) {
        bounds.render(transformation, camera, renderer);
        if (ship != null) {
            ship.render(transformation, camera, renderer);
        }
    }
}
