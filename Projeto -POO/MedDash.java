import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MedDash 
{
    JPanel background = new JPanel();
    NextConsultPanel ncp = new NextConsultPanel(background);

    {
        setUpGUI();
    }

    public MedDash(String m)
    {
        ncp.setMedic(m);
    }

    private void setUpGUI()
    {
        //JFrame frame = new JFrame();
        background.setLayout(new BoxLayout(background, BoxLayout.X_AXIS));
            background.add(ncp.$$$getRootComponent$$$());
        //frame.add(background);
        //frame.setVisible(true);
        //frame.setSize(800,600);
        //frame.pack();
    }

    public JComponent getRootComponent()
    {
        return this.background;
    }

}