package competition.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import competition.model.api.RadioCompetition;
import competition.model.api.RadioProgram;
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
   ctx.json(list);
  };
 }
}
