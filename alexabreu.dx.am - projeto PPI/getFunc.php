<?php

class Funcionario
{
	public $id;
	public $nome;
	public $email;
	public $cpf;
	public $ingresso;
	public $telefone;
	public $salario;
	public $cargo;
        public $bairro;
	public $cep;
	public $cidade;
	public $num;
	public $rua;
	public $uf;

}

function getFuncionarios($conn)
{
	$arrayFuncionario = null;

	$SQL = "
		SELECT *
		FROM Funcionario
	";

	$result = $conn->query($SQL);
	if (! $result)
		throw new Exception('Ocorreu uma falha ao gerar o relatorio de testes: ' . $conn->error);

	if ($result->num_rows > 0)
	{
		while ($row = $result->fetch_assoc())
		{
			$funcionario = new Funcionario();

            // Atenção para os nomes dos campos usados como chave de acesso a seguir.
            // O PHP faz distinção de maiúculas e minúsculas.
            // Por exemplo, se foi definido "Nome" com o "N" maiúculo na tabela do banco e o campo
            // não foi renomeado no SELECT da SQL, então é obrigatório utilizar "Nome" com o "N" maiúculo
            // em $row["Nome"].
            
			$funcionario->id            = $row["Id"];
			$funcionario->nome          = $row["Nome"];
			$funcionario->email         = $row["Email"];
			$funcionario->cpf           = $row["Cpf"];
			$funcionario->ingresso      = $row["Ingresso"];
			$funcionario->telefone      = $row["Telefone"];
			$funcionario->salario       = $row["Salario"];
			$funcionario->cargo         = $row["Cargo"];
                        $funcionario->bairro        = $row["Bairro"];
			$funcionario->cep           = $row["CEP"];
			$funcionario->cidade        = $row["Cidade"];
			$funcionario->num           = $row["Num"];
			$funcionario->rua           = $row["Rua"];
			$funcionario->uf            = $row["UF"];

	

			$arrayFuncionario[] = $funcionario;
		}
	}

	return $arrayFuncionario;
}

?>