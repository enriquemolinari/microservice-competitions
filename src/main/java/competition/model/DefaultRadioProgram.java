package competition.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import competitions.model.ports.CompetitionRepository;
import competitions.model.ports.RadioCompetition;
import competitions.model.ports.RadioException;
import competitions.model.ports.RadioProgram;

public class DefaultRadioProgram implements RadioProgram {

 private CompetitionRepository repository;

 public DefaultRadioProgram(CompetitionRepository repository) {
  this.repository = repository;
 }

 @Override
 public Iterable<RadioCompetition> availableCompetitions() {
  List<RadioCompetition> cs = repository.competitionsForInscription();

  return cs.stream().map(d -> new RadioCompetition() {
   @Override
   public int id() {
    return d.id();
   }

   @Override
   public String description() {
    return d.description();
   }

   @Override
   public String rules() {
    return d.rules();
   }

   @Override
   public LocalDateTime startDate() {
    return d.startDate();
   }

   @Override
   public LocalDateTime inscriptionStartDate() {
    return d.inscriptionStartDate();
   }

   @Override
   public LocalDateTime inscriptionEndDate() {
    return d.inscriptionEndDate();
   }
  }).collect(Collectors.toList());
 }

 @Override
 public void addInscription(int idCompetition, int idCompetitor) {
  RadioCompetition competition = repository.competitionBy(idCompetition)
    .orElseThrow(
      () -> new RadioException("Selected competition does not exists..."));

  repository.addInscription(idCompetitor, idCompetition,
    competition.pointsForCompetitor());
 }
}
