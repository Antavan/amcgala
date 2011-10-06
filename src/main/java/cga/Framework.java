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
import cga.framework.renderer.Renderer;
import cga.framework.renderer.RendererJ2d;
import cga.framework.scenegraph.Node;
import cga.framework.scenegraph.visitor.InterpolationVisitor;
import cga.framework.scenegraph.visitor.UpdateVisitor;
import cga.framework.scenegraph.visitor.Visitor;
import cga.framework.shape.Shape;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Die Hauptklasse des Frameworks, die die Hauptaufgaben übernimmt.
 * Sie initialisiert die wichtigsten Datenstrukturen und ermöglicht ihren Zugriff.
 * Der Aufbau des Frameworks folgt der Computer Graphics Pipeline, wie sie zum Beispiel 
 * in xxx beschrieben ist.
 * 
 * @author Robert Giacinto
 */
public abstract class Framework {

    private static final Logger logger = Logger.getLogger(Framework.class.getName());
    public SceneGraph scenegraph;
    public Renderer renderer;
    private Camera camera;
    private Animator animator;
    private List<Visitor> visitors;
    private double aspectRatio;
    private double fieldOfView;
    private int screenWidth;
    private int screenHeight;

    /**
     * Erstellt ein neues Framework, das eine grafische Ausgabe in der Auflösung width x height hat.
     * @param width die Breite der Auflösung
     * @param height die Höhe der Auflösung
     */
    public Framework(int width, int height) {

        screenWidth = width;
        screenHeight = height;
        logger.log(Level.INFO, "Initialisiere Framework.");
        logger.log(Level.INFO, "Erstelle Scenegraph.");
        visitors = new ArrayList<Visitor>();
        scenegraph = new SceneGraph();
        aspectRatio = width / height;
        fieldOfView = Math.toRadians(76);


        camera = new OrthographicalCamera(Vector3d.UNIT_Y, Vector3d.UNIT_Z, Vector3d.ZERO);


        logger.log(Level.INFO, "Erstelle Java2D Renderoutput.");
        renderer = new RendererJ2d(width, height);

        logger.log(Level.INFO, "Füge InterpolationVisitor hinzu.");
        visitors.add(new InterpolationVisitor());

        logger.log(Level.INFO, "Erstelle Animator.");
        animator = new Animator(50);
        AnimationVisitor animationVisitor = new AnimationVisitor();
        visitors.add(animationVisitor);

        logger.log(Level.INFO, "UpdateVisitor.");
        UpdateVisitor updateVisitor = new UpdateVisitor();
        visitors.add(updateVisitor);

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

    /**
     * Fügt der Szene ein neues zeichenbares Objekt hinzu. Dieses wird an den Root-Knoten
     * angehängt. 
     * @param shape das Grafikobjekt, das der Szene hinzugefügt wird 
     */
    public void add(Shape shape) {
        scenegraph.addGeometry(shape);
    }

    /**
     * Fügt dem Szenengraph einen neuen Knoten hinzu. 
     * @param node der neue Knoten
     */
    public void add(Node node) {
        scenegraph.addNode(node);
    }

    /**
     * Ändert die Kamera, die verwendet wird.
     * @param camera die neue Kamera, die das Framework verwenden soll
     */
    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    /**
     * Ändert den Renderer des Frameworks. 
     * Damit ist es möglich die Ausgabe des Frameworks zu verändern. Abhängig von der
     * Implementierung des Renderers.
     * @param renderer der neue Renderer
     */
    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }

    /**
     * Ändert den Szenengraph, der vom Framework verwendet wird.
     * @param scenegraph der neue Szenengraph
     */
    public void setScenegraph(SceneGraph scenegraph) {
        this.scenegraph = scenegraph;
    }

    /**
     * Das Seitenverhältnis der Ausgabe. Diese wird innerhalb der Kamera benötigt.
     * @return das Seitenverhältnis der Ausgabe
     */
    public double getAspectRatio() {
        return aspectRatio;
    }

    private void setAspectRatio(double aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    /**
     * Gibt die Höhe der Ausgabe zurück.
     * @return die Höhe in Pixeln
     */
    public int getScreenHeight() {
        return screenHeight;
    }

    /**
     * Ändert die Höhe der Bildschirmausgabe.
     * @param screenHeight die Höhe der Bildschirmausgabe in Pixeln
     */
    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
        setAspectRatio(screenWidth / screenHeight);
    }

    /**
     * Gibt die Breite der Bildschirmausgabe in Pixeln zurück.
     * 
     * @return  die Breite der Bildschirmausgabe in Pixeln
     */
    public int getScreenWidth() {
        return screenWidth;
    }

    /**
     * Ändert die Breite der Bildschirmausgabe.
     * @param screenWidth die neue Breite der Bildschirmausgabe in Pixeln
     */
    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
        setAspectRatio(screenWidth / screenHeight);
    }

    /**
     * Aktualisiert den Szenengraphen, in dem die einzelnen, registrierten Visitor den 
     * Szenengraphen besuchen.
     */
    public void update() {
        if (camera != null) {
            for (Visitor v : visitors) {
                scenegraph.accept(v);
            }
        }
    }

    /**
     * Rendert den Szenengraphen mithilfe des registrierten Renderers.
     */
    public void show() {
        if (renderer != null) {
            renderer.show();
        }
    }

    /**
     * Startet das Framework und aktualisiert den Szenengraphen mithilfe eines Animators.
     */
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
