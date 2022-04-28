/**
 * @author Ho Jun Hao (Group 8A)
 */

/**
 * Check if the counter queues are not empty. If they are not empty, check if the shop queue has
 * customers. If there is, then enq the customer from the shop queue to the min() counter
 * and BeginEvent for the next customer in the counter queue. If there is no one in the shop 
 * queue then just BeginEvent for the next person in counter queue.
 *
 * If the counter queues are empty. This can only mean there are no more customers or
 * that the counter queue length is 0. Thus we check if the shop queue has customers, 
 * if so, BeginEvent, taking customers straight from the shop queue.
 *
 * Else, this means that there are no more customers.
 * */

class EndEvent extends Event {
  private Shop shop;
  private Customer customer;
  private Counter counter;
 
  public EndEvent(Shop shop, Customer customer, Counter counter, double timeStamp) {
    super(timeStamp);
    this.shop = shop;
    this.customer = customer;
    this.counter = counter;
  }

  @Override
  public String toString() {
    String customerId = this.customer.toString();
    String counterId = this.counter.toString();
    return super.toString() + String.format(": %s service done (by %s", customerId, counterId);
  }

  @Override
  public Event[] simulate() {
    this.counter.serveStatus(false);
    Counter nextCounter = this.shop.getNextCounter();
    if (!this.counter.queueIsEmpty()) {
      this.counter.serveStatus(true);
      Customer nextCounterCustomer = this.counter.getNextCustomer();
      Customer nextShopCustomer = this.shop.getNextCustomer();
      if (nextShopCustomer != null) {
        return new Event[] {
          new DepartEvent(this.shop, this.customer, super.getTime()),
          new BeginEvent(this.shop, nextCounterCustomer, this.counter, super.getTime()),
          new CounterEnqueueEvent(this.shop, nextShopCustomer, nextCounter, super.getTime())
        };
      } else {
        return new Event[] {
          new DepartEvent(this.shop, this.customer, super.getTime()),
          new BeginEvent(this.shop, nextCounterCustomer, this.counter, super.getTime())
        };
      }
    } else if (!this.shop.shopQueueEmpty()) {
      this.counter.serveStatus(true);
      Customer nextShopQueue = this.shop.getNextCustomer();
      return new Event[] {
        new DepartEvent(this.shop, this.customer, super.getTime()),
        new BeginEvent(this.shop, nextShopQueue, nextCounter, super.getTime())
      };
    } else {
      return new Event[] {
        new DepartEvent(this.shop, this.customer, super.getTime())
      };
    }
  }
}
