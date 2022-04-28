import java.util.LinkedList;
/**
 * @author Ho Jun Hao (Group 8A)
 */

/**
 * Created a subclass of Event for every type of Event.
 * Similar to original ShopEvent but split up.
 * First check if linkedlist is empty. if it is not empty, BeginEvent. If it is empty,
 * check if queue is full. if queue is also full, DepartEvent, else, EnqueueEvent.
 */
class ArrivalEvent extends Event {
  private Shop shop;

  public ArrivalEvent(Shop shop, double timeStamp) {
    super(timeStamp);
    this.shop = shop;
  }

  @Override
  public String toString() {
    String customerId = this.shop.customerToString();
    String queue = Shop.q.toString();
    return super.toString() + String.format(": %s arrived %s", customerId, queue);
  }

  @Override
  public Event[] simulate() {
    if (Shop.counters.isEmpty()) {
      if (Shop.q.isFull()) {
        return new Event[] {
          new DepartEvent(this.shop, super.getTime())
        };
      } else {
        return new Event[] {
          new EnqueueEvent(this.shop, super.getTime())
        };
      }
    } else {
      this.shop.assignCounter();
      return new Event[] {
        new BeginEvent(this.shop, super.getTime())
      };
    }
  }
}
