import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.*;
import java.awt.*;

public class ValidDateAndHour
{

    public static boolean validaData(String data, boolean type)
    {
        Date d = null;
        SimpleDateFormat format;

        if(type == true)
            format = new SimpleDateFormat("dd/MM/yyyy");
        else 
            format = new SimpleDateFormat("dd-MM-yyyy");
            
        try {
                format.setLenient(false);
                d = format.parse(data);
        } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,"Formato de data está incorreto","AVISO",JOptionPane.WARNING_MESSAGE);
                return false;
        }
        return true;
    }

    public static boolean validaHora(String data)
    {
        if(data.charAt(2) != ':')
        {
            JOptionPane.showMessageDialog(null,"Formatação da hora incorreta","AVISO",JOptionPane.WARNING_MESSAGE);
            return false; 
        }

        String[] fields = data.split(":");
        int h = Integer.parseInt(fields[0]); int m = Integer.parseInt(fields[1]);
        if(h < 0 || h > 23 || m < 0 || m > 59)
        {
            JOptionPane.showMessageDialog(null,"Formatação da hora incorreta","AVISO",JOptionPane.WARNING_MESSAGE);
            return false; 
        }

        if(h > 20 || h < 8)
        {
            JOptionPane.showMessageDialog(null,"Não condiz com o horário de funcionamento (08:00 - 20:00)","AVISO",JOptionPane.WARNING_MESSAGE);
            return false; 
        }

        return true;

    }
}