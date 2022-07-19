import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        //pegar os dados do imdb
        //fazer uma conexao HTTP e buscar os tops 250 filmes
        // String url = "https://imdb-api.com/en/API/Top250Movies/k_0ojtoyvm" link de api do professor com a chave dele via imb, https://imdb-api.com/en/API/Top250Movies/chavedepois de registrado do imbd
        // String url = "https://alura-filmes.herouapp.com/conteudos"; // link com json de api criado pelo pessoal do discord no caso a professora da imersao tb
         String url = "https://alura-imdb-api.herokuapp.com/movies";
        //link das "blibliotecas", fazendo as requisiçoes -conexao
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString()); //fazendo uma requisicao e dizendo como eu vou ler os dados- ofString ler uma string
        String body = response.body();

       // extrair só os dados que interessam (titulo, poster, classificação)
       var parser = new JsonParser(); // classe criada para importar o arquivo jsonparse.java
       List<Map<String, String>> listaDeFilmes = parser.parse(body);


        // exibir e manipular os dados , trazendo apenas o titulo,imagem e nota filtro que foi feito perto do array json de todos os dados da api a partir de um mapa(map) criado
        for (Map<String,String> filme : listaDeFilmes) {
            // printando na tela o titulo do filme e a key é o nome como esta no array da api
            // System.out.println(filme.get("title")); 
            System.out.println("\u001b[3mTítulo: \u001b[m\u001b[1m"+ filme.get("title")+ "\u001b[m"); //criou uma legenda e negritou e italicou o titulo 
            // printando na tela imagem do filme
            System.out.println(filme.get("image"));
            // System.out.println(filme.get("imDbRating"));
            // System.out.println();
            //criacao e exibicao da nota colorida e as estrela em baixo da nota ... jeito um 
            // System.out.println("\u001b[45m\u001b[3mClassificação: \u001b[m\u001b[45m\u001b[1m"+ filme.get("imDbRating")+ " \u001b[m");
            // int classificacao = (int) Float.parseFloat(filme.get("imDbRating"));
            // String stars = "";            
            // for(int i = 0; i<classificacao; i++) {
            //     stars = stars + "\u2B50";
            // }
            // System.out.println(stars);
            // System.out.println();
             //criacao e exibicao da nota colorida e as estrela em baixo da nota ... jeito dois
             System.out
                    .println("\u001b[38;5;226m\u001b[48;5;53m Classificação: " + filme.get("imDbRating")
                            + " \u001b[m");
            String sTextOut = "";
            int iStars;
            for (iStars = 0; iStars < Double.valueOf(filme.get("imDbRating")).intValue(); iStars++) {
                sTextOut += "\u2B50\u001b[m";
            }

            if (Integer.valueOf(filme.get("imDbRating").substring(filme.get("imDbRating").indexOf(".") + 1,
                    filme.get("imDbRating").length())) > 0) {
                sTextOut += " \u272E\u001b[m";
                iStars++;
            }

            for (; iStars < 10; iStars++) {
                sTextOut += " \u2730\u001b[m";
            }
            System.out.println(sTextOut);
            System.out.println();
        }

       
    }
}

