package br.com.ontracker.domain;

import java.util.Date;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;



public class HistoryTracker {


	@Id
	private ObjectId id;




	public long ID_LOCALIZA_TRACKER = 1;
	public int MOTIVO = 1;
	public String ENDERECO = "end";
	public long ID_MODULO_TRACKER = 1;
	public double MODULO_POS_LAT = 2.0;
	public double MODULO_POS_LONG = 3.0 ;
	public double VELOCIDADE = 5.0;
	public int IGNICAO = 1;
	public int PANICO = 1;
	public int CERCA = 1;
	public int GPS = 1;
	public int SATELITE = 1;
	public String DIRECAO = "dir";
	public int STATUS_VEICULO = 1;
	public String STATUS_MODULO = "status mod";
	public Date DATA_GERACAO = new Date();
	public Date DATA_INCLUSAO = new Date();
	public String MENSAGEM = "mensagem";
	public String PAYLOAD = "payload";
	public long ID_VEICULO_TRACKER = 1;
	public long CNH_MOTORISTA_TRACKER = 1;
	public int  ID_TB_MOTIVO_TRANSMISSAO = 1;
	public String DRIVER_ID = "driver id";


}
