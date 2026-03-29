public abstract class Employee extends Person implements Payment {
  private String empId;
  private int startYear = getCurrentYear();
  private Payment payment;

  public Employee(String lname, String fname){
    super(lname, fname);

    this.empId = getEmployeeIdString() + Integer.toString(getRandomId(ConstantValues.MIN_EMP_ID, ConstantValues.MAX_EMP_ID));
  }

  @Override
  public String getIdString(){
    return empId;
  }

  public int getStartYear() {
    return startYear;
  }

  public void setStartYear(final int startYear){
    if(startYear <= getCurrentYear() && startYear > 2000){
      this.startYear = startYear;
    }
  }

  public Payment getPayment() {
    return payment;
  }

  public void setPayment(Payment payment){
    if(payment != null){
      this.payment = payment;
    }
  }

  @Override
  public double calculatePayment(){
    if(payment == null){
      return 0.0;
    }
    return payment.calculatePayment();
  }

  protected abstract String getEmployeeIdString();
}