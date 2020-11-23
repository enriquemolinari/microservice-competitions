package competition.model;

import competitions.ports.Event;
import competitions.ports.ListenerRepository;
import competitions.ports.RadioCompetition;
import competitions.ports.RadioProgram;

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
