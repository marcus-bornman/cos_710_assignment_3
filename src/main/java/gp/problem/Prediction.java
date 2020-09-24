package gp.problem;

import ec.gp.GPData;
import model.DischargeDecision;

/**
 * This class is used by the tree to carry prediction data between nodes. Specifically,
 * the prediction contains a single field in the form of the patient's discharge decision.
 */
public class Prediction extends GPData {
	/**
	 * The decision to take regarding the patients discharge.
	 */
	public DischargeDecision dischargeDecision;

	@Override
	public void copyTo(final GPData gpd) {
		((Prediction) gpd).dischargeDecision = dischargeDecision;
	}
}
