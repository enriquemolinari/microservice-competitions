package main;

import competition.model.DefaultRadioProgram;
import competition.persistence.JdbcCompetitionRepository;
import competition.web.Web;

public class Main {

 public static void main(String[] args) {
  String connString = System.getProperty("conn-string");
  String user = System.getProperty("user");
  String pass = System.getProperty("pwd");

  new Web(
    new DefaultRadioProgram(
      new JdbcCompetitionRepository(user, pass, connString)
      ), 
    7000).start();
 }
}
