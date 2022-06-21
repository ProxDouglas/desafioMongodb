package connections;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import model.Publicacao;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.time.LocalDateTime;
import java.util.Arrays;

import static com.mongodb.client.model.Filters.eq;

public class OperationsPublicacao {


    private MongoDatabase db;
    private MongoCollection<Document> collection;

    public MongoDatabase getDB() {return this.db;}

    public MongoCollection<Document> getCollection() {return this.collection;}

    public OperationsPublicacao(MongoDatabase db, String collection) {
        this.db = db;
        this.collection = getDB().getCollection(collection);
    }

    public void inserirPublicacao(Publicacao publicacao){

        try {
            InsertOneResult result = getCollection().insertOne(new Document()
                    .append("_id", new ObjectId())
                    .append("idUsuario", publicacao.getIdUsuario())
                    .append("tema", publicacao.getTema())
                    .append("descricao", publicacao.getDescricao())
                    .append("fotoPublicacao", publicacao.getFotoPublicacao())
                    .append("curtida_num", 0)
                    .append("curtidaDetalhe", Arrays.asList())
                    .append("comentarios_num", 0)

                    .append("comentarios", Arrays.asList())
                    .append("data", LocalDateTime.now()));

            System.out.println("Success! Inserted document id: " + result.getInsertedId());
        } catch (MongoException me) {
            System.err.println("Unable to insert due to an error: " + me);
        }
    }


    public void deleteByID(String _id){
        MongoCollection<Document> collection = getCollection();

        DeleteResult res = collection.deleteOne(new Document(("_id"), new ObjectId(_id)));

        if(res.getDeletedCount() == 1){
            System.out.println("Excluido ------------");
        }else if(res.getDeletedCount() > 1){
            System.out.println("Deu ruim ------------");
        }else{
            System.out.println("Não possivel ------------");
        }
    }

    public void curtirPublicacao(String id_publicacao, String id_usuario){

        Document query = new Document().append("_id", new ObjectId(id_publicacao));

        Bson updates = Updates.combine(
                Updates.inc("curtida_num", +1),
                Updates.addToSet("curtidaDetalhe", id_usuario));

        UpdateOptions options = new UpdateOptions().upsert(true);

        try {
            UpdateResult result = getCollection().updateOne(query, updates, options);

            System.out.println("Modified document count: " + result.getModifiedCount());

            System.out.println("Upserted id: " + result.getUpsertedId()); // only contains a value when an upsert is performed
        } catch (MongoException me) {
            System.err.println("Unable to update due to an error: " + me);
        }


    }

    public void descurtirPublicacao(String id_publicacao, String id_usuario){

        Document query = new Document().append("_id", new ObjectId(id_publicacao));

        BasicDBObject match = new BasicDBObject("_id", new ObjectId(id_publicacao)); //to match your direct app document
        BasicDBObject update = new BasicDBObject("curtidaDetalhe", id_usuario);

        getCollection().updateOne(match, new BasicDBObject("$pull", update));

        Bson updates = Updates.combine(
                Updates.inc("curtida_num", -1));

        UpdateOptions options = new UpdateOptions().upsert(true);

        try {
            UpdateResult result = getCollection().updateOne(query, updates, options);

            System.out.println("Modified document count: " + result.getModifiedCount());

            System.out.println("Upserted id: " + result.getUpsertedId()); // only contains a value when an upsert is performed
        } catch (MongoException me) {
            System.err.println("Unable to update due to an error: " + me);
        }
    }


    public Publicacao getByIdNumbers(ObjectId _id){

        Document doc = getCollection().find(eq("_id", _id)).first();

        return getByDocNumbers(doc);
    }



    private Publicacao getByDocNumbers(Document doc){
        Publicacao publicacao = new Publicacao();

        try {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(doc.toJson());


            publicacao.set_id(new ObjectId(((JSONObject) jsonObject.get("_id")).get("$oid").toString()));
            publicacao.setCurtida_num(Integer.parseInt(jsonObject.get("curtida_num").toString()));
            publicacao.setCurtida_num(Integer.parseInt(jsonObject.get("comentarios_num").toString()));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return publicacao;
    }





}
