package components;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class Productor {
	private KafkaProducer<String,String> producer;
	public Productor() {
		Properties props=new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		producer=new KafkaProducer<String, String>(props);
	}
	public void send(String topic, String mensaje) {
		producer.send(new ProducerRecord<String, String>(topic,mensaje));
	}
	
	public void cerrar() {
		producer.close();
	}
}
