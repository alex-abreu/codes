<?php
session_start();
?>
<?php
if (!isset($_SESSION['user'])){
    
    echo "
            <script type=\"text/javascript\">
            alert('user not logged in')
            window.location.href = 'http://alexabreu.dx.am/index.html';
            </script>
        ";
    
    

}

?>

<?php

// Inclui o arquivo com os dados e funções de conexão
require "conexaoMysql.php";

// Valida uma string removendo alguns caracteres
// especiais que poderiam ser provenientes
// de ataques do tipo HTML/CSS/JavaScript Injection
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
  $nome = $email = $telefone= $cpf =$profissao =$estadoCivil = $sexo = $bairro = $rua = $uf = $cidade = $num = $cep = "";

  $nome             = filtraEntrada($_POST["nome"]);     
  $email            = filtraEntrada($_POST["email"]);
  $telefone         = filtraEntrada($_POST["telefone"]);
  $cpf              = filtraEntrada($_POST["CPF"]);
  $profissao        = filtraEntrada($_POST["prof"]);
  $sexo             = filtraEntrada($_POST["sexo"]);
  $estadoCivil      = filtraEntrada($_POST["estadoCivil"]);
  $rua              = filtraEntrada($_POST["rua"]);
  $uf               = filtraEntrada($_POST["uf"]);
  $cidade           = filtraEntrada($_POST["cidade"]);
  $num              = filtraEntrada($_POST["numero"]);
  $bairro           = filtraEntrada($_POST["bairro"]);
  $cep              = filtraEntrada($_POST["cep"]);

  try
  {    
    // Função definida no arquivo conexaoMysql.php
    $conn = conectaAoMySQL();

    $sql = "
      INSERT INTO Cliente (Id, Nome, Email, Telefone, Cpf, Profissao, Sexo, EstadoCivil, CEP, Bairro, Num, Cidade, UF, Rua)
      VALUES (null, '$nome', '$email','$telefone' , '$cpf', '$profissao', '$sexo', '$estadoCivil', '$cep' , '$bairro', '$num', '$cidade', '$uf', '$rua');
    ";

    if (! $conn->query($sql))
      throw new Exception("Falha na inserção dos dados: " . $conn->error);
  }
  catch (Exception $e)
  {
    $msgErro = $e->getMessage();
  }
}
  
?>

<!DOCTYPE html>
<html lang="pt-br">

  <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Client Register</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="src/js/cep.js"></script>
    <link rel="stylesheet" href="src/css/style.css">

     <!-- Font Awesome JS -->
      <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js" integrity="sha384-tzzSw1/Vo+0N5UhStP3bvwWPq+uvzCMfrN1fEFe+xBmv1C/AtVX5K0uZtmcHitFZ" crossorigin="anonymous"></script>
      <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js" integrity="sha384-6OIrr52G08NpOFSZdxxz1xdNSndlD4vdcf/q2myIUVO0VsqaGHJsB0RaBE01VTOY" crossorigin="anonymous"></script>
</head>

