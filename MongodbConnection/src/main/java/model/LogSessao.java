package model;

import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.Date;


public class LogSessao {

    private ObjectId _id;
    private String id_usuario;
    private String tipo;
    private LocalDateTime data;
    //Mongo new Date("<YYYY-mm-ddTHH:MM:ss>")
    //

    public String getId_usuario() {return id_usuario;}

    public void setId_usuario(String id_usuario) {this.id_usuario = id_usuario;}

    public LocalDateTime getData() {return data;}

    public void setData(LocalDateTime data) {this.data = data;}

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipoSaiu() {
        this.tipo = "saiu";
    }

    public void setTipoEntrou() {
        this.tipo = "entrou";
    }

    public void setTipo(String tipo){
        if(tipo.equals("entrou") || tipo.equals("saiu")) {
            this.tipo = tipo;
        }
    }

}
