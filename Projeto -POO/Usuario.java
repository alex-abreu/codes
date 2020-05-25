import java.io.Serializable;
import java.util.*;

public class Usuario implements Serializable
{
    private String usuario;
    private String senha; 
    private Funcionario tipo_funcionario;

    //atributos estáticos da classe
    private static boolean esta_logado = false;
    private static int status;

    Usuario(String usr, String senha)
    {
        setUsuario(usr);
        setSenha(senha);
    }

    Usuario(String usr)
    {
        setUsuario(usr);
    }

    public int setUsuario(String s)
    { 
        if(s.length() > 0)
        {
            this.usuario = s;
            return -1;
        }
        return 0;  
    }

    public String getUsuario() 
    {
        return this.usuario;
    }

    public int setSenha(String s)
    { 
        if(s.length() > 0)
        {
            this.senha = s;
            return -1;
        }
        return 0;  
    }

    public String getSenha() 
    {
        return this.senha;
    }

    public void setTipoFuncionario(Funcionario f)
    {
        this.tipo_funcionario = f;
    }

    public Funcionario getTipoFuncionario()
    {
        return this.tipo_funcionario;
    }

    
    public int cadastraUsuario(Funcionario f)
    {

        if(Usuarios.usuarioExiste(this.usuario))
        {
            System.out.println("1 user alread exists");
                return 0;
        } else {
            System.out.println("-1 sucess set");
            setTipoFuncionario(f);
            return -1;
        }
    }

    /**
     * Se o usuário já estiver logado, retorne 0;
     * Se o usuário não existe no sistema, retorne 1;
     * Se a senha não bate, retorne 2;
     * Caso não falhe em nenhum destes testes, retorne 
     * @return int
     */
    public int autenticaUsuario() 
    {

        Usuario tmp = Usuarios.buscaUsuario(this.usuario);

        if(esta_logado == true)
        {
            System.out.println("0 usuario logado");
            return 0;
        } else {
            if(tmp == null)
            {
                System.out.println("usuario inexistente");
                return 1;
            } else {  
                if(this.senha.equals(tmp.getSenha()))
                {
                    esta_logado = true;
                    setTipoFuncionario(tmp.getTipoFuncionario());
                    System.out.println("logou com sucesso");
                    return -1;
                } else {
                    System.out.println("senha incorreta");
                    return 2;
                }
            }
        }
    }

}