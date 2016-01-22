package br.com.ontracker.domain;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.MULTI_LINE_STYLE;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("vehicle")
public class Vehicle {



	@Id
	public ObjectId id;

	public String model;
	public String board;
	public String referenceName;
	public long mysqlID;






















	public void setId(ObjectId id) {
		this.id = id;
	}






















	public void setModel(String model) {
		this.model = model;
	}






















	public void setBoard(String board) {
		this.board = board;
	}






















	public void setReferenceName(String referenceName) {
		this.referenceName = referenceName;
	}






















	public void setMysqlID(long mysqlID) {
		this.mysqlID = mysqlID;
	}






















	@Override
	public String toString() {
		return reflectionToString(this, MULTI_LINE_STYLE);
	}

}
