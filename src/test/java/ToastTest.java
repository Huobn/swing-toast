import javax.swing.*;

public class ToastTest
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(ToastFrame::new);
    }
}
