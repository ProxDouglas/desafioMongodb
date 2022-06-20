package model;

import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class Grupo {

    private ObjectId _id;

    private String id_adm;
    private String nome;
    private int numero_membros;
    private List<String> seguidores;

    public ObjectId get_id() {return _id;}

    public void set_id(ObjectId _id) {this._id = _id;}

    public String getId_adm() {return id_adm;}

    public void setId_adm(String id_adm) {this.id_adm = id_adm;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public int getNumero_membros() {return numero_membros;}

    public void setNumero_membros(int numero_membros) {this.numero_membros = numero_membros;}

    public List<String> getSeguidores() {return seguidores;}

    public void setSeguidores(ArrayList<String> seguidores) {this.seguidores = seguidores;}
}
