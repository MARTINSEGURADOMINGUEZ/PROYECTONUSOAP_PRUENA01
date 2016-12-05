package com.example.martin.proyectonusoap_pruena01.UTIL;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.martin.proyectonusoap_pruena01.BEAN.PersonaBean;
import com.example.martin.proyectonusoap_pruena01.R;

import java.util.ArrayList;


public class Personalizacion extends BaseAdapter
{
	ArrayList<PersonaBean> lista;
	LayoutInflater minflater;
	
	public Personalizacion(Context contexto, ArrayList<PersonaBean> lista )
	{
		      this.minflater= LayoutInflater.from(contexto);
		      this.lista=lista;
	}	
	@Override
	public int getCount() 
	{	return  lista.size() ;
	}
	@Override
	public Object getItem(int arg0)
	{	return    lista.get(arg0);
	}
	@Override
	public long getItemId(int arg0) 
	{	return  arg0;
	}
	
	static  class Campos
	{
		TextView LBLCODIGO;
		TextView LBLNOMBRE;
		TextView LBLAPELLIDO;
	}
	
	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
	
		Campos    referencias;
		
		if(arg1==null)
		{	arg1=minflater.inflate(R.layout.grilla,null);
			referencias=new Campos();
			referencias.LBLCODIGO=(TextView)arg1.findViewById(R.id.LBLCODIGO);
			referencias.LBLNOMBRE=(TextView)arg1.findViewById(R.id.LBLNOMBRE);
			referencias.LBLAPELLIDO=(TextView)arg1.findViewById(R.id.LBLAPELLIDO);
			arg1.setTag(referencias);			
		}
		else
		{	referencias =(Campos)arg1.getTag();
		}
		
		referencias.LBLCODIGO.setText(lista.get(arg0).getCODPERSO());
		referencias.LBLNOMBRE.setText(lista.get(arg0).getNOMBPERSO());
		referencias.LBLAPELLIDO.setText(lista.get(arg0).getAPELLIPERSO());
				
		return  arg1;
	}

}
