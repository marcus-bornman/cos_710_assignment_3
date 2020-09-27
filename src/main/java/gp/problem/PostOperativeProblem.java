package gp.problem;

import ec.EvolutionState;
import ec.Individual;
import ec.gp.GPIndividual;
import ec.gp.GPProblem;
import ec.gp.koza.KozaFitness;
import ec.simple.SimpleProblemForm;
import ec.util.Parameter;
import model.Patient;
import model.PatientReader;

import java.io.IOException;
import java.util.List;

/**
 * This class is used for the genetic programming problem that we are solving.
 */
public class PostOperativeProblem extends GPProblem implements SimpleProblemForm {
	private static final long serialVersionUID = 1;

	/**
	 * The list of patients to use for training.
	 */
	public final List<Patient> trainingPatients;

	/**
	 * The list of patients to use for testing.
	 */
	public final List<Patient> testingPatients;

	/**
	 * The current patient to consider.
	 */
	public Patient patient;

	/**
	 * Reads the training data from training_data.csv to initialise the
	 * problem instance accordingly.
	 *
	 * @throws IOException if the file could not be read successfully.
	 */
	public PostOperativeProblem() throws IOException {
		super();
		PatientReader reader = new PatientReader();
		trainingPatients = reader.readFromFile("/training_data.csv");
		testingPatients = reader.readFromFile("/testing_data.csv");
	}

	@Override
	public void setup(final EvolutionState state, final Parameter base) {
		super.setup(state, base);

		if (!(input instanceof Prediction)) {
			state.output.fatal("GPData class must subclass from " + Prediction.class, base.push(P_DATA), null);
		}
	}

	@Override
	public void evaluate(final EvolutionState state, final Individual ind, final int subpopulation, final int threadnum) {
		if (ind.evaluated) return;

		int correctTrainingPredictions = correctPredictions(state, (GPIndividual) ind, threadnum, trainingPatients);
		int correctTestingPredictions = correctPredictions(state, (GPIndividual) ind, threadnum, testingPatients);

		double incorrectTrainingPredictions = trainingPatients.size() - correctTrainingPredictions;
		((KozaFitness) ind.fitness).setStandardizedFitness(state, incorrectTrainingPredictions);
		((KozaFitness) ind.fitness).hits = correctTestingPredictions;
		ind.evaluated = true;
	}

	private int correctPredictions(EvolutionState state, GPIndividual ind, int threadnum, List<Patient> patients) {
		Prediction prediction = (Prediction) (this.input);

		int correctPredictions = 0;
		for (Patient patient : patients) {
			this.patient = patient;
			ind.trees[0].child.eval(state, threadnum, prediction, stack, ind, this);

			if (prediction.dischargeDecision.equals(patient.dischargeDecision)) correctPredictions++;
		}

		return correctPredictions;
	}
}

