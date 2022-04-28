/**
 * @author Ho Jun Hao (Group 8A)
 */

class EnqueueEvent extends Event {
  private Shop shop;
  private Customer customer;
 
  public EnqueueEvent(Shop shop, Customer customer, double timeStamp) {
    super(timeStamp);
    this.shop = shop;
    this.customer = customer;
  }

  @Override
  public String toString() {
    String customerId = this.customer.toString();
    String queue = this.shop.queueToString();
    return super.toString() + String.format(": %s joined shop queue %s", customerId, queue);
  }

  @Override
  public Event[] simulate() {
    this.shop.addToQueue(this.customer);
    return new Event[] {};
  }
}
