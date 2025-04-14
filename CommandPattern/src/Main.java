import models.Light;
import services.Command;
import services.RemoteControl;
import services.TurnOffCommand;
import services.TurnOnCommand;

public class Main {
    public static void main(String[] args) {
        Light light = new Light();
        Command onCommand = new TurnOnCommand(light);
        Command offCommand = new TurnOffCommand(light);

        RemoteControl remote = new RemoteControl();
        remote.setCommand(onCommand);
        remote.pressButton();

        remote.setCommand(offCommand);
        remote.pressButton();
    }
}