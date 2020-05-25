import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.*;


import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;


import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class SecDash 
{
    //system objects
    ExameHandle e;
    JFrame mainFrame;
    ConsultTable ct = new ConsultTable();
    PacientTable pt = new PacientTable(ct);
    PacientForm pf = new PacientForm(pt);
    //exam_table
    private DefaultTableModel exam_model;
    private JTable exam_table;
    private JTextField[] exam_fields;
    private JTextArea obs_field;

    {
        setUpGUI();
    }

    public void clearExamsFields()
    {
        exam_fields[0].setText("");
        exam_fields[1].setText("");
        exam_fields[2].setText("");
        exam_fields[3].setText("");
        obs_field.setText("");
    }

    /**
     * Cria um painel que contém uma tabela e os seus 
     * botões de controle, recebe dentre outras coisas
     * o tamanho preferido de exibição da tabela
     * usa o GridBagLayout
     * @return JPanel
     */
    public JPanel createTablePanel(int x, int y, String[] tc, String[][] tr, 
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

    public JFrame getRootComponent()
    {
        return mainFrame;
    }

    public void setUpGUI()
    {
        mainFrame = new JFrame("Clínica Médica Dr. Swing");
        e = new ExameHandle();
        exam_model = new DefaultTableModel(e.createTable(), GUI_HELPER.EXAMS_COLUMNS);//inicializa o modelo da tabela
        exam_table =  new JTable(exam_model);                                         //inicia a tabela com o modelo
        exam_fields = new JTextField[4];                                                   //inicia os campos do formulario da tabela
            exam_fields[0] = new JTextField(30);
            exam_fields[1] = new JTextField(8);
            exam_fields[2] = new JTextField(1);
            exam_fields[3] = new JTextField(1);
            obs_field = new JTextArea(3, 40);

        JMenuBar topContent = mainBar();

        JPanel left_panel = new JPanel();
            left_panel.setLayout(new BoxLayout(left_panel, BoxLayout.Y_AXIS));
        JPanel center_panel = new JPanel();
            center_panel.setLayout(new BoxLayout(center_panel, BoxLayout.Y_AXIS));

        JPanel exam_form_panel = examForm(exam_fields, obs_field);
        JPanel exam_panel = examPanel(GUI_HELPER.EXAMS_BUTTONS_NAMES, 
                                       GUI_HELPER.EXAMS_COLUMNS, e.createTable(), 
                                                         exam_model, exam_table);
        left_panel.add(exam_form_panel);
        left_panel.add(exam_panel);

        center_panel.add(pf.$$$getRootComponent$$$());
        center_panel.add(pt.getRootComponent());
        center_panel.add(ct.getRootComponent());
        //JPanel examContent = consultPanel();
        //JPanel patientContent = patientPanel();

        mainFrame.getContentPane().add(BorderLayout.NORTH, topContent);
        mainFrame.getContentPane().add(BorderLayout.EAST, left_panel);
        mainFrame.getContentPane().add(BorderLayout.CENTER, center_panel);
    }

    /**
     * Cria um painel contendo o formulário de cadastro
     * de exame, o gerenciador de layout será o GridBagLayout
     */
    public JPanel examForm(JTextField[] fields, JTextArea obs)
    {
        JLabel[] form_labels = new JLabel[GUI_HELPER.EXAMS_FORM.length];

        //box para duração e resultado 
        Box[] form_box = new Box[2];
            form_box[0] = new Box(BoxLayout.X_AXIS);
            form_box[1] = new Box(BoxLayout.X_AXIS);

        //obs pane
        JScrollPane obs_pane = new JScrollPane(obs);
        JPanel panel = new JPanel();
        GridBagConstraints c = new GridBagConstraints();
        TitledBorder title = BorderFactory.createTitledBorder("Formulário exame");

        panel.setLayout(new GridBagLayout());
        panel.setBorder(title);
        c.fill = GridBagConstraints.HORIZONTAL;          //completa pela horizontal, se necessário

        //carregando as labels do formulário
        for(int i = 0; i < GUI_HELPER.EXAMS_FORM.length; i++)
        {    
            form_labels[i] = new JLabel(GUI_HELPER.EXAMS_FORM[i]);
            System.out.println(GUI_HELPER.EXAMS_FORM[i]);
        }

        c.weightx = 0.5;

        //primeira linha

        //carregando o box com os itens
        form_box[0].add(form_labels[0]);
        form_box[0].add(Box.createRigidArea(new Dimension(5, 0)));
        form_box[0].add(fields[0]);
        form_box[0].add(Box.createRigidArea(new Dimension(10, 0)));
        form_box[0].add(form_labels[1]);
        form_box[0].add(Box.createRigidArea(new Dimension(5, 0)));
        form_box[0].add(fields[1]);

        c.gridy = 0;
        c.gridx = 0;
            panel.add(form_box[0], c);
        //segunda linha
        c.gridy = 1;
        c.gridx = 0;
            panel.add(form_labels[2], c);
        
        //terceira linha
        c.gridy = 2;
        //c.gridwidth = 4;
        c.gridx = 0;
            panel.add(obs_pane, c);

        //quarta linha

        //carregando o box com os itens
        form_box[1].add(form_labels[3]);
        form_box[1].add(Box.createRigidArea(new Dimension(5, 0)));
        form_box[1].add(fields[2]);
        form_box[1].add(Box.createRigidArea(new Dimension(10, 0)));
        form_box[1].add(form_labels[4]);
        form_box[1].add(Box.createRigidArea(new Dimension(5, 0)));
        form_box[1].add(fields[3]);

        c.gridy = 3;
        c.gridx = 0;
            panel.add(form_box[1], c);
        return panel;
    }//examForm

    /**
     * Essa função criará um JPanel que armazenará uma JLabel,
     * uma tabela e um grupo de botões. O gerenciador de layout
     * sera o GridBagLayout.
     *  */
    public JPanel examPanel(String[] bn, String[] tc, String[][] tr, DefaultTableModel tm, JTable t)
    {
        TitledBorder title = BorderFactory.createTitledBorder("Exames");
        Box button_box = new Box(BoxLayout.X_AXIS);  //caixa para os botões
        JButton[] buttons = new JButton[2];          //botões do painel
        JPanel exam_panel;

        for(int i = 0; i < bn.length; i++)
        {
            buttons[i] = new JButton(bn[i]);
            button_box.add(buttons[i]);
        }//for

        //adicionar os listeners dos botões
        buttons[0].addActionListener(new AddExamListener());
        buttons[1].addActionListener(new RmvExamListener());
        
        //criar o painel com a tabela
        exam_panel = createTablePanel(500,150, tc, tr, tm, t, button_box);
        exam_panel.setBorder(title);
        return exam_panel;
    }//examPanel        

    /**
     * Cria uma JMenuBar para o frame principal
     * @return JMenuBar
     */
    public JMenuBar mainBar()
    {
        JMenuBar mainBar = new JMenuBar();

        JMenu fileMenu = createMenu(GUI_HELPER.FILE_MENU);
            fileMenu.getItem(0).addActionListener(new QuitListener());
        mainBar.add(fileMenu);
        return mainBar;
    }//mainBar

    /**
     * LISTENERS PARA CAPTURAR OS EVENTOS DA GUI
     */
    class QuitListener implements ActionListener
    { //eu escrevi isso ? "salto linhas pra ficar bonitinho" carai, estou assustado
        @Override
        public void actionPerformed(ActionEvent ev)
        {
            ConsultaHandle.gravarConsultas();
            PacienteHandle.gravarPacientes();
            System.exit(0);
        }
    }//QuitListener


    class AddExamListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ev)
        {
            Object[] data = 
            {
                exam_fields[0].getText(), 
                exam_fields[1].getText(),
                obs_field.getText(), 
                exam_fields[2].getText(),
                exam_fields[3].getText()
            };

                int handle = e.addExame(data);
                if(handle == -1)
                {
                    exam_model.addRow(data);

                    exam_table.clearSelection();
                    exam_model.fireTableDataChanged();
                    clearExamsFields();

                    JOptionPane.showMessageDialog(null, "exame: "+ exam_fields[0].getText() + " adicionado com sucesso");
                } else if(handle == 8) {
                    
                    clearExamsFields();
                    JOptionPane.showMessageDialog(null, "Não se pode dar um nome vazio a um exame!");
                } else if(handle == 4){
                    clearExamsFields();
                    JOptionPane.showMessageDialog(null, "Codigo já perternce a outro exame, exame não adicionado!");
                }
                else {
                    clearExamsFields();
                    JOptionPane.showMessageDialog(null, "exame: "+ exam_fields[0].getText() + " não foi adicionado. Exame existente ou erro ao acessar o arquivo!");
            }
        }//actionPerformed
    }//addExamListener

    class MenuAddExamListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ev)
        {

        }//saveExameListneer
    }//AddExamListener

    class RmvExamListener implements ActionListener
    {   
        @Override
        public void actionPerformed(ActionEvent ev)
        {
            int exam_index = exam_table.getSelectedRow();
            String exam_name = (String)exam_table.getValueAt(exam_index, 0);

            if(e.delExame(exam_name) != -1)
            {
                JOptionPane.showMessageDialog(null, "erro ao remover o exame do arquivo.");  
            } else {
                exam_model.removeRow(exam_index);
                exam_table.clearSelection();
                exam_model.fireTableDataChanged();
                JOptionPane.showMessageDialog(null, "exame: "+ exam_name + " deletado com sucesso.");
            }//else

        }//actionPerformed
    }//RmvExamListener
    
    class MenuRmvExamListener implements ActionListener
    {   
        String exam_name;
        int exam_index = -1;
        boolean user_cancel = false;

        public void handleOpt()
        {
            exam_name = JOptionPane.showInputDialog(null, "Qual o nome do exame a ser deletado?");
            String file_name;
            if(exam_name == null)
            {
                user_cancel = true;
            } else if(exam_name.length() == 0)
            {
                JOptionPane.showMessageDialog(null, "Nome inválido!");
                handleOpt(); 
            } else {
                int opt = JOptionPane.showConfirmDialog(null, "O exame a ser deletado é: " + exam_name + " ?");
                file_name = exam_name.concat(".txt"); //no array listExams, o nome do arquivo está completo
                switch(opt)
                {
                    case(JOptionPane.YES_OPTION) : 
                    {
                        ArrayList<String> exams = e.getExam();
                        exam_index = exams.indexOf(file_name);
                            //System.out.println(exam_index + " " + exams.size());
                        break;
                    }
                    case(JOptionPane.NO_OPTION)  : handleOpt(); break;
                    case(JOptionPane.CANCEL_OPTION): user_cancel = true; break;
                }
            }
        }//handleopt

        @Override
        public void actionPerformed(ActionEvent ev)
        {
            handleOpt();
            if(exam_index == -1 && user_cancel == false)
            {
                JOptionPane.showMessageDialog(null, "Exame inexistente"); 
                return;
            } else if(user_cancel)
            {
                user_cancel = false;
                return;
            } else {
                if(e.delExame(exam_name) != -1)
                {
                    JOptionPane.showMessageDialog(null, "erro ao remover o exame do arquivo.");  
                } else {
                    exam_model.removeRow(exam_index);
                    exam_table.clearSelection();
                    exam_model.fireTableDataChanged();
                    JOptionPane.showMessageDialog(null, "exame: "+ exam_name + " deletado com sucesso.");
                }//else
            }
        }//actionPerformed
    }//MenuRmvExamListener
}