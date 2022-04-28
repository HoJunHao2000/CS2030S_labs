import java.util.Scanner;
/**
 * This class implements a shop simulation.
 *
 * @author Ho Jun Hao (Group 8A)
 * @version CS2030S AY21/22 Semester 2
 */

class ShopSimulation extends Simulation {
  /** 
   * The list of customer arrival events to populate
   * the simulation with.
   */
  public Event[] initEvents;

  public Queue<Customer> shopQueue;

  public Array<Counter> counters;

  public Shop shop;
  /** 
   * Constructor for a shop simulation. 
   *
   * @param sc A scanner to read the parameters from.  The first
   *           integer scanned is the number of customers; followed
   *           by the number of service counters.  Next is a 
   *           sequence of (arrival time, service time) pair, each
   *           pair represents a customer.
   */
 
  /**
   * A queue has been created for the customers
   *
   * A generic Array has replaced the old boolean array to track all available
   * counters
   *
   * Each of the counters have their own queue
   *
   * The queue and Array are combined into a single class called Shop.
   * */
  public ShopSimulation(Scanner sc) {
    initEvents = new Event[sc.nextInt()];
    
    int numOfCounters = sc.nextInt();
    int counterQueueLength = sc.nextInt();
    counters = new Array<Counter>(numOfCounters);
    for (int i = 0; i < numOfCounters; i++) {
      counters.set(i, new Counter(counterQueueLength));
    }

    int queueMaxLength = sc.nextInt();
    shopQueue = new Queue<Customer>(queueMaxLength);

    shop = new Shop(shopQueue, counters);

    int id = 0;
    while (sc.hasNextDouble()) {
      double arrivalTime = sc.nextDouble();
      double serviceTime = sc.nextDouble();
      Customer customer = new Customer(arrivalTime, serviceTime);
      initEvents[id] = new ArrivalEvent(shop, customer, arrivalTime);
      id += 1;
    }
  }

  /**
   * Retrieve an array of events to populate the 
   * simulator with.
   *
   * @return An array of events for the simulator.
   */
  @Override
  public Event[] getInitialEvents() {
    return initEvents;
  }
}
