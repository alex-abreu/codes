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

<!DOCTYPE html>
<html> 
  <head> 
    <title>Mallory Imobiliária </title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous"/>
    <link rel="stylesheet" href="src/css/stylesheet.css"/>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script type="text/javascript" src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script type="text/javascript" src="src/js/index.js"></script>
  </head>
  <body class="background-body">
    <nav>
      <div class="sidenav sidenav-restrict d-sm-flex d-md-flex d-lg-flex d-xl-flex flex-column bd-hightlight mb-3">
        <div class="sidenav-header d-inline-flex mb-4 mt-4">
          <div class="p-2"><img class="img-fluid" style="max-width: 200px" src="src/assets/mallory-logo-pb.svg"/></div>
        </div>
        <div>
          <div class="sidenav-item p-2"> 
            <button class="neutral-buttom btn-block text-left" type="button" data-toggle="modal" 
              onClick="Javascript:window.location.href = 'cadCliente.php';"
            />Cadastro Clientes</button>
          </div>
          <div class="sidenav-item p-2"> 
            <button class="neutral-buttom btn-block text-left" type="button" data-toggle="modal" 
                onClick="Javascript:window.location.href = 'cadFuncionario.php';" />
            Cadastro Funcionários</button>
          </div>
          <div class="sidenav-item p-2"> 
            <button class="neutral-buttom btn-block text-left" type="button" data-toggle="modal" data-target="#cad-imov">Cadastro Imóveis</button>
          </div>
          <div class="sidenav-item p-2"> 
            <button class="neutral-buttom btn-block text-left" type="button"  data-toggle="modal" 
            onClick="Javascript:window.location.href = 'listFunc.php';"
            >Listar Funcionários </button>
          </div>
          <div class="sidenav-item p-2"> 
            <button class="neutral-buttom btn-block text-left" type="button" onclick="listImovel()" data-toggle="modal" data-target="#list-imovel">Listar Imóveis</button>
          </div>
          <div class="sidenav-item p-2"> 
            <button class="neutral-buttom btn-block text-left" type="button" data-toggle="modal" 
            onClick="Javascript:window.location.href = 'listClient.php';"
            />Listar Clientes </button>
          </div>
          <div class="sidenav-item p-2"> 
            <button class="neutral-buttom btn-block text-left" type="button" onclick="listInt()" data-toggle="modal" data-target="#user-int">Listar Interesses dos Usuários</button>
          </div>
          <div class="sidenav-item p-2"> 
            <button class="neutral-buttom btn-block text-left" type="button" data-toggle="modal" 
            onClick="Javascript:window.location.href = 'index.html';"
            />Voltar </button>
          </div>
          <div class="sidenav-item p-2"> 
            <button class="neutral-buttom btn-block text-left" type="button" data-toggle="modal" 
            onClick="Javascript:window.location.href = 'logout.php';"
            >Sair</button>
          </div>
        </div>
      </div>
    </nav>
    <main class="main-margin" id="main" role="main">
      <div class="jumbotron jumbotron-transparent">
        <div class="container"> 
          <div>
            <h1 class="display-5 default-font" style="color: white">Sua casa. Seu jeito.</h1>
            <p class="default-font-lighter" style="color: white">Não vendemos casas, vendemos sonhos, então, se seu sonho é ter uma casa, você veio ao lugar certo.</p>
          </div>
        </div>
      </div>
      <div class="container" style="color: white">
        <h1> </h1>
      </div>
      <div class="jumbotron jumbotron-transparent m-0"> 
        <div class="footer">
          <h5 class="display-5 default-font text-center" style="color:white">Mallory ®</h5>
          <h6 class="display-5 default-font text-center" style="color:white">Nossas redes sociais</h6>
          <div class="d-sm-flex d-md-flex d-lg-flex d-xl-flex justify-content-center">
            <div><a class="fa fa-facebook no-decoration" href="https://www.facebook.com"></a></div>
            <div><a class="fa fa-twitter no-decoration" href="https://www.twitter.com"></a></div>
            <div><a class="fa fa-instagram no-decoration" href="https://www.instagram.com"></a></div>
          </div>
        </div>
      </div>
      <!--modal imóveis-->
      <div class="modal fade" id="cad-imov" tabindex="-1" role="dialog" aria-labelledby="Login" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
          <div class="modal-content"> 
            <div class="modal-header">  
              <h5 class="modal-title" id="Login">Cadastro de Imóveis</h5>
              <button class="close" type="button" data-dismiss="modal" aria-label="Fechar"><span aria-hidden="true">&times; </span></button>
            </div>
            <div class="modal-body">
              <nav> 
                <div class="nav nav-tabs" id="nav-tab" role="tablist"><a class="nav-item nav-link active" id="nav-casa-tab" data-toggle="tab" href="#nav-casa" role="tab" aria-controls="nav-casa" aria-selected="true">Casa</a><a class="nav-item nav-link" id="nav-ap-tab" data-toggle="tab" href="#nav-ap" role="tab" aria-controls="nav-ap" aria-selected="false">Apartamento</a></div>
              </nav>
              <div class="tab-content" id="nav-tabContent">
                <div class="tab-pane fade show active" id="nav-casa" role="tabpanel" aria-labelledby="nav-casa">
                  <form> 
                    <div class="form-row"> 
                      <div class="form-group col-sm col-md col-lg col-xl">
                        <label for="qrts">Número de quartos</label>
                        <input class="form-control" id="qrts" type="number"/>
                      </div>
                    </div>
                    <div class="form-row"> 
                      <div class="form-group col-sm col-md col-lg col-xl">
                        <label for="suites">Número de Suítes</label>
                        <input class="form-control" id="suites" type="number"/>
                      </div>
                    </div>
                    <div class="form-row"> 
                      <div class="form-group col-sm col-md col-lg col-xl">
                        <label for="sljantar">Número de Salas de Jantar</label>
                        <input class="form-control" id="sljantar" type="number"/>
                      </div>
                    </div>
                    <div class="form-row"> 
                      <div class="form-group col-sm col-md col-lg col-xl">
                        <label for="slestar">Número de Salas de Estar</label>
                        <input class="form-control" id="slestar" type="number"/>
                      </div>
                    </div>
                    <div class="form-row"> 
                      <div class="form-group col-sm col-md col-lg col-xl">
                        <label for="vgaragem">Número de Vagas na Garagem</label>
                        <input class="form-control" id="slestar" type="number"/>
                      </div>
                    </div>
                    <div class="form-row"> 
                      <div class="form-group col-sm col-md col-lg col-xl">
                        <label for="area">Área(m²)</label>
                        <input class="form-control" id="area" type="number"/>
                      </div>
                    </div>
                    <div class="form-row"> 
                      <div class="form-group col-sm col-md col-lg col-xl">
                        <label>Armário embutido</label>
                      </div>
                    </div>
                    <div class="form-row">
                      <div class="form-group col-sm col-md col-lg col-xl">
                        <input type="radio" name="aembutido" value="sim"/>
                        <label>Sim</label>
                      </div>
                      <div class="form-group col-sm col-md col-lg col-xl"> 
                        <input type="radio" name="aembutido" value="nao"/>
                        <label>Não</label>
                      </div>
                    </div>
                    <div class="form-row"> 
                      <div class="form-group col-sm col-md col-lg col-xl">
                        <label for="description-casa">Observações</label>
                        <textarea class="form-control" id="msg" rows="3"></textarea>
                      </div>
                    </div>
                  </form>
                </div>
                <div class="tab-pane fade show" id="nav-ap" role="tabpanel" aria-labelledby="nav-ap">more content
                  <form> 
                    <div class="form-row"> 
                      <div class="form-group col-sm col-md col-lg col-xl">
                        <label for="qrts">Número de quartos</label>
                        <input class="form-control" id="qrts" type="number"/>
                      </div>
                    </div>
                    <div class="form-row"> 
                      <div class="form-group col-sm col-md col-lg col-xl">
                        <label for="suites">Número de Suítes</label>
                        <input class="form-control" id="suites" type="number"/>
                      </div>
                    </div>
                    <div class="form-row"> 
                      <div class="form-group col-sm col-md col-lg col-xl">
                        <label for="sljantar">Número de Salas de Jantar</label>
                        <input class="form-control" id="sljantar" type="number"/>
                      </div>
                    </div>
                    <div class="form-row"> 
                      <div class="form-group col-sm col-md col-lg col-xl">
                        <label for="slestar">Número de Salas de Estar</label>
                        <input class="form-control" id="slestar" type="number"/>
                      </div>
                    </div>
                    <div class="form-row"> 
                      <div class="form-group col-sm col-md col-lg col-xl">
                        <label for="vgaragem">Número de Vagas na Garagem</label>
                        <input class="form-control" id="slestar" type="number"/>
                      </div>
                    </div>
                    <div class="form-row"> 
                      <div class="form-group col-sm col-md col-lg col-xl">
                        <label for="area">Área(m²)</label>
                        <input class="form-control" id="area" type="number"/>
                      </div>
                    </div>
                    <div class="form-row"> 
                      <div class="form-group col-sm col-md col-lg col-xl">
                        <label>Armário embutido</label>
                      </div>
                    </div>
                    <div class="form-row">
                      <div class="form-group col-sm col-md col-lg col-xl">
                        <input type="radio" name="aembutido" value="sim"/>
                        <label>Sim</label>
                      </div>
                      <div class="form-group col-sm col-md col-lg col-xl"> 
                        <input type="radio" name="aembutido" value="nao"/>
                        <label>Não</label>
                      </div>
                    </div>
                    <div class="form-row"> 
                      <div class="form-group col-sm col-md col-lg col-xl">
                        <label for="andr">Andar</label>
                        <input class="form-control" id="andr" type="number"/>
                      </div>
                    </div>
                    <div class="form-row"> 
                      <div class="form-group col-sm col-md col-lg col-xl">
                        <label for="condvl">Valor Condomínio</label>
                        <input class="form-control" id="condvl" type="number"/>
                      </div>
                    </div>
                    <div class="form-row"> 
                      <div class="form-group col-sm col-md col-lg col-xl">
                        <label>Porteiro 24h</label>
                      </div>
                    </div>
                    <div class="form-row">
                      <div class="form-group col-sm col-md col-lg col-xl">
                        <input type="radio" name="p24h" value="sim"/>
                        <label>Sim</label>
                      </div>
                      <div class="form-group col-sm col-md col-lg col-xl"> 
                        <input type="radio" name="p24" value="nao"/>
                        <label>Não</label>
                      </div>
                    </div>
                    <div class="form-row"> 
                      <div class="form-group col-sm col-md col-lg col-xl">
                        <label for="description-casa">Observações</label>
                        <textarea class="form-control" id="msg" rows="3"></textarea>
                      </div>
                    </div>
                  </form>
                </div>
              </div>
            </div>
            <div class="modal-footer"> 
              <button class="btn btn-primary" type="button">Cadastrar</button>
            </div>
          </div>
        </div>
      </div>

