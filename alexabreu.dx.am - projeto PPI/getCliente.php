<?php

class Cliente 
{
	public $id;
	public $nome;
	public $email;
	public $estadoCivil;
	public $bairro;
	public $cep;
	public $cidade;
	public $cpf;
	public $num;
	public $profissao;
	public $rua;
	public $sexo;
	public $telefone;
	public $uf;
}

function getClientes($conn)
{
	$arrayClientes = null;

	$SQL = "
		SELECT *
		FROM Cliente
	";

	$result = $conn->query($SQL);
	if (! $result)
		throw new Exception('Ocorreu uma falha ao gerar o relatorio de testes: ' . $conn->error);

	if ($result->num_rows > 0)
	{
		while ($row = $result->fetch_assoc())
		{
			$cliente = new Cliente();

            // Atenção para os nomes dos campos usados como chave de acesso a seguir.
            // O PHP faz distinção de maiúculas e minúsculas.
            // Por exemplo, se foi definido "Nome" com o "N" maiúculo na tabela do banco e o campo
            // não foi renomeado no SELECT da SQL, então é obrigatório utilizar "Nome" com o "N" maiúculo
            // em $row["Nome"].
            
			$cliente->id            = $row["Id"];
			$cliente->nome          = $row["Nome"];
			$cliente->email         = $row["Email"];
			$cliente->estadoCivil   = $row["EstadoCivil"];
			$cliente->bairro       	= $row["Bairro"];
			$cliente->cep       	= $row["CEP"];
			$cliente->cidade        = $row["Cidade"];
			$cliente->cpf           = $row["Cpf"];
			$cliente->num           = $row["Num"];
			$cliente->profissao     = $row["Profissao"];
			$cliente->rua           = $row["Rua"];
			$cliente->sexo          = $row["Sexo"];
			$cliente->telefone      = $row["Telefone"];
			$cliente->uf         = $row["UF"];

			$arrayClientes[] = $cliente;
		}
	}

	return $arrayClientes;
}

?>