package main;

import java.util.Objects;

import competition.infrastructure.JdbcCompetitionRepository;
import competition.infrastructure.JdbcListenerRepository;
import competition.infrastructure.RedisEvent;
import competition.model.DefaultRadioProgram;
import competition.model.NewListener;
import competition.model.PublishInscriptionAdded;
import competition.web.Web;

public class Main {

 public static void main(String[] args) {
  String connString = Objects.requireNonNull(
    System.getProperty("conn-string"),
    "specify conn-string as a jvm argument");

  String user = Objects.requireNonNull(System.getProperty("user"),
    "specify a database user as a jvm argument");

  String pass = Objects.requireNonNull(System.getProperty("pwd"),
    "specify a database pwd as a jvm argument");

  String pubsub = Objects
    .requireNonNullElse(System.getProperty("pub-sub-server"), "localhost");

  String pubsubPort = Objects
    .requireNonNullElse(System.getProperty("pub-sub-port"), "6379");

  // Redis event
  var redis = new RedisEvent(pubsub, Integer.valueOf(pubsubPort));

  // Starting Web App ...
  new Web(new PublishInscriptionAdded(
    new DefaultRadioProgram(
      new JdbcCompetitionRepository(user, pass, connString)),
    new JdbcListenerRepository(user, pass, connString), redis), 7000)
      .start();

  // Starting subcription to NewListener
  new Thread("subscription-to-redis") {
   public void run() {
    new NewListener(new JdbcListenerRepository(user, pass, connString),
      redis).startListening();
   }
  }.start();
 }
}
