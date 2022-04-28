/**
 * @author Ho Jun Hao (Group 8A)
 */

/*
 * created Customer class to track customer, contains customer id, arrival time, service time
 *
 * contains a method call getEndTime() that helps to calculate the leaving time if served
 */
class Customer {
  private final int customerId;
  private final double arrivalTime;
  private final double serviceTime;

  public Customer(int customerId, double arrivalTime, double serviceTime) {
    this.customerId = customerId;
    this.arrivalTime = arrivalTime;
    this.serviceTime = serviceTime;
  }

  public double getServiceTime() {
    return this.serviceTime;
  }

  @Override
  public String toString() {
    return String.format("C%d", this.customerId);
  }
}
