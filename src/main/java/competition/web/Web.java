package competition.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import competition.model.RadioCompetition;
import competition.model.RadioException;
import competition.model.RadioProgram;
import io.javalin.Javalin;
import io.javalin.http.Handler;

public class Web {

 private RadioProgram radioProgram;
 private int webPort;

 public Web(RadioProgram radio, int webPort) {
  this.radioProgram = radio;
  this.webPort = webPort;
 }

 public void start() {
  Javalin app = Javalin.create().start(this.webPort);
  app.get("/competitions", allCompetitions());
  app.post("/inscription", addInscription());
  
  app.exception(RadioException.class, (e, ctx) -> {
   ctx.json(Map.of("result", "error", "message", e.getMessage()));
   // log error in a stream...
  });

  app.exception(Exception.class, (e, ctx) -> {
   ctx.json(Map.of("result", "error", "message", "Ups, somethong went wrong"));
   e.printStackTrace();
   //log error in a stream...
  });
 }

 private Handler addInscription() {
  return ctx -> {
   InscriptionData dto = ctx.bodyAsClass(InscriptionData.class);
   radioProgram.addInscription(dto.getIdCompetition(), dto.getIdCompetitor());
   ctx.json(Map.of("result","success"));
  };
 }

 private Handler allCompetitions() {
  return ctx -> {
   var competitions = radioProgram.availableCompetitions();
   
   var list = new ArrayList<Map<String, String>>();

   for (RadioCompetition c : competitions) {
    var toJson = new HashMap<String, String>();
    toJson.put("id", String.valueOf(c.id()));
    toJson.put("description", c.description());
    toJson.put("rules", c.rules());
    toJson.put("inscriptions-start-date",
      c.inscriptionStartDate().toString());
    toJson.put("inscriptions-end-date", c.inscriptionEndDate().toString());
    toJson.put("start-date", c.startDate().toString());
    list.add(toJson);
   }
   ctx.json(Map.of("result", "success", "competitions", list));
  };
 }
}
