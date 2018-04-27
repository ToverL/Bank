package com.oracle.util;

import java.util.Comparator;

import com.oracle.vo.R_operation;

public class SortByTime<T> implements Comparator<T>{
@Override
public int compare(T o1, T o2) {
	R_operation r1 = (R_operation)o1;
	R_operation r2 = (R_operation)o2;
	return r2.getR_date().compareTo(r1.getR_date());
}
}
