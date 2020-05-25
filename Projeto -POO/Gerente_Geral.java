import java.util.*;
import java.io.Serializable;

public class Gerente_Geral extends Funcionario  implements Serializable
{
    private int CODIGO_ACESSO = 0;

    Gerente_Geral(String nome, String cpf, String rg, 
               String adress, String clt, String data_admss)
    {
        super(nome, cpf, rg, adress, clt, data_admss);
    }

    public void addSecretaria(String user, String senha, String nome,
                              String cpf, String rg, String adress, 
                              String clt, String data_admss, Horario entrada, 
                                                               Horario saida)
    {
        Usuario u = new Usuario(user, senha);
        u.cadastraUsuario(new Secretaria(nome, cpf, rg, adress, 
                                         clt, data_admss, entrada, 
                                                          saida));
        Usuarios.addUsuario(u);
    }

    public void addMedico(String user, String senha, String nome,
                          String cpf, String rg, String adress, 
                          String clt, String data_admss,String crm, 
                                                    Object[] espec)
    {
        Usuario u = new Usuario(user, senha);
        u.cadastraUsuario(new Medico(nome, cpf, rg, adress, 
                                         clt, data_admss, crm, espec));
        Usuarios.addUsuario(u);
    }


    public Funcionario buscaFuncionario(String user)
    {
        Usuario u = Usuarios.buscaUsuario(user);
        return u.getTipoFuncionario();
    }

    public void listaFuncionarios()
    {
        Usuarios.listaUsuarios();
    }

    public void salvarUsuarios()
    {
        Usuarios.salvaUsuarios();
    }

    public String getStringFuncionario()
    {
        return "Gerente Geral";
    }  

    public int getCodigoDeAcesso()
    {
        return this.CODIGO_ACESSO;
    }
}