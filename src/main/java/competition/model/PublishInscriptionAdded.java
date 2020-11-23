package competition.model;

import competitions.model.ports.Event;
import competitions.model.ports.ListenerRepository;
import competitions.model.ports.RadioCompetition;
import competitions.model.ports.RadioProgram;

public class PublishInscriptionAdded implements RadioProgram {

 private String EVENT_NAME = "inscription-added";
 private RadioProgram radioProgram;
 private Event event;
 private ListenerRepository listener;

 public PublishInscriptionAdded(RadioProgram radioProgram,
   ListenerRepository listener, Event notification) {
  this.radioProgram = radioProgram;
  this.event = notification;
  this.listener = listener;
 }

 @Override
 public Iterable<RadioCompetition> availableCompetitions() {
  return this.radioProgram.availableCompetitions();
 }

 @Override
 public void addInscription(int idCompetition, int idCompetitor) {
  this.radioProgram.addInscription(idCompetition, idCompetitor);
  event.publish(EVENT_NAME,
    new InscriptionAdded(idCompetitor,
      this.listener.listenerEmail(idCompetitor),
      "Inscription Added Successfully",
      "You have been enrolled successfully").toJson());
 }
}
