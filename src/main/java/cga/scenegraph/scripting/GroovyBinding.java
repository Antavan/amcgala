package cga.scenegraph.scripting;

import cga.scenegraph.scene.Scene;
import groovy.lang.Binding;

/**
 * Groovy binding für den Szenengraph.
 */
public class GroovyBinding extends Binding {

  private Scene scene;
  
  public GroovyBinding(Scene scene) {
    this.scene = scene;
    setVariable("scenegraph", scene.getScenegraph());
    setVariable("animator", scene.getAnimator());
  }
}
