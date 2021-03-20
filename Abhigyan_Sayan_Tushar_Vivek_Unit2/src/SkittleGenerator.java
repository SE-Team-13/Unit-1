import java.awt.*;

public class SkittleGenerator {
    public int skittleposition[];
    public boolean skittleUp[];

    SkittleGenerator() {
        skittleposition = new int[20];
        skittleposition[12] = 50;
        skittleposition[13] = 10;
        skittleposition[14] = 70;
        skittleposition[15] = 10;
        skittleposition[16] = 90;
        skittleposition[17] = 10;
        skittleposition[18] = 110;
        skittleposition[19] = 10;
        skittleposition[6] = 60;
        skittleposition[7] = 30;
        skittleposition[8] = 80;
        skittleposition[9] = 30;
        skittleposition[10] = 100;
        skittleposition[11] = 30;
        skittleposition[2] = 70;
        skittleposition[3] = 50;
        skittleposition[4] = 90;
        skittleposition[5] = 50;
        skittleposition[0] = 80;
        skittleposition[1] = 70;
        skittleUp = new boolean[10];
        for (int i = 0; i < 10; i++) {
            skittleUp[i] = true;
        }
    }

    SkittleGenerator(int pins[]) {
        skittleposition = new int[20];
        skittleposition[12] = 50;
        skittleposition[13] = 10;
        skittleposition[14] = 70;
        skittleposition[15] = 10;
        skittleposition[16] = 90;
        skittleposition[17] = 10;
        skittleposition[18] = 110;
        skittleposition[19] = 10;
        skittleposition[6] = 60;
        skittleposition[7] = 30;
        skittleposition[8] = 80;
        skittleposition[9] = 30;
        skittleposition[10] = 100;
        skittleposition[11] = 30;
        skittleposition[2] = 70;
        skittleposition[3] = 50;
        skittleposition[4] = 90;
        skittleposition[5] = 50;
        skittleposition[0] = 80;
        skittleposition[1] = 70;
        skittleUp = new boolean[10];
        for (int i = 0; i < 10; i++) {
            skittleUp[i] = true;
        }
        for (int i = 0; i < pins.length; i++) {
            skittleUp[pins[i]] = false;
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
