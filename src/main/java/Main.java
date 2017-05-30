import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

public class Main extends JFrame {
    public static final int P = 2;
    public static int N = 2;
    int[] vector;
    int[][] mat;
    JComboBox<Polynom> box = new JComboBox<Polynom>();
    JComboBox<Integer> boxExp = new JComboBox<Integer>();
    JComboBox<String> boxChar = new JComboBox<String>();
    private static Polynom polynom = null;
    private Set<Character> set = new HashSet<Character>();

    public static void main(String[] args) {
        try {
            new Main();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Main() throws HeadlessException, IOException {
        super();
        setTitle("Структура СРОС");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addComponents(getContentPane());
        setPreferredSize(new Dimension(700, 700));
        pack();
        setVisible(true);
    }

    private void addComponents(Container contentPane) {
        contentPane.setLayout(new BorderLayout());
        JToolBar toolBar = new JToolBar();
        toolBar.add(createButtonMenu());
        contentPane.add(toolBar, BorderLayout.NORTH);
    }

    private JComponent createButtonMenu() {
        Collections.addAll(set, 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H');
        for (int i = 1; i <= 15; i++) {
            boxExp.addItem(i);
        }
        for (char b : set) {
            boxChar.addItem(b + "");
        }
        boxChar.addItem("All");

        for (Polynom p : DB.read()) {
            if (p.getExp() == N) {
                box.addItem(p);
            }
        }

        boxExp.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                N = (Integer) boxExp.getSelectedItem();
                updateBox();
            }
        });
        boxChar.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBox();
            }
        });
        polynom = (Polynom) box.getSelectedItem();
        final JButton draw = new JButton("Нарисовать схему СРОС");
        draw.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Draw.drawCROC(getGraphics(), getWidth(), getHeight(), polynom.getBinKod());
            }
        });

        box.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                polynom = (Polynom) box.getSelectedItem();
            }
        });

        final JButton show = new JButton("Показать работу СРОС");
        show.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                vector = Arifmetic.getVector(N, P, "1");
                mat = Arifmetic.getMatrix(polynom.getBinKod(), P, N);
                System.out.println(polynom.getBinKod());
                System.out.println(Arifmetic.getMatrix(mat));
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            new CROCShow(vector, mat, (Polynom) box.getSelectedItem());
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }).start();

            }
        });
        JComponent res = new JComponent() {
        };
        res.setLayout(new FlowLayout(FlowLayout.LEFT));
        res.add(boxExp);
        res.add(boxChar);
        res.add(box);
        res.add(draw);
        res.add(show);
        return res;
    }

    private void updateBox() {
        int count = box.getItemCount();
        for (Polynom p : DB.read()) {
            if (boxChar.getSelectedItem().equals("All")) {
                if (p.getExp() == (Integer) boxExp.getSelectedItem())
                    box.addItem(p);
            } else if (p.getExp() == (Integer) boxExp.getSelectedItem() && String.valueOf(p.getT()).equals(boxChar.getSelectedItem())) {
                box.addItem(p);
            }
        }
        for (int i = 0; i < count; i++)
            box.removeItemAt(0);
        polynom = (Polynom) box.getSelectedItem();
    }
}
