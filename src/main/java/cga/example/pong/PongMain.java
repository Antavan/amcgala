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
package cga.example.pong;

import cga.Framework;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Ein simples Pongspiel, das mit der Maus gesteuert werden kann.
 *
 * @author Robert Giacinto
 */
public class PongMain extends Framework {

    private PongBoard board;

    public PongMain(int width, int height) {
        super(width, height);
       addKeyAdapter(new MyKeyListener());
    }

    @Override
    public void initGraph() {
        board = new PongBoard(-getScreenWidth() / 5, 0, 480, 240, 0.25, 0.9);
        add(board);
    }

    public static void main(String[] args) {
        PongMain game = new PongMain(800, 600);
        game.start();
    }

    private class MyKeyListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                board.getPaddle().moveDown();
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                board.getPaddle().moveUp();
            }
        }
    }
}
