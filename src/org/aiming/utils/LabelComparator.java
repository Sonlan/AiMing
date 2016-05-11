package org.aiming.utils;

import java.util.Comparator;

import org.aiming.entity.Label;

public class LabelComparator implements Comparator<Label> {

	@Override
	public int compare(Label o1, Label o2) {
		
		int flag = TimeRevert.toLong(o1.getCumulative_time()).compareTo(TimeRevert.toLong(o2.getCumulative_time()));
		if(0<flag){
			//降序排列
			return -1;
		}else return flag;
	}

}
