public class DynamicThings extends AnimatedThings {
    private int speedX, speedY;

    public DynamicThings(int x, int y, int width, int height, int speedX, int speedY) {
        super(x, y, width, height);
        this.speedX = speedX;
        this.speedY = speedY;
    }
}
