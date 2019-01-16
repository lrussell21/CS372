public class Circle extends interfaceShape {
    private int rad;

    /**
     * Creates a circle shape object.
     * @param kind the kind of shape. ie circle, rectangle, etc.
     * @param ID the ID of that shape.
     * @param color the color of the shape.
     * @param rad the radius of the circle.
     */
    Circle(String kind, int ID, String color, int rad){
        super(kind, ID, color);
        this.rad = rad;
    }

    /**
     * Returns a detailed string of the Circle.
     * @return a detailed string of the Circle.
     */
    public String getDetailString(){
        double area = Math.PI * this.rad * this.rad;
        String areaString = String.format("%.2f", area);
        double circumference = 2 * Math.PI * this.rad;
        String circumferenceString = String.format("%.2f", circumference);
        return this.kind + " (ID# " + this.ID + ")\n" +
                "Color: " + this.color + "\n" +
                "Radius: " + this.rad + "\n" +
                "Area: " + areaString + "\n" +
                "Circumference: " + circumferenceString;
    }
}
