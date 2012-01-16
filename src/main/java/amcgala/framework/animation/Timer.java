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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Ein einfacher Timer, der einen Zeitraum messen kann. Diese Klasse wird zur
 * Animation und Aktualisierung des Frameworks benötigt.
 *
 * @author Robert Giacinto
 */
public class Timer {

    private static final Logger log = LoggerFactory.getLogger(Timer.class);
    private double framesPerSecond;
    private double startTime;
    private double stopTime;
    private double timePerFrame;
    private double duration;

    /**
     * Erzeugt einen neuen Timer.
     *
     * @param framesPerSecond die Anzahl von Frames, die pro Sekunde berechnet
     *                        werden sollen.
     */
    public Timer(double framesPerSecond) {
        this.framesPerSecond = framesPerSecond;
        timePerFrame = 1000 * 1000000 / framesPerSecond;
    }

    /**
     * Startet den Timer.
     */
    public double start() {
        startTime = System.nanoTime();
        return startTime;
    }

    /**
     * Stoppt den Timer.
     */
    public double stop() {
        stopTime = System.nanoTime();
        duration = stopTime - startTime;
        return stopTime;
    }

    /**
     * Gibt die Sleeptime des Frameworks zurück. Dies ist die Differenzzeit, die
     * nach Abzug der benötigten Zeit zum Rendern, noch übrig bleibt.
     *
     * @return die Sleeptime
     */
    public double getSleepTime() {
        double sleepTime = (timePerFrame - duration < 0) ? 5 : (timePerFrame - duration) / 1000000;
        return sleepTime;
    }

    /**
     * Gibt die Zeit zurück, die pro Frame für die Aktion vorgesehen ist.
     *
     * @return die Zeit pro Frame
     */
    public double getTimePerFrame() {
        return timePerFrame;
    }
}
