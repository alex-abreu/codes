import java.util.*;
import java.time.*;

public class MedicoHandle
{
    public static ArrayList<Medico> medicos;
    private static final String fileName = "medicos.dat";
    
    static
    {
        medicos = (ArrayList<Medico>)Persist.recuperar(fileName);
        if (medicos == null){
            System.out.println("medicos == null");
            medicos = new ArrayList<Medico>();
        } 
        if(medicos.isEmpty() == false)
            System.out.println("recuperação executada com sucesso");
    }


    public static boolean add(Medico medico)
    {
        boolean r = false;
        if(medico != null && !medicos.contains(medico))
        {
            medicos.add(medico);
            r = Persist.gravar(medicos, fileName);
        }
        return r;
    }

    public static boolean rmv(Medico medico)
    {
        if(medicoExiste(medico))
        {
            medicos.remove(medico);
            return true;
        }
        return false; // medico não existe
    }

    public static boolean rmv(String medic_name)
    {
        for(Medico m : medicos)
        {
            if(m.getName().equals(medic_name))
            {
                medicos.remove(m);
                return true;
            }
        }
        return false; // medico não existe

    }

    public static boolean medicoExiste(Medico medico)
    {
        if(medicos.contains(medico))
                return true;
        return false;
    }

    public static int medicoIndex(String medico)
    {
        for(Medico m : medicos)
        {
            if(m.getName().equals(medico))
            {
                medicos.indexOf(m); // retorna índice do medico
            }
        }
        return -1; // medico nao existe
    }

    public static ArrayList<Medico> getMedicos()
    {
        return medicos;
    }

    public void carregaConsultas()
    {
        for(Medico m : medicos)
        {
            for(Consulta cons : ConsultaHandle.getConsultas())
            {
                if(m.getName().equals(cons.getMedico()))
                {
                    m.getConsultas().add(cons);
                }
            }
        }
    }

    public static boolean gravarMedicos()
    {
        return Persist.gravar(medicos, "medicos.dat");
    }

    public static boolean medicoExiste(String medico)
    {
        for(Medico m : medicos){
            if(m.getName().equals(medico)){
                return true;
            }
        }
        return false;
    }

    public static boolean medicoExisteCRM(String medicoCRM)
    {
        for(Medico m : medicos){
            if(m.getCrm().equals(medicoCRM)){
                return true;
            }
        }
        return false;
    }
}

