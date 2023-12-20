public class HitBox {
    private int x, y;
    private int width, height;

    public HitBox(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public boolean intersect(HitBox anotherHitBox) {
        int xOverlap = Math.max(0, Math.min(x + width, anotherHitBox.x + anotherHitBox.width) - Math.max(x, anotherHitBox.x));
        int yOverlap = Math.max(0, Math.min(y + height, anotherHitBox.y + anotherHitBox.height) - Math.max(y, anotherHitBox.y));

        return xOverlap > 0 && yOverlap > 0;
    }

    public static void main(String[] args) {
        HitBox box1 = new HitBox(0, 0, 50, 50);
        HitBox box2 = new HitBox(25, 25, 50, 50);
        HitBox box3 = new HitBox(100, 100, 50, 50);

        System.out.println("Intersection between box1 and box2: " + box1.intersect(box2)); // Should print true
        System.out.println("Intersection between box1 and box3: " + box1.intersect(box3)); // Should print false
        System.out.println("Intersection between box2 and box1: " + box2.intersect(box1)); // Test reverse of box1 and box2
        System.out.println("Intersection between box2 and box3: " + box2.intersect(box3)); // Should print false
        System.out.println("Intersection between box3 and box1: " + box3.intersect(box1)); // Test reverse of box1 and box3
        System.out.println("Intersection between box3 and box2: " + box3.intersect(box2)); // Test reverse of box2 and box3
    }
}
