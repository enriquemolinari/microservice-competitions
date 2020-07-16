package competition.model;

public class PublishInscriptionAdded implements RadioProgram {

 private String EVENT_NAME = "inscription-added";
 private RadioProgram radioProgram;
 private Event event;

 public PublishInscriptionAdded(RadioProgram radioProgram,
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
  event.publish(EVENT_NAME,
    new InscriptionAdded(idCompetitor, "unemail@gmail.com",
      "Inscription Added Successfully",
      "You have been enrolled successfully").toJson());
 }
}
