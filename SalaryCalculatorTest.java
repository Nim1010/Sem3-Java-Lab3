import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SalaryCalculatorTest {

    @Test
    public void testCalculateYearlySalary() {
        double monthly = 4000.0;
        double expected = 48000.0;
        assertEquals(expected, SalaryCalculator.calculateYearlySalary(monthly));
    }

    @Test
    public void testCalculateYearlySalaryWithBonus() {
        double monthly = 4000.0;
        double bonusRate = 0.10; // 10% bonus
        double expected = 48000.0 + 400.0;
        assertEquals(expected, SalaryCalculator.calculateYearlySalaryWithBonus(monthly, bonusRate));
    }

    @Test
    public void testZeroSalary() {
        double monthly = 0.0;
        double expected = 0.0;
        assertEquals(expected, SalaryCalculator.calculateYearlySalary(monthly));
    }

    @Test
    public void testNegativeSalary() {
        double monthly = -3000.0;
        double expected = -36000.0;
        assertEquals(expected, SalaryCalculator.calculateYearlySalary(monthly));
    }
}
