public abstract class Building {
    private String name, address;

    public Building(){
        this.name = "UNSET";
        this.address = "Homeless";
    }
    public Building(String name, String address){
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
