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

package cga;

import cga.framework.animation.Animator;
import cga.framework.camera.Camera;
import cga.framework.camera.OrthographicalCamera;
import cga.framework.scenegraph.SceneGraph;
import cga.framework.scenegraph.visitor.AnimationVisitor;
import cga.framework.scenegraph.visitor.RenderVisitor;
import cga.framework.math.Vector3d;
import cga.framework.renderer.RendererJ2d;
import cga.framework.scenegraph.Node;
import cga.framework.scenegraph.visitor.InterpolationVisitor;
import cga.framework.scenegraph.visitor.Visitor;
import cga.framework.shape.Shape;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Robert Giacinto
 */
public abstract class Framework {

  private static final Logger logger = Logger.getLogger(Framework.class.getName());
  public SceneGraph scenegraph;
  public RendererJ2d renderer;
  private Camera camera;
  private Animator animator;
  private List<Visitor> visitors;
  private double aspectRatio;
  private double fieldOfView;

  public Framework(int width, int height) {

    logger.log(Level.INFO, "Initialisiere Framework.");
    logger.log(Level.INFO, "Erstelle Scenegraph.");
    visitors = new ArrayList<Visitor>();
    scenegraph = new SceneGraph();
    aspectRatio = width / height;
    fieldOfView = Math.toRadians(76);

    // TODO hier fehlt jetzt der korrekte Aufruf der Kamera. Sobald die neue Klasse funktioniert muss das hier geändert werden!
    camera = new OrthographicalCamera(Vector3d.UNIT_Y, Vector3d.UNIT_Z, Vector3d.ZERO);


    logger.log(Level.INFO, "Erstelle Java2D Renderoutput.");
    renderer = new RendererJ2d(width, height);

    logger.log(Level.INFO, "Füge InterpolationVisitor hinzu.");
    visitors.add(new InterpolationVisitor());

    logger.log(Level.INFO, "Erstelle Animator.");
    animator = new Animator(20);
    AnimationVisitor animationVisitor = new AnimationVisitor();
    visitors.add(animationVisitor);


    logger.log(Level.INFO, "Erstelle RenderVisitor.");
    RenderVisitor renderVisitor = new RenderVisitor();
    renderVisitor.setCamera(camera);
    renderVisitor.setRenderer(renderer);
    visitors.add(renderVisitor);

     initGraph();

  }

  /**
   * Jedes Programm, das auf das Framework zurückgreift implementiert diese Methode. 
   * Hier findet die spezifische Initialisierung des Szenengraphs statt. Hier können zum Beispiel
   * Objekte an den Szenengraph gehängt werden.
   */
  public abstract void initGraph();

  public void add(Shape renderable) {
    scenegraph.addGeometry(renderable);
  }
  
  public void add(Node node){
    scenegraph.addNode(node);
  }

  public void setCamera(Camera camera) {
    this.camera = camera;
  }

  public void setRenderer(RendererJ2d renderer) {
    this.renderer = renderer;
  }

  public void setScenegraph(SceneGraph scenegraph) {
    this.scenegraph = scenegraph;
  }

  public void update() {
    if (camera != null) {
      for (Visitor v : visitors) {
        scenegraph.accept(v);
      }
    }
  }

  public void show() {
    if (renderer != null) {
      renderer.show();
    }
  }

  public void start() {
    if (animator == null) {
      update();
      show();
    } else {
      animator.setFramework(this);
      animator.start();
    }
  }
}
