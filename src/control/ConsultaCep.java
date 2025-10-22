package control;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import model.Endereco;


/**
 *
 * @author andre
 */
public class ConsultaCep {

  public static Endereco buscarEndereco(String cep) { // O método agora retorna um Endereco
        String json;

        try {
            URL url = new URL("http://viacep.com.br/ws/" + cep + "/json" );
            URLConnection urlConnection = url.openConnection();
            InputStream is = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            StringBuilder jsonSb = new StringBuilder();
            br.lines().forEach(l -> jsonSb.append(l.trim()));
            json = jsonSb.toString();

            // Aqui acontece a mágica da conversão
            Gson gson = new Gson();
            Endereco endereco = gson.fromJson(json, Endereco.class);

            return endereco;

        } catch (JsonSyntaxException | IOException e) {
            // É uma boa prática tratar a exceção de forma mais específica
            // ou relançá-la para que a camada superior da aplicação a trate.
            throw new RuntimeException("Erro ao buscar o CEP: " + e.getMessage(), e);
        }
    }
        
    
}

