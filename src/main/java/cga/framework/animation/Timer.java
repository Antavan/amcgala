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


public class Timer {
  private double framesPerSecond;
  private double startTime;
  private double stopTime;
  private double timePerFrame;
  private double duration;


  public Timer(double framesPerSecond) {
    this.framesPerSecond = framesPerSecond;
    timePerFrame = 1000 / framesPerSecond;
  }

  public void start() {
    startTime = System.currentTimeMillis();
  }

  public void stop() {
    stopTime = System.currentTimeMillis();
    duration = stopTime - startTime;
  }

  public double getSleepTime() {
    return (timePerFrame - duration < 0) ? 1 : (timePerFrame - duration);
  }


}
