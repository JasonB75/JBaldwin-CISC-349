//Base empoloyee class that the other three will inherit from
class Employee {
  public String name;
  public String job_title;
  public int ssn;
  
  public Employee(String name, String job_title, int ssn) {
    this.name = name;
    this.job_title = job_title;
    this.ssn = ssn;
  }

  public String getName() {
    return this.name;
  }
  public String getJobTitle() {
    return this.job_title;
  }
  public int getSsn() {
    return this.ssn;
  }
  
}
