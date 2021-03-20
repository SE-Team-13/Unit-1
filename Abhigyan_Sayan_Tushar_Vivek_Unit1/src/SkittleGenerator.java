import java.awt.*;

public class SkittleGenerator {
    public int skittleposition[];
    public boolean skittleUp[];

    SkittleGenerator() {
        skittleposition = new int[20];
        skittleposition[0] = 50;
        skittleposition[1] = 10;
        skittleposition[2] = 70;
        skittleposition[3] = 10;
        skittleposition[4] = 90;
        skittleposition[5] = 10;
        skittleposition[6] = 110;
        skittleposition[7] = 10;
        skittleposition[8] = 60;
        skittleposition[9] = 30;
        skittleposition[10] = 80;
        skittleposition[11] = 30;
        skittleposition[12] = 100;
        skittleposition[13] = 30;
        skittleposition[14] = 70;
        skittleposition[15] = 50;
        skittleposition[16] = 90;
        skittleposition[17] = 50;
        skittleposition[18] = 80;
        skittleposition[19] = 70;
        skittleUp = new boolean[10];
        for (int i = 0; i < 10; i++) {
            skittleUp[i] = true;
        }
    }

    SkittleGenerator(int pins[]) {
        skittleposition = new int[20];
        skittleposition[0] = 50;
        skittleposition[1] = 10;
        skittleposition[2] = 70;
        skittleposition[3] = 10;
        skittleposition[4] = 90;
        skittleposition[5] = 10;
        skittleposition[6] = 110;
        skittleposition[7] = 10;
        skittleposition[8] = 60;
        skittleposition[9] = 30;
        skittleposition[10] = 80;
        skittleposition[11] = 30;
        skittleposition[12] = 100;
        skittleposition[13] = 30;
        skittleposition[14] = 70;
        skittleposition[15] = 50;
        skittleposition[16] = 90;
        skittleposition[17] = 50;
        skittleposition[18] = 80;
        skittleposition[19] = 70;
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
