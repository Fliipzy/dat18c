import javax.swing.SwingUtilities;

public final class Program 
{
    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(() -> { new MainFrame("Pi Approximation"); } );
    }
}
