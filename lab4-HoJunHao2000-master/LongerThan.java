/**
 * A boolean condition with parameter x that can be applied to
 * a string.  Returns true if the string is longer than x; false
 * otherwise.
 * CS2030S Lab 4
 * AY21/22 Semester 2
 *
 * @author Ho Jun Hao (Group 8A)
 */

class LongerThan implements BooleanCondition<String> {
  private int len;

  public LongerThan(int len) {
    this.len = len;
  }

  @Override
  public boolean test(String str) {
    if (str == null) {
      return false;
    } 
   
    return this.len < str.length();
  }  
}


