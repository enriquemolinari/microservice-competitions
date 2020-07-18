package competition.infrastructure;

import competition.model.Event;
import competition.model.OnMessageReceived;
import redis.clients.jedis.Jedis;

public class RedisEvent implements Event {

 private String CHANNEL_NAME = "new-listener";
 private String host;
 private int port;

 public RedisEvent(String host, int port) {
  this.host = host;
  this.port = port;
 }

 @Override
 public void publish(String type, String value) {
  try (Jedis j = new Jedis(this.host, this.port)) {
   j.publish(type, value);
  }
 }
 
 @Override
 public void listenTo(OnMessageReceived onMessage) {
  try (Jedis jSubscriber = new Jedis(this.host, this.port)) {
   jSubscriber.subscribe(new RedisMessageReceived(onMessage),
     CHANNEL_NAME);
  }
 }
}
