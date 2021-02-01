package game;

import javax.swing.*;
import java.awt.*;

public class StartGame {
    public static void main(String[] args) {
        //创建窗体
        JFrame jFrame = new JFrame();
        jFrame.setTitle("别抓皮卡丘");//标题
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;//窗体宽
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;//窗体高
        jFrame.setBounds((width-700)/2, (height-700)/2, 700, 700);
        jFrame.setResizable(false);//不可调节大小
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//退出界面
        //创建面板
        GameJPanel gameJPanel=new GameJPanel();
        //添加面板
        jFrame.add(gameJPanel);
        //窗体可视化
        jFrame.setVisible(true);

    }
}
