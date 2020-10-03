import lab.callback.swingc.toast.Toast;
import lab.callback.swingc.toast.ToastStatus;

import javax.swing.*;
import java.awt.*;

public class ToastFrame extends JFrame
{
    public ToastFrame()
    {
        super();

        JButton button = new JButton("toast");

        button.addActionListener((event)->{
            Toast.showToast(this, Toast.TOP, ToastStatus.INFO,"网络异常", 2000);
        });


        setLayout(new BorderLayout());
        setTitle("Toast-Test");
        setVisible(true);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(button, BorderLayout.SOUTH);
    }
}
