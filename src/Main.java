import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        GameBoard gameBoard = new GameBoard();
        frame.setBounds(10, 10, 700, 600);
        frame.setTitle("打砖块游戏");
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gameBoard);
    }
}