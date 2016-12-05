package com.example.martin.proyectonusoap_pruena01;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.martin.proyectonusoap_pruena01.BEAN.PersonaBean;
import com.example.martin.proyectonusoap_pruena01.DAO.PersonaDAO;
import com.example.martin.proyectonusoap_pruena01.UTIL.Personalizacion;

import org.json.JSONArray;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

public class ClienteWS extends Activity  {

    private static final String SOAP_ACTION = "http://examenfinal2016.esy.es/PROYECTOUNIFICADONUSOAPWS/CONTROLADOR/PersonaControladorWS.php/ObtenerMensaje";
    private static final String METHOD_NAME = "ListarPersonas";
    private static final String NAMESPACE = "http://examenfinal2016.esy.es/PROYECTOUNIFICADONUSOAPWS/";
    private static final String URL = "http://examenfinal2016.esy.es/PROYECTOUNIFICADONUSOAPWS/CONTROLADOR/PersonaControladorWS.php";
    ListView LSTPERSONAS;
    ArrayList<PersonaBean> listado = null;
    PersonaDAO objpersonadao=null;
    PersonaBean objpersonabean=null;
    ProgressDialog progressDialog;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        tv = (TextView) findViewById(R.id.textView);
        LSTPERSONAS = (ListView) findViewById(R.id.LSTVW);


        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        listado = ListarPersonas();

        LSTPERSONAS.setAdapter(new Personalizacion(getApplicationContext(), listado));


    }

    public ArrayList<PersonaBean> ListarPersonas() {

        ArrayList<PersonaBean> lista = null;

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(URL);

        try {
            String TRAMA = "";

            httpTransport.call(SOAP_ACTION, envelope);
            Object response = envelope.getResponse();
            //Toast.makeText(getApplicationContext(), "Contenido" +response, Toast.LENGTH_SHORT).show();
            //tv.setText(response.toString() + "\n");

            TRAMA = response.toString();

            // Parseamos la respuesta obtenida del servidor a un objeto JSON
            JSONObject jsonObject = new JSONObject(TRAMA);

            JSONArray personas = jsonObject.getJSONArray("PERSONA");

            lista = new ArrayList<PersonaBean>();
            //
            for (int i = 0; i < personas.length(); i++) {
                JSONObject worker = personas.getJSONObject(i);
                PersonaBean objPersona = new PersonaBean();
                objPersona.setCODPERSO(worker.getString("CODPERSO"));
                objPersona.setNOMBPERSO(worker.getString("NOMBPERSO"));
                objPersona.setAPELLIPERSO(worker.getString("APELLIPERSO"));
                lista.add(objPersona);
            }

            //LSTPERSONAS.setAdapter(new Personalizacion(getApplicationContext(), lista));

        } catch (Exception exception) {
            tv.setText("Error: " + exception.toString());
            LSTPERSONAS.setAdapter(null);

        }

        return lista;

    }

}
