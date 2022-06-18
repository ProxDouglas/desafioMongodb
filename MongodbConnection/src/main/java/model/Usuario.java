package model;

import org.bson.types.ObjectId;


import java.util.ArrayList;
import java.util.List;


public class Usuario {

    private ObjectId _id;
    private String nome;
    private String departamento;
    private String cargo;
    private String email;
    private String data_nascimento;
    private String telefone;
    private boolean admin;
    private String foto;
    private String senha;
    private int seguindo_num;
    private List<String> seguindo ;
    private int seguindores_num;
    private List<String> seguidores;
    private List<String> grupos ;


    public Usuario(String nome, String departamento, String cargo,
                   String email, String data_nascimento, String telefone,
                   boolean admin, String foto, String senha) {
        this.nome = nome;
        this.departamento = departamento;
        this.cargo = cargo;
        this.email = email;
        this.data_nascimento = data_nascimento;
        this.telefone = telefone;
        this.admin = admin;
        this.foto = foto;
        this.senha = senha;
    }

    public Usuario(){}


    public ObjectId get_id() {return _id;}

    public void set_id(ObjectId _id) {this._id = _id;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public String getDepartamento() {return departamento;}

    public void setDepartamento(String departamento) {this.departamento = departamento;}

    public String getCargo() {return cargo;}

    public void setCargo(String cargo) {this.cargo = cargo;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getData_nascimento() {return data_nascimento;}

    public void setData_nascimento(String data_nascimento) {this.data_nascimento = data_nascimento;}

    public String getTelefone() {return telefone;}

    public void setTelefone(String telefone) {this.telefone = telefone;}

    public boolean isAdmin() {return admin;}

    public void setAdmin(boolean admin) {this.admin = admin;}

    public String getFoto() {return foto;}

    public void setFoto(String foto) {this.foto = foto;}

    public String getSenha() {return senha;}

    public void setSenha(String senha) {this.senha = senha;}

    public int getSeguindo_num() {return seguindo_num;}

    public void setSeguindo_num(int seguindo_num) {this.seguindo_num = seguindo_num;}

    public List<String> getSeguindo() {return seguindo;}

    public void setSeguindo(ArrayList<String> seguindo) {this.seguindo = seguindo;}

    public int getSeguindores_num() {return seguindores_num;}

    public void setSeguindores_num(int seguindores_num) {this.seguindores_num = seguindores_num;}

    public List<String> getSeguidores() {return seguidores;}

    public void setSeguidores(ArrayList<String> seguidores) {this.seguidores = seguidores;}

    public List<String> getGrupos() {return grupos;}

    public void setGrupos(ArrayList<String> grupos) {this.grupos = grupos;}
}
