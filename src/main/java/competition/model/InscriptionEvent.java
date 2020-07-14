package competition.model;

public class InscriptionEvent implements RadioProgram {

 private String EVENT_NAME = "inscription-done";
 private RadioProgram radioProgram;
 private Event event;

 public InscriptionEvent(RadioProgram radioProgram,
   Event notification) {
  this.radioProgram = radioProgram;
  this.event = notification;
 }

 @Override
 public Iterable<RadioCompetition> availableCompetitions() {
  return this.radioProgram.availableCompetitions();
 }

 @Override
 public void addInscription(int idCompetition, int idCompetitor) {
  this.radioProgram.addInscription(idCompetition, idCompetitor);
  event.publish(EVENT_NAME, String.valueOf(idCompetitor));
 }
}
