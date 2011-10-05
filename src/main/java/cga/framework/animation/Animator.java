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
package cga.framework.animation;

import cga.Framework;
import java.util.logging.Logger;

/**
 * Diese Klasse kümmert sich um das Timing der Animation. Sie ruft in regelmäßigen Abständen die 
 * Methoden update und show der Klasse Framework auf und ermöglicht so die Realisation von Animationen.
 * 
 * @author Robert Giacinto
 */
public class Animator {

    private static final Logger logger = Logger.getLogger(Animator.class.getName());
    private Timer timer;
    private Framework framework;
    private int framesPerSecond;

    /**
     * Erzeugt einen neuen Animator, der das Framework aktualisiert.
     * 
     * @param framesPerSecond  die Anzahl der Aktualisierungen pro Sekunde
     */
    public Animator(int framesPerSecond) {
        this.framesPerSecond = framesPerSecond;
        timer = new Timer(framesPerSecond);
    }

    /**
     * Ändert das Framework, das von dem Animator aktualisiert wird.
     * 
     * @param framework  das neue Framework
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
     * @param framesPerSecond die neue Anzahl von Aktualisierungen pro Sekunde
     */
    public void setFramesPerSecond(int framesPerSecond) {
        this.framesPerSecond = framesPerSecond;
        timer = new Timer(framesPerSecond);
    }

    /**
     * Startet den Animator.
     */
    public void start() {

        if (framework != null) {
            Thread animation = new Thread(new Runnable() {

                @Override
                public void run() {
                    while (true) {
                        timer.start();
                        framework.update();
                        framework.show();
                        timer.stop();
                        try {
                            Thread.sleep((long) timer.getSleepTime());
                        } catch (InterruptedException e) {
                        }
                    }
                }
            });
            animation.start();
        }
    }
}
