public class FinancialForecastingTool {
    public static double calculateFutureValue(double curVal, double growthRate, int years) {
        if (years == 0) {
            return curVal;
        }

        return calculateFutureValue(curVal * (1 + growthRate), growthRate, years - 1);
    }

    public static void main(String[] args) {
        double curVal = 1500.0;  
        double growthRate = 0.25;      
        int years = 15;                

        double futureValue = calculateFutureValue(curVal, growthRate, years);
        System.out.print("The predicted future value after "+years+" years is: "+futureValue);
    }
}
