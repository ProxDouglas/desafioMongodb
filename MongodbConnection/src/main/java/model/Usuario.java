package model;

import java.awt.*;

public class Usuario {

    private int _id;
    private String nome;
    private String departamento;
    private String cargo;
    private String email;
    private String data_nascimento;
    private String telefone;
    private int admin;
    private String foto;
    private String senha;
    private int seguindo_num;
    private List seguindo = null;
    private int seguindores_num;
    private List seguidores = null;
    private List grupos = null;

    public Usuario(String nome, String departamento, String cargo,
                   String email, String data_nascimento, String telefone,
                   int admin, String foto, String senha) {
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

    public int get_id() {return _id;}

    public void set_id(int _id) {this._id = _id;}

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

    public int isAdmin() {return admin;}

    public void setAdmin(int admin) {this.admin = admin;}

    public String getFoto() {return foto;}

    public void setFoto(String foto) {this.foto = foto;}

    public String getSenha() {return senha;}

    public void setSenha(String senha) {this.senha = senha;}

    public int getSeguindo_num() {return seguindo_num;}

    public void setSeguindo_num(int seguindo_num) {this.seguindo_num = seguindo_num;}

    public List getSeguindo() {return seguindo;}

    public void setSeguindo(List seguindo) {this.seguindo = seguindo;}

    public int getSeguindores_num() {return seguindores_num;}

    public void setSeguindores_num(int seguindores_num) {this.seguindores_num = seguindores_num;}

    public List getSeguidores() {return seguidores;}

    public void setSeguidores(List seguidores) {this.seguidores = seguidores;}

    public List getGrupos() {return grupos;}

    public void setGrupos(List grupos) {this.grupos = grupos;}
}
