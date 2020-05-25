import java.util.*;
import java.io.*;

public class Usuarios
{
    private static ArrayList<Usuario> users_data;
    private static final String file_name = "users.dat";

    public static void writeSecFile(int hin, int hin2, int hout, int hout2)
    {
        try
        {
            File file = new File("sechelper.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            String out = String.format("%d %d %d %d", hin, hin2,hout, hout2);

            writer.write(out);
            writer.write('\n');
            writer.close();

        }catch(Exception e){
			String error = e.toString();
		}
    }

    static 
    {
        users_data = (ArrayList<Usuario>) Persist.recuperar(file_name);
        if(users_data == null)
        {
            System.out.println("users_data == null");
            users_data = new ArrayList<Usuario>();
            writeSecFile(0,0,0,0);
        }
        
        if(users_data.isEmpty() == false)
            System.out.println("recuperação executada com sucesso");
    }


    public static void addUsuario(Usuario u)
    {
        users_data.add(u);
    }

    public static int rmvUsuario(String user)
    {
        Usuario tmp = buscaUsuario(user);
        if(tmp != null)
        {
            users_data.remove(tmp);
            return -1;
        }
        return 0;
    }

    public static Usuario buscaUsuario(String user)
    {
        for(Usuario u : users_data)
            if(u.getUsuario().equals(user))
                return u;
        return null;
    }

    
    public static Funcionario buscaMedico(String user)
    {
        String medic_name;

        for(int i = 0; i < users_data.size(); i++)
        {
            medic_name = users_data.get(i).getTipoFuncionario().getName();
            if(medic_name.equals(user))
                return users_data.get(i).getTipoFuncionario();
        }
        return null;
    }

    public static void listaUsuarios()
    {
        for(Usuario u : users_data)
        {
            System.out.println(u.getUsuario());
            System.out.println(u.getSenha());
            System.out.println(u.getTipoFuncionario().getStringFuncionario());
        }
    }

    public static Object[][] createTable()
    {
        System.out.println("Chamou a criação de tabela");
        Object table[][] = new Object[users_data.size()][];
        for(int i = 0; i < users_data.size(); i++)
        {
            String[] row = 
            {
                users_data.get(i).getUsuario(), 
                users_data.get(i).getSenha(),
                users_data.get(i).getTipoFuncionario().getStringFuncionario()
            };
            table[i] = row;
        }

        for(int i = 0; i < table.length; i++)
            for(int j = 0; j < table[i].length; j++)
                System.out.println(table[i][j]);
        return table;
    }

    public static  boolean usuarioExiste(String user)
    {
        for(Usuario u : users_data)
            if(u.getUsuario().equals(user))
                return true;
        return false;
    }

    public static boolean salvaUsuarios()
    {
        if(!Persist.gravar(users_data, file_name))
            return false;
        System.out.println("gravado com sucesso no disco!");
        return true;
    }

    public Object[] getMedicList()
    {
        ArrayList<String> medicNames = new ArrayList<String>();
        Object[] out;
        for(int i = 0; i < users_data.size(); i++)
            if(users_data.get(i).getTipoFuncionario().getCodigoDeAcesso() == 1)
                medicNames.add(users_data.get(i).getTipoFuncionario().getName());
                
        out = medicNames.toArray();

        return out;
    }

}