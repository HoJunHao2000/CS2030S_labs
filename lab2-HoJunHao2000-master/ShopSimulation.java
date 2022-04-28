import java.util.LinkedList;
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
   * A linkedlist has replaced the old boolean array to track all available
   * counters
   *
   * The queue, linkedlist,customer, and counter are all combined into a
   * single class called Shop.
   *
   * the queue and linkedlist are static varibles.
   * */
  public ShopSimulation(Scanner sc) {
    initEvents = new Event[sc.nextInt()];
    
    int numOfCounters = sc.nextInt();
    for (int i = 0; i < numOfCounters; i++) {
      Shop.counters.add(new Counter());
    }

    int queueMaxLength = sc.nextInt();
    Shop.q = new Queue(queueMaxLength);

    int id = 0;
    while (sc.hasNextDouble()) {
      double arrivalTime = sc.nextDouble();
      double serviceTime = sc.nextDouble();
      Customer customer = new Customer(id, arrivalTime, serviceTime);
      Shop shop = new Shop(customer);
      initEvents[id] = new ArrivalEvent(shop, arrivalTime);
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
