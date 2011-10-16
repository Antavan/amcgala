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

import java.util.logging.Logger;

/**
 * Ein einfacher Timer, der einen Zeitraum messen kann. Diese
 * Klasse wird zur Animation und Aktualisierung des Frameworks benötigt.
 * 
 * @author Robert Giacinto
 */
public class Timer {

    private static final Logger logger = Logger.getLogger(Timer.class.getName());
    private double framesPerSecond;
    private double startTime;
    private double stopTime;
    private double timePerFrame;
    private double duration;

    /**
     * Erzeugt einen neuen Timer.
     * 
     * @param framesPerSecond die Anzahl von Frames, die pro Sekunde berechnet werden sollen.
     */
    public Timer(double framesPerSecond) {
        this.framesPerSecond = framesPerSecond;
        timePerFrame = 1000 / framesPerSecond;
    }

    /**
     * Startet den Timer. 
     */
    public void start() {
        startTime = System.currentTimeMillis();
    }

    /**
     * Stoppt den Timer.
     */
    public void stop() {
        stopTime = System.currentTimeMillis();
        duration = stopTime - startTime;
    }

    /**
     * Gibt die Sleeptime des Frameworks zurück. Dies ist die Differenzzeit, die nach Abzug der benötigten Zeit zum Rendern, noch 
     * übrig bleibt.
     * @return die Sleeptime
     */
    public double getSleepTime() {
        return (timePerFrame - duration < 0) ? 1 : (timePerFrame - duration);
    }
}
