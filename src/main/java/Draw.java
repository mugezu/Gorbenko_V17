import java.awt.*;

public class Draw {

    public static void drawCROC(Graphics g, int maxX, int maxY, String poly) {
        g.clearRect(0, 80, maxX, maxY);
        int n = poly.length();
        int size;
        int size2;
        if (n > 3) {
            size = (maxX - maxX / (n / 2)) / n;
            size2 = size - size / 3;
        } else {
            size = (maxX / 8);
            size2 = size - size / 3;
        }

        int sumX = maxX - size - size / 3;
        int sumY = 100;
        int sinX = 0;
        int sinY = maxY - (50 / 2);
        g.setColor(Color.red);
        g.drawLine(sinX, sinY, 50 + size * (n - 1) - (size / 6 + size % 6), sinY);
        drawSum(g, size2, sumX, sumY, size);
        connectFirst(g, (50 - (size / 6 + size % 6)), maxY - 50 - size2 * 2 + size2 / 2, sumX + size2 + size2 / 5 + size2 % 5, sumY - 10, sumX + size2 + size2 / 5 + size2 % 5, sumY + size2 / 2);
        for (int i = 0; i < n; i++) {
            drawTrigger(g, size2, 50 + size * i, maxY - 50 - size2 * 2, size);
            g.drawLine(50 + size * i - (size / 6 + size % 6), sinY, 50 + size * i - (size / 6 + size % 6), sinY - (25 + size / 3 + size % 3));
            if (Integer.valueOf(poly.substring(i, i + 1)) != 0)
                connect(g, 50 + size * i + size2 + size / 6 + size % 6, maxY - 50 - size2 * 2 + size2 / 2, sumX, sumY + i * size / n + size / n);
        }
    }

    public static void connectFirst(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3) {
        g.drawLine(x1, y1, x1, y2);
        char[] a = {'1'};
        g.drawChars(a, 0, a.length, x1 - 5, y1 + 12);
        g.drawLine(x1, y2, x2, y2);
        g.drawLine(x2, y2, x3, y3);
    }

    public static void connect(Graphics g, int x1, int y1, int x2, int y2) {
        g.drawLine(x1, y1, x1, y2);
        char[] a = {'1'};
        g.drawChars(a, 0, a.length, x1 - 5, y1 + 12);
        g.drawLine(x1, y2, x2, y2);
    }

    public static void drawSum(Graphics g, int size, int x, int y, int size2) {
        g.drawLine(x, y, x + size, y); //верхняя
        char[] a = {'M', '2'};
        g.drawChars(a, 0, a.length, x + size / 2, y + size / 2);
        g.drawLine(x, y + size * 2, x + size, y + size * 2);//нижняя
        g.drawLine(x, y, x, y + size * 2);//первая слева
        g.drawLine(x + size, y, x + size, y + size * 2);//первая справа
        g.drawLine(x + size, y + size / 2, x + size + size / 5 + size % 5, y + size / 2);//выход
    }

    public static void drawTrigger(Graphics g, int size, int x, int y, int size2) {
        g.drawLine(x, y, x + size, y); //верхняя
        char[] a = {'T'};
        g.drawChars(a, 0, a.length, x + size / 2, y + size / 2);
        g.drawLine(x, y + size * 2, x + size, y + size * 2);//нижняя
        g.drawLine(x, y, x, y + size * 2);//первая слева
        g.drawLine(x + size / 3, y, x + size / 3, y + size * 2);//разделитель
        g.drawLine(x + size, y, x + size, y + size * 2);//первая справа
        g.drawLine(x, y + size / 2, x - (size2 / 6 + size2 % 6), y + size / 2);//вход
        g.drawLine(x, y + size * 3 / 2, x - (size2 / 6 + size2 % 6), y + size * 3 / 2);//синхро-вход
        g.drawLine(x + size, y + size / 2, x + size + size2 / 6 + size2 % 6, y + size / 2);//выход
    }
}
