package prototipo.adapto.com.menuc;

public class FirebaseDataAuth {

    public String emailIndicado, nomeIndicado, emailPadrinho;



    public FirebaseDataAuth(String emailIndicado, String nomeIndicado, String emailPadrinho) {
        this.emailIndicado = emailIndicado;
        this.nomeIndicado = nomeIndicado;
        this.emailPadrinho = emailPadrinho;

    }

    public FirebaseDataAuth() {
    }

    public String getEmailIndicado() {
        return emailIndicado;
    }



    public String getNomeIndicado() {
        return nomeIndicado;
    }



    public String getEmailPadrinho() {
        return emailPadrinho;
    }




}