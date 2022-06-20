package connections;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import model.Grupo;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.Arrays;

import static com.mongodb.client.model.Filters.eq;

public class OperationsGrupo {


    private MongoDatabase db;
    private MongoCollection<Document> collection;

    public MongoDatabase getDB() {return this.db;}

    public MongoCollection<Document> getCollection() {return this.collection;}

    public OperationsGrupo(MongoDatabase db, String collection) {
        this.db = db;
        this.collection = getDB().getCollection(collection);
    }



    public void inserirUsuario(Grupo grupo){


        MongoCollection<Document> collection = getCollection();

        try {
            InsertOneResult result = collection.insertOne(new Document()
                    .append("_id", new ObjectId())
                    .append("id_adm", grupo.getId_adm())
                    .append("nome", grupo.getNome())
                    .append("numero_membros", 0)
                    .append("usuarios", Arrays.asList()));

            System.out.println("Success! Inserted document id: " + result.getInsertedId());
        } catch (MongoException me) {
            System.err.println("Unable to insert due to an error: " + me);
        }
    }

    //Por favor não usar delete
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

    public void novoMembro(String id_usuario, String id_grupo){

        Grupo grupo = new Grupo();

        Document query = new Document().append("_id", new ObjectId(id_grupo));

        grupo = getById(new ObjectId(id_grupo));

        Bson updates = Updates.combine(
                Updates.set("nummero_membros", grupo.getNumero_membros() + 1),
                Updates.addToSet("genres", "Sports"));

        UpdateOptions options = new UpdateOptions().upsert(true);

        try {
            UpdateResult result = getCollection().updateOne(query, updates, options);

            System.out.println("Modified document count: " + result.getModifiedCount());

            System.out.println("Upserted id: " + result.getUpsertedId()); // only contains a value when an upsert is performed
        } catch (MongoException me) {
            System.err.println("Unable to update due to an error: " + me);
        }

    }

    public Grupo getById(ObjectId _id){

        Document doc = getCollection().find(eq("_id", _id)).first();

        return getByDoc(doc);
    }

    private Grupo getByDoc(Document doc){
        Grupo grupo = new Grupo();

        try {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(doc.toJson());


            grupo.set_id(new ObjectId(((JSONObject) jsonObject.get("_id")).get("$oid").toString()));
            grupo.setNumero_membros(Integer.parseInt(jsonObject.get("numero_membros").toString()));

            JSONArray seguindores = (JSONArray) jsonObject.get("seguidores");
            grupo.setSeguidores(seguindores);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return grupo;
    }




}
