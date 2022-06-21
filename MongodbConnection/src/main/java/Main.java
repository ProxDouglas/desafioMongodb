/**
 * Observações sobre o codigo
 *   1 - Não utilizar as funções de deletar
 *
 *
 */




import com.mongodb.client.MongoDatabase;
import connections.Connect;


public class Main {

    public static void main(String[] args){

        Connect cnt = new Connect();
        MongoDatabase db = cnt.iniciandoBD();


        Testes ts = new Testes(db);
//        ts.update();
//        ts.inserirUsuario();
//        ts.testeBusca();
//        ts.deleteById();
//        ts.findById();
//        ts.findName();
//        ts.acessandoSessao();
//        ts.getByIdSessao();
//        ts.listarLogsUser();
//        ts.criarPublicacao();
//        ts.descurtirPublicacao();
//        ts.comentarPublicacao();
//        ts.feed();
        ts.threadingTopics();

//        String str ="2022-06-18T21:36:01.52";
//        LocalDateTime localDateTime = LocalDateTime.parse(str);
//        System.out.println("LocalDateTime obj: "+localDateTime);



    }

}
