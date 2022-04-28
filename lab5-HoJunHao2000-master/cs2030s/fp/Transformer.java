package cs2030s.fp;

/**
 * CS2030S Lab 5
 * AY21/22 Semester 2
 *
 * @author Ho Jun Hao (Group 8A)
 */

public interface Transformer<T, U> {
  U transform(T t);
}
