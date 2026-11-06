import org.junit.Test;
import static org.junit.Assert.*;

public class LeaveCalculatorTest {

    LeaveCalculator calculator = new LeaveCalculator();

    @Test
    public void testRemainingLeave() {
        assertEquals(10, calculator.calculateRemainingLeave(20, 10));
    }

    @Test
    public void testZeroRemainingLeave() {
        assertEquals(0, calculator.calculateRemainingLeave(15, 15));
    }

    @Test
    public void testLeaveEncashment() {
        assertEquals(5000.0, calculator.calculateLeaveEncashment(5, 1000.0), 0.01);
    }
}
