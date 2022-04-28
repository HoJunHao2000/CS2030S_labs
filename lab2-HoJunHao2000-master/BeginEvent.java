import java.util.LinkedList;
/**
 * @author Ho Jun Hao (Group 8A)
 */

class BeginEvent extends Event {  
  private Shop shop;

  public BeginEvent(Shop shop, double timeStamp) {
    super(timeStamp);
    this.shop = shop;
  }
  
  @Override
  public String toString() {
    String customerId = this.shop.customerToString();
    String counterId = this.shop.counterToString();
    return super.toString() + String.format(": %s service begin %s", customerId, counterId);
  }

  @Override
  public Event[] simulate() {
    double endTime = this.shop.calcEndTime(super.getTime());
    return new Event[] { 
        new EndEvent(this.shop, endTime)
    };
  }
}
