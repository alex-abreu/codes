import java.io.Serializable;

public class Pessoa implements Serializable 
{
    private String name; 
    private String cpf;
    private String rg;
    private String adress;

    Pessoa(String name, String cpf, String rg, String adress)
    {
        if(!setName(name))
            System.out.println("erro ao setar o nome!");
        if(!setCpf(cpf))
            System.out.println("cpf invalido!");
        if(!setRg(rg))
            System.out.println("Rg inválido!");
        if(!setAdress(adress))
            System.out.println("erro ao setar o endereco!");
    }

    public boolean setName(String s)
    {
        if(s.length() > 0)
        {    
            this.name = s;
            return true; 
        }
        return false;  
    }

    public String getName()
    {
        return this.name;
    }

    public boolean setCpf(String s)
    {
        if(s.length() > 0) //&& (verifyCpf(s) == true)) desativar validação de cpf
        {
            this.cpf = s;    
            return true;
        }
        return false;  
    }

    public String getCpf()
    {
        return this.cpf;
    }


    public boolean setRg(String s)
    {
        if(s.length() > 0)
        {
            this.rg = s;
            return true;
        }
        return false;  
    }

    public String getRg()
    {
        return this.rg;
    }

    
    public boolean setAdress(String s)
    {
        if(s.length() > 0)
        {    
            this.adress = s;
            return true; 
        }
        return false;  
    }

    public String getAdress()
    {
        return this.adress;
    }

    private boolean verifyCpf(String s)
    {
        char _dv1, _dv2;
        int sm, r, num, peso;

        if(s.equals("00000000000") || s.equals("11111111111") ||
           s.equals("22222222222") || s.equals("33333333333") || 
           s.equals("44444444444") || s.equals("55555555555") ||
           s.equals("66666666666") || s.equals("77777777777") ||
           s.equals("88888888888") || s.equals("99999999999") ||
           (s.length() != 11))
        {
            return false;
        }

        try 
        {
            sm = 0;
            peso = 10;
            for(int i = 0; i < 9; i++)
            {
                num = (int) (s.charAt(i) - 48);
                sm += (num * peso);
                peso--;
            }

            r = 11 - (sm % 11);
            if((r == 10) || (r == 11))
                _dv1 = '0';
            else 
                _dv1 = (char)(r + 48);
            
            sm = 0;
            peso = 11;
            for(int i = 0; i < 10; i++)
            {
                num = (int) (s.charAt(i) - 48); 
                sm += (num * peso);
                peso--;
            }

            r = 11 - (sm % 11);
            if((r == 10) || (r == 11))
                _dv2 = '0';
            else 
                _dv2 = (char)(r + 48);

            //verificar se os digitos calculados são iguais aos digitos no cpf 
            if((_dv1 == s.charAt(9)) && (_dv2 == s.charAt(10)))
                return true; 
            else 
                return false;
        } catch(Exception e){
            return false;
        }
        
    }

}