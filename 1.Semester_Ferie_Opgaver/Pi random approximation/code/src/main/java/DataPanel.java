import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class DataPanel extends JPanel
{
    private JLabel pointsPlacedLabel = new JLabel("Points placed: ");
    private JLabel pointsPlacedData = new JLabel("0");
    private JLabel currentApproximationLabel = new JLabel("Current Approximation: ");
    private JLabel currentApproximationData = new JLabel("0");
    private JLabel bestApproximationLabel = new JLabel("Best Approximation: ");
    private JLabel bestApproximationData = new JLabel("0");

    public void setCurrentApproximation(String data)
    {
        currentApproximationData.setText(data);
    }

    public void setBestApproximation(String data)
    {
        bestApproximationData.setText(data);
    }

    public void setPointsPlaced(String data)
    {
        pointsPlacedData.setText(data);
    }
    
    public DataPanel() 
    {
        setBackground(new Color(50, 50, 50));
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(1,1));

        pointsPlacedLabel.setForeground(Color.WHITE);
        pointsPlacedData.setForeground(Color.WHITE);
        currentApproximationLabel.setForeground(Color.WHITE);
        currentApproximationData.setForeground(Color.WHITE);
        bestApproximationLabel.setForeground(Color.WHITE);
        bestApproximationData.setForeground(Color.WHITE);

        Border innerBorder = BorderFactory.createTitledBorder(
            this.getBorder(), 
            "Statistics", 
            TitledBorder.LEFT, 
            TitledBorder.TOP, 
            new Font("Default", Font.BOLD, 14)
            , Color.WHITE);

        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);

        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(2, 15, 2, 2);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(pointsPlacedLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(currentApproximationLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(bestApproximationLabel, gbc);

        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.WEST;
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(pointsPlacedData, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(currentApproximationData, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(bestApproximationData, gbc);

    }

    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
    }
}