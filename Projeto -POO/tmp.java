    /**
     * Cria um JPanel que contém uma tabela, usando o GridBagLayout
     * @return JPanel
     * */
    public JPanel createTablePanel(String[] table_columns, Object[][] table_rows, JButton[] buttons, 
                                                          String[] buttons_names, String panel_name, 
                                                                     DefaultTableModel tm, JTable t) 
    {
        JLabel panel_title = new JLabel(panel_name);           //título do painel
        Box button_box = new Box(BoxLayout.X_AXIS);            //caixa para os botoes
        buttons = new JButton[buttons_names.length];           //botões do painel

        JPanel table_panel = new JPanel();
            table_panel.setLayout(new GridBagLayout());        //setando o layout
        GridBagConstraints c = new GridBagConstraints();       //definições do layout
        c.fill = GridBagConstraints.HORIZONTAL;                //se necessário, aumente o tamanho do componente na horizontal

        //definindo o modelo da tabela e a tabela
        tm = new DefaultTableModel(table_rows, table_columns);
        t = new JTable(tm); 
        JScrollPane spane = new JScrollPane(t); //cria um scrollpane com a tabela
            t.setPreferredScrollableViewportSize(new Dimension(500, 300));
            t.setFillsViewportHeight(true);
        
        //adicionar o título do painel na linha 0 e coluna 0
        c.weightx = 0.5; //definir o espaço distribuido pelas colunas (0.0 - 1.0)
        c.gridx = 0; c.gridy = 0;
            table_panel.add(panel_title, c);
        
        //criar o grupo de botões
        for(int i = 0; i < buttons_names.length; i++)
        {
            buttons[i] = new JButton(buttons_names[i]);
            button_box.add(buttons[i]);
        }   

        //adicionar tabela na linha 1 e preencher 3 colunas na horizontal
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
            table_panel.add(spane, c);
        
        //adicionar os botões na linha 2 
        c.gridx = 0;
        c.gridy = 2;
            table_panel.add(button_box, c);
        
        return table_panel;
    }
    