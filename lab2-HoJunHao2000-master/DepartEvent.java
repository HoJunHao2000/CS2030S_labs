import java.util.LinkedList;
/**
 * @author Ho Jun Hao (Group 8A)
 */

class DepartEvent extends Event {
  private Shop shop;

  public DepartEvent(Shop shop, double timeStamp) {
    super(timeStamp);
    this.shop = shop;
  }

  @Override
  public String toString() {
    String customerId = this.shop.customerToString();
    return super.toString() + String.format(": %s departed", customerId);
  }

  @Override
  public Event[] simulate() {
    if (!Shop.counters.isEmpty() && !Shop.q.isEmpty()) {
      Customer queuing = (Customer) Shop.q.deq();
      Shop newShop = new Shop(queuing);
      newShop.assignCounter();
      return new Event[] {
        new BeginEvent(newShop, super.getTime())
      };
    } else {
      return new Event[] {};
    }
  }
}
