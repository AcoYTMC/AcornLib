package net.acoyt.acornlib.api.client;

import java.util.function.Function;

import static java.lang.Math.*;

/**
 * @author eeverest, AcoYT
 * Originally by eeverest, but I adapted it into an interface and record, and added the bounce easings
 */
public interface Easings {
    Easing LINEAR = new Easing(x -> x);

    Easing EASE_IN_SINE = new Easing(x -> 1 - cos(x * PI) / 2);
    Easing EASE_OUT_SINE = new Easing(x -> sin(x * PI) / 2);
    Easing EASE_IN_OUT_SINE = new Easing(x -> -(cos(PI * x) - 1) / 2);

    Easing EASE_IN_QUAD = new Easing(x -> x * x);
    Easing EASE_OUT_QUAD = new Easing(x -> 1 - (1 - x) * (1 - x));
    Easing EASE_IN_OUT_QUAD = new Easing(x -> x < 0.5 ? 2 * x * x : 1 - pow(-2 * x + 2, 2) / 2);

    Easing EASE_IN_CUBIC = new Easing(x -> x * x * x);
    Easing EASE_OUT_CUBIC = new Easing(x -> 1 - pow(1 - x, 3));
    Easing EASE_IN_OUT_CUBIC = new Easing(x -> x < 0.5 ? 4 * x * x * x : 1 - pow(-2 * x + 2, 3) / 2);

    Easing EASE_IN_QUART = new Easing(x -> x * x * x * x);
    Easing EASE_OUT_QUART = new Easing(x -> 1 - pow(1 - x, 4));
    Easing EASE_IN_OUT_QUART = new Easing(x -> x < 0.5 ? 8 * x * x * x * x : 1 - pow(-2 * x + 2, 4) / 2);

    Easing EASE_IN_QUINT = new Easing(x -> x * x * x * x * x);
    Easing EASE_OUT_QUINT = new Easing(x -> 1 - pow(1 - x, 5));
    Easing EASE_IN_OUT_QUINT = new Easing(x -> x < 0.5 ? 16 * x * x * x * x * x : 1 - pow(-2 * x + 2, 5) / 2);

    Easing EASE_IN_EXPO = new Easing(x -> x == 0 ? 0 : pow(2, 10 * x - 10));
    Easing EASE_OUT_EXPO = new Easing(x -> x == 1 ? 1 : 1 - pow(2, -10 * x));
    Easing EASE_IN_OUT_EXPO = new Easing(x -> x == 0 ? 0 : x == 1 ? 1 : x < 0.5 ? pow(2, 20 * x - 10) / 2 : (2 - pow(2, -20 * x + 10)) / 2);

    Easing EASE_IN_CIRC = new Easing(x -> 1 - sqrt(1 - pow(x, 2)));
    Easing EASE_OUT_CIRC = new Easing(x -> sqrt(1 - pow(x - 1, 2)));
    Easing EASE_IN_OUT_CIRC = new Easing(x -> x < 0.5 ? (1 - sqrt(1 - pow(2 * x, 2))) / 2 : (sqrt(1 - pow(-2 * x + 2, 2)) + 1) / 2);

    Easing EASE_IN_BACK = new Easing(x -> 2.70158 * x * x * x - 1.70158 * x * x);
    Easing EASE_OUT_BACK = new Easing(x -> 1 + 2.70158 * pow(x - 1, 3) + 1.70158 * pow(x - 1, 2));
    Easing EASE_IN_OUT_BACK = new Easing(x -> x < 0.5 ? (pow(2 * x, 2) * ((1.70158 * 1.525 + 1) * 2 * x - 1.70158 * 1.525)) / 2 : (pow(2 * x - 2, 2) * ((1.70158 * 1.525 + 1) * (x * 2 - 2) + 1.70158 * 1.525) + 2) / 2);

    Easing EASE_IN_ELASTIC = new Easing(x -> x == 0 ? 0 : x == 1 ? 1 : -pow(2, 10 * x - 10) * sin((x * 10 - 10.75) * ((2 * PI) / 3)));
    Easing EASE_OUT_ELASTIC = new Easing(x -> x == 0 ? 0 : x == 1 ? 1 : pow(2, -10 * x) * sin((x * 10 - 0.75) * ((2 * PI) / 3)) + 1);
    Easing EASE_IN_OUT_ELASTIC = new Easing(x -> x == 0 ? 0 : x == 1 ? 1 : x < 0.5 ? -(pow(2, 20 * x - 10) * sin((20 * x - 11.125) * ((2 * PI) / 4.5))) / 2 : (pow(2, -20 * x + 10) * sin((20 * x - 11.125) * ((2 * PI) / 4.5))) / 2 + 1);

    Easing EASE_OUT_BOUNCE = new Easing(x -> {
        double n1 = 7.5625;
        double d1 = 2.75;

        if (x < 1 / d1) {
            return n1 * x * x;
        } else if (x < 2 / d1) {
            return n1 * (x -= 1.5 / d1) * x + 0.75;
        } else if (x < 2.5 / d1) {
            return n1 * (x -= 2.25 / d1) * x + 0.9375;
        } else {
            return n1 * (x -= 2.625 / d1) * x + 0.984375;
        }
    });
    Easing EASE_IN_BOUNCE = new Easing(x -> {
        var easeOutBounce = EASE_OUT_BOUNCE.function().apply(1 - x);
        return 1 - easeOutBounce.doubleValue();
    });
    Easing EASE_IN_OUT_BOUNCE = new Easing(x -> {
        var easeOutBounce = EASE_OUT_BOUNCE.function().apply(1 - 2 * x);
        var easeOutBounce2 = EASE_OUT_BOUNCE.function().apply(2 * x - 1);

        return x < 0.5
                ? (1 - easeOutBounce.doubleValue()) / 2
                : (1 + easeOutBounce2.doubleValue()) / 2;
    });

    record Easing(Function<Double, Number> function) {}
}
