package competition.model;

import java.util.Map;

import com.google.gson.Gson;

public class InscriptionAdded {
 private static final String MSG_BODY = "msg-body";
 private static final String MSG_TITLE = "msg-title";
 private static final String EMAIL_ADDRESS = "email";
 private static final String ID = "id";
 private int idCompetitor;
 private String email;
 private String msgTitle;
 private String msgBody;

 public InscriptionAdded(int idCompetitor, String email, String msgTitle,
   String msgBody) {
  this.idCompetitor = idCompetitor;
  this.email = email;
  this.msgTitle = msgTitle;
  this.msgBody = msgBody;
 }

 public String toJson() {
  return new Gson().toJson(Map.of(ID, idCompetitor, EMAIL_ADDRESS,
    this.email, MSG_TITLE, this.msgTitle, MSG_BODY, this.msgBody));
 }
}
