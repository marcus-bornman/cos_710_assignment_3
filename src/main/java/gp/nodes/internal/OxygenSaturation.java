package gp.nodes.internal;

import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import gp.problem.PostOperativeProblem;

/**
 * This class models the oxygen saturation decision node. It accepts 4 children and acts as follows:
 * - if the patient's oxygen saturation is 'poor' it evaluates the 1st child.
 * - if the patient's oxygen saturation is 'fair' it evaluates the 2nd child.
 * - if the patient's oxygen saturation is 'good' it evaluates the 3rd child.
 * - if the patient's oxygen saturation is 'excellent' it evaluates the 4th child.
 */
public class OxygenSaturation extends GPNode {
	@Override
	public String toString() {
		return "OS";
	}

	@Override
	public int expectedChildren() {
		return 4;
	}

	@Override
	public void eval(EvolutionState evolutionState, int i, GPData gpData, ADFStack adfStack, GPIndividual gpIndividual, Problem problem) {
		PostOperativeProblem postOperativeProblem = ((PostOperativeProblem) (problem));

		switch (postOperativeProblem.patient.oxygenSaturation) {
			case poor:
				children[0].eval(evolutionState, i, gpData, adfStack, gpIndividual, problem);
				break;
			case fair:
				children[1].eval(evolutionState, i, gpData, adfStack, gpIndividual, problem);
				break;
			case good:
				children[2].eval(evolutionState, i, gpData, adfStack, gpIndividual, problem);
				break;
			case excellent:
				children[3].eval(evolutionState, i, gpData, adfStack, gpIndividual, problem);
				break;
		}
	}
}
