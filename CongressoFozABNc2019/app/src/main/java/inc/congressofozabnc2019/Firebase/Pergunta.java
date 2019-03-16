package inc.congressofozabnc2019.Firebase;
public class Pergunta {
    public String nome,mensagem,autor,email,numeroSala;


    public Pergunta() {
    }

    public String getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(String numeroSala) {
        this.numeroSala = numeroSala;
    }

    public Pergunta(String nome, String mensagem, String numeroSala, String autor, String email) {
        this.nome = nome;
        this.mensagem = mensagem;
        this.autor = autor;
        this.numeroSala = numeroSala;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
