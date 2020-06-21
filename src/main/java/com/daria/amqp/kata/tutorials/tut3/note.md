### Publish/Subscribe
Sending messages to many consumers at once

---
The fanout pattern to deliver a message to multiple consumers.
 * Essentially, published messages are going to be broadcast to all the receivers.
 * https://www.rabbitmq.com/tutorials/tutorial-three-spring-amqp.html

There are a few exchange types available: `direct`, `topic`, `headers` and `fanout`. 
We'll focus on the last one -- the fanout. Let's configure a bean to describe an exchange of this type, and call it `tut.fanout`:


```
# shell 1
java -jar build/libs/tutorials-0.0.1-SNAPSHOT.jar --spring.profiles.active=pub-sub,receiver \
\ --tutorial.client.duration=60000


# shell 2
java -jar build/libs/tutorials-0.0.1-SNAPSHOT.jar --spring.profiles.active=pub-sub,sender \
\ --tutorial.client.duration=60000
```

before
```
~/rabbitmq » ./rabbitmqctl list_bindings                                                                   sophia@sophiaui-MacBookPro
Listing bindings for vhost /...
source_name	source_kind	destination_name	destination_kind	routing_key	arguments
	exchange	hello	queue	hello	[]
------------------------------------------------------------
```
after
```
~/rabbitmq » ./rabbitmqctl list_bindings                                                                   sophia@sophiaui-MacBookPro
Listing bindings for vhost /...
source_name	source_kind	destination_name	destination_kind	routing_key	arguments
	exchange	hello	queue	hello	[]
	exchange	spring.gen-Y0uWEmB7TuWA8jQmLboc9w	queue	spring.gen-Y0uWEmB7TuWA8jQmLboc9w	[]
	exchange	spring.gen-oBXXfC5MRRyvCNzOjVBy1Q	queue	spring.gen-oBXXfC5MRRyvCNzOjVBy1Q	[]
tut.fanout	exchange	spring.gen-Y0uWEmB7TuWA8jQmLboc9w	queue		[]
tut.fanout	exchange	spring.gen-oBXXfC5MRRyvCNzOjVBy1Q	queue		[]
```