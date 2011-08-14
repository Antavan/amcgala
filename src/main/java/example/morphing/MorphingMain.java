package example.morphing;

import cga.Framework;
import cga.framework.shape.Line2d;

/**
 * Dieses Programm morpht zwischen zwei Bildern.
 *
 * @author Robert Giacinto
 */
public class MorphingMain extends Framework {

    public MorphingMain(int width, int height) {
        super(width, height);
    }

    @Override
    public void initGraph() {
        add(new MorphingItem(new Line2d(0, 0, 0, 200), new Line2d(0, 200, 200, -100), 200));
    }

    public static void main(String[] args) {
        MorphingMain morphing = new MorphingMain(800, 800);
        morphing.start();
    }
}
