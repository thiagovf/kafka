# Kafka  
Kafka é um framework voltado para mensageria. Tem sido muito usado com streaming.  
## Mensageria  
É uma forma de dois sistemas se comuncarem entre si de forma assíncrona. 
### Tópico  
Um tópico seria algo semelhante a um *feed* preenchido por um *producer* em que determinados *consumers*, inscridos nesse *feed* , irão ser notificados quando houver atualização.  
![Imagem ilustrativa](https://www.cloudkarafka.com/img/blog/apache-kafka-partition.png)  
## Download  
[Link Download Kafka](https://kafka.apache.org/downloads)  
Feito o download, descompacta e vamos aos primeiros testes.  
## Inicializando Kafka por linha de comando  
O Kafka precisa usar o Zookeeper. Isto é uma ferramenta que o Kafka utiliza para armazenar algumas das informações. Por isso, ele já vem disponível junto com o Kafka. Na pasta descompactada, executar o comando abaixo para iniciar o Zookeeper.    
```  
bin/zookeeper-server-start.sh config/zookeeper.properties   
```  
Só em seguida iniciar o Kafka em si.  
```  
bin/kafka-server-start.sh config/server.properties  
```  
## Novo tópico  
Para enviar mensagem de um lado para o outro, vamos criar um tópico novo de teste.  
```  
bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic LOJA_NOVO_PEDIDO  
```  
Feito isso, para termos certeza que o tópico foi criado, podemos executar o comando abaixo.  
```  
bin/kafka-topics.sh --list --bootstrap-server localhost:9092  
```  
## Novo produtor de conteúdo  
```  
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic LOJA_NOVO_PEDIDO   
```  
Após esse comando, abre-se um input que cada linha será considerada uma nova mensagem que será transmitida para o tópico passado.  
## Novo consumidor do conteúdo  
Em uma nova aba, o comando abaixo irá passar a escutar a partir do momento em que foi executado. Já o comando seguinte irá consumir todas as mensagens desde o começo e manter-se atualizando dali pra frente.  
```  
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic LOJA_NOVO_PEDIDO  
```  
Comando que executa desde o começo.  
```  
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic LOJA_NOVO_PEDIDO --from-beginning  
```  
# Kafka no java  
Criado um projeto maven do zero, adicionamos as seguintes dependências no pom.  
```xml   
<dependencies>  
  <dependency>  
    <groupId>org.apache.kafka</groupId>  
    <artifactId>kafka-clients</artifactId>  
    <version>2.8.1</version>  
  </dependency>  
  <dependency>  
    <groupId>org.slf4j</groupId>  
    <artifactId>slf4j-simple</artifactId>  
    <version>1.7.32</version>  
  </dependency>  
</dependencies>  
``` 
Além disso, usaremos códigos que passaram a ser usados a partir da versão 10 do java. Portanto, devemos colocar também no pom as configurações de build do maven.  
```xml  
<build>
  <plugins>
    <plugin>
      <artifactId>maven-compiler-plugin</artifactId>
      <configuration>
        <source>10</source>
        <target>10</target>
      </configuration>
    </plugin>
  </plugins>
</build>
```   
## Producer com java em um método main  
A fim de realizarmos testes, criamos uma classe que tem um método main do java. Nela instanciaremos um ```KafkaProducer``` que irá enviar as mensagens para o nosso servidor que está executando na porta ```9092```.
```java  
var producer = new KafkaProducer<String, String>(properties());
``` 
As propriedades retornadas do método ```properties``` se referem justamente às configurações do servidor. 
### Explicação das properties  
Endereço do servidor Kafka  
```java  
properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");  
```
Indicação de como as chaves e valores serão tratadas, no caso, como strings
```java  
properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());  
properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());  
```  
Os códigos expostos acima estão versionados na tag [Aula04](https://github.com/thiagovf/kafka/releases/tag/Aula04).
