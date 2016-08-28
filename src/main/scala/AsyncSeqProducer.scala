import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

object AsyncSeqProducer {

  def main(args : Array[String]): Unit = {
    if (args.length != 2){
      System.err.println(s"Usage: ${this.getClass.getName} <topic> <broker-list>")
      System.exit(2)
    }
    val topic = args(1)
    val props = new Properties()
    props.put("bootstrap.servers",args(2).split(",").toSet.mkString(","))
    props.put("key.serializer","org.apache.kafka.common.serialization.StringDeserializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.IntegerSerializer")
    props.put("linger.ms", "10")

    val producer = new KafkaProducer[String,Int](props)
    var c = 0
    while(true) {
      val record = new ProducerRecord[String, Int](topic, s"msg$c", c)
      producer.send(record)
      c += 1
    }
  }
}
