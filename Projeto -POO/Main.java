import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main 
{
    FormLogin user_login = new FormLogin();
    public static void main(String[] args)
    {
        new Main().setUpLogin();
    }

     public void setUpLogin()
     {
        JFrame main_frame = new JFrame("Clínica Médica Dr. Swing - LOGIN");
        main_frame.getContentPane().add(user_login.$$$getRootComponent$$$());
        main_frame.setVisible(true);
        main_frame.pack();
        user_login.setParentJFrame(main_frame);
     }
}//Main