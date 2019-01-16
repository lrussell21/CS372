public class Triangle extends interfaceShape {
    private int side1, side2, side3;

    /**
     * Creates a Triangle shape object.
     * @param kind the kind of shape. ie circle, rectangle, etc.
     * @param ID the ID of that shape.
     * @param color the color of the shape.
     * @param side1 first side of the triangle.
     * @param side2 second side of the triangle.
     * @param side3 third side of the triangle.
     */
    public Triangle(String kind, int ID, String color, int side1, int side2, int side3){
        super(kind, ID, color);
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    /**
     * Returns a detailed string of the Circle.
     * @return a detailed string of the Circle.
     */
    public String getDetailString(){
        int perimeter = this.side1 + this.side2 + this.side3;
        // TODO: FIX AREA.
        double p = (double)perimeter / 2;
        double areaCalc = Math.sqrt((p * (p - this.side1) * (p - this.side2) * (p - this.side3)));
        String areaString;
        if(areaCalc >= 0.0) {
            areaString = String.format("%.2f", areaCalc);
        }else{
            areaString = "Impossible triangle";
        }
        return this.kind + " (ID# " + this.ID + ")\n" +
                "Color: " + this.color + "\n" +
                "Side 1: " + this.side1 + "\n" +
                "Side 2: " + this.side2 + "\n" +
                "Side 3: " + this.side3 + "\n" +
                "Area: " + areaString + "\n" +
                "Perimeter: " + perimeter;
    }
}
