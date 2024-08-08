

public class TestStrategyPattern {
    public static void main(String[] args) {
        PaymentContext paymentContext = new PaymentContext();

        paymentContext.setPaymentStrategy(new CreditCardPayment("1234-5678-9012-3366"));
        paymentContext.executePayment(250.75);
        System.out.println();

        paymentContext.setPaymentStrategy(new PayPalPayment("konapalaaditya13@gmail.com"));
        paymentContext.executePayment(89.99);
    }
}
