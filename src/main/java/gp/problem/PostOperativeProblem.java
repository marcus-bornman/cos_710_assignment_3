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
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is used for the genetic programming problem that we are solving.
 */
public class PostOperativeProblem extends GPProblem implements SimpleProblemForm {
	private static final long serialVersionUID = 1;

	/**
	 * The list of patients to use.
	 */
	public final List<Patient> patients;

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
		patients = new PatientReader().readFromFile("/training_data.csv");
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

		Prediction prediction = (Prediction) (this.input);

		int correctTrainingPredictions = 0;
		for (Patient patient : patients) {
			this.patient = patient;
			((GPIndividual) ind).trees[0].child.eval(state, threadnum, prediction, stack, ((GPIndividual) ind), this);

			if (prediction.dischargeDecision.equals(patient.dischargeDecision)) correctTrainingPredictions++;
		}

		double fitness = 1 - (((double) correctTrainingPredictions) / patients.size());
		((KozaFitness) ind.fitness).setStandardizedFitness(state, fitness);
		((KozaFitness) ind.fitness).hits = correctTrainingPredictions;
		ind.evaluated = true;
	}
}

