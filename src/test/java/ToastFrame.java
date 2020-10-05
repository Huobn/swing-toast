import tech.callback.swingc.toast.Toast;
import tech.callback.swingc.toast.ToastStatus;

import javax.swing.*;
import java.awt.*;

public class ToastFrame extends JFrame
{
    public ToastFrame()
    {
        super();

        JButton button = new JButton("toast");

        button.addActionListener((event)->{
            Toast.showToast(this, Toast.CENTER, ToastStatus.INFO,"background task completed completed at this time", 2000);
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
