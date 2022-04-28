/**
 * The Array<T> for CS2030S 
 *
 * @author Ho Jun Hao (Group 8A)
 * @version CS2030S AY21/22 Semester 2
 */
class Array<T extends Comparable<T>> {
  private T[] array;

  Array(int size) {
    @SuppressWarnings({"unchecked", "rawtypes"})
    T[] a = (T[]) new Comparable[size];
    this.array = a;
  }

  public void set(int index, T item) {
    this.array[index] = item;
  }

  public T get(int index) {
    return this.array[index];
  }

  public T min() {
    T minimum = this.array[0];
    for (int i = 0; i < array.length; i++) {
      if (this.array[i] != null && minimum.compareTo(this.array[i]) > 0) {
        minimum = this.array[i];
      }
    }
    return minimum;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder("[ ");
    for (int i = 0; i < array.length; i++) {
      s.append(i + ":" + array[i]);
      if (i != array.length - 1) {
        s.append(", ");
      }
    }
    return s.append(" ]").toString();
  }
}
