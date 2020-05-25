import java.io.Serializable;
import java.time.*;
import java.util.ArrayList;

public class Consulta implements Serializable
{

    private LocalDate dataConsulta;
    private LocalTime horaConsulta;
    private String medico;
    private String paciente; 
    private String sintomas;
    private ArrayList<String> exames;
    private ArrayList<String> prescription;
    private boolean isConsulted;

    public Consulta(Consulta consulta)
    {
        if(!setDataConsulta(consulta.getDataConsulta()) ) System.out.println("erro ao setar a data!");
        if(!setHoraConsulta(consulta.getHoraConsulta()) ) System.out.println("erro ao setar a hora!");
        if(!setMedico(consulta.getMedico()) )  System.out.println("erro ao setar o medico!");
        if(!setPaciente(consulta.getPaciente()) )  System.out.println("erro ao setar o paciente!");
        if(!setSintomas(consulta.getSintomas()) )  System.out.println("erro ao setar os sintomas!");
        if(!setExames(consulta.getExames()) ){
            System.out.println("erro ao setar os exames!");
            exames = new ArrayList<String>();
        } 
        if(!setPrescription(consulta.getPrescription()) ){
            System.out.println("erro ao setar a prescrição!");
            prescription = new ArrayList<String>();
        } 
        setIsConsulted(consulta.getIsConsulted());

    }

    public Consulta(String dataConsulta, String horaConsulta, String medico, String paciente)
    {
        String splited1[] = dataConsulta.split("/");
        String splited2[] = horaConsulta.split(":");
        if(!setDataConsulta(Integer.parseInt(splited1[2]), Integer.parseInt(splited1[1]), 
            Integer.parseInt(splited1[0]))) System.out.println("erro ao setar a data!");
        if(!setHoraConsulta(Integer.parseInt(splited2[0]), Integer.parseInt(splited2[1])))
        {
            System.out.println("erro ao setar a hora!");
        }
        if(!setMedico(medico)) System.out.println("erro ao setar o medico!");
        if(!setPaciente(paciente)) System.out.println("erro ao setar o paciente!");
        exames = new ArrayList<String>();
        prescription = new ArrayList<String>();
        isConsulted = false;
    }

    public Consulta(LocalDate dataConsulta, LocalTime horaConsulta, String medico, String paciente)
    {
        if(!setDataConsulta(dataConsulta)) System.out.println("erro ao setar a data!");
        if(!setHoraConsulta(horaConsulta)) System.out.println("erro ao setar a hora!");
        if(!setMedico(medico)) System.out.println("erro ao setar o medico!");
        if(!setPaciente(paciente)) System.out.println("erro ao setar o paciente!");
        exames = new ArrayList<String>();
        prescription = new ArrayList<String>();
        isConsulted = false;
    }

    public boolean setDataConsulta(int ano, int mes, int dia) 
    {
        try{
            this.dataConsulta = LocalDate.of(ano, mes, dia);
            return true;
        }
        catch(DateTimeException dte){
            return false;
        }
            
        
    }

    public boolean setDataConsulta(LocalDate data_cons) 
    {
        if(data_cons == null) return false;
        else
        {
            this.dataConsulta = data_cons;
            return true;
        } 
    }

    public LocalDate getDataConsulta() 
    {
        return dataConsulta;
    }

    public String getDataConsultaStr() 
    {
        return dataConsulta.toString();
    }

    public boolean setHoraConsulta(int hora, int min) 
    {
        try{
            horaConsulta = LocalTime.of(hora, min, 0, 0);
            return true;
        }
        catch(DateTimeException de)
        {
            return false;
        }
        
    }

    public boolean setHoraConsulta(LocalTime hora_cons) 
    {
        if(hora_cons == null) return false;
        int hora = hora_cons.getHour();
        int min = hora_cons.getMinute();
        this.horaConsulta = LocalTime.of(hora, min, 0, 0);
        return true;
    }

    public LocalTime getHoraConsulta() 
    {
        return horaConsulta;
    }

    public String getHoraConsultaStr() 
    {
        return horaConsulta.toString();
    }

    public boolean setMedico(String medico) 
    {
        
        if(medico == null || medico.length() == 0) return false;
        else
        {
            this.medico = new String(medico);
            return true;
        }
    }

    public String getMedico() 
    {
        return medico;
    }

    public boolean setPaciente(String paciente) 
    {
        if(paciente == null || paciente.length() == 0) return false;
        else
        {
            this.paciente = paciente;
            return true;
        }
    }

    public boolean setSintomas(String sintomas)
    {
        if(sintomas == null || sintomas.length() == 0) return false;
        else
        {
            this.sintomas = new String(sintomas);
            return true;
        }
    }

    public String getSintomas(){
        return sintomas;
    }

    public String getPaciente() 
    {
        return paciente;
    }
    
    public boolean setPrescription(ArrayList<String> prescription)
    {
        if(prescription != null && prescription.size() > 0)
        {
            this.prescription = prescription;
            return true;
        }
        else return false;
    }

    public ArrayList<String> getPrescription()
    {
        return prescription;
    }

    public ArrayList<String> getExames()
    {
        return this.exames;
    }

    public boolean addRemedio(String remedio)
    {
        if(remedio != null && remedio.length() > 0 && !(prescription.contains(remedio)) )
        {
            prescription.add(remedio);
            return true;
        }
        else return false;
    }

    public boolean removeRemedio(String remedio)
    {
        for(String str : prescription)
        {
            if(remedio.equals(str)) // se a string inserida é igual à string da iteração atual, removê-la de prescription
            {
                prescription.remove(str);
                return true;
			}
        }
        return false;
    }

    public void deletePrescription()
    {
        this.prescription.clear();
    }

    public boolean addExame(String exame)
    {
        ExameHandle e = new ExameHandle();
        ArrayList<String> exames = e.getExam();
        int index = e.searchExam(exame);
        if( index >= 0 && !(exames.contains(exames.get(index))) ) // se exame existir na lista de exames cadastrados 
                                                                // e não existir nos exames da consulta
        {
            this.exames.add(exames.get(index)); // adicionar o exame nos exames da consulta
            return true;
        }
        else return false;
    }

    public boolean removeExame(String exame)
    {
        for(String str : exames)
        {
            if(exame.equals(str)) // se a string inserida é igual à string da iteração atual, removê-la de exames
            {
                exames.remove(str);
                return true;
			}
        }
        return false;
    }

    public void deleteExames()
    {
        this.exames.clear();
    }

    public void setIsConsulted(boolean is)
    {
        isConsulted = is;
    }

    public boolean getIsConsulted()
    {
        return isConsulted;
    }

    public LocalDateTime getDataEHora()
    {
        LocalDateTime ldt = LocalDateTime.of(this.dataConsulta, this.horaConsulta);
        return ldt;
    }

    public String getDataEHoraStr()
    {
        LocalDateTime ldt = LocalDateTime.of(this.dataConsulta, this.horaConsulta);
        return ldt.toString();
    }

    public boolean setExames(ArrayList<String> exames){
        if(exames != null)
        {
            this.exames = exames;
            return true;
        }
        return false;
    }

}