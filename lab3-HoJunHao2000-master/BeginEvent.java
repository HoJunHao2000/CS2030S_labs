/**
 * @author Ho Jun Hao (Group 8A)
 */

class BeginEvent extends Event {  
  private Shop shop;
  private Customer customer;
  private Counter counter;

  public BeginEvent(Shop shop, Customer customer, Counter counter, double timeStamp) {
    super(timeStamp);
    this.shop = shop;
    this.customer = customer;
    this.counter = counter;
  }
  
  @Override
  public String toString() {
    String customerId = this.customer.toString();
    String counterId = this.counter.toString();
    return super.toString() + String.format(": %s service begin (by %s", customerId, counterId);
  }

  @Override
  public Event[] simulate() {
    double endTime = this.customer.calcEndTime(super.getTime());
    return new Event[] { 
        new EndEvent(this.shop, this.customer, this.counter, endTime)
    };
  }
}
