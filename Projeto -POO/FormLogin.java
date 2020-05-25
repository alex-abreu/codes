import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class FormLogin
{
    //stores the type of useroi
    private int user_type;
    
    private JPanel login_form;
    private JPanel right_panel;
    private JButton entrarButton;
    private JPanel left_panel;
    
    //user_data
    Usuarios user_crud = new Usuarios();
    Usuario current_user;
    JTextField user_name;
    JPasswordField user_password;

    private JFrame parent_jframe;
    

    {
        // GUI initializer generated by IntelliJ IDEA GUI Designer
        // >>> IMPORTANT!! <<<
        // DO NOT EDIT OR ADD ANY CODE HERE!
                setUpGUI();
                $$$setupUI$$$();
    }
    

    public void clearLoginFields()
    {
        user_name.setText("");
        user_password.setText("");
    }
        
    private void setUpGUI()
    {
        user_name = new JTextField();
        user_password = new JPasswordField();
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
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() 
    {

        login_form = new JPanel();
        login_form.setLayout(new BorderLayout(0, 0));
        login_form.setBackground(new Color(-12470785));
        right_panel = new JPanel();
        right_panel.setLayout(new GridBagLayout());
        right_panel.setBackground(new Color(-1));
        login_form.add(right_panel, BorderLayout.EAST);
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$("FreeSerif", Font.BOLD, 18, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setForeground(new Color(-12470785));
        label1.setText("Usuário");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        right_panel.add(label1, gbc);
        user_name.setBackground(new Color(-1));
        user_name.setColumns(20);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        right_panel.add(user_name, gbc);
        final JLabel label2 = new JLabel();
        Font label2Font = this.$$$getFont$$$("FreeSerif", Font.BOLD, 18, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setForeground(new Color(-12470785));
        label2.setText("Senha");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        right_panel.add(label2, gbc);
        user_password.setBackground(new Color(-1));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        right_panel.add(user_password, gbc);
        entrarButton = new JButton();
        entrarButton.setBackground(new Color(-1));
        entrarButton.setForeground(new Color(-12470785));
        entrarButton.setText("Entrar");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        right_panel.add(entrarButton, gbc);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        panel1.setBackground(new Color(-1));
        panel1.setPreferredSize(new Dimension(10, 0));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        right_panel.add(panel1, gbc);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());
        panel2.setBackground(new Color(-1));
        panel2.setPreferredSize(new Dimension(10, 0));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        right_panel.add(panel2, gbc);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridBagLayout());
        panel3.setBackground(new Color(-1));
        panel3.setPreferredSize(new Dimension(0, 50));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.BOTH;
        right_panel.add(panel3, gbc);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridBagLayout());
        panel4.setBackground(new Color(-1));
        panel4.setPreferredSize(new Dimension(0, 50));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        right_panel.add(panel4, gbc);
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridBagLayout());
        panel5.setBackground(new Color(-1));
        panel5.setInheritsPopupMenu(true);
        panel5.setPreferredSize(new Dimension(0, 20));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.BOTH;
        right_panel.add(panel5, gbc);
        left_panel = new JPanel();
        left_panel.setLayout(new GridBagLayout());
        left_panel.setBackground(new Color(-12470785));
        login_form.add(left_panel, BorderLayout.WEST);
        final JLabel label3 = new JLabel();
        Font label3Font = this.$$$getFont$$$("Noto Mono", Font.BOLD, 18, label3.getFont());
        if (label3Font != null) label3.setFont(label3Font);
        label3.setForeground(new Color(-1));
        label3.setText("Clínica Dr. Swing - LOGIN ");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        left_panel.add(label3, gbc);
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridBagLayout());
        panel6.setBackground(new Color(-12470785));
        panel6.setPreferredSize(new Dimension(38, 0));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        left_panel.add(panel6, gbc);

        entrarButton.addActionListener(new LoginListener());
    }


    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() 
    {
        return login_form;
    }

    public void setParentJFrame(JFrame f)
    {
        this.parent_jframe = f;
    }

    class QuitListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ev)
        {
            System.exit(0);
        }
    }

    class LoginListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ev)
        {
            String pass_text = new String(user_password.getPassword());
            current_user = new Usuario(user_name.getText(), pass_text);
            clearLoginFields();

            int flag = current_user.autenticaUsuario();
            switch(flag)
            {
                case 0: 
                {
                    JOptionPane.showMessageDialog(null, "Usuário já está logado", "Erro login", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                case 1 : 
                {
                    
                    JOptionPane.showMessageDialog(null, "Usuário não cadastrado no sistema", "Erro login", JOptionPane.ERROR_MESSAGE);
                    return;
                    
                }

                case 2 : 
                {
                    JOptionPane.showMessageDialog(null, "Senha incorreta", "Erro login", JOptionPane.ERROR_MESSAGE);
                    return; 
                }

                case -1 : 
                {
                    user_type = current_user.getTipoFuncionario().getCodigoDeAcesso();
                    System.out.println("tipo de usuário:" + user_type);
                    if(user_type == 0)
                    {
                        //fechar o login
                        parent_jframe.dispatchEvent(new WindowEvent(parent_jframe, WindowEvent.WINDOW_CLOSING));

                        FormCad gg_dash = new FormCad();
                        JFrame frame = new JFrame("Gerente Geral");
                        JMenuBar main_bar = mainBar();
                            main_bar.getMenu(0).getItem(0).addActionListener(new SaveUsersListener());
                        frame.getContentPane().add(BorderLayout.NORTH, main_bar);
                        frame.getContentPane().add(gg_dash.getBackgroundPanel());
                        frame.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosing(WindowEvent e) 
                            {
                                int input = JOptionPane.showConfirmDialog(null, 
                                "Deseja sair? Todos os dados serão salvos", "Escolha uma das opções....",JOptionPane.YES_NO_CANCEL_OPTION);
                                switch(input)
                                {
                                    case 0 : 
                                    {
                                        Usuarios.salvaUsuarios();
                                        ConsultaHandle.gravarConsultas();
                                        PacienteHandle.gravarPacientes();
                                        System.exit(0);
                                        break;
                                    }

                                    case 1 : break;
                                    case 2 : break;

                                }
                            }
                        });
                        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                        frame.setVisible(true);
                        frame.setSize(800,600);
                    }else if(user_type == 2)
                    {
                        //fechar o login
                        parent_jframe.dispatchEvent(new WindowEvent(parent_jframe, WindowEvent.WINDOW_CLOSING));

                        SecDash sec_dash = new SecDash();
                        JFrame frame = sec_dash.getRootComponent();
                        frame.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosing(WindowEvent e) 
                            {
                                int input = JOptionPane.showConfirmDialog(null, 
                                "Deseja sair? Todos os dados serão salvos", "Escolha uma das opções....",JOptionPane.YES_NO_CANCEL_OPTION);
                                switch(input)
                                {
                                    case 0 : 
                                    {
                                        Usuarios.salvaUsuarios();
                                        ConsultaHandle.gravarConsultas();
                                        PacienteHandle.gravarPacientes();
                                        System.exit(0);
                                        break;
                                    }

                                    case 1 : break;
                                    case 2 : break;

                                }
                            }
                        });
                        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                        frame.setVisible(true);
                        frame.pack();
                    } else {

                        //fechar o login
                        parent_jframe.dispatchEvent(new WindowEvent(parent_jframe, WindowEvent.WINDOW_CLOSING));

                        String medic_name = current_user.getTipoFuncionario().getName();
                            System.out.println("login:"+medic_name);

                        MedDash md = new MedDash(medic_name);
                        JFrame frame = new JFrame("Medico");
                        JMenuBar main_bar = mainBar();
                            main_bar.getMenu(0).getItem(0).addActionListener(new SecPacientsListener());
                        frame.getContentPane().add(BorderLayout.NORTH, main_bar);
                        frame.getContentPane().add(md.getRootComponent());
                        frame.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosing(WindowEvent e) 
                            {
                                int input = JOptionPane.showConfirmDialog(null, 
                                "Deseja sair? Todos os dados serão salvos", "Escolha uma das opções....",JOptionPane.YES_NO_CANCEL_OPTION);
                                switch(input)
                                {
                                    case 0 : 
                                    {
                                        Usuarios.salvaUsuarios();
                                        ConsultaHandle.gravarConsultas();
                                        PacienteHandle.gravarPacientes();
                                        System.exit(0);
                                        break;
                                    }

                                    case 1 : break;
                                    case 2 : break;

                                }
                            }
                        });
                        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                        frame.setVisible(true);
                        frame.setSize(1500,600);
                    }
                    return;
                }
            }
        }

        class SaveUsersListener implements ActionListener
        {
            @Override
            public void actionPerformed(ActionEvent ev)
            {
                Usuarios.salvaUsuarios();
                PacienteHandle.gravarPacientes();
                ConsultaHandle.gravarConsultas();
                System.exit(0);
            }
        }

        class SecPacientsListener implements ActionListener
        {
            @Override
            public void actionPerformed(ActionEvent ev)
            {
                PacienteHandle.gravarPacientes();
                ConsultaHandle.gravarConsultas();
                Usuarios.salvaUsuarios();
                System.exit(0);
            }
        }
    }//reloadListener
}