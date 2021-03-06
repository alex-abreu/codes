import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ConsultPanel 
{
    ExameHandle e = new ExameHandle();
    DefaultListModel d = new DefaultListModel();
    DefaultListModel md;
    JPanel background;
    JButton sc;
    String medic;
    int index;
    Consulta c;

    private JPanel consult_panel;
    private JTextArea doctor_sint;
    private JTextField medicines;
    private JList exam_list;
    private JComboBox exam_combo_box;
    private JButton add_exam_button;
    private JButton end_consulta_button;
    private JButton removerExameButton;

    public ConsultPanel(JPanel back, JButton sc, String m, DefaultListModel md, int index)
    {
        this.background = back;
        this.sc = sc;
        this.medic = m;
        this.md = md;
        this.index = index;
        System.out.println(medic);
        System.out.println(index);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        consult_panel = new JPanel();
        consult_panel.setLayout(new GridBagLayout());
        consult_panel.setBorder(BorderFactory.createTitledBorder(null, "Consulta", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, new Color(-16777216)));
        final JScrollPane scrollPane1 = new JScrollPane();
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.BOTH;
        consult_panel.add(scrollPane1, gbc);
        doctor_sint = new JTextArea();
        doctor_sint.setColumns(80);
        doctor_sint.setRows(10);
        scrollPane1.setViewportView(doctor_sint);
        final JScrollPane scrollPane2 = new JScrollPane();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 10;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.BOTH;
        consult_panel.add(scrollPane2, gbc);
        exam_list = new JList(d);
        scrollPane2.setViewportView(exam_list);
        exam_combo_box = new JComboBox(e.createExamList());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        consult_panel.add(exam_combo_box, gbc);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        panel1.setPreferredSize(new Dimension(10, 0));
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        consult_panel.add(panel1, gbc);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());
        panel2.setPreferredSize(new Dimension(10, 0));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        consult_panel.add(panel2, gbc);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridBagLayout());
        panel3.setPreferredSize(new Dimension(0, 10));
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 11;
        gbc.fill = GridBagConstraints.BOTH;
        consult_panel.add(panel3, gbc);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridBagLayout());
        panel4.setPreferredSize(new Dimension(0, 10));
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.BOTH;
        consult_panel.add(panel4, gbc);
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridBagLayout());
        panel5.setPreferredSize(new Dimension(0, 5));
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.BOTH;
        consult_panel.add(panel5, gbc);
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridBagLayout());
        panel6.setPreferredSize(new Dimension(0, 5));
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.BOTH;
        consult_panel.add(panel6, gbc);
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new GridBagLayout());
        panel7.setPreferredSize(new Dimension(0, 5));
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        consult_panel.add(panel7, gbc);
        final JPanel panel8 = new JPanel();
        panel8.setLayout(new GridBagLayout());
        panel8.setPreferredSize(new Dimension(0, 10));
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 13;
        gbc.fill = GridBagConstraints.BOTH;
        consult_panel.add(panel8, gbc);
        add_exam_button = new JButton();
        add_exam_button.setText("Solicitar exame");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 12;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        consult_panel.add(add_exam_button, gbc);
        end_consulta_button = new JButton();
        end_consulta_button.setText("Finalizar Consulta");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 14;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        consult_panel.add(end_consulta_button, gbc);
        final JPanel panel9 = new JPanel();
        panel9.setLayout(new GridBagLayout());
        panel9.setPreferredSize(new Dimension(550, 0));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 14;
        gbc.fill = GridBagConstraints.BOTH;
        consult_panel.add(panel9, gbc);
        final JLabel label1 = new JLabel();
        label1.setText("Exames");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.WEST;
        consult_panel.add(label1, gbc);
        final JLabel label2 = new JLabel();
        label2.setText("Remédios (use , para separar um medicamento de outro)");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        consult_panel.add(label2, gbc);
        medicines = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        consult_panel.add(medicines, gbc);
        final JLabel label3 = new JLabel();
        label3.setText("Sintomas");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        consult_panel.add(label3, gbc);
        removerExameButton = new JButton();
        removerExameButton.setText("Remover exame");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 12;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        consult_panel.add(removerExameButton, gbc);
        final JPanel panel10 = new JPanel();
        panel10.setLayout(new GridBagLayout());
        panel10.setPreferredSize(new Dimension(5, 0));
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 12;
        gbc.fill = GridBagConstraints.BOTH;
        consult_panel.add(panel10, gbc);

        add_exam_button.addActionListener(new AddExamListener());
        removerExameButton.addActionListener(new RvmExamListener());
        end_consulta_button.addActionListener(new FimConsultaListener());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return consult_panel;
    }

    class AddExamListener implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent ev)
        {
            Object exam_to_add = exam_combo_box.getSelectedItem();
            for(int i = 0; i < d.size(); i++)
            {
                if(d.get(i).equals(exam_to_add))
                {
                    JOptionPane.showMessageDialog(null, "Exame já adicionado!","Erro funcionários", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            d.addElement(exam_to_add);
        }
    }


    class RvmExamListener implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent ev)
        {
            d.removeElement(exam_list.getSelectedValue());
        }
    }

    class FimConsultaListener implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent ev)
        {
            ArrayList<Consulta> consultas;
            Medico m;

            System.out.println("medico logado(consultpanel):"+medic);
            m  = (Medico) Usuarios.buscaMedico(medic);
            System.out.println("medicobuscado:"+m.getName());
            if(m != null)
            {
                consultas = m.getConsultas();
                System.out.println("tem "+consultas.size()+" consultas");
            }

            /**
            * Realiza consulta
            */
            String[] exams = new String[d.getSize()];
            for(int i = 0; i < d.getSize(); i++)
                exams[i] = d.getElementAt(i).toString();

            //adicionar prescrição
            String[] p = medicines.getText().split(",");
            
            if(ConsultaHandle.startConsult(index, exams, p, doctor_sint.getText()))
            {
                md.remove(index);
                m.getConsultas().remove(index);
                sc.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao fazer a consulta!");
                return;
            }
            background.remove(background.getComponentCount()-1);
            background.revalidate();
            background.repaint();
        }
    }
}
