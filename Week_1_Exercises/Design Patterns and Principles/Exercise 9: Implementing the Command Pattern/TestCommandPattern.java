public class TestCommandPattern {
    public static void main(String[] args) {
        Light livingRoomLight = new Light("Living Room");
        Light kitchenLight = new Light("Kitchen");

        Command livingRoomLightOn = new LightOnCommand(livingRoomLight);
        Command livingRoomLightOff = new LightOffCommand(livingRoomLight);
        Command kitchenLightOn = new LightOnCommand(kitchenLight);
        Command kitchenLightOff = new LightOffCommand(kitchenLight);

        RemoteControl remote = new RemoteControl();

        remote.setCommand(livingRoomLightOn);
        remote.pressButton();
        remote.setCommand(livingRoomLightOff);
        remote.pressButton();

        remote.setCommand(kitchenLightOn);
        remote.pressButton();

        remote.setCommand(kitchenLightOff);
        remote.pressButton();
    }
}
