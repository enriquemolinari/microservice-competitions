package competitions.model.ports;

public interface ListenerRepository {
 void addNewListener(int idListener, String email);
 
 String listenerEmail(int idListener);
}
