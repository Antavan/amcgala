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

import cga.framework.animation.interpolation.EaseInInterpolation;
import cga.framework.scenegraph.Node;
import cga.framework.scenegraph.transform.RotationZ;

/**
 *
 * @author Robert Giacinto
 */
public class RotatingTriangleNode extends Node {

    public RotatingTriangleNode(Triangle triangle) {
	super("Rotating Triangle");
	RotationZ rotationZ = new RotationZ();
	rotationZ.setInterpolationPhi(new EaseInInterpolation(0, 6 * Math.PI, 1000, true));
	setTransformation(rotationZ);
	addShape(triangle);
    }
}
