package Models;

public class User {

    private String pseudo;
    private String mail;
    private String pwd;
    private int idUser;

    public User() {

    }

    public User(String pseudo, String pwd, String mail) {
        this.pseudo = pseudo;
        this.mail = mail;
        this.pwd = pwd;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return ("id: " + this.idUser + "\nPseudo : " + this.pseudo + "\nmail: " + this.mail);
    }
}
