public class LeaveCalculator {

    public int calculateRemainingLeave(int totalLeave, int leaveTaken) {
        return totalLeave - leaveTaken;
    }

    public double calculateLeaveEncashment(int unusedLeave, double dailySalary) {
        return unusedLeave * dailySalary;
    }
}
