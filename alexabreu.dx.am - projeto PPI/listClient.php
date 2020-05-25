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

// Arquivo com os dados e função de conexão
require "conexaoMysql.php";
require "getCliente.php";

$arrayClientes = null;
$msgErro = "";

try
{
	$conn = conectaAoMySQL();
	$arrayClientes = getClientes($conn);  
}
catch (Exception $e)
{
	$msgErro = $e->getMessage();
}

?>

<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Client List</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
                    <h3 class="text-primary">Clientes Cadastrados</h3>
                         <table class="table table-striped">
                            <thead>
                              <tr>
                                <th>Nome</th>
                                <th>CPF</th>
                                <th>E-mail</th>
                                <th>Estado Civil</th>
                                <th>Telefone</th>
                                <th>Profissão</th>
                                <th>CEP</th>
                                <th>Bairro</th>
                                <th>Rua</th>
                                <th>Num</th>
                                <th>UF</th>
                                <th>Cidade</th>
                                <th>Sexo</th>
                              
                              </tr>
                            </thead>
                            
                            <tbody>
                          
                            <?php
                              if ($arrayClientes != null)
                              {
                                foreach ($arrayClientes as $cliente)
                                {       
                                  echo "
                                  <tr>
                                    <td>$cliente->nome</td>
                                    <td>$cliente->cpf</td>
                                    <td>$cliente->email</td>
                                    <td>$cliente->estadoCivil</td>
                                    <td>$cliente->telefone</td>
                                    <td>$cliente->profissao</td>
                                    <td>$cliente->cep</td>
                                    <td>$cliente->bairro</td>
                                    <td>$cliente->rua</td>
                                    <td>$cliente->num</td>
                                    <td>$cliente->uf</td>
                                    <td>$cliente->cidade</td>
                                    <td>$cliente->sexo</td>
                                    
                                    
                                  </tr> 
                                  ";
                                }
                              }
                            ?>    
                            
                            </tbody>
                          </table>
                          
                          <?php
                          
                          if ($msgErro != "")
                            echo "<p class='text-danger'>A operação não pode ser realizada: $msgErro</p>";
                          
                          ?>
  
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

</body>
</html>







