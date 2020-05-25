import java.time.*;
import java.io.Serializable;

public abstract class Funcionario extends Pessoa implements Serializable
{
    private String clt; 
    private LocalDate data_admiss; 

    Funcionario(String name, String cpf, String rg, 
                String adress, String clt, String d)
    {
        super(name, cpf, rg, adress);

        String splited[] = d.split("/");

        setClt(clt);
        //setDataAdmiss(Integer.parseInt(splited[2]), Integer.parseInt(splited[1]), 
                                                    //Integer.parseInt(splited[0]));
    }

    public boolean setClt(String s)
    {
        if(s.length() > 0)
        {
            this.clt = s;
            return true;
        }
        return false;  
    }

    public String getClt() 
    {
        return this.clt;
    }

    public boolean setDataAdmiss(int year, int month, int day)
    {
        if(year <= 999999999 && year >= -999999999 && 
           month >= 1 && month <= 12 && day >= 1 && day <= 31)
        {
            this.data_admiss = LocalDate.of(year, month, day);
            return true;
        } else {
            return false;
        }
    }
    

    public LocalDate getDataAdmiss()
    {
        return this.data_admiss;
    }

    public abstract String getStringFuncionario();
    public abstract int getCodigoDeAcesso();
}