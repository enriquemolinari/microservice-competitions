package competition.infrastructure;

import competitions.model.ports.OnMessageReceived;
import redis.clients.jedis.JedisPubSub;

public class RedisMessageReceived extends JedisPubSub {

 private OnMessageReceived message;
 
 public RedisMessageReceived(OnMessageReceived event) {
  this.message = event;
 }
 
 @Override
 public void onMessage(String channel, String message) {
  this.message.messageReceived(message);
 }
}
