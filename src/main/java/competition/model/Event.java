package competition.model;

public interface Event {

 void publish(String type, String value);

 void listenTo(String msgName, OnMessageReceived onMessage);

}
