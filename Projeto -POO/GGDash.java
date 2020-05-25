import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.*;
import java.io.*;
import java.io.IOException; 
import java.nio.file.*; 

public class GGDash 
{
    private JPanel users_panel;
    ArrayList<String> helper_horarios = new ArrayList<String>();

    //users_table
    public DefaultTableModel users_model;
    public JTable users_table;

    {
        setUpTable();
    }

    public void loadHelper()
    {
        try
        {
            File f = new File("sechelper.txt");
            BufferedReader br = new BufferedReader(new FileReader(f));

            String line = "";
            while(line != null)
            {
                helper_horarios.add(line);
                line = br.readLine();
            }
            br.close();

            for(int i = 0; i < helper_horarios.size(); i++)
            {
                System.out.println(helper_horarios.get(i));
            }

        } catch(Exception e){

			String error = e.toString();
		}
    }

    public void writeSecFile()
    {
        try
        {
            File file = new File("sechelper.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));

            for(int i = 0; i < helper_horarios.size(); i++)
                writer.write(helper_horarios.get(i));
            writer.close();

        }catch(Exception e){
			String error = e.toString();
		}
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
        users_model = new DefaultTableModel(Usuarios.createTable(), GUI_HELPER.USERS_COLUMNS);
        users_table = new JTable(users_model);

        users_panel = usersPanel(GUI_HELPER.USERS_BUTTON_NAMES, GUI_HELPER.USERS_COLUMNS, Usuarios.createTable(), users_model, users_table);
    }

    public JPanel usersPanel(String[] bn, String[] tc, Object[][] tr, DefaultTableModel tm, JTable t)
    {
        TitledBorder title = BorderFactory.createTitledBorder("Usuários do sistema");
        Box button_box = new Box(BoxLayout.X_AXIS); 
        JButton[] buttons = new JButton[bn.length];          //botões do painel
            for(int i = 0; i < bn.length; i++)
            {
                buttons[i] = new JButton(bn[i]);
                button_box.add(buttons[i]);
            }//for

        buttons[0].addActionListener(new RmvListener());
        JPanel panel;

        panel = createTablePanel(300, 100, tc, tr, tm, t, button_box);
        panel.setBorder(title);
        return panel;
    }

    public JPanel getRootComponent()
    {
        return this.users_panel;
    }

    class RmvListener implements ActionListener
    {   
        @Override
        public void actionPerformed(ActionEvent ev)
        {
            int index = users_table.getSelectedRow();
            String user_name = (String)users_table.getValueAt(index, 0);
            Usuario u = Usuarios.buscaUsuario(user_name);
            if(u != null)
            {
                if(u.getTipoFuncionario().getStringFuncionario().equals("Secretária"))
                {
                    loadHelper();
                    Secretaria c = (Secretaria) u.getTipoFuncionario();
                    int hours[] = c.getHours();
                    String out = String.format("%d %d %d %d", hours[0], hours[1], hours[2], hours[3]);
                    System.out.println("hora para deletar"+out);
                    for(int i = 0; i < helper_horarios.size(); i++)
                    {
                        if(helper_horarios.get(i).equals(out))
                        {
                            helper_horarios.remove(i);
                        }
                    }
                    writeSecFile();
                    Usuarios.rmvUsuario(user_name);
                    users_model.removeRow(index);
                    users_table.clearSelection();
                    users_model.fireTableDataChanged();
                    JOptionPane.showMessageDialog(null, "usuario: "+ user_name + " deletado com sucesso.");
                } else {
                    Usuarios.rmvUsuario(user_name);
                    users_model.removeRow(index);
                    users_table.clearSelection();
                    users_model.fireTableDataChanged();
                    JOptionPane.showMessageDialog(null, "usuario: "+ user_name + " deletado com sucesso.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "erro ao remover o usuário do arquivo.");  
                return;
            }
        }
    }//RmvExamListener
}