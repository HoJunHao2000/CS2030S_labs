/*
 * @author Ho Jun Hao (Group 8A)
 * */

import java.util.Random;

// TODO
class RandomPoint extends Point {
  private static Random rng = new Random(1); 
  
  public RandomPoint(int minX, int maxX, int minY, int maxY) {
    super(randomNum(minX, maxX), randomNum(minY, maxY));
  }

  public static void setSeed(int a) {
    rng = new Random(a);
  }

  private static double randomNum(int min, int max) {
    double num = rng.nextDouble();
    while (num < min || num > max) {
      num = rng.nextDouble();
    }
    return num;
  }
}
