package org.aiming.service;

import java.math.BigDecimal;

public interface AirconditionService {
	/**
	 * false空调停止工作
	 * @return
	 */
	public boolean isWork(int index,BigDecimal workMode);
}
