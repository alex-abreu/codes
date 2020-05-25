import java.util.*;
import java.time.*;

public class PacienteHandle
{
    public static ArrayList<Paciente> pacientes;
    private static final String fileName = "pacientes.dat";
    static
    {
        pacientes = (ArrayList<Paciente>) Persist.recuperar(fileName);
        if (pacientes == null)
        {
            System.out.println("pacientes == null");
            pacientes = new ArrayList<Paciente>();
        } 
        if(pacientes.isEmpty() == false)
            System.out.println("recuperação executada com sucesso");
    }

    public static boolean add(Paciente paciente)
    {
        
        if(paciente != null && !findCPF(paciente.getCpf()) && !findRG(paciente.getRg()))
        {
            pacientes.add(paciente);
            return true;
        }
        return false;
    }

    public static boolean findCPF(String CPF)
    {
        for(Paciente p : pacientes)
        {
            if(p.getCpf().equals(CPF))
                return true;
        }
        return false;
    }

    public static boolean findRG(String RG)
    {
        for(Paciente p : pacientes)
        {
            if(p.getRg().equals(RG))
                return true;
        }
        return false;
    }

    public static boolean rmv(Paciente paciente)
    {
        if(pacienteExiste(paciente))
        {
            pacientes.remove(paciente);
            return true;
        }
        return false; // paciente não existe

    }

    public static boolean rmv(String pacient_name)
    {
        for(Paciente p : pacientes)
        {
            if(p.getName().equals(pacient_name))
            {
                pacientes.remove(p);
                return true;
            }
        }
        return false; // paciente não existe
    }

    public static int pacienteIndex(String paciente)
    {
        for(Paciente p : pacientes)
        {
            if(p.getName().equals(paciente))
            {
                pacientes.indexOf(p); // retorna índice do paciente
            }
        }
        return -1; // paciente não existe
    }

    public static boolean pacienteExiste(Paciente paciente)
    {
        if(pacientes.contains(paciente))
                return true;
        return false;
    }
    
    public static ArrayList<Paciente> getPacientes()
    {
        return pacientes;
    }
    
    public static Object[][] createTable()
    {
        System.out.println("Chamou a criação de tabela");
        Object table[][] = new Object[pacientes.size()][];
        for(int i = 0; i < pacientes.size(); i++)
        {
            String[] row = 
            {
                pacientes.get(i).getName(),
                pacientes.get(i).getCpf(),
                pacientes.get(i).getRg(),
                pacientes.get(i).getAdress(),
                pacientes.get(i).getNascimento(),
                pacientes.get(i).getTelefone(),
                pacientes.get(i).getPrimeira_consultaStr()
            };
            table[i] = row;
        }

        for(int i = 0; i < table.length; i++)
            for(int j = 0; j < table[i].length; j++)
                System.out.println(table[i][j]);
        return table;
    }
    
    public static boolean gravarPacientes()
    {
        return Persist.gravar(pacientes, "pacientes.dat");
    }

    public static boolean pacienteExiste(String paciente){
        for(Paciente p : pacientes){
            if(p.getName().equals(paciente))
                return true;
        }
        return false;
    }
}

