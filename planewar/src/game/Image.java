package game;
//图像
import javax.swing.*;
import java.net.URL;

public class Image {
    public static URL planeURL = Image.class.getResource("/image/plane.png");
    public static ImageIcon planeImg = new ImageIcon(planeURL);
    public static URL shellURL = Image.class.getResource("/image/shell.png");
    public static ImageIcon shellImg = new ImageIcon(shellURL);


}
