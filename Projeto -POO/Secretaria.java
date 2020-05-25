import java.time.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Secretaria extends Funcionario implements Serializable
{
    private int CODIGO_ACESSO = 2;
    private LocalTime expediente;
    private int[] hours = new int[4];

    Secretaria(String nome, String cpf, String rg, 
               String adress, String clt, String data_admss, 
                             Horario entrada, Horario saida)
    {
        super(nome, cpf, rg, adress, clt, data_admss);
        setExpediente(entrada, saida);
        hours[0] = entrada.getH();
        hours[1] = entrada.getM();
        hours[2] = saida.getH();
        hours[3] = saida.getM();
    }

    public void setExpediente(Horario in, Horario out)
    {
        int diff_h = out.getH() - in.getH();
        int diff_m = out.getM() - in.getM();
        if(diff_h < 0) 
            diff_h *= -1;
        if(diff_m < 0)
            diff_m *= -1;
        System.out.println(diff_m+diff_h);
        this.expediente = LocalTime.of(diff_h, diff_m);
    }

    public LocalTime getExpediente()
    {
        return this.expediente;
    }

    public String getStringFuncionario()
    {
        return "Secretária";
    }

    public int getCodigoDeAcesso()
    {
        return this.CODIGO_ACESSO;
    }

    public int[] getHours()
    {
        return this.hours;
    }
}