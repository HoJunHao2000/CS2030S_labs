import java.util.Scanner;
import java.util.LinkedList;
/**
 * This class implements a shop simulation.
 *
 * @author Ho Jun Hao (Group 8A)
 * @version CS2030S AY21/22 Semester 2
 */ 
class ShopSimulation extends Simulation {
  /** 
   * The availability of counters in the shop. 
   */

//  public boolean[] available;

/*
 *  I used a queue to replace the array of booleans. This way so long the queue is not empty,
 *  I can just take the next available counter.
 * */

  
  public LinkedList<Counter> queue = new LinkedList<Counter>();
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
  public ShopSimulation(Scanner sc) {
    initEvents = new Event[sc.nextInt()];
    int numOfCounters = sc.nextInt();
    
    for (int i = 0; i < numOfCounters; i++) {
      queue.add(new Counter());
    }

    int id = 0;
    while (sc.hasNextDouble()) {
      double arrivalTime = sc.nextDouble();
      double serviceTime = sc.nextDouble();
      initEvents[id] = new ArrivalEvent(new Customer(id, arrivalTime, serviceTime), this.queue);
      id += 1;
    }

 /*
  *  available = new boolean[numOfCounters];
  *  for (int i = 0; i < numOfCounters; i++) {
  *    available[i] = true;
  *  }
  *                                          
  *  int id = 0;
  *  while (sc.hasNextDouble()) {
  *    double arrivalTime = sc.nextDouble();
  *    double serviceTime = sc.nextDouble();
  *    initEvents[id] = new ShopEvent(ShopEvent.ARRIVAL, 
  *        arrivalTime, id, serviceTime, available);
  *    id += 1;
  *  }
  */
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
