package connections;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Connect {





        //MongoCollection col = db.getCollection("usuario");

        //inserir em uma collection
        //Document sampleDoc = new Document("_id","1").append("name", "john Smith");
        //col.insertOne(sampleDoc);

    public MongoDatabase iniciandoBD(){
        MongoClient client = MongoClients.create("mongodb+srv://groupLobtec:redesocialLobtec@redesocial.eb5fwga.mongodb.net/?retryWrites=true&w=majority");
        return client.getDatabase("case_rede_social_backup");
    }


}
