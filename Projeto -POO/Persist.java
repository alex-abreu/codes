import java.io.*;
import java.util.*;

public class Persist 
{
    public static boolean gravar(Object a, String file_name)
    {
        try 
        {
            //cria o arquivo para armazenar os objetos
            FileOutputStream file = new FileOutputStream(file_name);
            //classe responsável por inserir os objetos 
            ObjectOutputStream object = new ObjectOutputStream(file);

            //grava o objeto no arquivo
            object.writeObject(a);
            object.flush();
            object.close();
            file.flush();
            file.close();
            System.out.println("true gravado com sucesso!");
            return true;
        } catch(Exception e) 
        {
            e.printStackTrace();
            return false;
        }
    }

    public static Object recuperar(String file_name)
    {
        Object obj = null;
        FileInputStream file = null;
        ObjectInputStream obj_leitura = null;

        try 
        {
            System.out.println("tentou");
            //carrega o arquivo
            file = new FileInputStream(file_name);
            //recupera o objeto do arquivo
            obj_leitura = new ObjectInputStream(file);
            //le o objeto 
            obj = obj_leitura.readObject();
            obj_leitura.close();
            file.close();
        } catch (Exception e)
        {
            System.out.println("falhou");
            e.printStackTrace();
            return null;
        }

        return obj;
    }
}