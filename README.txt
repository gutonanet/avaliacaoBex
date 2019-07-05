A aplicação foi desenvolvida utilizando spring boot, assim facilitando o desenvolvimento dos serviços REST e também empacotar o a interface shell em um jar.
Na estrutura de pacotes temos os DTO's, que são os objetos para transitar os dados, o controller do serviço REST, o pacote service e o pacote exception. A interface do shell se encontra no pacote execute. Por ultimo, os testes unitários se encontram no pacote tests.
Para executar a Interface do Shell, execute a classe "com.lemes.augusto.bexs.execute.BestRouteExecute" que possui um metodo main. 
Para executar a Interface Rest, execute a classe "com.lemes.augusto.bexs.BexsApplication" ou "java -jar bexs-0.0.1-SNAPSHOT.jar" ou na pasta do projeto execute "mvn spring-boot:run".
Foi criado um service comum para atender as duas interfaces, onde efetua a pesquisa do "melhor preço" e salva a rota no CSV.
Para efetuar a pesquisa é criada uma lista encadeada amarrando ponta a ponta e somando o valor de cada rota da origem solicitada até o destino solicitado.
Ao pesquisar todas as possíveis rotas, é adicionado a um Map onde a chave é ordenada por valor, sendo que o primeiro item do Map é o de menor custo.

