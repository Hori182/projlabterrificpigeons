package terrific_pigeons;

public abstract class MoveAble implements IViewable{
    protected int life;
    protected boolean inWater = false;
    protected int drown = 0;
    protected int work = 0;
    protected Tile myTile;
    protected String moveableid;

    /*Mozgás függvénye*/
    protected  void move(Tile t){

    }
    /*Visszaadja a getDrown változó értékét.*/
    public int getDrown(){
        return drown;
    }
    /*Beállítja a drown értékét a paraméterként kapott értékre
    * @param int x: Ez lesz a drown változó új értéke.
    * */
    public void setDrown(int x){
        this.drown = x;
    }
    /*
     * A játékos elpasszolja a körét, nem él az összes lépésével.
     * */
    public void pass() {
        this.setWork(4);
    }
    /*
     * Visszaadja, hogy a játékos vízben van-e.
     * */
    public boolean getInWater() {
        return inWater;
    }

    /*
     * Beállítja az inWater értékét.
     * @param inWater: Ezzel az értékkel írja felül az inWater értékét.
     * */
    public void setInWater(boolean inWater)
    {
        if(inWater){
            this.setDrown(1);
            this.pass();
        } else{
            this.setDrown(0);
        }
        this.inWater = inWater;
    }

    /*
     * Visszaadja a work változó értékét.
     * */
    public int getWork() {
        return work;
    }
    /*
     * A work változó értékét megváltoztatja.
     * @param work: A work változót ezzel az értékkel írja felül.
     * */
    public void setWork(int work)
    {
        this.work = work;
    }
    /*
     * Visszaadja azt a tile-t amin a player áll.
     * */
    public Tile getTile()
    {
        return myTile;
    }
    /*
     * Beáálítja a myTile változó értékét.
     * @param t: Ez a myTile változó új értéke.
     * */
    public void setMyTile(Tile t)
    {
        this.myTile = t;
        //t.getMovables().add(this);
    }
    /*
    *Kirajzolás fügvénye.
    * */
    protected void draw(){}
    
    /*
     * Növeli az életerőt eggyel.
     * */
    protected boolean addLife() { return  true;}

    /*
     * Csökkenti az életerőt eggyel.
     * */
    public void subLife()
    {
        if (this.myTile.getSafe() == false && this.myTile.getSafeByTent() == false)
        {
            if(this.getLife()-1 > 1) {
                setLife(this.getLife() - 1);
            }
            else{
                setLife(this.getLife()-1);
                pass();
            }
        }
    }

    /*
     * A játékos meghal.
     * */
    public void die() {
        this.life = 0;
    }

    /*
     * Visszaadja, hogy jelenleg hány élete van.
     */
    public int getLife() {
        return life;
    }

    /*
     * Beállítja a life attribútum új értékét.
     * @param life: élet - a life új értéke
     */
    public void setLife(int life)
    {
        this.life = life;
    }
    /*
    * Megtámadás függvénye.
    * */
    public void attacked(){}
    /*
    * Visszaadja a moveableid értékét.
    * */
    public String getId(){return moveableid;}

    @Override
    public void notify(View v) {

    }
    /*
     * Visszaadja az X értékét.
     * */
    @Override
    public int GetX() {
        return 0;
    }
    /*
     * Visszaadja az Y értékét.
     * */
    @Override
    public int GetY() {
        return 0;
    }

}
