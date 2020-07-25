package competition.model;

public class NewListener implements OnMessageReceived {
 private ListenerRepository repository;
 private Event event;
 private String MSG_NAME = "new-listener";

 public NewListener(ListenerRepository repository, Event event) {
  this.repository = repository;
  this.event = event;
 }

 public void startListening() {
  this.event.listenTo(MSG_NAME, this);
 }

 @Override
 public void messageReceived(String message) {
  var event = new NewListenerEvent(message);
  this.repository.addNewListener(event.idListener(), event.email());
 }
}
