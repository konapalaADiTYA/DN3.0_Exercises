public class TestBuilderPattern {
    public static void main(String[] args) {
        Computer gamingComputer = new Computer.Builder("Intel i7", "16GB")
                .setStorage("1TB")
                .setGraphicsCardEnabled(true)
                .setBluetoothEnabled(true)
                .build();

        System.out.println(gamingComputer);

        Computer officeComputer = new Computer.Builder("Intel i5", "8GB")
                .setStorage("512GB")
                .setGraphicsCardEnabled(false)
                .setBluetoothEnabled(true)
                .build();

        System.out.println(officeComputer);
    }
}