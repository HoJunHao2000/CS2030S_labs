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
  private static int count = 0;

  public Customer(double arrivalTime, double serviceTime) {
    this.customerId = count++;
    this.arrivalTime = arrivalTime;
    this.serviceTime = serviceTime;
  }

  public double calcEndTime(double time) {
    return this.serviceTime + time;
  }

  @Override
  public String toString() {
    return String.format("C%d", this.customerId);
  }
}
