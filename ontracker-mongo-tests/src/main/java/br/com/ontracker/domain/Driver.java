package br.com.ontracker.domain;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.MULTI_LINE_STYLE;

public class Driver {

	public String name = "Robson";




	@Override
	public String toString() {
		return reflectionToString(this, MULTI_LINE_STYLE);
	}
}
