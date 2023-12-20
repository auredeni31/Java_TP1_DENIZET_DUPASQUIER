import java.awt.*;

public class SolidThings extends Things {
    private HitBox hitBox;


    public SolidThings(int x, int y, int width, int height, Image image) {
        super(x, y, width, height, image);
        this.hitBox = new HitBox(x, y, image.getWidth(null), image.getHeight(null));
    }
}