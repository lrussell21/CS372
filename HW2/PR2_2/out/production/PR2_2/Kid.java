public class Kid extends Person {
    private String favCandy;

    public Kid(String name, int age, int phoneNum,String favCandy){
        super(name, age, phoneNum);
        this.favCandy = favCandy;
    }

    public String getFavCandy() {
        return favCandy;
    }

    public void setFavCandy(String favCandy) {
        this.favCandy = favCandy;
    }
}
