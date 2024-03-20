public class Paddle {
    public int x, y; // 挡板的位置
    private int width = 100; // 挡板的宽度
    private int height = 8; // 挡板的高度
    private int screenWidth; // 游戏屏幕宽度

    public Paddle(int screenWidth, int screenHeight) {
        // 初始化挡板的位置
        this.screenWidth = screenWidth;
        // 将挡板置于屏幕底部中央
        this.x = (screenWidth - width) / 2;
        this.y = screenHeight - height - 30; // 假设挡板距离屏幕底部30像素
    }

    public void moveRight() {
        // 向右移动挡板，确保不会超出屏幕
        this.x += 20;
        if (this.x >= (screenWidth - width)) { // 防止挡板越界
            this.x = screenWidth - width;
        }
    }

    public void moveLeft() {
        // 向左移动挡板，确保不会超出屏幕
        this.x -= 20;
        if (this.x < 0) { // 防止挡板越界
            this.x = 0;
        }
    }

    // 获取挡板的宽度
    public int getWidth() {
        return width;
    }

    // 获取挡板的高度
    public int getHeight() {
        return height;
    }
}