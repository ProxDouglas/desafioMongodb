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
//        ts.threadingTopics();


/**
 * Apresentação
 */

//        ts.criarGrupo();
//        ts.addGrupo();
//        ts.feed();
//        ts.listUsuarios();
//        Nino segue Arthur
        ts.Seguir("62b090203d05a9160bd87035","62b082750748947acd0d20e3");
        //Marcos Bento segue Nino Silva
//        ts.Seguir("62b08f9f476563214508afc4","62b090203d05a9160bd87035");
//        ts.listGroup();

//        ts.feed();

//        ts.comentarPublicacao();
//        ts.threadingTopics();
//
//        ts.descurtirPublicacao();




    }

}
