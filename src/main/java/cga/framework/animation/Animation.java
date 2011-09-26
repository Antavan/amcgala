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

import cga.framework.shape.Shape;
import java.util.logging.Logger;

/**
 * Eine Animation, die das Verhalten eines Shapes beeinflussen kann.
 * @author Robert Giacinto
 * @param <T>  Der Typ des Shapes, das durch diese Animation beeinflusst wird. Dies ermöglicht den direkten Zugriff auf die Felder des Objekts ohne einen Cast durchführen zu müssen.
 */
public abstract class Animation<T extends Shape> {
    private static final Logger logger = Logger.getLogger(Animation.class.getName());
    private T shape;

    public T getShape() {
        return shape;
    }

    public void setShape(T shape) {
        this.shape = shape;
    }

    public abstract void animate();
}
