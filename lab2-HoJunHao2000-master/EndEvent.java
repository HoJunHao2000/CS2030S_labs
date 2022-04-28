import java.util.LinkedList;
/**
 * @author Ho Jun Hao (Group 8A)
 */

/*
 * adds the counter to the back of the queue and starts DepartEvent.
 */

class EndEvent extends Event {
  private Shop shop;
 
  public EndEvent(Shop shop, double timeStamp) {
    super(timeStamp);
    this.shop = shop; 
  }

  @Override
  public String toString() {
    String customerId = this.shop.customerToString();
    String counterId = this.shop.counterToString();
    return super.toString() + String.format(": %s service done %s", customerId, counterId);
  }

  @Override
  public Event[] simulate() {
    Shop.addCounterToCounters(this.shop);
    return new Event[] {
      new DepartEvent(this.shop, super.getTime())
    };
  }
}
