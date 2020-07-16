package competition.model;

import java.util.Map;

import com.google.gson.Gson;

public class InscriptionAdded {
 private int idCompetitor;
 private String email;
 
 public InscriptionAdded(int idCompetitor, String email) {
  this.idCompetitor = idCompetitor;
  this.email = email;
 }
 
 public String toJson() {
//  Map<Object,Object> attributes = gson.fromJson(gson.toJson(value)),Map.class);
  return new Gson().toJson(Map.of("id", idCompetitor, "email", this.email));
 }
}
