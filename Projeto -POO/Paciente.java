import java.time.*;
import java.io.Serializable;

public class Paciente extends Pessoa implements Serializable
{

    private String nascimento;
    private String telefone;
    private LocalDate primeira_consulta;

    public Paciente(String nome, String cpf, String rg, String endereco, String nascimento, String telefone)
    {
        super(nome, cpf, rg, endereco);
        if(!setNascimento(nascimento)){
            this.nascimento = new String("");
            System.out.println("erro ao setar o ano de nascimento!");
        } 
        if(!setTelefone(telefone)){
            this.telefone = new String("");
            System.out.println("erro ao setar o telefone!");
        } 
    }

    public Paciente(String nome, String cpf, String rg, String endereco, String nascimento, String telefone, LocalDate fst_cons)
    {
        super(nome, cpf, rg, endereco);
        if(!setNascimento(nascimento)) System.out.println("erro ao setar o ano de nascimento!");
        if(!setTelefone(telefone)) System.out.println("erro ao setar o telefone!");
        if(!setPrimeira_consulta(fst_cons)) System.out.println("erro ao setar a primeira consulta!");
    }

    public boolean setNascimento(String nascimento) 
    {
        int ano;
        try
        {
            ano = Integer.parseInt(nascimento);
            if(ano < 999999999 && ano > 1)
            {
                this.nascimento = nascimento;
                return true;
            }
            else return false; 
        } 
        catch(NumberFormatException | NullPointerException nfe)
        {
            return false;
        }
    }
    
    public String getNascimento() 
    {
        return nascimento;
    }

    public boolean setTelefone(String telefone) 
    {
        if(telefone != null && telefone.length() == 14) 
        {
            this.telefone = telefone;
            return true;
        }
        //se o tamanho da string for 11 e ela for numérica, então será setado
        //do contrário, a função retornará false
        return false;
    }
    
    public String getTelefone() 
    {
        return telefone;
    }

    public boolean setPrimeira_consulta(String d)
    {
        int ano, mes, dia;
        if(d != null && d.length() > 0)
        {
            String data[] = d.split("/");
            try
            {
                ano = Integer.parseInt(data[2]);
                mes = Integer.parseInt(data[1]);
                dia = Integer.parseInt(data[0]);
                this.primeira_consulta = LocalDate.of(ano, mes, dia);
                return true;
                
            }
            catch(NumberFormatException | DateTimeException nfe)
            {
                return false;
            }
        }
        else return false;
    }

    public boolean setPrimeira_consulta(LocalDate fst_cons)
    {
        if(fst_cons == null) return false;
        this.primeira_consulta = fst_cons;
        return true;
    }
     
    public LocalDate getPrimeira_consulta() 
    {
        return primeira_consulta;
    }

    public String getPrimeira_consultaStr()
    {
        if(primeira_consulta == null)
            return "ainda não consultado";
            
        return primeira_consulta.toString();
    }

}