public class TestDecoratorPattern {
    public static void main(String[] args) {
        Notifier notifier = new EmailNotifier();
        notifier.send("Hello, this is your email notification!");

        Notifier smsNotifier = new SMSNotifierDecorator(new EmailNotifier());
        smsNotifier.send("Hello, this is your email and SMS notification!");

        Notifier slackNotifier = new SlackNotifierDecorator(
                                    new SMSNotifierDecorator(
                                        new EmailNotifier()));
        slackNotifier.send("Hello, this is your email, SMS, and Slack notification!");
    }
}
