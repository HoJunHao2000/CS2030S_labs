/**
 * @author Ho Jun Hao (Group 8A)
 */

class DepartEvent extends Event {
  private Shop shop;
  private Customer customer;

  public DepartEvent(Shop shop, Customer customer, double timeStamp) {
    super(timeStamp);
    this.shop = shop;
    this.customer = customer;
  }

  @Override
  public String toString() {
    String customerId = this.customer.toString();
    return super.toString() + String.format(": %s departed", customerId);
  }

  @Override
  public Event[] simulate() {
    return new Event[] {}; 
  }
}
