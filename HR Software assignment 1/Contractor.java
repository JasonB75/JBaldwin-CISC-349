class Contractor extends Employee{

  public int pay_rate;

  public Contractor(String name, String job_title, int ssn, int pay_rate){
    super(name, job_title, ssn);
    this.pay_rate = pay_rate;
  }

  public int getPayRate(){
    return this.pay_rate;
  }

  @Override
  public String toString(){
    return "Name: " + this.name + "\nJob Title: " + this.job_title + "\nSSN: " + this.ssn  + "\nHourly rate: $" + this.pay_rate+ "\n";
  }


}