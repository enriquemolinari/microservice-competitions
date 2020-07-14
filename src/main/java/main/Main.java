package main;

import java.util.Objects;

import competition.model.DefaultRadioProgram;
import competition.persistence.JdbcCompetitionRepository;
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

  new Web(
    new DefaultRadioProgram(
      new JdbcCompetitionRepository(user, pass, connString)
    ), 7000).start();
 }
}
