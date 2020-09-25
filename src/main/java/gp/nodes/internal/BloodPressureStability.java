package gp.nodes.internal;

import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import gp.problem.PostOperativeProblem;

/**
 * This class models the blood pressure stability decision node. It accepts 3 children and acts as follows:
 * - if the patient's blood pressure stability is 'unstable' it evaluates the 1st child.
 * - if the patient's blood pressure stability is 'moderate' it evaluates the 2nd child.
 * - if the patient's blood pressure stability is 'stable' it evaluates the 3rd child.
 */
public class BloodPressureStability extends GPNode {
	@Override
	public String toString() {
		return "BPS";
	}

	@Override
	public int expectedChildren() {
		return 3;
	}

	@Override
	public void eval(EvolutionState evolutionState, int i, GPData gpData, ADFStack adfStack, GPIndividual gpIndividual, Problem problem) {
		PostOperativeProblem postOperativeProblem = ((PostOperativeProblem) (problem));

		switch (postOperativeProblem.patient.bloodPressureStability) {
			case unstable:
				children[0].eval(evolutionState, i, gpData, adfStack, gpIndividual, problem);
				break;
			case moderate:
				children[1].eval(evolutionState, i, gpData, adfStack, gpIndividual, problem);
				break;
			case stable:
				children[2].eval(evolutionState, i, gpData, adfStack, gpIndividual, problem);
				break;
		}
	}
}
