package model;

import java.util.Date;
import java.util.List;

public class Publicacao {


    private String idPublicacao;
    private String idUsuario;
    private String nomeUsuario;
    private String fotoUsuario;
    private String tema;
    private String descricao;
    private String fotoPublicacao;
    private int curtida_num;
    private List<String> curtidaDetalhe; //String
    private List<Comentario> comentarios;
    private int comentarios_num;
    private Date data;

    public String getIdPublicacao() {return idPublicacao;}

    public void setIdPublicacao(String idPublicacao) {this.idPublicacao = idPublicacao;}

    public String getIdUsuario() {return idUsuario;}

    public void setIdUsuario(String idUsuario) {this.idUsuario = idUsuario;}

    public String getNomeUsuario() {return nomeUsuario;}

    public void setNomeUsuario(String nomeUsuario) {this.nomeUsuario = nomeUsuario;}

    public String getFotoUsuario() {return fotoUsuario;}

    public void setFotoUsuario(String fotoUsuario) {this.fotoUsuario = fotoUsuario;}

    public String getTema() {return tema;}

    public void setTema(String tema) {this.tema = tema;}

    public String getDescricao() {return descricao;}

    public void setDescricao(String descricao) {this.descricao = descricao;}

    public String getFotoPublicacao() {return fotoPublicacao;}

    public void setFotoPublicacao(String fotoPublicacao) {this.fotoPublicacao = fotoPublicacao;}

    public int getCurtida_num() {return curtida_num;}

    public void setCurtida_num(int curtida_num) {this.curtida_num = curtida_num;}

    public List<String> getCurtidaDetalhe() {return curtidaDetalhe;}

    public void setCurtidaDetalhe(List<String> curtidaDetalhe) {this.curtidaDetalhe = curtidaDetalhe;}

    public List<Comentario> getComentarios() {return comentarios;}

    public void setComentarios(List<Comentario> comentarios) {this.comentarios = comentarios;}

    public int getComentarios_num() {return comentarios_num;}

    public void setComentarios_num(int comentarios_num) {this.comentarios_num = comentarios_num;}

    public Date getData() {return data;}

    public void setData(Date data) {




        this.data = data;
    }


    public Publicacao(String idUsuario, String nomeUsuario, String fotoUsuario,
                      String tema, String descricao, String fotoPublicacao, Date data) {
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;
        this.fotoUsuario = fotoUsuario;
        this.tema = tema;
        this.descricao = descricao;
        this.fotoPublicacao = fotoPublicacao;
        this.data = data;
    }
}

