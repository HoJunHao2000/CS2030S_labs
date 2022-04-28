/**
 * A generic box storing an item.
 * CS2030S Lab 4
 * AY21/22 Semester 2
 *
 * @author Ho Jun Hao (Group 8A)
 */

class Box<T> {
  private final T content;
  private static final Box<?> EMPTY_BOX = new Box<>(null);

  private Box(T content) {
    this.content = content;
  }

  public static <T> Box<T> of(T item) {
    if (item == null) {
      return null;
    }

    return new Box<T>(item);
  } 

  @SuppressWarnings("unchecked")
  public static <T> Box<T> empty() {
    return (Box<T>) EMPTY_BOX;
  }

  public boolean isPresent() {
    return this.content != null ? true : false;
  }
  
  public static <T> Box<T> ofNullable(T item) {
    if (item == null) {
      return empty();
    } else {
      return of(item);
    }
  }

  public Box<T> filter(BooleanCondition<? super T> cond) {
    if (content == null || !cond.test(content)) {
      return empty(); 
    } else {
      return this;
    }
  }

  public <U> Box<U> map(Transformer<? super T, U> trans) {
    if (this.content == null) {
      return empty();
    }

    U newContent = trans.transform(content);
    return Box.<U>ofNullable(newContent);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (obj instanceof Box<?>) {
      Box<?> converted = (Box<?>) obj;
      if (this.content == converted.content) {
        return true;
      }
      
      if (this.content == null || converted.content == null) {
        return false;
      }

      return this.content.equals(converted.content);
    }

    return false;
  }

  @Override
  public String toString() {
    return content == null ? "[]" : "[" + content.toString() + "]";
  }
}
