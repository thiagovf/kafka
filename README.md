# Kafka  
Kafka é um framework voltado para mensageria. Tem sido muito usado com streaming.  
## Mensageria  
É uma forma de dois sistemas se comuncarem entre si de forma assíncrona. 
## Download  
[Link Download Kafka](https://kafka.apache.org/downloads)  
Feito o download, descompacta e vamos aos primeiros testes.  
## Inicializando Kafka por linha de comando  
O Kafka precisa usar o Zookeeper. Por isso, ele já vem disponível junto com o Kafka. Na pasta descompactada, executar o comando abaixo para iniciar o Zookeeper.    
`  
bin/zookeeper-server-start.sh config/zookeeper.properties   
`  
Só em seguida iniciar o Kafka em si.  
`  
bin/kafka-server-start.sh config/server.properties  
`  
