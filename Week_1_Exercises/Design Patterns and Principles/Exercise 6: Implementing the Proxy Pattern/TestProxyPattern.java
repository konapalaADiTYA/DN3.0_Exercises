public class TestProxyPattern {
    public static void main(String[] args) {
        Image image1 = new ProxyImage("CRCV9138.JPG");
        Image image2 = new ProxyImage("CRCVE9138.JPG");

        image1.display();
        System.out.println("");

        image1.display();
        System.out.println("");

        image2.display();
        System.out.println("");

        image2.display();
    }
}
