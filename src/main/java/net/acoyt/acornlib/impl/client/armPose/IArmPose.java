package net.acoyt.acornlib.impl.client.armPose;

public interface IArmPose {
    boolean twoHanded();
    Value value();

    enum Value {
        VANILLA,
        CUSTOM
    }
}
