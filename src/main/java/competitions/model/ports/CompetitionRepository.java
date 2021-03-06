package competitions.model.ports;

import java.util.List;
import java.util.Optional;

public interface CompetitionRepository {

	Optional<RadioCompetition> competitionBy(int id);
	
	void addInscription(int idCompetitor, int idCompetition, int additionalPointsForCompetitor)
			throws RadioException;

	List<RadioCompetition> competitionsForInscription()
			throws RadioException;
}
