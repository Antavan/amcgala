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
package cga.example.movingCamera;

import cga.Framework;
import cga.framework.event.InputHandler;
import cga.framework.math.Vector3d;
import cga.framework.shape.Box3d;
import com.google.common.eventbus.Subscribe;
import java.awt.event.KeyEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Dieses Beispiel zeigt die Minimalkonfiguration des Frameworks.
 *
 * @author Robert Giacinto
 */
public class MovingCamera extends Framework implements InputHandler {

    private static final Logger log = LoggerFactory.getLogger(MovingCamera.class);
    private Box3d box;

    /**
     * Neues Framework, das eine Java2D Ausgabe der Größe width x height hat.
     *
     * @param width die Breite des Fensters
     * @param height die Höhe des Fensters
     */
    public MovingCamera(int width, int height) {
        super(width, height);
    }

    @Override
    public void initGraph() {
        registerInputEventHandler(this);
        box = new Box3d(new Vector3d(-200, -200, -400), 400, 400, 400);
        add(box);
    }

    @Subscribe
    public void handleKeys(KeyEvent event) {
        Vector3d old = getCamera().getPosition();
        if (event.getKeyCode() == KeyEvent.VK_UP) {
            old.z += 0.1;
            getCamera().update();
        } else if (event.getKeyCode() == KeyEvent.VK_DOWN) {
            old.z -= 0.1;
            getCamera().update();
        } else if (event.getKeyCode() == KeyEvent.VK_LEFT) {
            old.x -= 0.01;
            getCamera().update();
        } else if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
            old.x += 0.01;
            getCamera().update();
        }
        log.info("{}", old);
    }

    public static void main(String[] args) {
        MovingCamera example = new MovingCamera(800, 600);
        example.start();
    }
}
