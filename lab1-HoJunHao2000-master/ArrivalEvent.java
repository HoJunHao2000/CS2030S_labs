// @author Ho Jun Hao (Group 8A)
/*
 * Created a subclass of Event for every type of Event.
 * Similar to original ShopEvent but split up.
 * checks if queue is empty. if true, start DepartEvent, else start to serve.
 * Counter is removed from queue when serving.
 * */

import java.util.LinkedList;

class ArrivalEvent extends Event {
  private Customer customer;
  private LinkedList<Counter> queue;
 
  public ArrivalEvent(Customer customer, LinkedList<Counter> queue) {
    super(customer.getTime());
    this.customer = customer;
    this.queue = queue;
  }

  @Override
  public String toString() {
    return super.toString() + String.format(": Customer %d arrives", this.customer.getId());
  }

  @Override
  public Event[] simulate() {
    if (this.queue.isEmpty()) {
      return new Event[] {
        new DepartEvent(this.customer, this.customer.getTime())
      };
    } else {
      Counter counter = this.queue.remove();
      return new Event[] { 
         new BeginEvent(this.customer, this.queue, counter)
      };
    }
  }
}
