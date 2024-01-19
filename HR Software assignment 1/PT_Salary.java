class PT_Salary extends Employee {
  public int hours;
  public int salary;

  public PT_Salary(String name, String job_title, int ssn, int salary, int hours) {
    super(name, job_title, ssn);
    this.hours = hours;
    this.salary = salary;
  }
  public int getSalary() {
    return this.salary;
  }
  public int getHours() {
    return this.hours;
  }

  @Override
  public String toString(){
    return "Name: " + this.name + "\nJob Title: " + this.job_title + "\nSSN: " + this.ssn + "\nSalary: " + this.salary + "\nHours: " + this.hours+ "\n";
  }
  
}