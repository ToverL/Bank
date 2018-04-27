package com.oracle.vo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * ÊÇ·ñ·µ»ØÖ÷¼ü
 * @author Áõ¼ÑÎ°
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Identity {
	boolean value( ) default false;
	
}
