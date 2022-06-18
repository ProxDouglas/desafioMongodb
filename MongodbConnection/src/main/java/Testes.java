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
        Usuario us = new Usuario("Ándre da Silva", "TI", "Developer", "andre@gmail.com",
                "16/10/1995", "11 99999-8888", 0,
                "https://upload.wikimedia.org/wikipedia/commons/6/6b/Taka_Shiba.jpg", "123456789");

        OperationsUsuario opu = new OperationsUsuario(getDB(), "teste");

        opu.inserirUsuario(us);
    }

    public void update(){
        OperationsUsuario opu = new OperationsUsuario(getDB(), "teste");

        opu.seguirPerfil("62adf85dea38353826515398", "Andre", "62ace0054f2ef974fe6fd299", "Adriano");

    }


    public void deleteById(){
        OperationsUsuario opu = new OperationsUsuario(getDB(), "teste");

        opu.deleteByID(new ObjectId("62adf7f132506178bf798196"));
    }





}
