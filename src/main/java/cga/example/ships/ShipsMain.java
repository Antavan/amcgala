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

/**
 * Das Spiel Schiffe Versenken in einer einfachen Fassung.
 * Es werden zwei Spielfelder auf dem Bildschirm ausgegeben (eins vom Gegner, eins
 * vom Spieler).
 * Diese erste Fassung demonstriert nur die Ausgabe und die Datenstruktur, die für
 * die Speicherung benutzt wird.
 * 
 * @author Robert Giacinto
 */
public class ShipsMain extends Framework {

    public ShipsMain(int width, int height) {
        super(width, height);
    }

    @Override
    public void initGraph() {
        // Spielerboard
        add(new Board());
        // Board des Gegners
        add(new Board(-250, 0));
    }

    public static void main(String[] args) {
        Framework ships = new ShipsMain(800, 600);
        ships.start();
    }
}
