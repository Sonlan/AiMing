package org.aiming.service;

import java.math.BigDecimal;
import java.util.Map;

public interface AirconditionService {

	public Map<String, Float> getWorkValue(BigDecimal workMode);
}
