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
package cga.example.concurrency;

import cga.Framework;
import cga.framework.shape.BresenhamLine2d;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * Ein Testbeispiel, um mögliche Probleme bei nicht-synchronisierten Zugriffen
 * auf den Szenengraph zu evaluieren.
 *
 * @author Robert Giacinto
 */
public class ConcurrencyTest extends Framework {

    public ConcurrencyTest(int width, int height) {
        super(width, height);
        addKeyAdapter(new KeyAdapter() {

            Random random = new Random(System.nanoTime());

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    add(new BresenhamLine2d(
                            Math.pow(-1, random.nextInt(2)) * random.nextInt(getScreenWidth() / 2),
                            Math.pow(-1, random.nextInt(2)) * random.nextInt(getScreenHeight() / 2),
                            Math.pow(-1, random.nextInt(2)) * random.nextInt(getScreenWidth() / 2),
                            Math.pow(-1, random.nextInt(2)) * random.nextInt(getScreenWidth() / 2)));
                }
            }
        });

    }

    @Override
    public void initGraph() {
    }

    public static void main(String[] args) {
        Framework test = new ConcurrencyTest(800, 600);
        test.start();

    }
}
