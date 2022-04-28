/**
 * @author Ho Jun Hao (Group 8A)
 */

class CounterEnqueueEvent extends Event {
  private Shop shop;
  private Customer customer;
  private Counter counter;
 
  public CounterEnqueueEvent(Shop shop, Customer customer, Counter counter, double timeStamp) {
    super(timeStamp);
    this.shop = shop;
    this.customer = customer;
    this.counter = counter;
  }

  @Override
  public String toString() {
    String custId = this.customer.toString();
    String countId = this.counter.toString();
    return super.toString() + String.format(": %s joined counter queue (at %s", custId, countId);
  }

  @Override
  public Event[] simulate() {
    this.counter.addToCounterQueue(this.customer);
    return new Event[] {};
  }
}
