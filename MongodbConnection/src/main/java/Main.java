import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import connections.Connect;
import connections.OperationsUsuario;
import model.Usuario;
import org.bson.Document;

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

    }

}
