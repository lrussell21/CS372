public class Square extends interfaceShape {
    private int side;

    /**
     * Creates a Square shape object.
     * @param kind the kind of shape. ie circle, rectangle, etc.
     * @param ID the ID of that shape.
     * @param color the color of the shape.
     * @param side length of one side of the square.
     */
    public Square(String kind, int ID, String color, int side){
        super(kind, ID, color);
        this.side = side;
    }

    /**
     * Returns a detailed string of the Circle.
     * @return a detailed string of the Circle.
     */
    public String getDetailString(){
        double area = this.side * this.side;
        String areaS = String.format("%.0f", area);
        int perimeter = this.side * 4;
        return this.kind + " (ID# " + this.ID + ")\n" +
                "Color: " + this.color + "\n" +
                "Side: " + this.side + "\n" +
                "Area: " + areaS + "\n" +
                "Perimeter: " + perimeter;
    }
}
