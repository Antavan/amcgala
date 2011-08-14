package example.sinecurve;

import cga.Framework;
import cga.framework.scenegraph.transform.Translation;

public final class AnimatedSineCurveMain extends Framework {

  private Translation translation;

  public AnimatedSineCurveMain() {
    super(800, 800);
  }

  @Override
  public void initGraph() {
    translation = new Translation(-400, 0, 0);
    scenegraph.addTransformation(translation);
    add(new AnimatedSineCurve(2000, 0, 0));
  }

  public static void main(String[] args) {
    AnimatedSineCurveMain example = new AnimatedSineCurveMain();
    example.start();
  }
}
