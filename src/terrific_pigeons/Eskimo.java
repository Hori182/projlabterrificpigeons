package terrific_pigeons;

public class Eskimo extends Player {

    private int life = 5;

    /*
     * Iglut épít a saját jégtáblájára, ezzel biztonságossá
     * teszi a mezőt.
     * */
    public void build() {}

    /**/
    public int getLife() {
        return life;
    }

    /**/
    public void setLife(int life) {
        this.life = life;
    }

}
