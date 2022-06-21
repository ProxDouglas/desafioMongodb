package connections;



import com.mongodb.BasicDBObject;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import model.Comentario;
import model.Publicacao;
import org.bson.Document;

import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;


import static com.mongodb.client.model.Filters.eq;

public class OparationsComentario {


    private MongoDatabase db;
    private MongoCollection<Document> collection;
    private OperationsPublicacao operatonP;

    public MongoDatabase getDB() {return this.db;}

    public MongoCollection<Document> getCollection() {return this.collection;}

    public OperationsPublicacao getOperatonP() {return operatonP;}

    public OparationsComentario(MongoDatabase db, String collection) {
        this.db = db;
        this.collection = getDB().getCollection(collection);
        this.operatonP = new OperationsPublicacao(getDB(), "publicacao");
    }



    public void comentarPublicacao(Comentario comentario, String idPublicacao){

        Publicacao publicacao = getOperatonP().getByIdNumbers(new ObjectId(idPublicacao));

        Document newComentario = new Document()
                .append("_id", new ObjectId())
                .append("idUsuario", comentario.getIdUsuario())
                .append("nome_usuario", comentario.getNomeUsuario())
                .append("texto", comentario.getTexto())
                .append("data", LocalDateTime.now());


       getCollection().updateOne(eq("_id", new ObjectId(idPublicacao)),
                Updates.combine(
                        Updates.inc("comentarios_num",   1),
                        Updates.addToSet("comentarios", newComentario)));

    }







//    public void deleteByID(ObjectId _id){
//        MongoCollection<Document> collection = getCollection();
//
//        DeleteResult res = collection.deleteOne(new Document(("_id"), _id));
//
//        if(res.getDeletedCount() == 1){
//            System.out.println("Excluido ------------");
//        }else if(res.getDeletedCount() > 1){
//            System.out.println("Deu ruim ------------");
//        }else{
//            System.out.println("Não possivel ------------");
//        }
//    }

//    public void deleteComentario(String id_publicacao, String id_comentario){
//
//        Document query = new Document().append("_id", new ObjectId(id_publicacao));
//
//        Document comentarioDoc = new Document()
//                .append("_id", new ObjectId(id_comentario));
//
////        BasicDBObject match = new BasicDBObject("_id", new ObjectId(id_publicacao)); //to match your direct app document
//        BasicDBObject update = new BasicDBObject("comentarios" ,comentarioDoc);
//
////        getCollection().updateOne(match, new BasicDBObject("$pull", update));
//
//
////        getCollection().updateOne(match,
////                Updates.combine(
////                        Updates.pullByFilter(new BasicDBObject("$pull", update)),
////                        Updates.inc("comentarios_num", -1)
////                )
////        );
//
//
//        Bson updates = Updates.combine(
//                Updates.pullByFilter(new BasicDBObject("$pull", update)),
//                Updates.inc("comentarios_num", -1)
//        );
//
//        UpdateOptions options = new UpdateOptions().upsert(true);
//
//        try {
//            UpdateResult result = getCollection().updateOne(query, updates, options);
//
//            System.out.println("Modified document count: " + result.getModifiedCount());
//
//            System.out.println("Upserted id: " + result.getUpsertedId()); // only contains a value when an upsert is performed
//        } catch (MongoException me) {
//            System.err.println("Unable to update due to an error: " + me);
//        }
//    }





}
