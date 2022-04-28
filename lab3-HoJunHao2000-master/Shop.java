/**
 * @author Ho Jun Hao (Group 8A)
 * */

class Shop {
  private Queue<Customer> queue;
  private Array<Counter> counters;

  public Shop(Queue<Customer> queue, Array<Counter> counters) {
    this.queue = queue;
    this.counters = counters;
  }

  public String queueToString() {
    return this.queue.toString();
  }

  public boolean counterQueueFull() {
    return this.counters.min().queueIsFull();
  }

  public boolean checkEmptyCounter() {
    return this.counters.min().queueIsEmpty() && !this.counters.min().isServing();
  }

  public boolean shopQueueEmpty() {
    return this.queue.isEmpty();
  }

  public boolean shopQueueFull() {
    return this.queue.isFull();
  }

  public Counter getNextCounter() {
    return this.counters.min();
  }

  public Customer getNextCustomer() {
    return this.queue.deq();
  }

  public void addToQueue(Customer customer) {
    this.queue.enq(customer);
  }
}
