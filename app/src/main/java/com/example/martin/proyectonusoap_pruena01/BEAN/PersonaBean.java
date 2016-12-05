package com.example.martin.proyectonusoap_pruena01.BEAN;

import java.io.Serializable;

public class PersonaBean implements Serializable
{
		String CODPERSO;
        String NOMBPERSO;
        String APELLIPERSO;

		public String getCODPERSO() {
			return CODPERSO;
		}
		public void setCODPERSO(String cODPERSO) {
			CODPERSO = cODPERSO;
		}
		public String getNOMBPERSO() {
			return NOMBPERSO;
		}
		public void setNOMBPERSO(String nOMBPERSO) {
			NOMBPERSO = nOMBPERSO;
		}
		public String getAPELLIPERSO() {
			return APELLIPERSO;
		}
		public void setAPELLIPERSO(String aPELLIPERSO) {APELLIPERSO = aPELLIPERSO; }

}
