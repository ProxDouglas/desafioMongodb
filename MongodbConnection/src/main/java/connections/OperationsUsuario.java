package connections;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.lt;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


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
                    .append("telefone", usuario.getTelefone())
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

    public void updateSeguir(String idSeguidor, String idSeguido){

        Usuario usuarioSeguidor, usuarioSeguido;



        usuarioSeguido = getById(new ObjectId(idSeguido));
        usuarioSeguidor = getById(new ObjectId(idSeguidor));


        updateSeguidor(idSeguidor, idSeguido);
        updateSeguido(idSeguidor, idSeguido);
    }


    private void updateSeguidor(String idSeguidor, String idSeguido){ //collection

        Document query = new Document().append("_id", new ObjectId(idSeguidor));

        Bson updates = Updates.combine(
                Updates.inc("seguindo_num",  1),
                Updates.addToSet("seguindo", idSeguido));

        UpdateOptions options = new UpdateOptions().upsert(true);

        try {
            UpdateResult result = getCollection().updateOne(query, updates, options);

            System.out.println("Modified document count: " + result.getModifiedCount());

            System.out.println("Upserted id: " + result.getUpsertedId()); // only contains a value when an upsert is performed
        } catch (MongoException me) {
            System.err.println("Unable to update due to an error: " + me);
        }
    }

    private void updateSeguido(String idSeguidor, String idSeguido){

        Document query = new Document().append("_id", new ObjectId(idSeguido));

            Bson updates = Updates.combine(
                    Updates.inc("seguindores_num",  1),
                    Updates.addToSet("seguindores", idSeguidor));

            UpdateOptions options = new UpdateOptions().upsert(true);

            try {
                UpdateResult result = getCollection().updateOne(query, updates, options);

                System.out.println("Modified document count: " + result.getModifiedCount());

                System.out.println("Upserted id: " + result.getUpsertedId()); // only contains a value when an upsert is performed
            } catch (MongoException me) {
                System.err.println("Unable to update due to an error: " + me);
            }
    }


    public void trocarSenha(String idUser, String senha){

        Document query = new Document().append("_id", new ObjectId(idUser));

        Bson updates = Updates.set("senha",  senha);

        UpdateOptions options = new UpdateOptions().upsert(true);

        try {
            UpdateResult result = getCollection().updateOne(query, updates, options);

            System.out.println("Senha alterada: " + result.getModifiedCount());

            System.out.println("Alterado em id: " + result.getUpsertedId()); // only contains a value when an upsert is performed
        } catch (MongoException me) {
            System.err.println("Unable to update due to an error: " + me);
        }

    }


    public void trocarFoto(String idUser, String foto){

        Document query = new Document().append("_id", new ObjectId(idUser));

        Bson updates = Updates.set("foto",  foto);

        UpdateOptions options = new UpdateOptions().upsert(true);

        try {
            UpdateResult result = getCollection().updateOne(query, updates, options);

            System.out.println("Senha alterada: " + result.getModifiedCount());

            System.out.println("Alterado em id: " + result.getUpsertedId()); // only contains a value when an upsert is performed
        } catch (MongoException me) {
            System.err.println("Unable to update due to an error: " + me);
        }

    }





    public Usuario getByName(String nome){

        MongoCollection<Document> collection = getCollection();

        Document doc = collection.find(eq("nome", nome)).first();

        return getByDoc(doc);

    }

    public Usuario getById(ObjectId _id){

        MongoCollection<Document> collection = getCollection();

        Document doc = collection.find(eq("_id", _id)).first();

        return getByDoc(doc);
    }

    public Usuario getByDoc(Document doc){
        Usuario usuario = new Usuario();

        try {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(doc.toJson());


            usuario.set_id(new ObjectId(((JSONObject) jsonObject.get("_id")).get("$oid").toString()));
            usuario.setNome(jsonObject.get("nome").toString());
            usuario.setDepartamento(jsonObject.get("departamento").toString());
            usuario.setCargo(jsonObject.get("cargo").toString());
            usuario.setEmail(jsonObject.get("email").toString());
            usuario.setData_nascimento(jsonObject.get("data_nascimento").toString());
            usuario.setTelefone(jsonObject.get("telefone").toString());
            usuario.setAdmin(Boolean.parseBoolean(jsonObject.get("admin").toString()));
            usuario.setFoto(jsonObject.get("foto").toString());
            usuario.setSeguindo_num(Integer.parseInt(jsonObject.get("seguindo_num").toString()));
            usuario.setNome(jsonObject.get("seguidores_num").toString());

            JSONArray seguindo = (JSONArray) jsonObject.get("seguindo");
            usuario.setSeguindo(seguindo);

            JSONArray seguindores = (JSONArray) jsonObject.get("seguidores");
            usuario.setSeguidores(seguindores);


            JSONArray grupo = (JSONArray) jsonObject.get("grupo");
            usuario.setSeguindo(grupo);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuario;
    }

    public void deleteByID(ObjectId _id){
        MongoCollection<Document> collection = getCollection();

        DeleteResult res = collection.deleteOne(new Document(("_id"), _id));

        if(res.getDeletedCount() == 1){
            System.out.println("Excluido ------------");
        }else if(res.getDeletedCount() > 1){
            System.out.println("Deu ruim ------------");
        }else{
            System.out.println("N??o possivel ------------");
        }
    }


    public void allUsuario(){


        Bson projectionFields = Projections.fields(
                Projections.include( "nome","departamento", "cargo", "email"));

//        MongoCursor<Document> cursor = getCollectionPub().find(and(lt("data", LocalDateTime.now()),  gte("data", yesterday())))
        MongoCursor<Document> cursor = getCollection().find()
                .projection(projectionFields)
                .sort(Sorts.descending("_id")).iterator();

        try {

            while(cursor.hasNext()) {

                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                JsonElement je = JsonParser.parseString(cursor.next().toJson());
                String prettyJsonString = gson.toJson(je);

                System.out.println(prettyJsonString);
                System.out.println("==============================");
            }
        } finally {
            cursor.close();
        }
    }


}

//mongodump --archive --db=case_rede_social | mongorestore --archive  --nsFrom='case_rede_social.*' --nsTo='case_rede_social_backup.*'

