package com.pessoa.jonathan.livraria.debug.messenger

import com.rabbitmq.client.*


class RabbitMQ {

    companion object {
        private val QUEUE_NAME = "queue123"
    }

    fun prod() {
        val factory = ConnectionFactory()
        factory.host = "localhost"
        factory.username = System.getProperty("DBEAVER_USER")
        factory.password = System.getProperty("DBEAVER_PASSWORD")
        factory.newConnection().use { connection ->
            connection.createChannel().use { channel ->
                channel.queueDeclare(QUEUE_NAME, false, false, false, null)
                val message = "Hello World!"
                channel.basicPublish("", QUEUE_NAME, null, message.toByteArray())
                println(" [x] Sent '$message'")
            }
        }
    }

    fun recep() {
        val factory = ConnectionFactory()
        factory.host = "localhost" // Use o IP do seu serviço Docker se não estiver rodando localmente
        factory.username = System.getProperty("DBEAVER_USER")
        factory.password = System.getProperty("DBEAVER_PASSWORD")
        val connection: Connection = factory.newConnection()
        val channel: Channel = connection.createChannel()
        channel.queueDeclare(QUEUE_NAME, false, false, false, null)
        println(" [*] Waiting for messages. To exit press CTRL+C")

        val deliverCallback = DeliverCallback { consumerTag: String?, delivery: Delivery ->
            val message = String(delivery.body, Charsets.UTF_8)
            println(" [x] Received '$message'")
        }
        channel.basicConsume(QUEUE_NAME, true, deliverCallback) { consumerTag -> }
    }
}