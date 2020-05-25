**Informações gerais:

*Implementação do descritor de Imagens GIST
*O programa foi escrito em java com auxilio da biblioteca OPENIMAJ(versões 1.3.6 e 1.3.8, as duas versões são necessárias)
*Programa escrito utilizando a IDE Eclipse
*Escrito e testado em ambiente UNIX (kubuntu 18.04)


**How to use:

*Colocar as imagens do diretorio de compilação
*executar o programa
*Fornecer o nome do arquivo que contem a lista de imagens
(ou o caminho completo para a lista, caso esteja num diretorio diferente)

**Saida:
*O programa gera um arquivo "GIST.txt" contendo a lista com o nome dos arquivos
contendo a descrição das imagens
*Cada arquivo é nomeado referente a imagem que o originou e segue a regra:
"nome da imagem"-GIST.txt
*cada um dos descritores gerados armazena os dados de um vetor que descreve
uma imagem, sendo uma casa do vetor por linha
