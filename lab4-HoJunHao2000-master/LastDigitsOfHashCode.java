/**
 * A transformer with a parameter k that takes in an object x 
 * and outputs the last k digits of the hash value of x.
 * CS2030S Lab 4
 * AY21/22 Semester 2
 *
 * @author Ho Jun Hao (Group 8A))
 */

class LastDigitsOfHashCode implements Transformer<Object, Integer> {
  private int k;

  public LastDigitsOfHashCode(int k) {
    this.k = k;
  }

  @Override
  public Integer transform(Object t) {
    if (t == null) {
      return null;
    }
    int lastK = 1;
    for (int i = 0; i < k; i++) {
      lastK *= 10;
    }
    return Math.abs(t.hashCode()) % lastK;
  }
}

