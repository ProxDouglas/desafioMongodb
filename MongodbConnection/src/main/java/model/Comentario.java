package model;

public class Comentario {

    private int idUsuario;
    private String nomeUsuario;
    private String fotoUsuario;
    private String texto;


    public int getIdUsuario() {return idUsuario;}

    public void setIdUsuario(int idUsuario) {this.idUsuario = idUsuario;}

    public String getNomeUsuario() {return nomeUsuario;}

    public void setNomeUsuario(String nomeUsuario) {this.nomeUsuario = nomeUsuario;}

    public String getFotoUsuario() {return fotoUsuario;}

    public void setFotoUsuario(String fotoUsuario) {this.fotoUsuario = fotoUsuario;}

    public String getTexto() {return texto;}

    public void setTexto(String texto) {this.texto = texto;}


    public Comentario(int idUsuario, String nomeUsuario, String fotoUsuario, String texto) {
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;
        this.fotoUsuario = fotoUsuario;
        this.texto = texto;
    }
}
