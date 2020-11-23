package competition.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import competitions.ports.RadioException;

public class JdbcConn {
 public String user;
 public String pwd;
 public String connString;

 public JdbcConn(String user, String pwd, String connString) {
  this.user = user;
  this.pwd = pwd;
  this.connString = connString;
 }

 public Connection connection() {
  String url = this.connString;
  String user = this.user;
  String password = this.pwd;
  try {
   return DriverManager.getConnection(url, user, password);
  } catch (SQLException e) {
   throw new RadioException(e);
  }
 }

}