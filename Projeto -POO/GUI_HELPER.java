public class GUI_HELPER
{
    /**
     * String[]s para a área da GUI relacionada a exames
     */
    public static final String[] EXAMS_COLUMNS = 
    {
        "Nome do exame", 
        "Código do exame", 
        "Observações do exame",
        "Duração do exame", 
        "Tempo estimado para obtenção do resultado"
    };

    public static final String[] EXAMS_FORM = 
    {
        "Nome", 
        "Código", 
        "Observações",
        "Duração", 
        "Resultado"
    };

    public static final String[] EXAMS_BUTTONS_NAMES = 
    {
        "Adicionar",
        "Remover",
    };

    /**
    * String[]s para a área da GUI relacionada a consultas
    */
    public static final String[] CONSULT_COLUMNS = 
    {
        "Data",
        "Hora", 
        "Médico", 
        "Paciente",
        "Finalizada"
    };
    
    public static final String[] CONSULT_BUTTONS = 
    {
        "remover consulta da lista",
        "Desmarcar consulta",
        "Remarcar consulta"
    };

    /**
    * String[]s para a área da GUI relacionada a consultas
    */
    public static final String[] PATIENT_COLUMNS = 
    {
        "Nome", 
        "Cpf", 
        "Rg", 
        "Endereço",
        "Data de nascimento",
        "Telefone",
        "Primeira consulta"
    };

    public static final String[] PATIENT_BUTTONS = 
    {
        "Remover paciente",
        "Marcar consulta...",
    };

    /**
    * String[]s para a área da GUI relacionada a menu bar
    */

    public static final String[] genericMenu(String name)
    {
        String[] generic_menu = 
        {
            name,
            "Adicionar",
            "Remover",
            "Listar"
        };

        return generic_menu;
    }

    public static final String[] FILE_MENU = 
    {
        "Arquivo", 
        "Salvar e Sair",
    };

    public static final String[] CONSULT_MENU = 
    {
        "Consultas", 
        "Marcar",
        "Desmarcar", 
        "Editar",
    };

    public static final String[] INFO_MENU = 
    {
        "Sobre", 
        "História da clínica Dr. Swing"
    };

    /**
     * String[]s para a área da GUI relacionada ao login
     */

    public static final String[] LOGIN_LABELS = 
    {
        "Usuário",
        "Senha"
    };

    public static final String[] USERS_COLUMNS = 
    {
        "Usuário",
        "Senha",
        "Função",
    };

    public static final String[] USERS_BUTTON_NAMES = 
    {
        "Remover"
    };
    
}