import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainFrame extends JFrame 
{
    private static final int WIDTH = 370;
    private static final int HEIGHT = 550;

    private Timer timer;

    private JFrame frame; 

    private DrawingDisplay drawDisplay;
    private DataPanel dataPanel;
    private JPanel bottomPanel;

    private JButton startButton;
    private JButton stopButton;

    public MainFrame(String title)
    {
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(WIDTH,HEIGHT));
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setResizable(false);
        frame.setVisible(true);

        drawDisplay = new DrawingDisplay();
        dataPanel = new DataPanel();
        bottomPanel = new JPanel(new GridBagLayout());

        startButton = new JButton("start");
        startButton.setBackground(Color.BLACK);
        startButton.setForeground(Color.WHITE);
        startButton.setFocusable(false);

        stopButton = new JButton("stop");
        stopButton.setBackground(Color.BLACK);
        stopButton.setForeground(Color.WHITE);
        stopButton.setFocusable(false);
        stopButton.setEnabled(false);

        frame.add(drawDisplay, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.setPreferredSize(new Dimension(bottomPanel.getWidth(), 170));
        bottomPanel.setBackground(new Color(50, 50, 50));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(0, 4, 4, 4);

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.weightx = 0.5;
        c.weighty = 0.5;
        bottomPanel.add(dataPanel, c);

        c.weighty = 0;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        
        c.gridx = 0;
        c.gridy = 1;
        bottomPanel.add(startButton, c);

        c.gridx = 1;
        c.gridy = 1;
        bottomPanel.add(stopButton, c);

        addEventListeners();
    }

    private void addEventListeners()
    {
        startButton.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    timer = new Timer();

                    startButton.setEnabled(false);
                    stopButton.setEnabled(true);

                    timer.schedule( new TimerTask()
                        {
                            @Override
                            public void run() 
                            {
                                drawDisplay.placeRandomPoint(drawDisplay.getGraphics());
                                dataPanel.setPointsPlaced(String.valueOf(drawDisplay.getDotsPlaced()));
                                dataPanel.setCurrentApproximation(String.valueOf(drawDisplay.getCurrentApprox()));
                                dataPanel.setBestApproximation(String.valueOf(drawDisplay.getCurrentBestApprox()));
                            }
                        }, 
                    0, 10);
                }
            }
        );

        stopButton.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    stopButton.setEnabled(false);
                    startButton.setEnabled(true);

                    timer.cancel();
                }
            }
        );
    }
}