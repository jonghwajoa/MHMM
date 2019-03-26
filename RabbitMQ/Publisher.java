import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Publisher {
	private final static String QUEUE_NAME = "hello";

	public static void main(String[] args) throws Exception {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
			channel.exchangeDeclare(QUEUE_NAME, BuiltinExchangeType.FANOUT);

			for (int i = 0; i < 10; i++) {
				String message = "Helloworld message - " + i;

				channel.basicPublish(QUEUE_NAME, "", null, message.getBytes("UTF-8"));
				System.out.println(" [x] Sent '" + message + " " + i);
			}
		}

	}
}
