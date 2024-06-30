package com.pessoa.jonathan.livraria.debug.service
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.DeliverCallback
import jakarta.annotation.PostConstruct
import org.springframework.amqp.core.Queue
import org.springframework.stereotype.Service

@Service
class QueueService(private val queue: Queue) {

    private val factory: ConnectionFactory = ConnectionFactory().apply {
        host = "localhost"
        username = System.getProperty("DBEAVER_USER")
        password = System.getProperty("DBEAVER_PASSWORD")
    }

    fun sendMessage(message: String) {
        factory.newConnection().use { connection ->
            connection.createChannel().use { channel ->
                channel.queueDeclare(queue.name, false, false, false, null)
                channel.basicPublish("", queue.name, null, message.toByteArray())
                println(" [x] Sent '$message'")
            }
        }
    }

    @PostConstruct
    fun receiveMessage() {
        val connection = factory.newConnection()
        val channel = connection.createChannel()
        channel.queueDeclare(queue.name, false, false, false, null)
        println(" [*] Waiting for messages. To exit press CTRL+C")

        val deliverCallback = DeliverCallback { consumerTag, delivery ->
            val message = String(delivery.body, Charsets.UTF_8)
            println(" [x] Received '$message'")
        }
        channel.basicConsume(queue.name, true, deliverCallback) { _ ->
            // callback consumer cancel sending message
        }
    }
}