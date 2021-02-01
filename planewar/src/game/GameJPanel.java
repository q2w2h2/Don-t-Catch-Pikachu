package game;

import javax.lang.model.element.NestingKind;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Random;
import javax.swing.Timer;

import static java.awt.Font.BOLD;

public class GameJPanel extends JPanel {

    int planeX;//定义皮卡丘的x轴
    int planeY;//定义皮卡丘的y轴
    int[] shellX = new int[20];//定义炮弹的x轴
    int[] shellY = new int[20];//定义炮弹的y轴
    double[] face = new double[20];//定义炮弹朝向
    Random random = new Random();//随机函数
    boolean isStart = false;//判断游戏是否开始
    boolean isDie = false;//判断皮卡丘是否死亡
    long starttime;//开始时间
    long endtime;
    int score = (int) ((endtime - starttime) / 100);//计算得分
    boolean up, left, down, right;
    Timer timer;//计时器

    public void init() {//初始化
        planeX = 330;
        planeY = 320;
        for (int i = 0; i < 20; i++) {
            shellX[i] = random.nextInt(700);
            shellY[i] = random.nextInt(700);
            // shellY[i]=100;
            //shellX[i]=100;
            face[i] = Math.random() * Math.PI * 2;
        }
    }

    public GameJPanel() {//构造函数里写
        starttime = System.currentTimeMillis();
        init();
        this.setFocusable(true);//将焦点放到面板
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {//键盘按压
                super.keyPressed(e);
                int code = e.getKeyCode();
                if (code == 32) {//空格的码就是32
                    //判断
                    if (isDie == true) {
                        init();
                        isDie = false;
                        starttime=System.currentTimeMillis();
                    } else {
                        isStart = !isStart;
                    }
                    repaint();
                }
                //判断上
                if (code == KeyEvent.VK_UP) {
                    up = true;
                }
                //判断右
                if (code == KeyEvent.VK_RIGHT) {
                    right = true;
                }
                //判断左
                if (code == KeyEvent.VK_LEFT) {
                    left = true;
                }
                //判断下
                if (code == KeyEvent.VK_DOWN) {
                    down = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {//键盘释放
                super.keyReleased(e);
                int code = e.getKeyCode();//获取键盘按键
                //判断
                if (code == KeyEvent.VK_UP) {
                    up = false;
                }
                if (code == KeyEvent.VK_RIGHT) {
                    right = false;
                }
                if (code == KeyEvent.VK_LEFT) {
                    left = false;
                }
                if (code == KeyEvent.VK_DOWN) {
                    down = false;
                }
            }
        });
        //new一个计时器
        timer = new Timer(50, new ActionListener() {//每50毫秒监听一个动作
            @Override
            public void actionPerformed(ActionEvent e) {
                //判断
                if (isStart == true && isDie == false) {
                    if (up) {
                        planeY -= 10;//皮卡丘y轴移动速度
                    }
                    if (down) {
                        planeY += 10;
                    }
                    if (left) {
                        planeX -= 10;
                    }
                    if (right) {
                        planeX += 10;
                    }
                    for (int i = 0; i < 20; i++) {
                        shellX[i] += Math.cos(face[i]) * 9;//计算炮弹的角度
                        shellY[i] += Math.sin(face[i]) * 9;
                        //炮弹的边界判定
                        if (shellX[i] < 0) {
                            shellX[i] = 700;
                        }
                        if (shellX[i] > 700) {
                            shellX[i] = 0;
                        }
                        if (shellY[i] < 10) {
                            shellY[i] = 700;
                        }
                        if (shellY[i] > 700) {
                            shellY[i] = 10;
                        }
                        //封装矩形判断是否撞击并定义一个flag去判断
                        boolean flag = new Rectangle(planeX, planeY, 20, 28).intersects(new Rectangle(shellX[i], shellY[i], 30, 30));
//                        用flag判断皮卡丘是否是死亡
                        if (flag) {
                            isDie = true;
                            endtime = System.currentTimeMillis();
                        }
                    }
                    repaint();//重绘
                }
            }
        });
        timer.start();//开始计时器
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(new Color(255, 255, 255));
        Image.planeImg.paintIcon(this, g, planeX, planeY);
        for (int i = 0; i < 20; i++) {
            Image.shellImg.paintIcon(this, g, shellX[i], shellY[i]);
        }
        if (isStart == false) {
            g.setColor(new Color(255, 0, 0, 255));
            g.setFont(new Font("微软雅黑", BOLD, 32));
            g.drawString("点击空格键开始游戏", 200, 150);
        }
        if (isDie == true) {
            g.setColor(new Color(0, 255, 34, 255));
            g.setFont(new Font("微软雅黑", BOLD, 32));
            g.drawString("game over", 250, 150);
            g.setColor(new Color(5, 4, 4, 255));
            g.setFont(new Font("微软雅黑", BOLD, 20));
            g.drawString("分数:" +((endtime - starttime) / 100) , 12, 32);
            g.setColor(new Color(255, 0, 0, 255));
            g.setFont(new Font("微软雅黑", BOLD, 20));
            g.drawString("分数:" + ((endtime - starttime) / 100), 10, 30);
        }
    }
}