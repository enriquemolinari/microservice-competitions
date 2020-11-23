package competition.infrastructure;

import competitions.ports.Event;
import competitions.ports.OnMessageReceived;
import redis.clients.jedis.Jedis;

public class RedisEvent implements Event {

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
 public void listenTo(String msgName, OnMessageReceived onMessage) {
  try (Jedis jSubscriber = new Jedis(this.host, this.port)) {
   jSubscriber.subscribe(new RedisMessageReceived(onMessage), msgName);
  }
 }
}
