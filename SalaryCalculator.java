public class SalaryCalculator {
    public static double calculateYearlySalary(double monthlySalary) {
        return monthlySalary * 12;
    }

    public static double calculateYearlySalaryWithBonus(double monthlySalary, double bonusRate) {
        return monthlySalary * 12 + (monthlySalary * bonusRate);
    }
}
