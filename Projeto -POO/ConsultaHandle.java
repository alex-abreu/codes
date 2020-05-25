import java.util.*;
import java.time.*;

public class ConsultaHandle
{
    public static ArrayList<Consulta> consultas;
    private static final String fileName = "consultas.dat";

    static
    {
        consultas = (ArrayList<Consulta>) Persist.recuperar(fileName);
        if (consultas == null)
        {
            System.out.println("consultas == null");
            consultas = new ArrayList<Consulta>();
        } 
        if(consultas.isEmpty() == false)
            System.out.println("recuperação executada com sucesso");
    }

    public static ArrayList<Consulta> getConsultas()
    {
        return consultas;
    }

    public static Object[][] createTable()
    {
        System.out.println("Chamou a criação de tabela");
        Object table[][] = new Object[consultas.size()][];
        for(int i = 0; i < consultas.size(); i++)
        {   
            String tmp = "";
            if(consultas.get(i).getIsConsulted() == true)
            {
                System.out.println("foi consultado");
                tmp = "finalizada";
            }  
            
            String[] row = 
            {
                consultas.get(i).getDataConsultaStr(),
                consultas.get(i).getHoraConsultaStr(),
                consultas.get(i).getMedico(),
                consultas.get(i).getPaciente(), 
                tmp
            };
            table[i] = row;
        }

        for(int i = 0; i < table.length; i++)
            for(int j = 0; j < table[i].length; j++)
                System.out.println(table[i][j]);
        return table;
    }
    /**
     * Essa função pega o índice da consulta e realiza o processo 
     * de adicionar os campos restantes da consulta.
     * @return boolean
     */
    public static boolean startConsult(int index, String[] exams, String[] p, String s)
    {
        Consulta c = consultas.get(index);
        if(c == null)
        {    
            return false;
        } else 
        {
            /**
             * Adicionando exames
             */
            for(int i = 0; i < exams.length; i++)
                c.addExame(exams[i]);
            //add remedios
            for(int i = 0; i < p.length; i++)
                c.addRemedio(p[i]);
            //add sintomas
            c.setSintomas(s);
            c.setIsConsulted(true);
            System.out.println("gotcha");
            return true;
        }
    }

    private static int percorreConsultas(Consulta consulta, String medico)
    {
        Consulta cons;
        int i = 0;
        int j = 0;
        int comp = 0;
        int hora_em_min1, hora_em_min2, min1, min2;
        int n = consultas.size();

        if(n == 0) return 0; // se não existirem consultas marcadas

        for(i = 0; i < n; i++) // percorre o arraylist de consultas
        { 
            cons = new Consulta(consultas.get(i));
            if(!cons.getMedico().equals(consulta.getMedico())) continue; // se a consulta atual não for do médico inserido, pular iteração
            if(consulta.getDataConsulta().isBefore(cons.getDataConsulta())) // se for antes da data da consulta atual
            {
                return i; // retorne a posição atual
            }
            else
            {
                for(j = i; j < n; j++) // percorre com outras verificações
                {
                    if(consulta.getDataConsulta().isEqual(cons.getDataConsulta()))
                    {

                        hora_em_min1 = consulta.getHoraConsulta().getHour() * 60;
                        hora_em_min2 = cons.getHoraConsulta().getHour() * 60;
                        min1 =  consulta.getHoraConsulta().getMinute();
                        min2 = cons.getHoraConsulta().getMinute();

                        comp = (hora_em_min1 + min1) - (hora_em_min2 + min2);
                        if(comp <= -30) // se for 30 minutos ou mais antes da consulta atual
                        {
                            return j; // retorne a posição atual
                        } 
                        else if(comp >= 30) // se for 30 minutos ou mais após a consulta atual
                        {
                            try{ // se ainda tiver uma ou mais consultas após essa, pular iteração
                                cons = new Consulta(consultas.get(j+1)); 
                                continue; 
                            }
                            catch(IndexOutOfBoundsException iOOBE)
                            {
                                return j+1; // retorne a próxima posição
                            }
                            
                        } 
                        else return -1; //impossível marcar consulta, pois a diferença é menor do que 30 minutos
                    }
                    else if(consulta.getDataConsulta().isBefore(cons.getDataConsulta())) // se a data for anterior à consulta atual
                    {
                        return j; // retorne a posição atual
                    }
                    else break;
                }
                i = j; // se o loop interno foi interrompido, então o externo deve continuar pela posição j+1 na próxima iteração
            }
        }
        if(i == n) // se passou pelo loop sem nenhum erro, adicionar no final do ArrayList
        {
            cons = new Consulta(consultas.get(n-1));
            if(consulta.getDataConsulta().isAfter(cons.getDataConsulta()))
            {
                return n;
            }
        }
        return -1;
    }
    /**
     * ...sua descrição vitor
     * Procura o médico responsável pela consulta e 
     * add a consulta ao seu array de consultas
     */
    public static  boolean marcar_base(Consulta consulta, String medico)
    {
        int n = percorreConsultas(consulta, medico);
        if(n == -1) return false;
        else
        {
            consultas.add(n, consulta);
            Medico m = (Medico) Usuarios.buscaMedico(medico);
            if(m != null)
            {    
                m.getConsultas().add(consulta);
            }
            return true;
        } 

    }

