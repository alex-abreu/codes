<?php

define("HOST", "fdb22.awardspace.net");
define("USER", "3173920_ppi");
define("PASSWORD", "S@suked12");
define("DATABASE", "3173920_ppi");

function conectaAoMySQL()
{
    $conn = new mysqli(HOST, USER, PASSWORD, DATABASE);
    if ($conn->connect_error)
        throw new Exception('Falha na conexão com o MySQL: ' . $conn->connect_error);

    return $conn;
}

?>