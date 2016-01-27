package br.com.ontracker.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import br.com.ontracker.domain.Driver;
import br.com.ontracker.domain.GPRS;
import br.com.ontracker.domain.GPS;
import br.com.ontracker.domain.Localization;
import br.com.ontracker.domain.Monitor;
import br.com.ontracker.domain.Vehicle;

public class MySQLStore {






	private QueryRunner queryRunner = new QueryRunner();


	private Connection connection = connection();





	public List<Vehicle> vehicles() throws Exception {
		return queryRunner.query(
				connection(),
				"select NULL as id, MODELO as model, PLACA as board, NOME_REF as referenceName, ID_TB_VEICULO_TRACKER as mysqlID from TB_VEICULO_TRACKER ",
				vehicleHandler());
	}












	public List<Localization> localizations(long id) throws Exception {
		return queryRunner.query(
				connection,
				query(id),
				localizationHandler());
	}


































































	private ResultSetHandler<List<Localization>> localizationHandler() {

		return rs -> {

			List<Localization> localizations = new ArrayList<>();


			while (rs.next()) {
		    	  try {
		    		  localizations.add(create(rs));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		return localizations;
	};





















	}









































	private Localization create(ResultSet rs) throws SQLException {
		Localization localization = new Localization();
		localization.blockade = rs.getBoolean("blockade");
		localization.date = rs.getTimestamp("date");
		localization.driver = driver(rs.getString("name"));
		localization.gprs = gprs(rs.getBoolean("gprs"));
		localization.gps = gps(rs.getBoolean("gps"), rs.getLong("hodometer"));
		localization.ignition = rs.getBoolean("ignition");
		localization.latitude = rs.getDouble("latitude");
		localization.longitude = rs.getDouble("longitude");
		localization.monitor = monitor(rs.getDouble("power"));
		localization.panic = rs.getBoolean("panic");
		localization.speed = rs.getDouble("speed");
		localization.transmissionReason = rs.getInt("transmissionReason");

		return localization;
	}










































	private Monitor monitor(double d) {
		Monitor monitor = new Monitor();
		monitor.power = d;

		return monitor;
	}









	private GPS gps(boolean b, long l) {
		GPS gps = new GPS();
		gps.hodometer = l;
		gps.online = b;
		return gps;
	}









	private GPRS gprs(boolean b) {
		GPRS gprs = new GPRS();
		gprs.online = b;
		return gprs;
	}









	private Driver driver(String name) {
		Driver driver = new Driver();
		driver.name = name;
		return driver;
	}









	private String query(long id) {
		String q = new StringBuffer("select ")
		.append("LH.MODULO_POS_LAT as latitude, ")
		.append("LH.MODULO_POS_LONG as longitude, ")
		.append("LH.IGNICAO as ignition, ")
		.append("LH.PANICO as panic, ")
		.append("LH.BLOQUEIO as blockade, ")
		.append("LH.VELOCIDADE as speed, ")
		.append("LH.MOTIVO as transmissionReason," )
		.append("LH.DATA_GERACAO as date, " )
		.append("LH.GPS as gps, ")
		.append("TPG.GPS_HODOMETER as hodometer, ")
		.append("TPG.GPS_FLAG_GPRS_CONNECTION as gprs, ")
		.append("TPH.HM_POWER_SUPPLY as power, ")
		.append("TMT.NOME_MOTORISTA_TRACKER as name ")
		.append("from TB_LOCALIZA_HIST_TRACKER LH ")
		.append("INNER JOIN TB_HIST_POS_GPS TPG ON LH.ID_LOCALIZA_TRACKER = TPG.ID_LOCALIZA_TRACKER ")
		.append("INNER JOIN TB_HIST_POS_HARDWARE_MONITOR TPH ON LH.ID_LOCALIZA_TRACKER = TPH.ID_LOCALIZA_TRACKER ")
		.append("LEFT JOIN TB_MOTORISTA_TRACKER TMT ON LH.CNH_MOTORISTA_TRACKER = TMT.CNH_MOTORISTA_TRACKER ")
		.append("where LH.ID_VEICULO_TRACKER = ")
		.append(id)
		/*.append(" LIMIT 0, 1")*/
		.toString();

		return q;
	}
















































	private BeanListHandler<Vehicle> vehicleHandler() {
		return new BeanListHandler<Vehicle>(Vehicle.class);
	}




























	private Connection connection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			return DriverManager.getConnection("jdbc:mysql://homolog.c6jfzumtfhiy.sa-east-1.rds.amazonaws.com:3306/DB_TRACKER?" + "user=AWSTRACKER&password=ontracker999");

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}


}
