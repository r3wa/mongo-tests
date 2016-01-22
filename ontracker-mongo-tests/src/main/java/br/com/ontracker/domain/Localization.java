package br.com.ontracker.domain;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.MULTI_LINE_STYLE;

import java.util.Date;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

@Entity("localization")
public class Localization {

	@Id
	public ObjectId id;


	public double latitude ;
	public double longitude;
	public boolean ignition;
	public boolean panic;
	public boolean blockade;
	public double speed;
	public int transmissionReason;
	public Date date = new Date();

	public GPS gps = new GPS();
	public GPRS gprs = new GPRS();
	public Monitor monitor = new Monitor();
	public Driver driver = new Driver();


	@Reference
	public Vehicle vechile;





	@Override
	public String toString() {
		return reflectionToString(this, MULTI_LINE_STYLE);
	}


}
