package services;

import models.Light;

public class TurnOnCommand implements Command{
    private Light light;

    public TurnOnCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.turnOn();
    }
}
