package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PatientReader {
	public List<Patient> readFromFile(String filename) throws IOException {
		List<Patient> patients = new ArrayList<>();
		InputStream inputStream = getClass().getResourceAsStream(filename);

		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		String line;
		while ((line = br.readLine()) != null) {
			String[] data = line.split(",", -1);

			Temperature internalTemperature = Temperature.valueOf(data[0]);
			Temperature surfaceTemperature = Temperature.valueOf(data[1]);
			Saturation oxygenSaturation = Saturation.valueOf(data[2]);
			BloodPressure bloodPressure = BloodPressure.valueOf(data[3]);
			Stability surfaceTemperatureStability = Stability.valueOf(data[4]);
			Stability internalTemperatureStability = Stability.valueOf(data[5]);
			Stability bloodPressureStability = Stability.valueOf(data[6]);
			DischargeDecision dischargeDecision = DischargeDecision.valueOf(data[8]);

			Patient patient = new Patient(internalTemperature, surfaceTemperature, oxygenSaturation, bloodPressure,
					surfaceTemperatureStability, internalTemperatureStability, bloodPressureStability, dischargeDecision);
			patients.add(patient);
		}

		return patients;
	}
}
