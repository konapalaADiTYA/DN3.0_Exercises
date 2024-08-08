public class TestObserverPattern {
    public static void main(String[] args) {
        StockMarket stockMarket = new StockMarket();

        Observer mobileApp1 = new MobileApp("App1");
        Observer mobileApp2 = new MobileApp("App2");
        Observer webApp1 = new WebApp("WebApp");

        stockMarket.registerObserver(mobileApp1);
        stockMarket.registerObserver(mobileApp2);
        stockMarket.registerObserver(webApp1);

        stockMarket.setStockPrice(100.5);
        System.out.println();

        stockMarket.setStockPrice(102.75);
        System.out.println();

        stockMarket.deregisterObserver(mobileApp1);

        stockMarket.setStockPrice(99.0);
    }
}
