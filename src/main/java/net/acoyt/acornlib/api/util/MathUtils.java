package net.acoyt.acornlib.api.util;

@SuppressWarnings("unused")
public class MathUtils {
    public static float random(float min, float max) {
        return (float) Math.random() * (max - min) + min;
    }

    public static double random(double min, double max) {
        return Math.random() * (max - min) + min;
    }

    public static float closerTo(float value, float thisFloat, float thatFloat) {
        return Math.abs(value - thisFloat) < Math.abs(value - thatFloat) ? thisFloat : thatFloat;
    }
}
