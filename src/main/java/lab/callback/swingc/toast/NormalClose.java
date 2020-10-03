package lab.callback.swingc.toast;

import java.util.TimerTask;

/**
 * Toast,普通方式关闭
 */
public class NormalClose extends ToastClose
{
    private BaseToast instance = null;

    public NormalClose(BaseToast toast)
    {
        instance = toast;
    }

    @Override
    public void run()
    {
        if (instance != null) instance.dispose();
    }
}
