import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;

public class GameBoard extends JPanel implements KeyListener, ActionListener {
    private boolean play = false; // 游戏是否开始
    private int score = 0; // 得分

    private int totalBricks = 21; // 总的砖块数量

    private Timer timer; // 用于游戏事件监听的定时器
    private int delay = 8; // 定时器的延迟

    private int playerX = 310; // 挡板的起始位置

    private int ballposX = 120; // 球的起始X位置
    private int ballposY = 350; // 球的起始Y位置
    private int ballXdir = -1; // 球在X轴的移动方向
    private int ballYdir = -2; // 球在Y轴的移动方向

    private Brick[] bricks; // 砖块数组

    public GameBoard() {
        bricks = new Brick[totalBricks];
        for(int i = 0; i < bricks.length; i++) {
            // 假设砖块分布在3行7列
            bricks[i] = new Brick(80 + (i % 7) * 100, 50 + (i / 7) * 50);
        }

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g) {
        // 背景
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);

        // 绘制砖块
        for (Brick brick : bricks) {
            if (!brick.isDestroyed) {
                g.setColor(Color.white);
                g.fillRect(brick.x, brick.y, 80, 30);
            }
        }

        // 挡板
        g.setColor(Color.green);
        g.fillRect(playerX, 550, 100, 8);

        // 球
        g.setColor(Color.yellow);
        g.fillOval(ballposX, ballposY, 20, 20);

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (play) {
            // 球的移动逻辑
            ballposX += ballXdir;
            ballposY += ballYdir;
            // 球碰到左右边界
            if (ballposX < 0 || ballposX > 670) {
                ballXdir = -ballXdir;
            }
            // 球碰到顶部或挡板
            if (ballposY < 0 || (ballposY > 540 && ballposX > playerX && ballposX < playerX + 100)) {
                ballYdir = -ballYdir;
            }

            // 砖块碰撞检测和处理
            for (Brick brick : bricks) {
                if (!brick.isDestroyed) {
                    int brickX = brick.x;
                    int brickY = brick.y;
                    int ballX = ballposX + 10; // 球的中心点
                    int ballY = ballposY + 10;

                    // 简单的碰撞检测
                    if (ballX > brickX && ballX < brickX + 80 && ballY > brickY && ballY < brickY + 30) {
                        brick.isDestroyed = true;
                        ballYdir = -ballYdir;
                        score += 5;
                    }
                }
            }

            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if (playerX >= 600) {
                    playerX = 600; // 防止挡板移出屏幕右边界
                } else {
                    moveRight();
                }
            }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerX < 10) {
                playerX = 10; // 防止挡板移出屏幕左边界
            } else {
                moveLeft();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (!play) {
                play = true; // 通过空格键开始游戏
                ballposX = 120;
                ballposY = 350;
                ballXdir = -1;
                ballYdir = -2;
                playerX = 310;
                score = 0;
                totalBricks = 21;
                initializeBricks();

                repaint();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    public void moveRight() {
        play = true;
        playerX += 20; // 向右移动挡板
    }

    public void moveLeft() {
        play = true;
        playerX -= 20; // 向左移动挡板
    }

    public void initializeBricks() {
        // 重置砖块状态
        bricks = new Brick[totalBricks];
        for(int i = 0; i < bricks.length; i++) {
            bricks[i] = new Brick(80 + (i % 7) * 100, 50 + (i / 7) * 50);
        }
    }
}