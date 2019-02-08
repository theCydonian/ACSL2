import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Acsl {

  private static int findChar(char c, StringBuilder array) {
    int i = 0;
    for (char ch : array.toString().toCharArray()) {
      if (ch == c)
        return i;
      i++;
    }
    return -1;
  }

  private static StringBuilder diff1(String line1, StringBuilder line2) {
    String[] words1 = line1.split(" ");

    StringBuilder common = new StringBuilder();

    int i = 0;
    for (String c : words1) {
      int start = line2.indexOf(c);
      int end = start + c.length();
      if (start != -1) {
        if (end <= line2.length()) {
          common.append(c + ' ');
          line2.delete(start, end);
        }
      }
    }

    if (common.length() != 0)
      return common;

    return null;
  }

  private static String getWord(int i, char c, char[] words1) {
    int t = i;
    StringBuilder output = new StringBuilder();
    while (t < words1.length && words1[t] != ' ') {
      output.append(words1[t]);
      t++;
    }
    return output.toString();
  }

  private static boolean findWord(String word, StringBuilder line2) {
    return line2.indexOf(word) != -1;
  }

  private static String diff2(StringBuilder d1, StringBuilder d2) {
    char[] words1 = d1.toString().toCharArray();

    StringBuilder common = new StringBuilder();

    for (char c : words1) {
      if (c != ' ') {
        int pos = findChar(c, d2);
        if (pos != -1) {
          common.append(c);
          d2.delete(0, pos + 1);
        }
      }
    }

    if (common.length() != 0)
      return common.toString();

    return "NONE";
  }

  private static String diff(String line1, String line2) {
    StringBuilder common1 = diff1(line1, new StringBuilder(line2));
    StringBuilder common2 = diff1(line2, new StringBuilder(line1));
    return diff2(common1, common2);
  }

  public static void main(String[] args) throws FileNotFoundException {
    Scanner scan = new Scanner(new File(args[0]));
    int i = 1;
    String a = null;
    String b = null;
    while(scan.hasNextLine()) {
      String line = scan.nextLine();
      if(line.length() == 0) {
        a = null;
        b = null;
      } else if (a == null) {
        a = line;
      } else {
        b = line;
        System.out.println(i + ". " + diff(a,b));
        i++;
      }
    }
  }
}
