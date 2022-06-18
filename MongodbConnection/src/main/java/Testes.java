import com.mongodb.client.MongoDatabase;
import connections.OperationsUsuario;
import model.Usuario;
import org.bson.types.ObjectId;

public class Testes {

    private MongoDatabase db;

    public MongoDatabase getDB() {return db;}

    public Testes(MongoDatabase db) {
        this.db = db;
    }

    public void inserirUsuario(){
//        Usuario us = new Usuario("Ándre da Silva", "TI", "Developer", "andre@gmail.com",
//                "16/10/1995", "11 99999-8888", false,
//                "https://upload.wikimedia.org/wikipedia/commons/6/6b/Taka_Shiba.jpg", "123456789");

        Usuario us = new Usuario("Rafael Ferrari", "TI", "Security Master", "rafael@gmail.com",
                "16/09/2000", "11 99999-8888", true,
                "https://upload.wikimedia.org/wikipedia/commons/6/6b/Taka_Shiba.jpg", "123456789");

        OperationsUsuario opu = new OperationsUsuario(getDB(), "teste");

        opu.inserirUsuario(us);
    }

    public void update(){
        OperationsUsuario opu = new OperationsUsuario(getDB(), "teste");

        opu.updateSeguidor("62ae312a4314623d8f9553b6",  "62ace0054f2ef974fe6fd299");

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






}
