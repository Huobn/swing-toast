package tech.callback.swingc.toast;

import java.awt.*;
import java.awt.event.ComponentListener;
import java.util.Optional;
import java.util.Timer;

/**
 * Swing Toast工具类
 * @author callback
 * @version 1.0.0
 */
public class Toast
{
    /* Toast出现位置（相对于Toast的owner） */
    public static final int LEFT_TOP = 0;
    public static final int LEFT_BOTTOM = 1;
    public static final int RIGHT_TOP = 2;
    public static final int RIGHT_BOTTOM = 3;
    public static final int CENTER = 4;
    public static final int BOTTOM = 5;
    public static final int TOP = 6;

    private static final int GAP = 15;
    private static final int DECORATE_GAP = 25;

    private static final Timer toastTimer = new Timer(); /* toast关闭调度器 */

    private Toast(){}
    /**
     * 展示一个Toast
     * @param toast Toast对象
     * @param close Toast关闭方式
     * @param duration Toast显示持续时间
     */
    public static void show(BaseToast toast, ToastClose close, long duration)
    {
        toast.setVisible(true);
        toastTimer.schedule(close, duration);
    }

    /**
     * 展示一个Toast
     * @param owner Toast出现的父窗口（DefaultToast必须依赖于JFrame而存在）
     * @param location 出现在父窗口的位置
     * @param text 展示的文本
     */
    public static void showToast(Frame owner, int location, String text)
    {
        showToast(owner, location, ToastStatus.INFO ,text, 1500L);
    }

    /**
     * 展示一个Toast
     * @param owner Toast出现的父窗口（DefaultToast必须依赖于JFrame而存在）
     * @param location 出现在父窗口的位置
     * @param text 展示的文本
     * @param duration Toast的持续时间
     */
    public static void showToast(Frame owner, int location, String text, long duration)
    {
        showToast(owner, location, ToastStatus.INFO, text, duration);
    }


    /**
     * 展示一个Toast
     * @param owner Toast出现的父窗口（DefaultToast必须依赖于JFrame而存在）
     * @param location 出现在父窗口的位置
     * @param status Toast的状态，INFO，ERROR， SUCCESS
     * @param text 展示的文本
     * @param duration Toast的持续时间
     */
    public static void showToast(Frame owner, int location, ToastStatus status ,String text, long duration)
    {
        DefaultToast toast = new DefaultToast(owner,text, status);
        Window frame = toast.getOwner();
        ComponentListener[] listeners = frame.getComponentListeners();

        /* 清除过期的listener */
        Optional.ofNullable(listeners)
                .ifPresent((arrays)->{
                    for (ComponentListener listener : arrays) {
                        boolean removePredict = (listener instanceof ToastMoveListener) &&
                                !((ToastMoveListener)listener).isToastShowing();
                        if (removePredict) frame.removeComponentListener(listener);
                    }
                });

        frame.addComponentListener(new ToastMoveListener(toast, location)); /* 添加一个监听器，使得Toast跟随主窗口移动。 */
        Point toastLocation = calculateToastLocation(frame.getBounds(), toast.getBounds(), location);
        toast.setLocation(toastLocation);
        show(toast, new FadeClose(toast), duration);
    }

    /**
     * 计算Toast的位置
     * @param frameBounds Frame的约束信息
     * @param toastBound Toast的约束信息
     * @param location Toast出现的位置
     * @return Toast出现位置的坐标点
     */
    public static Point calculateToastLocation(Rectangle frameBounds, Rectangle toastBound, int location)
    {
        /* 默认为居中显示 */
        int x = frameBounds.x + ( frameBounds.width - toastBound.width ) / 2;
        int y = frameBounds.y + ( frameBounds.height - toastBound.height ) / 2;

        /* 计算位置的坐标 */
        if (RIGHT_BOTTOM == location) {
            y = frameBounds.y + ( frameBounds.height - toastBound.height  - GAP );
            x = frameBounds.x + ( frameBounds.width - toastBound.width  - GAP );
        } else if (LEFT_BOTTOM == location) {
            y = frameBounds.y + ( frameBounds.height - toastBound.height  - GAP );
            x = frameBounds.x + GAP;
        } else if (RIGHT_TOP == location) {
            y = frameBounds.y + GAP + DECORATE_GAP; /* Frame顶部装饰栏缘故 */
            x = frameBounds.x + ( frameBounds.width - toastBound.width  - GAP );
        } else if (LEFT_TOP == location) {
            y = frameBounds.y + GAP + DECORATE_GAP; /* Frame顶部装饰栏缘故 */
            x = frameBounds.x + GAP;
        } else if (BOTTOM == location) {
            y = frameBounds.y + (frameBounds.height - toastBound.height  - GAP * 2);
        } else if (TOP == location) {
            y = frameBounds.y + GAP + DECORATE_GAP;
        }
        return new Point(x,y);
    }

}
