package connections;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.result.InsertOneResult;
import model.LogSessao;

import model.Usuario;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import static com.mongodb.client.model.Filters.*;

public class OperationLogSessao {

    private MongoDatabase db;
    private MongoCollection<Document> collection;

    public MongoDatabase getDB() {return this.db;}

    public MongoCollection<Document> getCollection() {return this.collection;}


    public OperationLogSessao(MongoDatabase db, String collection) {
        this.db = db;
        this.collection = getDB().getCollection(collection);
    }


    public void logEntrou(LogSessao logSessao){
        logSessao.setTipoEntrou();
        inserirLog(logSessao);
    }

    public void logSaiu(LogSessao logSessao){
        logSessao.setTipoSaiu();
        inserirLog(logSessao);
    }


    private void inserirLog(LogSessao logSessao){


        MongoCollection<Document> collection = getCollection();

        try {
            InsertOneResult result = collection.insertOne(new Document()
                    .append("_id", new ObjectId())
                    .append("id_usuario", logSessao.getId_usuario())
                    .append("tipo", logSessao.getTipo())
                    .append("data", logSessao.getData()));

            System.out.println("Success! Inserted document id: " + result.getInsertedId());
        } catch (MongoException me) {
            System.err.println("Unable to insert due to an error: " + me);
        }
    }


    public LogSessao getByIdSessao(ObjectId idUsuario){

        MongoCollection<Document> collection = getCollection();

        Document doc = collection.find(eq("_id", idUsuario)).first();

//        Document doc = collection.find(eq("id_usuario", idUsuario)).first();

        return getByDoc(doc);
    }

    private LogSessao getByDoc(Document doc){
        LogSessao logSessao = new LogSessao();

        try {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(doc.toJson());


            logSessao.set_id(new ObjectId(((JSONObject) jsonObject.get("_id")).get("$oid").toString()));
            logSessao.setId_usuario(jsonObject.get("id_usuario").toString());
            logSessao.setTipo(jsonObject.get("tipo").toString());
            String obj = ((JSONObject) jsonObject.get("data")).get("$date").toString();
            logSessao.setData(LocalDateTime.parse(obj.replaceAll("Z", "")));
//            logSessao.setData(LocalDateTime.parse(jsonObject.get("data").toString()));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return logSessao;
    }


    public void listALlLogUser(String id_usuario){
        MongoCollection<Document> collection = getCollection();

        Bson projectionFields = Projections.fields(
                Projections.include("id_usuario", "tipo", "data"),
                Projections.excludeId());


        LocalDate ldt = LocalDate.parse("2022-06-18");

        MongoCursor<Document> cursor = collection.find(gte("data", ldt))
                .projection(projectionFields)
                .sort(Sorts.descending("id_usuario")).iterator();

//        MongoCursor<Document> cursor = collection.find(Filters.eq("id_usuario", id_usuario)).iterator();

        try {
            while(cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        } finally {
            cursor.close();
        }


    }


}



