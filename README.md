# sequence-producer
Simple Kafka producers for producing Sequece of numbers

##Producers
* [SyncSeqProducer](src/main/scala/SyncSeqProducer.scala): Produces number sequence in `sync` mode.
* [AsyncSeqProducer](src/main/scala/AsyncSeqProducer.scala): Produces number sequence in `async` mode

###Build : 
`sbt assembly`

###Usage:

#### SyncSeqProducer
```shell
java -cp target/scala-2.11/sequence-producer-assembly-0.1.jar SyncSeqProducer <topic> <broker-list>
```

#### AsyncSeqProducer
```shell
java -cp target/scala-2.11/sequence-producer-assembly-0.1.jar AsyncSeqProducer <topic> <broker-list>
```
