/**
 * @author Ho Jun Hao (Group 8A)
 */

/**
 * Created a subclass of Event for every type of Event.
 * Similar to original ShopEvent but split up.
 *
 * First check if there is an empty counter with no queue. If there is, BeginEvent. 
 * 
 * If not you want to check if the counter queues are full, if it is full, check if 
 * the shop queue is full too, if the shop queue is full, DepartEvent, else EnqueueEvent. 
 *
 * Otherwise, if counter queues are not full, add customer to the min() counter's queue.
 */
class ArrivalEvent extends Event {
  private Shop shop;
  private Customer customer;

  public ArrivalEvent(Shop shop, Customer customer, double timeStamp) {
    super(timeStamp);
    this.shop = shop;
    this.customer = customer;
  }

  @Override
  public String toString() {
    String customerId = this.customer.toString();
    String queue = this.shop.queueToString();
    return super.toString() + String.format(": %s arrived %s", customerId, queue);
  }

  @Override
  public Event[] simulate() {
    Counter counter = this.shop.getNextCounter();
    if (this.shop.checkEmptyCounter()) {
      counter.serveStatus(true);
      return new Event[] {
        new BeginEvent(this.shop, this.customer, counter, super.getTime())
      };  
    } else if (this.shop.counterQueueFull()) {
      if (this.shop.shopQueueFull()) {
        return new Event[] {
          new DepartEvent(this.shop, this.customer, super.getTime())
        };
      } else {
        return new Event[] {
          new EnqueueEvent(this.shop, this.customer, super.getTime())
        };
      } 
    } else {
      return new Event[] {
        new CounterEnqueueEvent(this.shop, this.customer, counter, super.getTime())
      }; 
    }
  }
}
