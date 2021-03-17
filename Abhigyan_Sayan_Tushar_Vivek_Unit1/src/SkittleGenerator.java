import java.awt.*;

public class SkittleGenerator {
    public int skittleposition[];
    public boolean skittleUp[];

    SkittleGenerator() {
        skittleposition = new int[20];
        skittleposition[0] = 10;
        skittleposition[1] = 10;
        skittleposition[2] = 30;
        skittleposition[3] = 10;
        skittleposition[4] = 50;
        skittleposition[5] = 10;
        skittleposition[6] = 70;
        skittleposition[7] = 10;
        skittleposition[8] = 20;
        skittleposition[9] = 30;
        skittleposition[10] = 40;
        skittleposition[11] = 30;
        skittleposition[12] = 60;
        skittleposition[13] = 30;
        skittleposition[14] = 30;
        skittleposition[15] = 50;
        skittleposition[16] = 50;
        skittleposition[17] = 50;
        skittleposition[18] = 40;
        skittleposition[19] = 70;
        skittleUp = new boolean[10];
        for (int i = 0; i < 10; i++) {
            skittleUp[i] = true;
        }
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.WHITE);
        for (int i = 0; i < 10; i++) {
            if (skittleUp[i])
                g.fillOval(skittleposition[2 * i], skittleposition[2 * i + 1], 20, 20);
        }
    }

    public void dropSkittle(int x) {
        skittleUp[x] = false;
    }
}
