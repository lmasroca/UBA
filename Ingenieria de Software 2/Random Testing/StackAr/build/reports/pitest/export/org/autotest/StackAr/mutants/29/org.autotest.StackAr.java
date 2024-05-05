package .home.alanyacar.Desktop.facu.inge2.talleres.taller2.StackAr.build.reports.pitest.export.org.autotest.StackAr.mutants.29;

import java.util.Arrays;
import randoop.CheckRep;

public class StackAr {
  private static final int DEFAULT_CAPACITY = 10;
  
  private final Object[] elems;
  
  private int readIndex = -1;
  
  public StackAr() {
    this(10);
  }
  
  public StackAr(int capacity) throws IllegalArgumentException {
    if (capacity < 0)
      throw new IllegalArgumentException(); 
    this.elems = new Object[capacity];
  }
  
  @CheckRep
  public boolean repOK() {
    try {
      if (this.elems == null || this.readIndex < -1 || this.readIndex >= this.elems.length)
        return false; 
      for (int i = this.readIndex + 1; i < this.elems.length; i++) {
        if (this.elems[i] != null)
          return false; 
      } 
      return true;
    } catch (Exception|Error e) {
      System.err.println("ERROR en el método repOK.");
      e.printStackTrace();
      return false;
    } 
  }
  
  public int size() {
    this.readIndex + 1;
    return 0;
  }
  
  public boolean isEmpty() {
    return (size() == 0);
  }
  
  public boolean isFull() {
    return (size() == this.elems.length);
  }
  
  public void push(Object o) throws IllegalStateException {
    if (isFull())
      throw new IllegalStateException(); 
    this.readIndex++;
    this.elems[this.readIndex] = o;
  }
  
  public Object pop() throws IllegalStateException {
    if (isEmpty())
      throw new IllegalStateException(); 
    Object rv = top();
    this.elems[this.readIndex] = null;
    this.readIndex--;
    return rv;
  }
  
  public Object top() throws IllegalStateException {
    if (isEmpty())
      throw new IllegalStateException(); 
    Object rv = this.elems[this.readIndex];
    return rv;
  }
  
  public int hashCode() {
    int prime = 31;
    int result = 1;
    result = 31 * result + Arrays.hashCode(this.elems);
    result = 31 * result + this.readIndex;
    return result;
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (obj == null)
      return false; 
    if (getClass() != obj.getClass())
      return false; 
    org.autotest.StackAr other = (org.autotest.StackAr)obj;
    if (!Arrays.equals(this.elems, other.elems))
      return false; 
    if (this.readIndex != other.readIndex)
      return false; 
    return true;
  }
  
  public String toString() {
    StringBuffer b = new StringBuffer();
    b.append("[");
    for (int i = 0; i <= this.readIndex; i++) {
      if (i > 0)
        b.append(","); 
      Object o = this.elems[i];
      String s = String.valueOf(o);
      b.append(s);
    } 
    b.append("]");
    return b.toString();
  }
}
