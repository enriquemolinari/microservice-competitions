package competitions.ports;

public interface RadioProgram {

	Iterable<RadioCompetition> availableCompetitions();

	void addInscription(int idCompetition, int idCompetitor);
}
