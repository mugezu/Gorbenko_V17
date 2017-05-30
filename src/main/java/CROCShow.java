import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class CROCShow extends JFrame {
    int[] vector;
    int[][] mat;
    Polynom p;
    int P = 2;
    JTextArea textArea;
    JScrollPane sendTextScroll;

    public CROCShow(int[] vector, int[][] mat, Polynom p) throws HeadlessException, IOException {
        super();
        this.vector = vector;
        this.p = p;
        this.mat = mat;
        setTitle("Вычисление состояний СРОС");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        textArea = new JTextArea();
        textArea.setEditable(false);
        sendTextScroll = new JScrollPane(textArea);
        setPreferredSize(new Dimension(800, 500));
        JLabel label = new JLabel(" ");
        getContentPane().add(label, BorderLayout.SOUTH);
        int[] vectortmp = vector;
        getContentPane().add(sendTextScroll, BorderLayout.CENTER);
        textArea.append("Начальное состояние: " + Arifmetic.getVector(vectortmp) + System.lineSeparator());
        textArea.append("Характеристическая матрица F: " + System.lineSeparator() + Arifmetic.getMatrix(mat) + System.lineSeparator());
        textArea.append("Состояния СРОС" + System.lineSeparator());
        textArea.append(0 + ": " + Arifmetic.getVector(vectortmp) + System.lineSeparator());
        boolean flag = true;
        int count = 0;
        for (int i = 0; flag; i++) {
            vectortmp = Arifmetic.multiplication(mat, vectortmp, P);
            if (Arifmetic.getVector(vector).equals(Arifmetic.getVector(vectortmp))) {
                textArea.append(0 + ": " + Arifmetic.getVector(vectortmp) + System.lineSeparator());
                flag = false;
            } else {
                textArea.append(i + 1 + ": " + Arifmetic.getVector(vectortmp) + System.lineSeparator());
            }
            count = i;
        }
        count++;
        int T = (int) Math.pow(P, vector.length);
        String poly;
        if (p.getT().equals('A') || p.getT().equals('B') || p.getT().equals('C') || p.getT().equals('D'))
            poly = "Полином непримитивный";
        else poly = "Полином примитивный";
        int Tt = Arifmetic.T(p.getExp(), P, p.getJ());
        label.setText("Размер периода(экс): " + Tt + "Размер периода(расчетное): " + count + " Количество состояний в целом: " + T + " " + poly);
        pack();
        setVisible(true);
    }
}
