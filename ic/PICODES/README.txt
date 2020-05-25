**Informações Gerais:

*O script "install_opencv.sh" instala a bilbioteca OPENCV-2.4.12
necessária para utilização do picodes
*O arquivo Convert.py é utilizado para converter a saida do programa
"vlg_extractor", que gera arquivos .dat para arquivo .txt

**Compilação vlg_extractor:
*instalar a biblioteca OPENCV-2.4.12
*editar o makefile do programa, adicionando os caminhos da biblioteca
*utilizar o makefile
*executar o script "download_parameters.sh" que esta na pasta data do vlg

*How to use PICODES extractor:

Após a compilação com sucesso do programa, através do terminal, ir até o diretorio de
instalação e rodar o programa ./vlg_extractor com os parametros:

1- path para a pasta onde os dados baixados atraves de download_parameters.sh estão
caso  os arquivos estejam na pasta data nao é necessario informar o caminho

2- qual opção do picodes deverá ser executada (opçoes disponiveis para visualização ao digitar
./vlg_extractor (sem nenhum parametro))

3- caminho para o arquivo que contem a lista de imagens

4- caminho para o diretorio onde as imagens estão

5- caminho para pasta onde serão gerados os arquivos de saida 

Exemplo:

./vlg_extractor --extract_picodes128=FLOAT /home/alex/Documents/vlg_extractor-master/test/list_images.txt /home/alex/Documents/vlg_extractor-master/test /home/alex/Documents/vlg_extractor-master/test/output

**How to use Convert.py
*executar o programa no diretorio onde os arquivos .dat gerados pelo descritor estão
*informar o nome do arquivo que contem a lista com os nomes dos .dat
*o programa irá gerar um arquivo .txt para cada arquivo .dat existente
*o nome do arquivo gerado será o mesmo do arquivo .dat, com excessão da extensão que
mudará de .dat para .txt
