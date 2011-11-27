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

import cga.Framework;
import cga.framework.shape.Text;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Das Spiel Schiffe Versenken in einer einfachen Fassung. Es werden zwei
 * Spielfelder auf dem Bildschirm ausgegeben (eins vom Gegner, eins vom
 * Spieler). Diese erste Fassung demonstriert nur die Ausgabe und die
 * Datenstruktur, die für die Speicherung benutzt wird.
 *
 * @author Robert Giacinto
 */
public class ShipsMain extends Framework {

    private Board playerBoard;
    private Board aiBoard;

    public ShipsMain(int width, int height) {
        super(width, height);


        addKeyAdapter(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    playerBoard.moveLeft();
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    playerBoard.moveRight();
                }
                // TODO hier kommt noch der Code für die Tasten "hoch" und "runter".
            }
        });
    }

    @Override
    public void initGraph() {
        playerBoard = new Board();
        aiBoard = new Board(-250, 0);

        // Spielerboard
        add(playerBoard);
        // Board des Gegners
        add(aiBoard);

        add(new Text("Schiffe Versenken", -250, 245, 245));
    }

    public static void main(String[] args) {
        Framework ships = new ShipsMain(800, 600);
        ships.start();
    }
}
