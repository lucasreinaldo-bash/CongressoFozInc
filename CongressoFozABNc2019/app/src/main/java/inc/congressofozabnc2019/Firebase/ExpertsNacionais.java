package inc.congressofozabnc2019.Firebase;
public class ExpertsNacionais {

    public String nome,image,nacionalidade;
    public String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ExpertsNacionais(){

    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }
    public ExpertsNacionais(String nome, String id, String image, String nacionalidade) {
        this.nome = nome;
        this.image = image;
        this.id = id;
        this.nacionalidade = nacionalidade;
    }
}
