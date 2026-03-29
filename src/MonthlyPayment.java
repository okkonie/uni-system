public class MonthlyPayment implements Payment {
  private double salary;

  public double getSalary() {
    return salary;
  }

  public void setSalary(double salary) {
    if(salary > 0.0) this.salary = salary;
  }

  @Override
  public double calculatePayment() {
    return getSalary();
  }
}
