import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DB {
    public static List<Polynom> read() {
        int n = 0;
        String[] a;
        List<Polynom> list = new ArrayList<Polynom>();
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader("polinom.txt"));
            String str = "";
            while ((str = in.readLine()) != null) {
                if (str.contains("Exponent")) {
                    a = str.split(" ");
                    n = Integer.parseInt(a[1]);
                } else {
                    list.add(new Polynom(str, n));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static void saveFile(StringBuffer dataVKF, String name) {
        FileWriter FW = null;
        try {
            System.out.println(dataVKF.toString());
            if (dataVKF.toString().length() > 2) {
                FW = new FileWriter(new File("").getAbsolutePath() + "\\" + name + System.currentTimeMillis() + ".txt", false);
                FW.write(dataVKF.toString());
                dataVKF = null;
                dataVKF = new StringBuffer();
            } else {
                int reply = JOptionPane.showConfirmDialog(null, "Расчеты не были завершены, подождите пожалуйста или введите новую степень полинома", "Ошибка", JOptionPane.CLOSED_OPTION);
            }
        } catch (
                Exception c) {
            c.printStackTrace();
        } finally {
            try {
                if (FW != null)
                    FW.close();
            } catch (IOException c) {
                c.printStackTrace();
            }
        }
    }

    ;
}
