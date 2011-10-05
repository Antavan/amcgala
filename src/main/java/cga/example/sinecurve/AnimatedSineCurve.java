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
package cga.example.sinecurve;

import cga.framework.animation.Animation;
import cga.framework.animation.interpolation.Interpolation;
import cga.framework.animation.interpolation.LinearInterpolation;
import cga.framework.camera.Camera;
import cga.framework.math.Matrix;
import cga.framework.renderer.Renderer;
import cga.framework.shape.Line2d;
import cga.framework.shape.Point2d;
import cga.framework.shape.Shape;

/**
 * Eine animierte Sinuskurve.
 * <p/>
 * @author Robert Giacinto
 */
public class AnimatedSineCurve extends Shape {

    private int steps;
    private Point2d[] points;
    private double frequency;
    private double amplitude;
    private Interpolation interpolationFrq;
    private Interpolation interpolationAmp;

    public AnimatedSineCurve(int steps, double frequency, double amplitude) {
        this.steps = steps;
        this.frequency = frequency;
        this.amplitude = amplitude;

        points = new Point2d[steps];

        init();


        interpolationFrq = new LinearInterpolation(0, 3, steps, true);
        interpolationAmp = new LinearInterpolation(15, 20, steps, true);

        setAnimation(new Animation<AnimatedSineCurve>() {

            @Override
            public void animate() {
                if (interpolationAmp != null) {
                    setAmplitude(interpolationAmp.nextValue());
                }

                if (interpolationFrq != null) {
                    setFrequency(interpolationFrq.nextValue());
                }
            }
        });

    }

    private void init() {
        int step = 0;
        while (step < steps) {
            points[step] = new Point2d(step * (Math.PI * 10 / steps) * 100, 10 * amplitude * Math.sin(step * (Math.PI * 10 / steps) * frequency * 10));
            step++;
        }
    }

    public Interpolation getInterpolationAmp() {
        return interpolationAmp;
    }

    public void setInterpolationAmp(Interpolation interpolationAmp) {
        this.interpolationAmp = interpolationAmp;
    }

    public Interpolation getInterpolationFrq() {
        return interpolationFrq;
    }

    public void setInterpolationFrq(Interpolation interpolationFrq) {
        this.interpolationFrq = interpolationFrq;
    }

    public double getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(double amplitude) {
        this.amplitude = amplitude;
        init();
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
        init();
    }

    @Override
    public void render(Matrix transformation, Camera camera, Renderer renderer) {
        for (int i = 0; i < points.length - 1; i++) {
            Line2d line = new Line2d(points[i].x, points[i].y, points[i + 1].x, points[i + 1].y);
            line.render(transformation, camera, renderer);
        }
    }
}
