package lab.callback.swingc.toast;

import javax.swing.*;
import java.awt.*;

/**
 * 默认的Toast
 */
public class DefaultToast extends BaseToast
{

    private static final Font contentFont = new Font(Font.SERIF, Font.PLAIN, 15);

    private final JLabel contentArea = new JLabel();

    public DefaultToast(){ this(null, "", ToastStatus.INFO); }

    public DefaultToast(String text){ this(null, text, ToastStatus.INFO); }

    public DefaultToast(Frame owner, String text, ToastStatus status)
    {
        super(owner);
        /* 设置文本显示域样式 */
        contentArea.setText(text);
        contentArea.setOpaque(true);
        contentArea.setAutoscrolls(true);
        contentArea.setFont(contentFont);
        contentArea.setBackground(status.background);
        contentArea.setForeground(status.foreground);
        contentArea.setHorizontalAlignment(SwingConstants.CENTER);
        /* 添加组件 */
        add(contentArea, BorderLayout.CENTER);
    }

    public DefaultToast setContent(String text)
    {
        contentArea.setText(text);
        return this;
    }

    public DefaultToast setDimension(int width, int height)
    {
        setSize(width, height);
        return this;
    }

    public DefaultToast setContentFont(Font font)
    {
        contentArea.setFont(font);
        return this;
    }


}
