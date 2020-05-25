import java.io.Serializable;
import java.util.ArrayList;

public class Medico extends Funcionario implements Serializable
{
    private String crm;
    private Object[] especialidades;
    private ArrayList<Consulta> consultas;
    private int CODIGO_ACESSO = 1;

    Medico(String nome, String cpf, String rg, 
           String adress, String clt, String data_admss, 
                    String crm, Object[] espec)
    {
        super(nome, cpf, rg, adress, clt, data_admss);
        setCrm(crm);
        setEspecialidades(espec);
        consultas = new ArrayList<Consulta>();
    }

    public boolean setCrm(String s)
    { 
        if(s.length() > 0)
        {
            this.crm = s;
            return true;
        }
        return false;  
    }

    public String getCrm() 
    {
        return this.crm;
    }

    public void setEspecialidades(Object[] e)
    {
        this.especialidades = e;
    }

    public Object[] getEspecialidades()
    {
        return this.especialidades;
    }

    public String getStringFuncionario()
    {
        return "Medico";
    }

    public int getCodigoDeAcesso()
    {
        return this.CODIGO_ACESSO;
    }

    public ArrayList<Consulta> getConsultas()
    {
        return this.consultas;
    }

    public void setConsultas(ArrayList<Consulta> c)
    {
        System.out.println("lista de consultas att com sucesso");
        this.consultas = c;
    }

    public void deleteConsultas()
    {
        consultas.clear();
    }

}