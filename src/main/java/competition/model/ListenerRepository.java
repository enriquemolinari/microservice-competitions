package competition.model;

public interface ListenerRepository {
 void addNewListener(int idListener, String email);
 
 String listenerEmail(int idListener);
}
