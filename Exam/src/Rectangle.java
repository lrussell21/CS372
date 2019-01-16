public class Rectangle extends interfaceShape {
    private int width, length;

    /**
     * Creates a Rectangle shape object.
     * @param kind the kind of shape. ie circle, rectangle, etc.
     * @param ID the ID of that shape.
     * @param color the color of the shape.
     * @param length length of long side of the rectangle.
     * @param width length of short side of the rectangle.
     */
    public Rectangle(String kind, int ID, String color, int length, int width){
        super(kind, ID, color);
        this.width = width;
        this.length = length;
    }

    /**
     * Returns a detailed string of the Circle.
     * @return a detailed string of the Circle.
     */
    public String getDetailString(){
        double area = this.length * this.width;
        String areaS = String.format("%.0f", area);
        int perimeter = (this.length * 4) + (this.width * 2);
        return this.kind + " (ID# " + this.ID + ")\n" +
                "Color: " + this.color + "\n" +
                "Length: " + this.length + "\n" +
                "Width: " + this.width + "\n" +
                "Area: " + areaS + "\n" +
                "Perimeter: " + perimeter;
    }
}
