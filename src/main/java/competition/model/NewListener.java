package competition.model;

import java.util.Map;

public class NewListener implements OnMessageReceived {

 private static final String EMAIL = "email";
 private static final String ID_LISTENER = "idListener";
 private CompetitionRepository repository;
 private Event event;

 public NewListener(CompetitionRepository repository, Event event) {
  this.repository = repository;
  this.event = event;
 }

 public void startListening() {
  this.event.listenTo(this);
 }

 @Override
 public void messageReceived(String message) {
  Map<String, String> msg = new NewListenerEvent(message).toMap();
  this.repository.addNewListener(Integer.valueOf(msg.get(ID_LISTENER)),
    msg.get(EMAIL));
 }
}
