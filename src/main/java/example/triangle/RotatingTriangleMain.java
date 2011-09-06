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
package example.triangle;

import cga.Framework;
import cga.framework.shape.Point2d;

/**
 * Dies ist das Aufwärmbeispiel für die Einführungsveranstaltung. Es ist ein
 * kleines Beispiel, bei dem ein Dreieck implementiert werden soll, das um eine
 * beliebe Achse im 2d Raum rotiert wird.
 *
 * @author Robert Giacinto
 */
public class RotatingTriangleMain extends Framework {

    public RotatingTriangleMain(int width, int height) {
        super(width, height);

    }

    @Override
    public void initGraph() {
        add(new RotatingTriangle(new Triangle(new Point2d(0, 0), new Point2d(200, 0), new Point2d(100, 200))));
    }

    public static void main(String[] args) {
        Framework demo = new RotatingTriangleMain(1600, 800);
        demo.start();
    }
}