    public static  boolean marcarConsulta(String dataConsulta, String horaConsulta, String medico, String paciente)
    {
        Consulta consulta = new Consulta(dataConsulta, horaConsulta, medico, paciente);
        return marcar_base(consulta, medico);
    }

    public static boolean marcarConsulta(LocalDate dataConsulta, LocalTime horaConsulta, String medico, String paciente)
    {
        Consulta consulta = new Consulta(dataConsulta, horaConsulta, medico, paciente);
        return marcar_base(consulta, medico);
    }

    public static boolean marcarConsulta(Consulta consulta, String medico)
    {
        return marcar_base(consulta, medico);
    }

    public static boolean desmarcarConsulta(LocalDate data, LocalTime hora, String medico)
    {
        LocalDateTime ldt = LocalDateTime.of(data, hora);
        Consulta cons;
        int i = 0;
        boolean is_unmarked = false;
        int n = consultas.size();
        for(i = 0; i < n; i++)
        {
            cons = new Consulta(consultas.get(i));
            if(!cons.getMedico().equals(medico)) continue; // se a consulta atual não é do médico informado, pular iteração
            if(ldt.isEqual(cons.getDataEHora()))
            {
                consultas.remove(i);
                is_unmarked = true;
                break;
            }
        }
        return is_unmarked;
    }

    public static boolean desmarcarConsulta(String data, String hora, String medico){
        
        try{
            String splitedData[] = data.split("-");
            String splitedHora[] = hora.split(":");
            LocalDate lData = LocalDate.of(Integer.parseInt(splitedData[0]), Integer.parseInt(splitedData[1]), Integer.parseInt(splitedData[2]));
            LocalTime lHora = LocalTime.of(Integer.parseInt(splitedHora[0]), Integer.parseInt(splitedHora[1]));
            return desmarcarConsulta(lData, lHora, medico);
        }
        catch(DateTimeException | NullPointerException |  NumberFormatException | ArrayIndexOutOfBoundsException aio)
        {
            System.out.println("achei o erro kkkkk");
            return false;
        }
    }

    public static boolean remarcarConsulta(String data, String hora, String novaData, String novaHora, String medico){

        try{
            String splitedData[] = data.split("-");
            String splitedHora[] = hora.split(":");
            String splitedNovaData[] = novaData.split("-");
            String splitedNovaHora[] = novaHora.split(":");
            
            LocalDate lData = LocalDate.of(Integer.parseInt(splitedData[2]), Integer.parseInt(splitedData[1]), Integer.parseInt(splitedData[0]));
            LocalTime lHora = LocalTime.of(Integer.parseInt(splitedHora[0]), Integer.parseInt(splitedHora[1]));
            LocalDate lNovaData = LocalDate.of(Integer.parseInt(splitedNovaData[2]), Integer.parseInt(splitedNovaData[1]), 
                                                Integer.parseInt(splitedNovaData[0]));
            LocalTime lNovaHora = LocalTime.of(Integer.parseInt(splitedNovaHora[0]), Integer.parseInt(splitedNovaHora[1]));
            return remarcarConsulta(lData, lHora, lNovaData, lNovaHora, medico);

        }
        catch(DateTimeException | NullPointerException |  NumberFormatException | ArrayIndexOutOfBoundsException aio){
            aio.printStackTrace();
            return false;
        }
        
    }

    public static boolean remarcarConsulta(LocalDate data, LocalTime hora, LocalDate novaData, LocalTime novaHora, String medico)
    {
        LocalDateTime ldt = LocalDateTime.of(data, hora);
        Consulta cons;
        int i = 0;
        String paciente;
        boolean is_remarked = false;
        int n = consultas.size();
        for(i = 0; i < n; i++)
        {
            cons = new Consulta(consultas.get(i));
            if(!cons.getMedico().equals(medico)) continue; // se a consulta atual não é do médico informado, pular iteração
            if(ldt.isEqual(cons.getDataEHora())) // se a data e hora da consulta atual for igual à data e hora inseridas, essa é a consulta a ser remarcada
            {
                paciente = cons.getPaciente();
                consultas.remove(i);
                if(marcarConsulta(novaData, novaHora, medico, paciente)) // se for possível remarcar
                {
                    is_remarked = true;
                }
                else consultas.add(i, cons); // se não for possível, adicionará a consulta removida novamente
                break;
            }
        }
        return is_remarked;
    }

    public static boolean gravarConsultas()
    {
        return Persist.gravar(consultas, "consultas.dat");
    }
    
}

