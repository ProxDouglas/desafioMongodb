import com.mongodb.client.MongoDatabase;
import connections.*;
import model.*;
import org.bson.types.ObjectId;
import view.Feed;

import java.time.LocalDateTime;

public class Testes {

    private MongoDatabase db;

    private OperationsUsuario opu;

    public MongoDatabase getDB() {return db;}

    public OperationsUsuario getOpu() {return opu;}

    public Testes(MongoDatabase db) {
        this.db = db;
        this.opu= new OperationsUsuario(db, "usuario");
    }


    public void criarGrupo(){

        OperationsGrupo opg = new OperationsGrupo(getDB(), "grupo");

        Grupo grupo = new Grupo();

        grupo.setId_adm("62ac9a742b95e80364fce177");
        grupo.setNome("Devs anonimos");

        opg.inserirGrupo(grupo);

    }

    public void addGrupo(){

        OperationsGrupo opg = new OperationsGrupo(getDB(), "grupo");


        opg.addNovoMembroGrupo("62ac9e3a2b95e80364fce17a" ,"62b1aec0a23d7645806472b5");

    }

    public void listGroup(){

        OperationsGrupo opg = new OperationsGrupo(getDB(), "grupo");

        opg.listGroup();

    }


    public void listUsuarios(){
        OperationsUsuario opu = new OperationsUsuario(getDB(), "usuario");


        opu.allUsuario();
    }

    public void Seguir(String idSeguidor, String idSeguido){
        OperationsUsuario opu = new OperationsUsuario(getDB(), "usuario");


        opu.updateSeguir(idSeguidor, idSeguido);
    }

    public void inserirUsuario(){
        OperationsUsuario opu = new OperationsUsuario(getDB(), "usuario");

//        Usuario us = new Usuario("Ándre da Silva", "TI", "Developer", "andre@gmail.com",
//                "16/10/1995", "11 99999-8888", false,
//                "https://upload.wikimedia.org/wikipedia/commons/6/6b/Taka_Shiba.jpg", "123456789");

//        Usuario us = new Usuario("Rafael Ferrari", "TI", "Security Master", "rafael@gmail.com",
//                "16/09/2000", "11 99999-8888", true,
//                "https://upload.wikimedia.org/wikipedia/commons/6/6b/Taka_Shiba.jpg", "123456789");

        Usuario us = new Usuario("Demetrio", "Tecnologia", "Trainee", "demetrio@gmail.com",
                "20/09/1995", "11 99999-8888", true,
                "https://upload.wikimedia.org/wikipedia/commons/6/6b/Taka_Shiba.jpg", "123456789");



        opu.inserirUsuario(us);
    }

    public void update(){
        OperationsUsuario opu = getOpu();

        opu.updateSeguir("62ae859f91451e44c23c9d60",  "62afadad1b94263347be71ad");

    }


    public void deleteById(){
        OperationsUsuario opu = new OperationsUsuario(getDB(), "teste");

        opu.deleteByID(new ObjectId("62ae312a4314623d8f9553b6"));
    }

    public void findById(){
        OperationsUsuario opu = new OperationsUsuario(getDB(), "teste");

        opu.getById(new ObjectId("62ae312a4314623d8f9553b6"));
    }

    public void findName(){
        OperationsUsuario opu = new OperationsUsuario(getDB(), "teste");

        opu.getByName("Adriano");
    }



    public void acessandoSessao(){

        OperationsUsuario opu = getOpu();

        OperationLogSessao lgs = new OperationLogSessao(getDB(), "logsessao");

        LogSessao lg = new LogSessao();
        lg.setId_usuario("62aded8d2563767d12a2f0c1");
        lg.setData(LocalDateTime.now());

        lgs.logEntrou(lg);
        lgs.logSaiu(lg);

    }

    public void getByIdSessao(){

        OperationLogSessao lgs = new OperationLogSessao(getDB(), "logsessao");

        LogSessao log = lgs.getByIdSessao(new ObjectId("62ae90ca3bbd5325bba81c32"));

        System.out.println(log);


    }

    public void listarLogsUser(){
        OperationLogSessao lgs = new OperationLogSessao(getDB(), "logsessao");

        lgs.listALlLogUser("62aded8d2563767d12a2f0c1");

    }


    public void criarPublicacao(){
        OperationsPublicacao opP = new OperationsPublicacao(getDB(), "publicacao");

        Publicacao publi = new Publicacao();
        publi.setIdUsuario("62b082750748947acd0d20e3");
        publi.setTema("Marco");
        publi.setDescricao("Melhor chefe");
        publi.setFotoPublicacao("https://tse2.mm.bing.net/th/id/OIP.AiOMO8aUk6gp3ck6_WGtWQHaFS?w=239&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7");
        publi.setCurtida_num(0);
        publi.setCurtidaDetalhe(null);
        publi.setComentarios_num(0);
        publi.setData(LocalDateTime.now());


        opP.inserirPublicacao(publi);
    }

    public void curtirPublicacao(){
        OperationsPublicacao opP = new OperationsPublicacao(getDB(), "publicacao");

        opP.curtirPublicacao("62b086c8c8d2cd78245c2969", "62aded8d2563767d12a2f0c1");
    }

    public void descurtirPublicacao(){
        OperationsPublicacao opP = new OperationsPublicacao(getDB(), "publicacao");

        opP.descurtirPublicacao("62b086c8c8d2cd78245c2969", "62aded8d2563767d12a2f0c1");
    }


    public void comentarPublicacao(){
        OparationsComentario opC = new OparationsComentario(getDB(), "publicacao");

        Comentario comentario = new Comentario();
        comentario.setIdUsuario("62aded8d2563767d12a2f0c1");
        comentario.setNomeUsuario("Helena");
        comentario.setTexto("ADM arrasa");

        opC.comentarPublicacao(comentario, "62b0fe7c7372817159597cec");
    }


    public void feed(){
        Feed feed = new Feed(getDB());

        feed.allPublicacao();

    }

    public void threadingTopics(){
        Feed feed = new Feed(getDB());
        feed.threadingTopics();
    }


//    public void deletarComentario(){
//        OparationsComentario opC = new OparationsComentario(getDB(), "publicacao");
//
//        Comentario comentario = new Comentario();
//
//        opC.deleteComentario("62b086c8c8d2cd78245c2969", "62b0cb48f878984eea736a43");
//
//
//
//
//    }






}
