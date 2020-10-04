package tech.callback.swingc.toast;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ToastMoveListener extends ComponentAdapter
{
    private final BaseToast toastRef;
    private final int toastLocation;

    public ToastMoveListener(BaseToast toast, int location)
    {
        toastRef = toast;
        toastLocation = location;
    }

    @Override
    public void componentMoved(ComponentEvent e)
    {
        super.componentMoved(e);
        if (e.getComponent().isVisible()) {

            Point point = Toast.calculateToastLocation(e.getComponent().getBounds(), toastRef.getBounds(), toastLocation);
            toastRef.setLocation(point);
        }
    }

    public boolean isToastShowing() { return toastRef.isVisible(); }

}
