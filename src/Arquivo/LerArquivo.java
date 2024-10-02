package Arquivo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class LerArquivo {

    public static HashMap<String, String> carregarConfiguracoes() {
        HashMap<String, String> configuracoes = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader("configuracoesJogo.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split("=");
                if (partes.length == 2) {
                    configuracoes.put(partes[0], partes[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo de configurações: " + e.getMessage());
        }

        return configuracoes;
    }
}
