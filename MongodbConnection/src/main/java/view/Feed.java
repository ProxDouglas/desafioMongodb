package view;


import com.mongodb.MongoException;
import com.mongodb.client.DistinctIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;

import org.bson.Document;
import org.bson.conversions.Bson;
import com.google.gson.*;
import org.bson.types.ObjectId;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;

public class Feed {

    private MongoDatabase db;
    private MongoCollection<Document> collectionPub;

    private MongoCollection<Document> collectionUser;

    public MongoDatabase getDB() {return this.db;}

    public MongoCollection<Document> getCollectionPub() {return this.collectionPub;}

    public MongoCollection<Document> getCollectionUser() {return this.collectionUser;}

    public Feed(MongoDatabase db) {
        this.db = db;
        this.collectionPub = getDB().getCollection("publicacao");
        this.collectionUser = getDB().getCollection("usuario");
    }


    public void allPublicacao(){


        Bson projectionFields = Projections.fields(
                Projections.include( "_id","idUsuario", "tema", "fotoPublicacao", "descricao", "comentarios"));

//        MongoCursor<Document> cursor = getCollectionPub().find(and(lt("data", LocalDateTime.now()),  gte("data", yesterday())))
        MongoCursor<Document> cursor = getCollectionPub().find(lt("data", LocalDateTime.now()))
                .projection(projectionFields)
                .sort(Sorts.descending("data")).iterator();

        try {
            List seguindo = getUsuarioSeguindo("62afadad1b94263347be71ad");

            while(cursor.hasNext()) {

                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                JsonElement je = JsonParser.parseString(cursor.next().toJson());
                String prettyJsonString = gson.toJson(je);

                if(!compareIdFromCursor(seguindo, cursor.next().toJson())) {
                    System.out.println(prettyJsonString);
                    System.out.println("==============================");
                }
            }
        } finally {
            cursor.close();
        }
    }

    private LocalDateTime yesterday(){

        return  LocalDateTime.of(
                LocalDateTime.now().getYear(), LocalDateTime.now().getMonth(),
                LocalDateTime.now().getDayOfMonth() - 1, 0, 0);
    }


    private boolean compareIdFromCursor(List seguidos, String obj){
        try {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(obj);

            String idSeguindo = jsonObject.get("idUsuario").toString();
            Boolean adm = Boolean.parseBoolean(jsonObject.get("admin").toString());

            if(seguidos.contains(idSeguindo) || adm){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }



    public List getUsuarioSeguindo(String idUser){

        Bson projectionFields = Projections.fields(
                Projections.include("seguindo"),
                Projections.excludeId());

        Document doc = getCollectionUser().find(eq("_id", new ObjectId(idUser)))
                .projection(projectionFields).first();
//        Document doc = getCollectionUser().find(eq("_id", new ObjectId(idUser))).first();

        if (doc == null) {
            System.out.println("No results found.");
        } else {
//            System.out.println(doc.toJson());
            return getSeguidores(doc);

        }
        return null;

    }


    private List getSeguidores(Document doc){

        JSONArray seguindo;
        List<String> list = null;

        try {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(doc.toJson());

            seguindo = (JSONArray) jsonObject.get("seguindo");
            list = seguindo;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }


    /**
     * Desafio
     *
     */

    public void threadingTopics(){

        List<String> temas = getTemas();


        if(temas != null){
            for(int i =0; i < temas.size(); i++){

                System.out.print(temas.get(i) + ":  ");
                System.out.println(getCollectionPub().countDocuments(and(eq("tema", temas.get(i)))));





            }
        }else{
            System.out.println("Publicações vazias");
        }



    }

    private List getTemas(){
        List<String> temas = new ArrayList<>();

        Bson projectionFields = Projections.fields(
                Projections.include( "tema"));



        try {
//            MongoCursor<Document> cursor = getCollectionPub().find(and(lt("data", LocalDateTime.now()),  gte("data", yesterday())))
            DistinctIterable<String> docs = getCollectionPub().distinct("tema", String.class);
            MongoCursor<String> cursor = docs.iterator();

            while(cursor.hasNext()) {
                String teste = cursor.next();
//                System.out.println(teste);
                temas.add(getTema(teste));

            }
        }catch (MongoException me){
            System.err.println("An error occurred: " + me);
        }

        return temas;
    }

    private String getTema(String tema){
//        String topico = "";
//
//        try {
//            JSONObject jsonObject = (JSONObject) new JSONParser().parse(tema);
//
//            topico = jsonObject.get("tema").toString();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        return topico;
        return tema;
    }





}
