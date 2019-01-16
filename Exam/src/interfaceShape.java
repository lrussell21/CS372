public class interfaceShape {
    protected String kind, color;
    protected int ID;

    /**
     * Base shape object.
     * @param kind the kind of shape. ie circle, rectangle, etc.
     * @param ID the ID of that shape.
     * @param color the color of the shape.
     */
    public interfaceShape(String kind, int ID, String color){
        this.kind = kind;
        this.ID = ID;
        this.color = color;
    }

    /**
     * Returns a string with shapes kind and ID.
     * @return  a string with shapes kind and ID.
     */
    public String toString(){
        return this.kind + " (ID# " + this.ID + ")";
    }

    /**
     * Returns the kind of shape.
     * @return the kind of shape.
     */
    public String getKind(){
        return this.kind;
    }

    // Should be initialized in each shape class.
    public String getDetailString(){
        return null;
    }

    /**
     * Returns the objects/shapes ID.
     * @return the objects/shapes ID.
     */
    int getID(){
        return this.ID;
    }

    /**
     * Returns the color of the shape.
     * @return the color of the shape.
     */
    String getColor(){
        return this.color;
    }

}
