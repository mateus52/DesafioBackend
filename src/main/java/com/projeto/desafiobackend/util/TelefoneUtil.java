package com.projeto.desafiobackend.util;

public class TelefoneUtil {

	 public static String aplicarMascaraTelefone(String numero, String tipo) {
	        if (tipo == "CELULAR") {
	            return numero.replaceAll("(\\d{2})(\\d{5})(\\d{4})", "($1) $2-$3");
	        } else {
	            return numero.replaceAll("(\\d{2})(\\d{4})(\\d{4})", "($1) $2-$3");
	        }
	    }
}
