package com.oracle.util;

import java.util.Comparator;

import com.oracle.vo.R_operation;
import com.oracle.vo.U_operation;

public class SortByTime2<T> implements Comparator<T>{
@Override
public int compare(T o1, T o2) {
	U_operation r1 = (U_operation)o1;
	U_operation r2 = (U_operation)o2;
	return r2.getU_date().compareTo(r1.getU_date());
}
}
