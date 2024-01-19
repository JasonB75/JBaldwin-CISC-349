class FT_Salary extends Employee {

  public int hours;
  public int salary;
  
  public FT_Salary(String name, String job_title, int ssn, int salary) {
    super(name, job_title, ssn);
    this.hours = 45;
    this.salary = salary;
  }

  public int getSalary() {
    return this.salary;
  }
  @Override
  public String toString(){
    return "Name: " + this.name + "\nJob Title: " + this.job_title + "\nSSN: " + this.ssn + "\nSalary: " + this.salary + "\n";
  } 

  
}