<body>

    <div class="wrapper">
        <!-- Sidebar  -->
        <nav id="sidebar">
            <div class="sidebar-header">
               <div class="p-2"><img class="img-fluid" style="max-width: 200px" src="src/assets/mallory-logo-pb.svg"/></div>
            </div>

            <ul class="list-unstyled components">
                <p>MALLORY IMOBILIARIA</p>
               
                <li>
                    <a href="index.html">Home</a>
                </li>
               
                <li>
                    <a href="restrict.php">Voltar</a>
                </li>
                <li>
                    <a href="logout.php">Sair</a>
                </li>
            </ul>

        </nav>

        <!-- Page Content  -->
        <div id="content">

            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <div class="container-fluid">

                    <button type="button" id="sidebarCollapse" class="btn btn-info">
                        <i class="fas fa-align-left"></i>
                        <span>Toggle Sidebar</span>
                    </button>
                     <h2>Cadastro Cliente</h2>
  <form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>" method="POST">
  
    <div class="form-group">
      <label for="nome">Nome:</label>
      <input type="text" class="form-control" placeholder="Informe seu nome" name="nome" id="nome" required>
    </div>

     <div class="form-group">
      <label for="nome">Telefone:</label>
      <input type="text" class="form-control" maxlength="11" 
      pattern="[0-9]{11}" placeholder="0000000000" name="telefone" id="telefone" required>
    </div>

     <div class="form-group">
      <label for="nome">CPF:</label>
      <input type="text" class="form-control" placeholder="Informe seu CPF" name="CPF" maxlength="11" id="CPF" required>
    </div>
    
    <div class="form-group">
      <label for="">E-mail:</label>
      <input type="email" class="form-control" placeholder="Informe o e-mail" name="email" id="email" required>
    </div>
    
   <div class="form-group">
      <label for="nome">Profissão:</label>
      <input type="text" class="form-control" placeholder="Informe seu Cargo" name="prof" id="prof" required>
    </div>
     <div class="form-group">
      <label for="nome">Sexo:</label>
      <input type="text" class="form-control" placeholder="Informe seu Sexo" name="sexo" id="sexo" required>
    </div>
                  

    <div class="form-group">
      <label for="estadoCivil">Estado Civil:</label>
      <select name="estadoCivil" id="estadoCivil" class="form-control" required>
        <option value="solteiro">Solteiro</option>
        <option value="casado">Casado</option>
        <option value="viuvo">Viuvo</option>
      </select>
    </div>
    
    <div class="row">
            <div class="col-md-3">
              <div class="form-group">
                  <label>CEP</label>
                  <input type="text" class="form-control pula" id="cep" maxlength="12" name="cep" required onkeyup="buscaEndereco(this.value)" placeholder="Formato XXXXXX-XXX">
              </div>
            </div>
            <div class="col-md-7">
             <div class="form-group">
                 <label>Rua <span></span> <span id='mensagem'></span></label>
                   <input type="text" class="form-control pula" id="rua" name="rua" placeholder="Informe a Rua" required>
              </div>
            </div>
            <div class="col-md-2">
             <div class="form-group">
                 <label>Número</label>
                   <input type="text" class="form-control pula" id="numero" name="numero" placeholder="Informe o Número" required>
              </div>
            </div>
          </div>  

          <div class="row">
            <div class="col-md-3">
              <div class="form-group">
                  <label>Bairro</label>
                  <input type="text" class="form-control pula" id="bairro" name="bairro" placeholder="Informe o Bairro"required>
              </div>
            </div>
            <div class="col-md-3">
              <div class="form-group">
                 <label>Cidade</label>
                  <input type="text" class="form-control pula" id="cidade" name="cidade" placeholder="Informe a Cidade"required>
              </div>
            </div>

            <div class="col-md-1">
             <div class="form-group">
                  <label>UF</label>
                  <input type="text" class="form-control pula" id="uf" maxlength="2" name="uf" placeholder="UF"required>
              </div>
            </div>
           
            
          </div>
    
    <button type="submit" class="btn btn-default">Enviar</button>
  </form>

  
                </div>
            </nav>

<!-- START FOOTER DARK-->
    <footer class="navbar footer fixed-bottom footer-light footer-shadow content container-fluid">
       <!-- Copyright -->
  <div class="footer-copyright text-center py-3">© 2019 Copyright:
    <a href="mallory.website"> mallory.website</a>
  </div>
    </footer>
    <!-- START FOOTER DARK-->
  



        </div>
    </div>

    <script type="text/javascript">
        $(document).ready(function () {
            $('#sidebarCollapse').on('click', function () {
                $('#sidebar').toggleClass('active');
            });
        });
    </script>

  <?php 
  if ($_SERVER["REQUEST_METHOD"] == "POST") 
  {  
    if ($msgErro == "")
      echo "<h3 class='text-success'>Dados armazenados com sucesso!</h3>";
    else
      echo "<h3 class='text-danger'>Cadastro não realizado: $msgErro</h3>";
  }
?>
</body>
