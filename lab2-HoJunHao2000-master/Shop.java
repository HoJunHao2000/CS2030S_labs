import java.util.LinkedList;
/**
 * @author Ho Jun Hao (Group 8A)
 * */

class Shop {
  public static Queue q;
  public static LinkedList<Counter> counters = new LinkedList<Counter>();
  private Customer customer;
  private Counter counter;

  public Shop(Customer customer) {
    this.customer = customer;
  }

  public void assignCounter() {
    this.counter = counters.remove();
  }

  public String customerToString() {
    return this.customer.toString();
  }

  public String counterToString() {
    return this.counter.toString();
  }

  public static void addCustomerToQueue(Shop shop) {
    q.enq(shop.customer);
  }

  public double calcEndTime(double time) {
    return this.customer.getServiceTime() + time;
  }

  public static void addCounterToCounters(Shop shop) {
    counters.add(shop.counter);
  }  
}
