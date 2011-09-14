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
package cga.framework.animation.interpolation;

import java.util.logging.Logger;

/**
 * Eine Interpolation mit kleinen Schritten zu Beginn, die zum Ende hin größer
 * werden. Auf diese Weise ist es zum Beispiel möglich Beschleunigungen zu
 * animieren.
 *
 * @author Robert Giacinto
 */
public class EaseInInterpolation extends Interpolation {
    private static final Logger logger = Logger.getLogger(EaseInInterpolation.class.getName());
    public EaseInInterpolation(double start, double end, int stepCount, boolean cyclic) {
        super(start, end, stepCount, cyclic);
    }

    @Override
    public double nextValue() {
        if (stepCounter++ < stepCount) {
            double x = (stepCounter / stepCount);
            x = Math.pow(x, 2);
            return min + max * x;
        } else if (cyclic) {
            stepCounter = 0;
        }
        return max;
    }
}
