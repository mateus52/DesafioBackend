package com.projeto.desafiobackend.util;

public class CpfUtil {

	// Método para mascarar CPF no formato 123.XXX.XXX-00
    public static String mascaraCpf(String cpf) {
        if (cpf == null || cpf.isEmpty()) {
            return cpf;
        }

        // Aplica a máscara no CPF no formato: 123.XXX.XXX-00
        return cpf.substring(0, 3) + ".XXX.XXX-" + cpf.substring(9, 11);
    }
}
