public class Salary {
    private int id;
    private double baseSalary;
    private double bonus;
    private double deductions;

    public Salary(int id, double baseSalary, double bonus, double deductions) {
        this.id = id;
        this.baseSalary = baseSalary;
        this.bonus = bonus;
        this.deductions = deductions;
    }

    public int getId() { return id; }
    public double getBaseSalary() { return baseSalary; }
    public double getBonus() { return bonus; }
    public double getDeductions() { return deductions; }
}
