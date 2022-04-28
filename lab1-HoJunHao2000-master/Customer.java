// author Ho Jun Hao (Group 8A)
/*
 * created Customer class to track customer, contains customer id, arrival time, service time
 *
 * contains a method call getEndTime() that helps to calculate the leaving time if served
 * */
class Customer {
  private final int customerId;
  private final double arrivalTime;
  private final double serviceTime;

  public Customer(int customerId, double arrivalTime, double serviceTime) {
    this.customerId = customerId;
    this.arrivalTime = arrivalTime;
    this.serviceTime = serviceTime;
  }

  public int getId() {
    return this.customerId;
  }

  public double getTime() {
    return this.arrivalTime;
  }

  public double getServiceTime() {
    return this.serviceTime;
  }

  public double getEndTime() {
    return this.arrivalTime + this.serviceTime;
  }
}
