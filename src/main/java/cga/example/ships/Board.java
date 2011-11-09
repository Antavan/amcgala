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
import cga.framework.shape.Shape;

/**
 * Das
 * Spielbrett.
 *
 * @author
 * Robert
 * Giacinto
 */
public class Board extends Shape {

    private int size;
    private double width;
    private double distance;
    private BoardCell[][] boardArray;

    public Board() {
        this(6, 35, 2);
    }

    public Board(int size, double width, double distance) {
        this.size = size;
        this.width = width;
        this.distance = distance;

        boardArray = new BoardCell[size][size];

        for (int i = 0; i < boardArray.length; i++) {
            for (int k = 0; k < boardArray[0].length; k++) {
                boardArray[i][k] = new BoardCell(i * (width + distance), k * (width + distance), width, width);
            }
        }
    }

    @Override
    public void render(Matrix transformation, Camera camera, Renderer renderer) {
        for (int i = 0; i < boardArray.length; i++) {
            for (int k = 0; k < boardArray[0].length; k++) {
                boardArray[i][k].render(transformation, camera, renderer);
            }
        }
    }
}
