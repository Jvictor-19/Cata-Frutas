package Arquivo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * A classe LerArquivo é responsável por carregar configurações de um arquivo de texto.
 * O arquivo deve conter pares chave-valor no formato "chave=valor", onde cada linha do arquivo 
 * representa uma configuração do jogo.
 */
public class LerArquivo {

    /**
     * Carrega as configurações do jogo a partir do arquivo "configuracoesJogo.txt".
     * O arquivo deve estar no formato "chave=valor", onde cada linha representa uma configuração.
     *
     * @return Um HashMap contendo as configurações do jogo. As chaves são os nomes das configurações
     *         e os valores são as definições correspondentes. Se ocorrer um erro ao ler o arquivo,
     *         uma mensagem de erro será exibida no console e o HashMap retornado será vazio.
     */

	public static HashMap<String, String> carregarConfiguracoes() {
	    HashMap<String, String> configuracoes = new HashMap<>();


	    // Obtém o diretório do usuário
	    String userDir = System.getProperty("user.home");
	    // Define o caminho para o arquivo de configuração no diretório do usuário
	    File configFile = new File(userDir, "configuracaoJogo.txt");

	    try (BufferedReader br = new BufferedReader(new FileReader(configFile))) {
	        String linha;
	        while ((linha = br.readLine()) != null) {
	            String[] partes = linha.split(":");
	            if (partes.length == 2) {
	                configuracoes.put(partes[0].trim(), partes[1].trim());
	            }
	        }
	    } catch (IOException e) {
	        System.out.println("Erro ao ler o arquivo de configurações: " + e.getMessage());
	    }

	    return configuracoes;
	}

}
