package cs2030s.fp;

import java.util.NoSuchElementException;

/**
 * CS2030S Lab 6.
 * AY21/22 Semester 2
 *
 * @author Ho Jun Hao (Group 8A)
 */

public abstract class Maybe<T> {

  private static class None extends Maybe<Object> {
    private static final None EMPTY = new None();
    
    private None() {

    }

    private static None getEmpty() {
      return EMPTY;
    }

    @Override
    public String toString() {
      return "[]";
    }

    @Override
    public boolean equals(Object obj) {
      return this == obj;
    }

    @Override
    protected Object get() {
      throw new NoSuchElementException();
    }
    
    @Override
    public Maybe<Object> filter(BooleanCondition<? super Object> bc) {
      return Maybe.none();
    }
    
    @Override
    public <U> Maybe<U> map(Transformer<? super Object, ? extends U> t) {
      return Maybe.none();
    }
    
    @Override
    public <U> Maybe<U> flatMap(Transformer<? super Object, ? extends Maybe<? extends U>> t) {
      return Maybe.none();   
    }
    
    @Override
    public Object orElse(Object t) {
      return t;
    }
    
    @Override
    public Object orElseGet(Producer<? extends Object> p) {
      return p.produce();
    }
  }

  private static final class Some<T> extends Maybe<T> {
    private final T value;

    private Some(T t) {
      this.value = t;
    }

    @Override
    public String toString() {
      if (value == null) {
        return "[null]";
      } else {
        return "[" + this.value.toString() + "]";
      }
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }

      if (obj instanceof Some<?>) {
        Some<?> convertedObj = (Some<?>) obj;
        if (this.value == convertedObj.value) {
          return true;
        }

        if (this.value == null && convertedObj.value == null) {
          return true;
        }

        if (this.value == null && convertedObj.value != this.value) {
          return false;
        }

        return this.value.equals(convertedObj.value);
      }
      
      return false;
    }

    @Override
    protected T get() {
      return this.value;
    }
  
    @Override  
    public Maybe<T> filter(BooleanCondition<? super T> bc) {
      return this.get() != null && !bc.test(this.get()) ? none() : this; 
    }

    @Override
    public <U> Maybe<U> map(Transformer<? super T, ? extends U> t) {
      return some(t.transform(this.get()));
    }

    @Override
    public <U> Maybe<U> flatMap(Transformer<? super T, ? extends Maybe<? extends U>> t) {
      @SuppressWarnings("unchecked")
      Maybe<U> m = (Maybe<U>) t.transform(this.get());
      return m;    
    }

    @Override
    public T orElse(T t) {
      return this.get();
    }

    @Override
    public T orElseGet(Producer<? extends T> p) {
      return this.get();
    }
  }

  public static <T> Maybe<T> none() {
    @SuppressWarnings("unchecked")
    Maybe<T> mt = (Maybe<T>) None.getEmpty();
    return mt;
  }

  public static <T> Maybe<T> some(T t) {
    return new Some<T>(t);
  }

  public static <T> Maybe<T> of(T t) {
    if (t != null) {
      return some(t);
    } else {
      return none();
    }
  }

  protected abstract T get();

  public abstract Maybe<T> filter(BooleanCondition<? super T> bc);
  
  public abstract <U> Maybe<U> map(Transformer<? super T, ? extends U> t);
  
  public abstract <U> Maybe<U> flatMap(Transformer<? super T, ? extends Maybe<? extends U>> t);
  
  public abstract T orElse(T t);

  public abstract T orElseGet(Producer<? extends T> p);
}
