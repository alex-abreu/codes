import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;


public class ConsultTable
{
    private JPanel consult_panel;
    JButton[] buttons;
    //consult table
    public DefaultTableModel consult_model;
    public JTable consult_table;

    {
        setUpTable();
    }

    public void externalAddOperation(Object[] data)
    {
        System.out.println("addoperation");
        consult_model.addRow(data);
        consult_table.clearSelection();
        consult_model.fireTableDataChanged();
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
        consult_model = new DefaultTableModel(ConsultaHandle.createTable(), GUI_HELPER.CONSULT_COLUMNS);
        consult_table = new JTable(consult_model);

        consult_panel = usersPanel(GUI_HELPER.CONSULT_BUTTONS, GUI_HELPER.CONSULT_COLUMNS, ConsultaHandle.createTable(), consult_model, consult_table);
    }

    public JPanel usersPanel(String[] bn, String[] tc, Object[][] tr, DefaultTableModel tm, JTable t)
    {
        TitledBorder title = BorderFactory.createTitledBorder("Consultas cadastradas no sistema");
        Box button_box = new Box(BoxLayout.X_AXIS); 
        buttons = new JButton[bn.length];          //botões do painel
            for(int i = 0; i < bn.length; i++)
            {
                buttons[i] = new JButton(bn[i]);
                button_box.add(buttons[i]);
            }//for

        JPanel panel;   
        buttons[0].addActionListener(new RmvListener());
        buttons[1].addActionListener(new DesmarcListener());
        buttons[2].addActionListener(new RemarcListener());

        panel = createTablePanel(300, 100, tc, tr, tm, t, button_box);
        panel.setBorder(title);
        return panel;
    }

    public JComponent getRootComponent()
    {
        return this.consult_panel;
    }

    class RmvListener implements ActionListener
    {   
        @Override
        public void actionPerformed(ActionEvent ev)
        {
            int index = consult_table.getSelectedRow();
            if(index == -1)
            {
                JOptionPane.showMessageDialog(null,"Selecione uma consulta a ser desmarcada/removida","AVISO",JOptionPane.WARNING_MESSAGE);
                return;
            }
            String data = (String) consult_table.getValueAt(index, 0);
            String hour = (String) consult_table.getValueAt(index, 1);
            String medic_name = (String) consult_table.getValueAt(index, 2);
            String status = (String) consult_table.getValueAt(index, 4);

            /**
             * prevent null pointer exception
             */
            if(status == null)
                status = "";

            if(ConsultaHandle.desmarcarConsulta(data, hour, medic_name) == false)
            {
                JOptionPane.showMessageDialog(null,"Erro ao desmarcar consulta","AVISO",JOptionPane.WARNING_MESSAGE);
                return;
            }
            consult_model.removeRow(index);
            consult_table.clearSelection();
            consult_model.fireTableDataChanged();
            JOptionPane.showMessageDialog(null, "consulta do medico: "+ medic_name + " foi removida.");
        }
    }//RmvExamListener

    class DesmarcListener implements ActionListener
    {   
        @Override
        public void actionPerformed(ActionEvent ev)
        {   

            int index = consult_table.getSelectedRow();
            if(index == -1)
            {
                JOptionPane.showMessageDialog(null,"Selecione uma consulta a ser desmarcada","AVISO",JOptionPane.WARNING_MESSAGE);
                return;
            }
            String data = (String) consult_table.getValueAt(index, 0);
            String hour = (String) consult_table.getValueAt(index, 1);
            
            String medic_name = (String) consult_table.getValueAt(index, 2);
            String status = (String) consult_table.getValueAt(index, 4);

            /**
             * prevent null pointer exception
             */
            if(status == null)
                status = "";

            if(status.equals("finalizada"))
            {
                JOptionPane.showMessageDialog(null, "consulta do medico: "+ medic_name + " já foi feita.");
                return;
            } 
            
            if(ConsultaHandle.desmarcarConsulta(data, hour, medic_name) == false)
            {
                JOptionPane.showMessageDialog(null,"Erro ao desmarcar consulta","AVISO",JOptionPane.WARNING_MESSAGE);
                return;
            }

            consult_model.removeRow(index);
            consult_table.clearSelection();
            consult_model.fireTableDataChanged();
            JOptionPane.showMessageDialog(null, "consulta do medico: "+ medic_name + " desmarcada com sucesso.");
        }//actionPerformed
    }//RmvExamListener


    class RemarcListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ev)
        {   
            int index = consult_table.getSelectedRow();
            if(index == -1)
            {
                JOptionPane.showMessageDialog(null,"Selecione uma consulta a ser remarcada","AVISO",JOptionPane.WARNING_MESSAGE);
                return;
            }
            String l_data = (String) consult_table.getValueAt(index, 0);
            String l_hour = (String) consult_table.getValueAt(index, 1);

            String data = JOptionPane.showInputDialog(null, "Insira a nova data dd-mm-aaaa");
                if(ValidDateAndHour.validaData(data, false) == false)
                    return;
            String hour = JOptionPane.showInputDialog(null, "Insira o novo horário hh:mm");
                if(ValidDateAndHour.validaHora(hour) == false)
                    return;

            String medic_name = (String) consult_table.getValueAt(index, 2);
            String status = (String) consult_table.getValueAt(index, 4);

            /**
             * prevent null pointer exception
             */
            if(status == null)
                status = "";

            if(status.equals("finalizada"))
            {
                JOptionPane.showMessageDialog(null, "consulta do medico: "+ medic_name + " já foi feita.");
                return;
            } 

            if(ConsultaHandle.remarcarConsulta(l_data, l_hour, data, hour, medic_name) == false)
            {
                JOptionPane.showMessageDialog(null,"Erro ao remarcar consulta","AVISO",JOptionPane.WARNING_MESSAGE);
                return;
            }
            consult_model.setValueAt(data, index, 0);
            consult_model.setValueAt(hour, index, 1);
            consult_table.clearSelection();
            consult_model.fireTableDataChanged();
            JOptionPane.showMessageDialog(null, "consulta do medico: "+ medic_name + " remarcada com sucesso.");
        }
    }
}
