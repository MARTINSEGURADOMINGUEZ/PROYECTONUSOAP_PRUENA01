package com.example.martin.proyectonusoap_pruena01.DAO;

import com.example.martin.proyectonusoap_pruena01.BEAN.PersonaBean;

import org.json.JSONArray;
import org.json.JSONObject;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public class PersonaDAO 
{
	ArrayList<PersonaBean> lista=null;

	String namespace="http://examenfinal2016.esy.es/PROYECTOUNIFICADONUSOAPWS/";

	String url="http://examenfinal2016.esy.es/PROYECTOUNIFICADONUSOAPWS/CONTROLADOR/PersonaControladorWS.php";

	String soapaction="http://examenfinal2016.esy.es/PROYECTOUNIFICADONUSOAPWS/CONTROLADOR/PersonaControladorWS.php/ListarPersonas";

    String metodo="ListarPersonas";

	String ruta="http://examenfinal2016.esy.es/PROYECTOUNIFICADONUSOAPWS/CONTROLADOR/PersonaControladorWS.php#";


	public ArrayList<PersonaBean> ListarPersonas()
	{        String TRAMA;
	  try 
	  {

		  SoapObject  request= new SoapObject(namespace, metodo);

		    SoapSerializationEnvelope  referencia=new SoapSerializationEnvelope(SoapEnvelope.VER11);

		  referencia.dotNet=true;
		    referencia.setOutputSoapObject(request);
		    HttpTransportSE  transporte=new HttpTransportSE(url);
	
	        transporte.call(soapaction,referencia);
	        SoapPrimitive  response=(SoapPrimitive)referencia.getResponse();
	     
		    TRAMA=response.toString();
		    
		    // Parseamos la respuesta obtenida del servidor a un objeto JSON    
		    JSONObject jsonObject = new JSONObject(TRAMA);
		    
		    JSONArray personas = jsonObject.getJSONArray("PERSONA");
		    
		    lista=new ArrayList<PersonaBean>();
		    //
		  for(int i = 0; i < personas.length(); i++)
		  {  	JSONObject worker =personas.getJSONObject(i);
			  PersonaBean   objPersona=new PersonaBean();
			  objPersona.setCODPERSO(worker.getString("CODPERSO"));
			  objPersona.setNOMBPERSO(worker.getString("NOMBPERSO"));
			  objPersona.setAPELLIPERSO(worker.getString("APELLIPERSO"));
			  lista.add(objPersona);
		  }
			                  
		} catch (Exception e)
		{
			
		}
		return  lista;
	}
	
	
	
}
