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

public class Animator {

    private Timer timer;
    private Framework framework;
    private int framesPerSecond;

    public Animator(int framesPerSecond) {
        this.framesPerSecond = framesPerSecond;
        timer = new Timer(framesPerSecond);
    }

    public void setFramework(Framework framework) {
        this.framework = framework;
    }

    public int getFramesPerSecond() {
        return framesPerSecond;
    }

    public void setFramesPerSecond(int framesPerSecond) {
        this.framesPerSecond = framesPerSecond;
        timer = new Timer(framesPerSecond);
    }

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
