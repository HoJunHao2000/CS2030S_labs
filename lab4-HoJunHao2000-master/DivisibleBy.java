/**
 * A boolean condition with an integer parameter y that can be 
 * apply to another integer x.  Returns true if x is divisible 
 * by y, false otherwise.
 * CS2030S Lab 4
 * AY21/22 Semester 2
 *
 * @author Ho Jun Hao (Group 8A)
 */

class DivisibleBy implements BooleanCondition<Integer> {
  private int value;

  public DivisibleBy(int value) {
    this.value = value;
  }

  @Override
  public boolean test(Integer otherValue) {
    if (otherValue == null) {
      return false;
    }

    return otherValue % this.value == 0;
  }

}
