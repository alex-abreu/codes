<?php

session_start();
?>

<?php

require_once "login.php";
require_once "conexaoMysql.php";


$mysqli = conectaAoMySQL();

function filtraEntrada($dado) 
{
  $dado = trim($dado);               // remove espaços no inicio e no final da string
  $dado = stripslashes($dado);       // remove contra barras: "cobra d\'agua" vira "cobra d'agua"
  $dado = htmlspecialchars($dado);   // caracteres especiais do HTML (como < e >) são codificados

  return $dado;
}

if ($_SERVER["REQUEST_METHOD"] == "POST") 
{
  $msgErro = "";

  // Define e inicializa as variáveis
  $nome = $email = $senha ="";

  $nome             = filtraEntrada($_POST["nome"]);     
  $email            = filtraEntrada($_POST["email"]);
  $senha            = $_POST["password"];

if(loginUsuario($email,$senha,$mysqli)){
	
        $_SESSION['user'] = $email;
         echo "
            <script type=\"text/javascript\">
            window.location.href = 'restrict.php';
            </script>
        ";

/* Make sure that code below does not get executed when we redirect. */
exit;



}
else{
        echo '<script language="javascript">';
        echo 'alert("usuário ou senha inválido")';
        echo '</script>';
        echo "
            <script type=\"text/javascript\">
            window.location.href = 'http://alexabreu.dx.am/index.html';
            </script>
        ";
  
/* Make sure that code below does not get executed when we redirect. */

}
}

?>