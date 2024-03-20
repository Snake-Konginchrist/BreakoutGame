public class Ball {
    public int x, y;
    private int xDir, yDir;
    private int diameter = 20; // 球的直径

    public Ball(int startX, int startY) {
        // 初始化球的位置和移动方向
        this.x = startX;
        this.y = startY;
        this.xDir = -1; // 默认的X轴方向
        this.yDir = -2; // 默认的Y轴方向
    }

    public void move() {
        x += xDir;
        y += yDir;
        // 这里可以添加边界检测和逻辑来改变球的移动方向
    }

    public void reverseXDir() {
        xDir = -xDir;
    }

    public void reverseYDir() {
        yDir = -yDir;
    }

    public int getDiameter() {
        return diameter;
    }
}