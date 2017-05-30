import java.util.Random;

public class Arifmetic {
    public static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static int T(int n, int GFP, int j) {
        return (int) ((Math.pow(GFP, n) - 1) / gcd((long) Math.pow(GFP, n) - 1, j));
    }


    public static int[] getVector(int n, int p, String start) {
        int[] vector;
        boolean flag = false;
        vector = new int[n];
        try {
            for (int i = 0; i < n; i++) {
                vector[i] = Integer.valueOf(start.substring(i, i + 1)) % p;
                if (vector[i] != 0) flag = true;
            }
        } catch (NullPointerException NPE) {
        } finally {
            if (flag == false) vector[0] = 1;
            return vector;
        }
    }

    public static int[][] getMatrix(String binKod, int P, int n) {
        int[][] A;
        Random random = new Random();
        A = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    if (binKod != null)
                        A[i][j] = Integer.valueOf(binKod.substring(j, j + 1));
                    else A[i][j] = random.nextInt(P);
                }
                if (i - j == 1) {
                    A[i][j] = 1;
                }
            }
        return A;
    }

    public static int[] multiplication(int[][] a, int[] b, int p) {
        int[] c = new int[b.length];
        for (int i = 0; i < b.length; i++)
            for (int j = 0; j < a[i].length; j++) {
                    c[i] = c[i] + a[i][j] * b[j];
                    if (j + 1 == b.length) c[i] = c[i] % p;
                }
        return c;
    }
    public static String getMatrix(int[][]mat){
        StringBuffer s=new StringBuffer();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                s.append(mat[i][j] + " ");
            }
            s.append("\r\n");
        }
            return s.toString();
    }
    public static String getVector(int[]v){
        StringBuffer s=new StringBuffer();
        for (int i = 0; i < v.length; i++) {
            s.append( " "+String.valueOf(v[i]));
        }
        return s.toString();
    }

}
