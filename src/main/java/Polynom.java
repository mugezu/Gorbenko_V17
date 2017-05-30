import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Polynom {
    private int j;
    private long c;
    private int exp;
    private Character t;
    private char[] binom;
    private String binKod;
    private String poly = null;
    private Set<Character> set = new HashSet<Character>();

    //конструктор, текстовую строку в формат полинома
    public Polynom(String str, int st) {
        Collections.addAll(set, 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H');
        poly = str;
        String[] mas = str.split(" "); //разделяем строки пробелом
        this.exp = st;
        try {
            j = Integer.parseInt(mas[0]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (set.contains(mas[1].charAt(mas[1].length() - 1))) {
            c = Long.parseLong(mas[1].substring(0, mas[1].length() - 1), 8);
            t = mas[1].charAt(mas[1].length() - 1);
        } else {
            c = Long.parseLong(mas[1].substring(0, mas[1].length()), 8);
            t = ' ';
        }
        String s = Long.toBinaryString(c);
        binom = s.toCharArray();
        if (binom.length < (exp + 1)) {
            char[] h = new char[binom.length + 1];
            h[0] = '0';
            for (int i = 0; i < binom.length; i++)
                h[i + 1] = binom[i];
            binom = h;
        }
        binKod = new String(binom);
        binKod = binKod.substring(1, binKod.length());
    }

    public int getJ() {
        return j;
    }

    public long getC() {
        return c;
    }

    public int getExp() {
        return exp;
    }

    public Character getT() {
        return t;
    }

    public String toString() {
        return poly;
    }

    public char[] getBinom() {
        return binom;
    }

    public String getBinKod() {
        return binKod;
    }

    public void setBinKod(String binKod) {
        this.binKod = binKod;
    }
}