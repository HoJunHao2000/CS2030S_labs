package cs2030s.fp;

/**
 * A Lazy generic object that allows to carry out both lazy and eager evaluation.
 *
 * @author Ho Jun Hao (Group 8A)
 * @version CS2030S AY 21/22 Sem 2
 */

public class Lazy<T> {
  /**
   * variables producer and value.
   */
  private Producer<? extends T> producer;
  private Maybe<T> value;
  
  /**
   * Private constructor used to initialize Lazy to the given one.
   *  
   * @param s A producer.
   * @param v A maybe.
   */
  private Lazy(Producer<? extends T> s, Maybe<T> v) {
    this.producer = s;
    this.value = v;
  }

  /**
   * Factory method of used to create Lazy instance holding a given value v
   * and null for producer.
   *
   * @param <T> The type of element inside Maybe and the type produced by Producer.
   * @param v A value of Type T that Lazy takes.
   * @return The newly instantiated Lazy.
   */
  public static <T> Lazy<T> of(T v) {
    return new Lazy<T>(null, Maybe.some(v));
  }
  
  /**
   * Factory method used to create Lazy instance holding a given producer of s
   * and value of Maybe.none().
   *
   * @param <T> The type of element inside Maybe and the type produced by Producer.
   * @param s A producer of type T or T's subtype.
   * @return The newly instantiated Lazy.
   */
  public static <T> Lazy<T> of(Producer<? extends T> s) {
    return new Lazy<T>(s, Maybe.none());
  }
  
  /**
   * A method that returns the value in Lazy object.
   * If value is already computed,
   * return it, if not compute, cache and return. Computing is only done once.
   *
   * @return The value or computed value from producer.
   */
  public T get() {
    T obj = this.value.orElseGet(this.producer);
    this.value = Maybe.some(obj);
    return obj;
  }

  /**
   * Return the String representation of the Lazy.
   * if value not yet computed, return "?" else return the string represenation of value.
   *
   * @return The string representation of Lazy.
   */
  @Override
  public String toString() {
    Maybe<String> m = this.value.map(t -> String.valueOf(t));
    return m.orElse("?");
  }

  /**
   * Transforms the value or computed value of Lazy into another type U, then returns
   * a Lazy container a Producer of U.
   *
   * @param <U> The type of element transformed to by the Transformer.
   * @param t A Transformer from type T to U.
   * @return A Lazy containing producer of type U.
   */
  public <U> Lazy<U> map(Transformer<? super T, ? extends U> t) {
    return Lazy.of(() -> t.transform(this.get()));
  }

  /**
   * Transform the value or computed value of Lazy into a Lazy of Type U, unlike map however,
   * it returns the Lazy of Type U and does not put the transformed value into another Lazy.
   *
   * @param <U> The type of element transformed to by the transformer
   * @param t A transformer from type T to Lazy of type U
   * @return A Lazy of type U.
   */
  public <U> Lazy<U> flatMap(Transformer<? super T, ? extends Lazy<? extends U>> t) {
    return Lazy.of(() -> t.transform(this.get()).get());
  }

  /**
   * Return a Lazy of type Boolean whose value indicates if the current lazy value passes the given
   * BooleanCondition.
   *
   * @param bc A BooleanCondition of type T
   * @return A lazy of type Boolean
   */
  public Lazy<Boolean> filter(BooleanCondition<? super T> bc) {
    return Lazy.of(() -> bc.test(this.get()));
  }
  
  /**
   * Compares two Lazy and returns a boolean indicating if they are equivalent to each other.
   *
   * @param obj The object to be compared
   * @return A boolean indicating if they are equivalent
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (obj instanceof Lazy<?>) {
      Lazy<?> convertedObj = (Lazy<?>) obj;
      if (this.value == convertedObj.value) {
        return true;
      }

      if (this.get() == convertedObj.get()) {
        return true;
      }

      return this.value.equals(convertedObj.value);
    }
      
    return false;
  }
  
  /**
   * Combines two Lazys (may or may not be of the same type) into a single Lazy of type R.
   *
   * @param <S> The type of element inside the Lazy provided in parameters
   * @param <R> The type of element inside the lazy returned after combining the two lazys
   * @param c A Combiner of parameters T, S and R
   * @param lz A Lazy of Type S
   * @return A Lazy containing a Producer of Type R.
   */
  public <S, R> Lazy<R> combine(Lazy<S> lz, Combiner<? super T, ? super S, ? extends R> c) {
    return Lazy.of(() -> c.combine(this.get(), lz.get()));
  } 
}
