/**
 * Takes an item and return the item in a box.
 * CS2030S Lab 4
 * AY21/22 Semester 2
 *
 * @author Ho Jun Hao (Group 8A)
 */

class BoxIt<T> implements Transformer<T, Box<T>> {
  public BoxIt() {
  }

  @Override
  public Box<T> transform(T t) {
    return Box.<T>ofNullable(t); 
  }
}

