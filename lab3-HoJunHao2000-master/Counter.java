/**
 * @author Ho Jun Hao (Group 8A)
 */

/*
 * Created Counter class to keep track of counters. Contains counter id
 */
class Counter implements Comparable<Counter> {
  private int id;
  private static int count = 0;
  private Queue<Customer> counterQueue;
  private boolean serving = false;

  public Counter(int size) {
    this.counterQueue = new Queue<Customer>(size);
    this.id = count++;
  }
  
  public boolean queueIsFull() {
    return this.counterQueue.isFull();
  }
  
  public boolean queueIsEmpty() {
    return this.counterQueue.isEmpty();
  }

  public void serveStatus(boolean t) {
    serving = t;
  }

  public boolean isServing() {
    return serving == true;
  }

  public void addToCounterQueue(Customer customer) {
    this.counterQueue.enq(customer);
  }

  public Customer getNextCustomer() {
    return this.counterQueue.deq();
  }

  @Override
  public int compareTo(Counter c) {
    int firstLen = this.counterQueue.length();
    int secondLen = c.counterQueue.length();
    int firstId = this.id;
    int secondId = c.id;
    boolean firstBool = this.serving;
    boolean secondBool = c.serving;
    if (firstBool == false && secondBool == true) {
      return -1;
    } else if (firstBool == true && secondBool == false) {
      return 1;
    } else if (firstLen > secondLen) {
      return 1;
    } else if (firstLen < secondLen) {
      return -1;
    } else if (firstId < secondId) {
      return -1;
    } else {
      return 1;
    }
  }

  @Override
  public String toString() {
    return String.format("S%d %s)", this.id, this.counterQueue.toString());
  }
}
