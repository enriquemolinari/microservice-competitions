package competition.infrastructure;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import competition.model.ListenerRepository;
import competition.model.RadioException;

public class JdbcListenerRepository implements ListenerRepository {

 private JdbcConn conn;

 public JdbcListenerRepository(String user, String pwd,
   String connString) {
  this.conn = new JdbcConn(user, pwd, connString);
 }

 @Override
 public void addNewListener(int idListener, String email) {
  Connection c = this.conn.connection();
  try {
   var st = c.prepareStatement(
     "insert into listener(id_listener, email) " + "values(?,?)");

   st.setInt(1, idListener);
   st.setString(2, email);
   st.executeUpdate();
  } catch (SQLException e) {
   throw new RadioException(e);
  } finally {
   try {
    c.close();
   } catch (SQLException e) {
    throw new RadioException(e);
   }
  }
 }

 @Override
 public String listenerEmail(int idListener) {
  Connection c = this.conn.connection();
  try {

   var st = c.prepareStatement(
     "select email from listener " + "where id_listener = ?");

   st.setInt(1, idListener);

   ResultSet resultSet = st.executeQuery();

   if (resultSet.next()) {
    return resultSet.getString(1);
   }

   throw new RadioException(
     "idListener not found in Competitions microservice...");
  } catch (SQLException e) {
   throw new RadioException(e);
  } finally {
   try {
    c.close();
   } catch (SQLException e) {
    throw new RadioException(e);
   }
  }
 }
}
