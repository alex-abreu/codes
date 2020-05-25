import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;


public class PacientTable
{
    private JPanel pacient_panel;
    ConsultTable ct;

    //patient_table
    public DefaultTableModel pacient_model;
    public JTable pacient_table;

    //consult_table

    {
        setUpTable();
    }

    public PacientTable(ConsultTable ct)
    {
        this.ct = ct;
    }

    public void externalAddOperation(Object[] data)
    {
        System.out.println("addoperation");
        pacient_model.addRow(data);
        pacient_table.clearSelection();
        pacient_model.fireTableDataChanged();
    }

    /**
     * Cria um painel que contém uma tabela e os seus 
     * botões de controle, recebe dentre outras coisas
     * o tamanho preferido de exibição da tabela
     * usa o GridBagLayout
     * @return JPanel
     */
    public JPanel createTablePanel(int x, int y, String[] tc, Object[][] tr, 
                            DefaultTableModel tm,  JTable t, Box button_box)
    {
        JPanel panel = new JPanel(); //cria o JPanel
        GridBagConstraints c = new GridBagConstraints(); //configurações do layout
        JScrollPane table_pane;                          //JScrollPane que conterá a tabela


        panel.setLayout(new GridBagLayout());            //layout do painel
        c.fill = GridBagConstraints.HORIZONTAL;          //completa pela horizontal, se necessário
        table_pane = new JScrollPane(t);                 //incializa o scrollpane com a tabela
            t.setPreferredScrollableViewportSize(new Dimension(x, y)); //seta o tamanho da tabela
            t.setFillsViewportHeight(true);

        //adiciona o título do painel na linha 0 e coluna 0
        c.weightx = 0.5; //distancia entre os elementos
        //adiciona a tabela na linha 1 e preenche 3 colunas
        c.gridwidth = 3;
        c.gridx = 0; c.gridy = 0;
            panel.add(table_pane, c);
        //adiciona os botões de controle na linha 2 e coluna 0
        c.gridx = 0; c.gridy = 1;  
            panel.add(button_box, c);
            
        return panel;
    }

    /**
     * Cria um JMenu item com seus itens passados
     * pelos argumentos ...
     * @return JMenuItem
     */
    public JMenu createMenu(String ... args)
    {
        JMenu menu;

        if(args.length == 0)
        {
            return null;
        } else if(args.length == 1) 
        {
            menu = new JMenu(args[0]);
            return menu;
        } else {
            menu = new JMenu(args[0]);
            for(int i = 1; i < args.length; i++)
                menu.add(new JMenuItem(args[i]));
        }

        return menu;
    }//createMenu

    public void setUpTable()
    {
        pacient_model = new DefaultTableModel(PacienteHandle.createTable(), GUI_HELPER.PATIENT_COLUMNS);
        pacient_table = new JTable(pacient_model);

        pacient_panel = usersPanel(GUI_HELPER.PATIENT_BUTTONS, GUI_HELPER.PATIENT_COLUMNS, PacienteHandle.createTable(), pacient_model, pacient_table);
    }

    public JPanel usersPanel(String[] bn, String[] tc, Object[][] tr, DefaultTableModel tm, JTable t)
    {
        TitledBorder title = BorderFactory.createTitledBorder("Pacientes");
        Box button_box = new Box(BoxLayout.X_AXIS); 
        JButton[] buttons = new JButton[bn.length];          //botões do painel
            for(int i = 0; i < bn.length; i++)
            {
                buttons[i] = new JButton(bn[i]);
                button_box.add(buttons[i]);
            }//for
        buttons[0].addActionListener(new RmvListener());
        buttons[1].addActionListener(new AddConsultListener());
        JPanel panel;

        panel = createTablePanel(800, 200, tc, tr, tm, t, button_box);
        panel.setBorder(title);
        return panel;
    }

    public JComponent getRootComponent()
    {
        return this.pacient_panel;
    }

    class RmvListener implements ActionListener
    {   
        @Override
        public void actionPerformed(ActionEvent ev)
        {
            int index = pacient_table.getSelectedRow();
            String patient_name = (String)pacient_table.getValueAt(index, 0);

            if(PacienteHandle.rmv(patient_name) == true)
            {
                pacient_model.removeRow(index);
                pacient_table.clearSelection();
                pacient_model.fireTableDataChanged();
                JOptionPane.showMessageDialog(null, "paciente: "+ patient_name + " deletado com sucesso.");
            } else {
                JOptionPane.showMessageDialog(null, "paciente não existente no arquivo.");  
            }//else
        }//actionPerformed
    }//RmvExamListener

    class AddConsultListener implements ActionListener
    {   
        @Override
        public void actionPerformed(ActionEvent ev)
        {
            int index = pacient_table.getSelectedRow();
            if(index == -1)
            {
                JOptionPane.showMessageDialog(null, "Nenhum paciente foi selecionado!");
                return;
            }else {
                String patient_name = (String)pacient_table.getValueAt(index, 0);
                JFrame frame = new JFrame("Adicionar consulta");
                ConsultForm cf = new ConsultForm(patient_name, ct);
                frame.getContentPane().add(cf.$$$getRootComponent$$$());
                frame.setVisible(true);
                frame.pack();
            }
        }//actionPerformed
    }//RmvExamListener
}