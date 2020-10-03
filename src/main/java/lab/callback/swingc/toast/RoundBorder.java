package lab.callback.swingc.toast;

import javax.swing.border.Border;
import java.awt.*;

/**
 * 圆角边框
 */
public class RoundBorder implements Border
{
    private Color color = Color.GRAY; /* 边框颜色 */
    private int arc = 25; /* 边框圆角度数 */
    private int thickness = 3; /* 边框厚度 */

    public RoundBorder(){}

    public RoundBorder(Color color, int arc, int thickness)
    {
        this.color = color;
        this.arc = arc;
        this.thickness = thickness;
    }

    public Insets getBorderInsets(Component c)
    {
        return new Insets(0, 0, 0, 0);
    }

    public boolean isBorderOpaque()
    {
        return false;
    }

    // 实现Border（父类）方法
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y,
                            int width, int height)
    {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setStroke(new BasicStroke(thickness));
        g2d.setColor(color);
        g2d.drawRoundRect(0, 0, c.getWidth() - 1, c.getHeight() - 1, arc, arc);
        g2d.dispose();
    }
}
