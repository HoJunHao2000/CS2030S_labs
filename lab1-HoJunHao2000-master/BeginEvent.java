// @author Ho Jun Hao (Group 8A)
import java.util.LinkedList;

class BeginEvent extends Event {
  private Customer customer;
  private LinkedList<Counter> queue;
  private Counter counter;
  private double serviceTime;

  public BeginEvent (Customer customer, LinkedList<Counter> queue, Counter counter) {
    super(customer.getTime());
    this.customer = customer;
    this.counter = counter;
    this.queue = queue;
  }
  
  @Override
  public String toString() {
    return super.toString() + String.format(": Customer %d service begin (by Counter %d)", this.customer.getId(), this.counter.getId());
  }

  @Override
  public Event[] simulate() {
    return new Event[] { 
        new EndEvent(this.customer, this.queue, this.counter)
    };
  }
}
