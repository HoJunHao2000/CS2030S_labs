/**
 * The Transformer interface that can transform a type T
 * to type U.
 * CS2030S Lab 4
 * AY21/22 Semester 2
 *
 * @author Ho Jun Hao (Group 8A)
 */

interface Transformer<T, U> {
  U transform(T t);
}
