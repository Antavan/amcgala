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
package amcgala.example.movingCamera;

import amcgala.Framework;
import amcgala.framework.animation.interpolation.LinearInterpolation;
import amcgala.framework.camera.SimplePerspectiveCamera;
import amcgala.framework.event.InputHandler;
import amcgala.framework.math.Vector3d;
import amcgala.framework.scenegraph.Node;
import amcgala.framework.scenegraph.transform.RotationY;
import amcgala.framework.shape.Box3d;
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
        setCamera(new SimplePerspectiveCamera(Vector3d.UNIT_Y, new Vector3d(0, 25, 150), Vector3d.ZERO, 10900));

        Node n = new Node("rotating box");
        RotationY rotY = new RotationY();
        rotY.setInterpolationPhi(new LinearInterpolation(0, 4 * Math.PI, 250, true));
        n.setTransformation(rotY);
        n.addShape(new Box3d(new Vector3d(0, 0, 0), 40, 40, 40));
        n.addShape(new Box3d(new Vector3d(0, 100, 0), 40, 40, 40));
        n.addShape(new Box3d(new Vector3d(0, 200, 0), 40, 40, 40));
        add(n);
    }

    @Subscribe
    public void handleKeys(KeyEvent event) {
        Vector3d position = getCamera().getPosition();
        if (event.getKeyCode() == KeyEvent.VK_UP) {
            position.z += 0.1;
            getCamera().update();
        } else if (event.getKeyCode() == KeyEvent.VK_DOWN) {
            position.z -= 0.1;
            getCamera().update();
        } else if (event.getKeyCode() == KeyEvent.VK_LEFT) {
            position.x -= 0.01;
            getCamera().update();
        } else if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
            position.x += 0.01;
            getCamera().update();
        }
//        log.info("{}", old);
    }

    public static void main(String[] args) {
        MovingCamera example = new MovingCamera(800, 600);
        example.start();
    }
}
