package connections;


import com.mongodb.MongoException;
import com.mongodb.client.*;
import com.mongodb.client.model.*;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import model.Usuario;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import static com.mongodb.client.model.Filters.eq;

import java.util.Arrays;

public class OperationsUsuario {

    private MongoDatabase db;
    private MongoCollection<Document> collection;

    public MongoDatabase getDB() {return this.db;}

    public MongoCollection<Document> getCollection() {return this.collection;}

    public OperationsUsuario(MongoDatabase db, String collection) {
        this.db = db;
        this.collection = getDB().getCollection(collection);
    }

    public void inserirUsuario(Usuario usuario){


        MongoCollection<Document> collection = getCollection();

        try {
            InsertOneResult result = collection.insertOne(new Document()
                    .append("_id", new ObjectId())
                    .append("nome", usuario.getNome())
                    .append("departamento", usuario.getDepartamento())
                    .append("cargo", usuario.getCargo())
                    .append("email", usuario.getEmail())
                    .append("data_nascimento", usuario.getData_nascimento())
                    .append("teleone", usuario.getTelefone())
                    .append("admin", usuario.isAdmin())
                    .append("foto", usuario.getFoto())
                    .append("senha", usuario.getSenha())
                    .append("seguindo", Arrays.asList())
                    .append("seguindo_num", 0)
                    .append("seguidores", Arrays.asList())
                    .append("seguidores_num", 0)
                    .append("grupos", Arrays.asList()));

            System.out.println("Success! Inserted document id: " + result.getInsertedId());
        } catch (MongoException me) {
            System.err.println("Unable to insert due to an error: " + me);
        }
    }


    public void seguirPerfil(String idUsuario, String nomeUsuario
                            , String idP, String nomeP){

        MongoCollection<Document> collection = getCollection();

        Document query = new Document().append("_id", new ObjectId(idUsuario));

        Bson updates = Updates.combine(
                Updates.set("seguindo_num",  1),
                Updates.addToSet("seguindo", idP));

        UpdateOptions options = new UpdateOptions().upsert(true);

        try {
            UpdateResult result = collection.updateOne(query, updates, options);

            System.out.println("Modified document count: " + result.getModifiedCount());

            System.out.println("Upserted id: " + result.getUpsertedId()); // only contains a value when an upsert is performed
        } catch (MongoException me) {
            System.err.println("Unable to update due to an error: " + me);
        }
    }

    public void deleteByID(ObjectId _id){
        MongoCollection<Document> collection = getCollection();

        DeleteResult res = collection.deleteOne(new Document(("_id"), _id));

        if(res.getDeletedCount() == 1){
            System.out.println("Excluido ------------");
        }else if(res.getDeletedCount() > 1){
            System.out.println("Deu ruim ------------");
        }else{
            System.out.println("Não possivel ------------");
        }
    }

    private void desconectar(MongoCursor<Document> cursor){
        cursor.close();
    }

    public void listarUsuarios(){

        MongoCollection<Document> collection = getCollection();

        if(collection.countDocuments() > 0){
            MongoCursor<Document> cursor = collection.find().iterator();


            try{
                System.out.println("Listando...");
                System.out.println("------------");
                while(cursor.hasNext()){
                    String json = cursor.next().toJson();

                    System.out.println(json);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            desconectar(cursor);


        }

    }


}



//mongodump --archive --db=case_rede_social | mongorestore --archive  --nsFrom='case_rede_social.*' --nsTo='case_rede_social_backup.*'

