package model;

/**
 * This class models a single record from the Postoperative Patient Data.
 * <p>
 * See https://archive.ics.uci.edu/ml/datasets/Post-Operative+Patient for more information regarding the data set.
 */
public class Patient {
	/**
	 * L-CORE (patient's internal temperature in C):
	 * high (> 37), mid (>= 36 and <= 37), low (< 36)
	 */
	public final LevelMeasure internalTemperature;

	/**
	 * L-SURF (patient's surface temperature in C):
	 * high (> 36.5), mid (>= 36.5 and <= 35), low (< 35)
	 */
	public final LevelMeasure surfaceTemperature;

	/**
	 * L-O2 (oxygen saturation in %):
	 * excellent (>= 98), good (>= 90 and < 98),
	 * fair (>= 80 and < 90), poor (< 80)
	 */
	public final QualityMeasure oxygenSaturation;

	/**
	 * L-BP (last measurement of blood pressure):
	 * high (> 130/90), mid (<= 130/90 and >= 90/70), low (< 90/70)
	 */
	public final LevelMeasure bloodPressure;

	/**
	 * SURF-STBL (stability of patient's surface temperature):
	 * stable, moderate, unstable
	 */
	public final StabilityMeasure surfaceTemperatureStability;

	/**
	 * CORE-STBL (stability of patient's core temperature)
	 * stable, moderate, unstable
	 */
	public final StabilityMeasure internalTemperatureStability;

	/**
	 * BP-STBL (stability of patient's blood pressure)
	 * stable, moderate, unstable
	 */
	public final StabilityMeasure bloodPressureStability;

	/**
	 * decision ADM-DECS (discharge decision):
	 * I (patient sent to Intensive Care Unit),
	 * S (patient prepared to go home),
	 * A (patient sent to general hospital floor)
	 */
	public final DischargeDecision dischargeDecision;

	/**
	 * The default constructor for this class. Will most likely used to instantiate instances of this class
	 * from a data file.
	 */
	public Patient(LevelMeasure internalTemperature, LevelMeasure surfaceTemperature, QualityMeasure oxygenSaturation, LevelMeasure bloodPressure, StabilityMeasure surfaceTemperatureStability, StabilityMeasure internalTemperatureStability, StabilityMeasure bloodPressureStability, DischargeDecision dischargeDecision) {
		this.internalTemperature = internalTemperature;
		this.surfaceTemperature = surfaceTemperature;
		this.oxygenSaturation = oxygenSaturation;
		this.bloodPressure = bloodPressure;
		this.surfaceTemperatureStability = surfaceTemperatureStability;
		this.internalTemperatureStability = internalTemperatureStability;
		this.bloodPressureStability = bloodPressureStability;
		this.dischargeDecision = dischargeDecision;
	}
}
