package org.aiming.utils;

import java.util.Comparator;

import org.aiming.entity.Label;

public class LabelComparator implements Comparator<Label> {

	@Override
	public int compare(Label o1, Label o2) {
		if(o1.getWashRemain()==o2.getWashRemain()) return TimeRevert.toLong(o1.getAliveTime()).compareTo(TimeRevert.toLong(o2.getAliveTime()));
		else return o1.getWashRemain()-o2.getWashRemain();
	}

}
