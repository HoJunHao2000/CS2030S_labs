package cs2030s.fp;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class InfiniteList<T> {

  private final Lazy<Maybe<T>> head;
  private final Lazy<InfiniteList<T>> tail;
  private static final InfiniteList<Object> SENTINEL = new Sentinel();

  private static final class Sentinel extends InfiniteList<Object> {
    /**
     * A method used to return the head of Sentinel InfiniteList.
     * As sentinel is an empty InfiniteList, nothing would be returned, instead
     * NoSuchElementException would be thrown.
     *
     * @throws NoSuchElementException whenever method is called, will always throw this exception
     */
    @Override
    public Object head() {
      throw new NoSuchElementException();
    }

    /**
     * A method used to return the tail of Sentinel InfiniteList.
     * As sentinel is an empty InfiniteList, it would just return another sentinel.
     *
     * @return another sentinel
     */
    @Override
    public InfiniteList<Object> tail() {
      return sentinel();
    }

    /**
     * A method used to map elements in sentinel InfiniteList.
     * As sentinel does not have any elements, it would just return another sentinel.
     *
     * @param <R> the return type after mapping
     * @param mapper a Transformer from type Object to type R
     * @return another sentinel
     */
    @Override
    public <R> InfiniteList<R> map(Transformer<? super Object, ? extends R> mapper) {
      return sentinel();
    }

    /**
     * A method used to filter an InfiniteList based on a BooleanCondition.
     * As sentinel does not have any elements to filter, it would return another sentinel.
     *
     * @param predicate a BooleanCondition for Type Object
     * @return another sentinel
     */
    @Override
    public InfiniteList<Object> filter(BooleanCondition<? super Object> predicate) {
      return sentinel();
    }

    /**
     * Used to truncate an InfiniteList to specific length.
     * As sentinel is an empty InfiniteList with no size to begin with, it woudl just
     * return another sentinel.
     *
     * @param n length of truncated infiniteList
     * @return another sentinel
     */
    @Override
    public InfiniteList<Object> limit(long n) {
      return sentinel();
    }

    /**
     * Returns the String representation of sentinel.
     *
     * @return string representation of sentinel
     */
    @Override
    public String toString() {
      return "-";
    }

    /**
     * A method used to truncate an InfiniteList the moment an element fails the BooleanCondition.
     * As a sentinel as no elements to test, it would just return another sentinel.
     *
     * @param predicate a BooleanCondition for type Object
     * @return another sentinel
     */
    @Override
    public InfiniteList<Object> takeWhile(BooleanCondition<? super Object> predicate) {
      return sentinel();
    }

    /**
     * A method used to accumalate the elements in the InfiniteList.
     * As a sentinel as no elements to begin with it would return the inital identity.
     *
     * @param <U> the return type and type of identity
     * @param identity the initial value
     * @param acc A combiner that takes in elements of Type U and Type Object, returning Type U
     */
    @Override 
    public <U> U reduce(U identity, Combiner<U, ? super Object, U> acc) {
      return identity;
    }

    /**
     * A method used to return the size of the InfiniteList.
     * As sentinel as no elements, it returns 0.
     *
     * @return 0
     */
    @Override
    public long count() {
      return 0;
    }
  }

  /**
   * Private constructor for an empty InfiniteList.
   */
  private InfiniteList() { 
    this.head = null;
    this.tail = null;
  }

  /**
   * Static method to create an InfiniteList using a Producer.
   *
   * @param <T> type of element inside InfiniteList
   * @param producer a producer that produces a value of type T
   * @return InfiniteList of type T
   */
  public static <T> InfiniteList<T> generate(Producer<T> producer) {
    Lazy<InfiniteList<T>> t = Lazy.of(() -> InfiniteList.generate(producer));
    Lazy<Maybe<T>> h = Lazy.of(() -> Maybe.some(producer.produce())); 
    return new InfiniteList<>(h, t);
  }

  /**
   * Static method to create an InfiniteList using an initial value, subsequent values
   * in the InfiniteList are generated by transforming the previous value.
   *
   * @param <T> type of element inside InfiniteList
   * @param seed the iniital value
   * @param next a transformer that transform values from type T to T
   * @return Infinite list of type T
   */
  public static <T> InfiniteList<T> iterate(T seed, Transformer<T, T> next) {
    return new InfiniteList<>(seed, () -> InfiniteList.iterate(next.transform(seed), next));
  }

  /**
   * Private constructor used to create InfiniteList.
   * The initial value provided and producer are both wrapped in a Lazy, before
   * being initialised.
   *
   * @param head the head value
   * @param tail a producer that produces an InfiniteList for the tail
   */
  private InfiniteList(T head, Producer<InfiniteList<T>> tail) {
    this.head = Lazy.of(Maybe.some(head));
    this.tail = Lazy.of(tail);
  }

  /**
   * Private constructor used to create InfiniteList.
   * Takes in two Lazies, one for the head containing a Maybe and the other
   * for the tail containing an InfiniteList. Lazies are immediately initalised.
   *
   * @param head a Lazy containing a Maybe for the head
   * @param tail a lazy containing an InfiniteList for the tail
   */
  private InfiniteList(Lazy<Maybe<T>> head, Lazy<InfiniteList<T>> tail) {
    this.head = head;
    this.tail = tail;
  }

  /**
   * Used to attain the head value of type T.
   *
   * @return the head value of InfiniteList
   */
  public T head() {
    Maybe<T> m = this.head.get();
    return m.orElseGet(() -> this.tail.get().head());
  }
  
  /**
   * Used to attain the subsequent InfiniteList from the tail.
   *
   * @return the tail of InfiniteList
   */
  public InfiniteList<T> tail() {
    Maybe<T> m = this.head.get();
    return m.map(x -> this.tail.get()).orElseGet(() -> this.tail.get().tail());
  }

  /**
   * Used to map every head value of the InfiniteList from Type T to another value of Type R.
   *
   * @param <R> the type of value that is being mapped to
   * @param mapper a transformer used to transform a value from type T to another value of type R
   * @return an InfiniteList of type R
   */
  public <R> InfiniteList<R> map(Transformer<? super T, ? extends R> mapper) {
    Lazy<Maybe<R>> h = Lazy.of(() -> Maybe.some(mapper.transform(this.head())));
    Lazy<InfiniteList<R>> t = Lazy.of(() -> this.tail().map(mapper));
    return new InfiniteList<>(h, t);
  }

  /**
   * Used to filter out values from the InfiniteList based on a BooleanCondition.
   * Values that are filtered are marked as Maybe.none().
   *
   * @param predicate a BooleanCondition that determines if values of Type T are filtered
   * @return the filtered InfiniteList of Type T
   */
  public InfiniteList<T> filter(BooleanCondition<? super T> predicate) {
    Producer<Maybe<T>> p = () -> this.head.get().filter(predicate);
    Lazy<Maybe<T>> h = Lazy.of(p);
    Lazy<InfiniteList<T>> t = Lazy.of(() -> this.tail.get().filter(predicate));
    return new InfiniteList<>(h, t);
  }

  /**
   * A method ued to call the sentinel InfiniteList.
   * Sentinel is a special InfiniteList representing the end of a finite InfiniteList.
   * Sentinel is being cast into the appropriate type before being returned.
   *
   * @param <T> type of InfiniteList
   * @return a sentinel InfiniteList
   */
  public static <T> InfiniteList<T> sentinel() {
    @SuppressWarnings("unchecked")
    InfiniteList<T> il = (InfiniteList<T>) SENTINEL;
    return il;
  }

  /**
   * A method used to truncate the InfiniteList to a finite list of specific length.
   * InfiniteList ends with a sentinel.
   *
   * @param n the length of truncated list
   * @return a finite InfiniteList of length n
   */
  public InfiniteList<T> limit(long n) {
    Lazy<InfiniteList<T>> t = Lazy.of(() -> this.head.get().map(
          x -> this.tail.get().limit(n - 1)).orElseGet(() -> this.tail.get().limit(n)));
    return n <= 0 ? sentinel() : new InfiniteList<>(this.head, t);
  }

  /**
   * A method used to truncate an InfiniteList once it evaluates a value that fails the
   * BooleanCondition.
   * Once truncated, InfiniteList ends with a sentinel.
   *
   * @param predicate the BooleanCondition used to test values of type T
   * @return truncated InfiniteList
   */
  public InfiniteList<T> takeWhile(BooleanCondition<? super T> predicate) {
    Lazy<Maybe<T>> test = Lazy.of(() -> Maybe.of(this.head()).filter(predicate));
    Lazy<Maybe<T>> h = Lazy.of(() -> test.get());
    Lazy<InfiniteList<T>> t = Lazy.of(() -> test.get().map(
          x -> this.tail().takeWhile(predicate)).orElseGet(() -> this.sentinel()));
    return new InfiniteList<>(h, t);
  }

  /**
   * A method used to determine if an InfiniteList is sentinel.
   *
   * @return boolean value indicating if InfiniteList is sentinel
   */
  public boolean isSentinel() {
    return this.equals(sentinel());
  }

  /**
   * A method used to accumulate the values of the InfiniteList.
   * Can only be used on InfiniteList that are not infinite.
   *
   * @param <U> the return type of value
   * @param identity the initial value used for the method
   * @param accumulator a combiner that combines values of Type U and T using specific functions
   * @return the result of combining all the values in the InfiniteList
   */
  public <U> U reduce(U identity, Combiner<U, ? super T, U> accumulator) {
    return this.tail().reduce(accumulator.combine(identity, this.head()), accumulator);
  }

  /**
   * A method used to retun the size of InfiniteList.
   *
   * @return a value of type long containing the size of InfiniteList
   */
  public long count() {
    return this.head.get().map(x -> 1).orElse(0) + tail.get().count();
  }

  /**
   * A method to turn an InfiniteList to a List.
   *
   * @return a list containing the values of InfiniteList
   */
  public List<T> toList() {
    List<T> l = new ArrayList<>();
    InfiniteList<T> il = this;
    while (!il.isSentinel()) {
      if (il.head.get().equals(Maybe.none())) {
        il = il.tail.get();
        continue;
      }
      l.add(il.head());
      il = il.tail();
    }
    return l;
  }
  
  /**
   * Returns the string representation of an InfiniteList.
   *
   * @return a string representation of InfiniteList
   */
  public String toString() { 
    return "[" + this.head + " " + this.tail + "]";
  }
}
