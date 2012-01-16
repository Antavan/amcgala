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
package amcgala.framework.animation;

import amcgala.Framework;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Diese Klasse kümmert sich um das Timing der Animation. Sie ruft in
 * regelmäßigen Abständen die Methoden update und show der Klasse Framework auf
 * und ermöglicht so die Realisation von Animationen.
 *
 * @author Robert Giacinto
 */
public class Animator {

    private static final Logger log = LoggerFactory.getLogger(Animator.class);
    private Timer fpsTimer;
    private Timer upsTimer;
    private Framework framework;
    private int framesPerSecond;
    private int updatesPerSecond;
    private Thread animation;
    private boolean running;

    /**
     * Erzeugt einen neuen Animator, der das Framework aktualisiert.
     *
     * @param framesPerSecond  die Anzahl der Bilder pro Sekunde
     * @param updatesPerSecond die Anzahl der Aktualisierungen pro Sekunde
     */
    public Animator(int framesPerSecond, int updatesPerSecond) {
        this.framesPerSecond = framesPerSecond;
        this.updatesPerSecond = updatesPerSecond;
        fpsTimer = new Timer(framesPerSecond);
        upsTimer = new Timer(updatesPerSecond);
    }

    /**
     * Ändert das Framework, das von dem Animator aktualisiert wird.
     *
     * @param framework das neue Framework
     */
    public void setFramework(Framework framework) {
        this.framework = framework;
    }

    /**
     * GIbt die Anzahl der Aktualisierungen zurück.
     *
     * @return die Anzahl der Aktualsierungen pro Sekunde
     */
    public int getFramesPerSecond() {
        return framesPerSecond;
    }

    /**
     * Ändert die Anzahl der Aktualisierungen pro Sekunde.
     *
     * @param framesPerSecond die neue Anzahl von Aktualisierungen pro Sekunde
     */
    public void setFramesPerSecond(int framesPerSecond) {
        this.framesPerSecond = framesPerSecond;
        fpsTimer = new Timer(framesPerSecond);
    }

    /**
     * Startet den Animator.
     */
    public void start() {
        running = true;
        if (framework != null) {
            animation = new Thread(new Runnable() {

                @Override
                public void run() {
                    double fpsLastTime = System.nanoTime();
                    double upsLastTime = System.nanoTime();
                    int upsCounter = 0;
                    int fpsCounter = 0;
                    int unprocessed = 1;
                    double last = System.nanoTime();
                    boolean render = true;
                    while (running) {
                        double now = System.nanoTime();
                        unprocessed += (now - last) / upsTimer.getTimePerFrame();
                        fpsTimer.start();
                        while (unprocessed > 0) {
                            upsCounter++;
                            unprocessed--;

                            framework.update();
                            last = System.nanoTime();
                            render = true;
                        }

                        fpsCounter++;
                        if (render) {
                            framework.show();
                            render = false;
                        }
                        fpsTimer.stop();
                        try {
                            Thread.sleep((long) fpsTimer.getSleepTime());
                        } catch (InterruptedException e) {
                        }

                        if (System.nanoTime() - fpsLastTime > 1000000000) {
                            log.debug("FPS = {}", fpsCounter);
                            fpsCounter = 0;
                            fpsLastTime = System.nanoTime();
                        }

                        if (System.nanoTime() - upsLastTime > 1000000000) {
                            log.debug("UPS = {}", upsCounter);
                            upsCounter = 0;
                            upsLastTime = System.nanoTime();
                        }
                    }
                }
            });
            animation.start();
        }
    }

    /**
     * Hält das Framework an und zeigt das letzte gerenderte Bild an.
     */
    public void stop() {
        running = false;
        framework.update();
        framework.show();
    }
}
