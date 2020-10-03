package lab.callback.swingc.toast;

import com.sun.awt.AWTUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Toast的基类, 所有的Toast可以选择性继承此类
 * @author callback
 * @version 1.0.0
 */
public class BaseToast extends JDialog
{

    private static final int PREFERRED_WIDTH = 200;
    private static final int PREFERRED_HEIGHT = 45;
    private static final float PREFERRED_OPACITY = 0.85f;
    private static final int PREFERRED_ARC = 20;

    public BaseToast()
    {
        this(null);
    }

    public BaseToast(Frame owner)
    {
        super((Frame) owner);
        setVisible(false); /* 窗体创建时默认不显示 */
        setResizable(false); /* 设置总是至于顶部 */
        setAlwaysOnTop(true);
        setUndecorated(true); /* 去掉定部装饰栏 */
        setOpacity(PREFERRED_OPACITY);
        setLayout(new BorderLayout()); /* 设置边界布局 */
        setSize(PREFERRED_WIDTH, PREFERRED_HEIGHT);

        /* 设置窗体边框圆角 */
        AWTUtilities.setWindowShape(this,
                new RoundRectangle2D.Double(0, 0, PREFERRED_WIDTH,
                        PREFERRED_HEIGHT, PREFERRED_ARC, PREFERRED_ARC
                )
        );
    }
}
