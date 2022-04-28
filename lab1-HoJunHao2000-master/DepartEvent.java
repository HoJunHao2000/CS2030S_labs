// @author Ho Jun Hao (Group 8A)
import java.util.LinkedList;

class DepartEvent extends Event {
  private Customer customer;

  public DepartEvent(Customer customer, double Time) {
    super(Time);
    this.customer = customer;
  }

  @Override
  public String toString() {
    return super.toString() + String.format(": Customer %d departed", this.customer.getId());
  }

  @Override
  public Event[] simulate() {
    return new Event[] {};
  }
}
