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

package cga.framework.shape;

import cga.framework.camera.Camera;
import cga.framework.math.Matrix;
import cga.framework.renderer.Renderer;


public class Rectangle2d extends Shape {
  public Line2d a, b, c, d;

  public Rectangle2d(Line2d a, Line2d b, Line2d c, Line2d d) {
    this.a = a;
    this.b = b;
    this.c = c;
    this.d = d;
  }

  @Override
  public void render(Matrix transformation, Camera camera, Renderer renderer) {
    a.render(transformation, camera, renderer);
    b.render(transformation, camera, renderer);
    c.render(transformation, camera, renderer);
    d.render(transformation, camera, renderer);
  }
}
