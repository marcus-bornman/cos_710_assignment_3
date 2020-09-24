package gp.nodes;

import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import gp.problem.Prediction;
import model.DischargeDecision;

/**
 * This class models the leaf node that results in a prediction that the patient should be sent to general hospital floor.
 */
public class A extends GPNode {
	@Override
	public String toString() {
		return "A";
	}

	@Override
	public void eval(EvolutionState evolutionState, int i, GPData gpData, ADFStack adfStack, GPIndividual gpIndividual, Problem problem) {
		Prediction data = ((Prediction) (gpData));

		data.dischargeDecision = DischargeDecision.A;
	}
}
