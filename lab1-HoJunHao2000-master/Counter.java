// @author Ho Jun Hao (Group 8A)
/*
  Created Counter class to keep track of counters. Contains counter id
 * */
class Counter {
  private int id;
  private static int count = 0;

  public Counter() {
    this.id = count++;
  }

  public int getId() {
    return this.id;
  }
}
