<?php
session_start();
?>
<?php

function filtraEntradaForm($data)
{
  $data = trim($data);
  $data = stripslashes($data);
  $data = htmlspecialchars($data);
  return $data;
}


function loginUsuario($email, $senha, $mysqli)
{
  $SQL = "
    SELECT Nome, SenhaHash 
    FROM Usuario
    WHERE Email = ?
    LIMIT 1
  ";
  
  $stmt = $mysqli->prepare($SQL);
  $stmt->bind_param('s', $email);
  $stmt->execute();
  $stmt->store_result();
  
  // Resgata o resultado nas variáveis
  $stmt->bind_result($user, $senhaHash);
  $stmt->fetch();
  
  if ($stmt->num_rows == 1)
  {
    if (password_verify($senha, $senhaHash))
    {
      // Senha correta
            
      // Armazena dados úteis para confirmação de login
      // em outros scripts PHP
      $_SESSION['user'] = $user;
      $_SESSION['email'] = $email;
      $_SESSION['loginString'] = hash('sha512', $senhaHash . $_SERVER['HTTP_USER_AGENT']);
      
      // Sucesso no login
      return true;
    }
    else
    {
      // Senha incorreta
      return false;
    }
  }
  else
  {
    // Usuário não existe
    return false;
  }
}

function checkUsuarioLogado($mysqli)
{
  // Check if all session variables are set
  if (!isset($_SESSION['user'], $_SESSION['loginString']))
    return false;
  
  $user = $_SESSION['user'];
  $loginString = $_SESSION['loginString'];
    
  $SQL = "
    SELECT SenhaHash 
    FROM Usuario
    WHERE Id = ?
    LIMIT 1
  ";
  
  $stmt = $mysqli->prepare($SQL);
  $stmt->bind_param('i', $user);
  $stmt->execute();
  $stmt->store_result();
  
  if ($stmt->num_rows == 1)
  {
    $stmt->bind_result($senhaHash);
    $stmt->fetch();
    
    $loginStringCheck = hash('sha512', $senhaHash . $_SERVER['HTTP_USER_AGENT']);
    
    if (hash_equals($loginStringCheck, $loginString))
      return true;
  }
  
  return false;
}

function checkUsuarioLogadoOrDie($mysqli)
{
  if (!checkUsuarioLogado($mysqli))
  {
    $mysqli->close();
    header("Location: index.html");
    die();
  }
}