<!--modal list funcionario-->
      <div class="modal fade" id="list-func" tabindex="-1" role="dialog" aria-labelledby="Login" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
          <div class="modal-content"> 
            <div class="modal-header">  
              <h5 class="modal-title" id="Login">Lista de Funcionários</h5>
              <button class="close" type="button" data-dismiss="modal" aria-label="Fechar"><span aria-hidden="true">&times; </span></button>
            </div>
            <div class="modal-body">
              <div class="container-fluid"> 
                <table id="listF" class="table table-striped table-bordered"> 
                  
                </table>
              </div>
            </div>
          </div>
        </div>
      </div>

<!--modal list Cliente-->
      <div class="modal fade" id="list-client" tabindex="-1" role="dialog" aria-labelledby="Login" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
          <div class="modal-content"> 
            <div class="modal-header">  
              <h5 class="modal-title" id="Login">Lista de Clientes</h5>
              <button class="close" type="button" data-dismiss="modal" aria-label="Fechar"><span aria-hidden="true">&times; </span></button>
            </div>
            <div class="modal-body">
              <div class="container-fluid"> 
                <table id="listC" class="table table-striped table-bordered"> 
                  
                </table>
              </div>
            </div>
          </div>
        </div>
      </div>

<!--modal User interes-->
      <div class="modal fade" id="user-int" tabindex="-1" role="dialog" aria-labelledby="Login" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
          <div class="modal-content"> 
            <div class="modal-header">  
              <h5 class="modal-title" id="Login">Lista de Interesses por imóvel</h5>
              <button class="close" type="button" data-dismiss="modal" aria-label="Fechar"><span aria-hidden="true">&times; </span></button>
            </div>
            <div class="modal-body">
              <div class="container-fluid"> 
                <table id="listI" class="table table-striped table-bordered"> 
                  
                </table>
              </div>
            </div>
          </div>
        </div>
      </div>

<!--modal User interes-->
      <div class="modal fade" id="list-imovel" tabindex="-1" role="dialog" aria-labelledby="Login" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
          <div class="modal-content"> 
            <div class="modal-header">  
              <h5 class="modal-title" id="Login">Lista de Interesses por imóvel</h5>
              <button class="close" type="button" data-dismiss="modal" aria-label="Fechar"><span aria-hidden="true">&times; </span></button>
            </div>
            <div class="modal-body">
              <div class="container-fluid"> 
                <table id="listIM" class="table table-striped table-bordered"> 
                  
                </table>
              </div>
            </div>
          </div>
        </div>
      </div>




    </main>
  </body>
</html>