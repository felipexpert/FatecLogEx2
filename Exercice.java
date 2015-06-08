import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
public class Exercice {
  /*
   * Caro Nilton, optei por fazer os outputs dos exercícios
   * para facilitar a correção. Eu iria usar os recursos
   * funcionais do Java 8, mas vou fazer imperativo em Java
   * e funcional em Haskell (este último assim que eu tiver tempo) 
   */
  public static void main(String[] args) {
    Ex1: {
      System.out.println(operate(
          Arrays.asList(1f,2f,3f,4f,5f,6f,7f), 
          Arrays.asList(8f,9f,10f,11f,12f,13f,14f),
          Arrays.asList('/', '-', '*', '+', '-', '*', '+')));
    }
    Ex2: {
      List<Integer> mistakesPerPage = new ArrayList<>();
      for(int i = 0; i < 370; i++)
        mistakesPerPage.add((int) (15 * Math.random()));
      System.out.println(mistakesPerPage);
      MistakeReporter mr = new MistakeReporter(mistakesPerPage);
      System.out.println("Page with more mistakes: " + mr.pageWithMoreMistakes());
      System.out.println("Amount of pages without mistakes: " 
          + mr.pagesWithoutMistakes().size());
      System.out.println("Pages with more than 10 mistakes: " 
          + mr.pagesWithMoreMistakesThan(10).size());
    }
    Ex3: {
      String[][] eventsTable = {
        {"1", "Linux", "Auditorio 1", "8h as 9h"},
        {"2", "Recuperação de Desastres", "Auditório 2", "9h as 10h"},
        {"3", "Windows Server", "Auditório 3", "13h as 14h"},
        {"4", "Lógica e Programação", "Auditório Principal", "15h as 17h"}
      };
      System.out.println(whereAndWhen("2", eventsTable));
      System.out.println(whereAndWhen("4", eventsTable));
      System.out.println(whereAndWhen("5", eventsTable)); // -> null
    }
    Ex4: {
      System.out.println(determinant2x2(new float[][] {
        {1f, 2f},
        {9f, 7f}
      }));
    }
    Ex5: {
      System.out.println(sumEvenRows(new float[][] {
        {1,2,3,4,5,6,7,8,9},
        {11,12,13,14,15,16,17,18,19},
        {21,22,23,24,25,26,27,28,29},
        {31,32,33,34,35,36,37,38,39},
        {41,42,43,44,45,46,47,48,49},
        {51,52,53,54,55,56,57,58,59},
        {61,62,63,64,65,66,67,68,69},
        {71,72,73,74,75,76,77,78,79},
        {81,82,83,84,85,86,87,88,89}
      }));
    }
    Ex6: {
      System.out.println(getTriangleTypeOf(5, 5, 5));
      System.out.println(getTriangleTypeOf(5, 5, 8));
      System.out.println(getTriangleTypeOf(5, 4, 8));
      System.out.println(getTriangleTypeOf(4,3,1));
    }
    System.out.println("Until next time!");
  }
  public static List<Float> operate(List<Float> xs, List<Float> ys, List<Character> cs) {
    if(xs.size() != ys.size() || xs.size() != cs.size())
      throw new IllegalArgumentException("All Arrays should be of the same length");
    List<Float> results = new ArrayList<>();
    for(int i = 0; i < xs.size(); i++) {
      float num = 0f;
      switch(cs.get(i)) {
        case '+':
          num = xs.get(i) + ys.get(i);
          break;
        case '-':
          num = xs.get(i) - ys.get(i);
          break;
        case '*':
          num = xs.get(i) * ys.get(i);
          break;
        case '/':
          num = xs.get(i) / ys.get(i);
          break;
      }
      results.add(num);
    }
    return results;
  }
  private static class Page implements Comparable<Page>{
    private int id;
    private int mistakes;
    public Page(int id, int mistakes) { 
      this.id = id;
      this.mistakes = mistakes; 
    }
    public boolean hasMoreMistakesThan(int amount) { return mistakes > amount; }
    public boolean hasMistakes() { return hasMoreMistakesThan(0); }
    public int compareTo(Page page) { return getMistakes() - page.getMistakes(); }
    public int getId() { return id; }
    public int getMistakes() { return mistakes; }
    public String toString() { return "Page " + id + " has " + mistakes + " mistakes"; }
  }
  public static class MistakeReporter {
    private List<Page> pages;
    public MistakeReporter(List<Integer> mistakesPerPage) {
      pages = new ArrayList<>();
      int id = 0;
      for(Integer mistakes : mistakesPerPage) pages.add(new Page(id++, mistakes));
      Collections.sort(pages);
    }
    public Page pageWithMoreMistakes() { return pages.get(pages.size() - 1); }
    public List<Page> pagesWithoutMistakes() {
      List<Page> pages = new ArrayList<>();
      for(int i = 0; !this.pages.get(i).hasMistakes(); i++) 
        pages.add(this.pages.get(i));
      return pages;
    }
    public List<Page> pagesWithMoreMistakesThan(int amount) {
      List<Page> pages = new ArrayList<>();
      for(int i = 0; i < this.pages.size(); i++)
        if(pages.size() > 0 && !this.pages.get(i).hasMoreMistakesThan(amount))
          break;
        else if(this.pages.get(i).hasMoreMistakesThan(amount))
          pages.add(this.pages.get(i));
      return pages;
    }
  }
  public static String whereAndWhen(String code, String[][] eventTable) {
    String result = null;
    for(int i = 0; i < eventTable.length; i++) {
      String[] row = eventTable[i];
      if(row[0].equals(code)) {
        result = row[1] + " - Localidade: " + row[2] + ", horário: " + row[3];
        break;
      }
    }
    return result;
  }
  public static float determinant2x2(float[][] matrix) {
    if(matrix.length != 2 || matrix[0].length != 2 || matrix[1].length != 2)
      throw new IllegalArgumentException("Please use 2x2 matrixes");
    return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
  }
  public static float sumEvenRows(float[][] matrix) {
    if(matrix.length != 9) throw new IllegalArgumentException("Please use 9 rows");
    float sum = 0;
    for(int i = 0; i < matrix.length; i++) {
      if(matrix[i].length != 9)
        throw new IllegalArgumentException("Please use 9 columns");
      for(int j = 0; j < matrix[i].length; j += 2)
        sum += matrix[i][j];
    }
    return sum;
  }
  private enum TriangleType {
    EQUILATERAL, ISOSCELES, SCALENE, IMPOSSIBLE; 
    public static TriangleType triangleTypeOf(Triangle t) {
      if (t.getX() >= t.getY() + t.getZ() || t.getY() >= t.getX() + t.getZ())
        return IMPOSSIBLE;
      if(t.getX() == t.getY() && t.getX() == t.getZ())
        return EQUILATERAL;
      else if(t.getX() == t.getY() || t.getX() == t.getZ() || t.getY() == t.getZ())
        return ISOSCELES;
      return SCALENE;
    }
  }
  private static class Triangle {
    private float x, y, z;
    public Triangle(float x, float y, float z) {
      this.x = x;
      this.y = y;
      this.z = z;
    }
    public TriangleType getType() {
      return TriangleType.triangleTypeOf(this);
    }
    public float getX() { return x; }
    public float getY() { return y; }
    public float getZ() { return z; }
  }
  public static TriangleType getTriangleTypeOf(float x, float y, float z) {
    return new Triangle(x, y, z).getType();
  }
}
