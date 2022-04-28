// @author Ho Jun Hao (Group 8A)
import java.util.LinkedList;
/*
 *  adds the counter to the back of the queue and starts DepartEvent.
 * */
class EndEvent extends Event {
  private Customer customer;
  private LinkedList<Counter> queue;
  private Counter counter;
 
  public EndEvent (Customer customer, LinkedList<Counter> queue, Counter counter) {
    super(customer.getEndTime());
    this.customer = customer;
    this.counter = counter;
    this.queue = queue;
  }

  @Override
   public String toString() {
      return super.toString() + String.format(": Customer %d service done (by Counter %d)", this.customer.getId(), this.counter.getId());
    }

  @Override
  public Event[] simulate() {
    this.queue.add(this.counter);
    return new Event[] {
      new DepartEvent(this.customer, this.customer.getEndTime())
    };
  }
}
