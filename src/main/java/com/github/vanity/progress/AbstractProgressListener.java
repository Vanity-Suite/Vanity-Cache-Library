package com.github.vanity.progress;

public abstract class AbstractProgressListener implements ProgressListener {

    @Override
    public void notify(double progress, String message) {
        double value = progress;
        value = value * 100;
        value = (int) value;
        value = value / 100;
        change(value, message);
    }

    public abstract void change(double progress, String message);

}
