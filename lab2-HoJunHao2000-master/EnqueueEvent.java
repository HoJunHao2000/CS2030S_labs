import java.util.LinkedList;
/**
 * @author Ho Jun Hao (Group 8A)
 */

class EnqueueEvent extends Event {
  private Shop shop;
 
  public EnqueueEvent(Shop shop, double timeStamp) {
    super(timeStamp);
    this.shop = shop;
  }

  @Override
  public String toString() {
    String customerId = this.shop.customerToString();
    String queue = Shop.q.toString();
    return super.toString() + String.format(": %s joined queue %s", customerId, queue);
  }

  @Override
  public Event[] simulate() {
    Shop.addCustomerToQueue(this.shop);
    return new Event[] {};
  }
}
