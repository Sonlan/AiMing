package org.aiming.utils;

import java.util.Comparator;

import org.aiming.entity.Label;

public class LabelComparator implements Comparator<Label> {

	@Override
	public int compare(Label o1, Label o2) {
		if(o1.getWashing_count()==o2.getWashing_count()) return TimeRevert.toLong(o1.getCumulative_time()).compareTo(TimeRevert.toLong(o2.getCumulative_time()));
		else return o1.getWashing_count()-o2.getWashing_count();
	}

}
