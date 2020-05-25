import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class CadFunc 
{
    public static void main(String[] args)
    {
        FormCad gg_dash = new FormCad();
        JFrame frame = new JFrame("Gerente Geral");
        frame.getContentPane().add(gg_dash.getBackgroundPanel());
        frame.setVisible(true);
        frame.setSize(800,600);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) 
            {
                Object[] options = { "Sair", "Cancelar" };
                if(JOptionPane.showOptionDialog(null, "Tem certeza?", "Sair", 
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, 
                null, options, options[0]) == 0)
                {
                    Usuarios.salvaUsuarios();
                    ConsultaHandle.gravarConsultas();
                    PacienteHandle.gravarPacientes();
                    System.exit(0);
                }
            }
        });
    }
}