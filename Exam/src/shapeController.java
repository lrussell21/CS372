import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class shapeController {
    ArrayList peices = new ArrayList();
    ArrayList<interfaceShape> shapes = new ArrayList<>();

    /**
     * Creates a controller object and inputs csv file into an Arraylist.
     */
    public shapeController(){
        String fileName = "shapes.csv";
        try(FileInputStream is = new FileInputStream(fileName)){
            InputStreamReader ir = new InputStreamReader(is);
            BufferedReader rdr = new BufferedReader(ir);
            String line = rdr.readLine();

            while(line != null){
                String[] parts = line.split(",");
                for(String p: parts){
                    // The type of shape doesn't have a space after comma.
                    if(p.length() > 0 && p.charAt(0) == '"') {
                        peices.add(p.substring(1, p.length() - 1));
                        // For color because color has space before '"'.
                    }else if(p.length() > 0 && p.charAt(1) == '"'){
                        peices.add(p.substring(2, p.length() - 1));
                        // Add numbers without space.
                    }else if(p.length() > 0){
                        peices.add(p.substring(1, p.length()));
                    }else{
                        //System.out.printf("%sC\n", p);
                    }
                }
                line = rdr.readLine();
            }
        }
        catch (Exception ex){
            System.out.printf("Failed for %sC\n", fileName);
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Goes through all peices input from csv file and creates shapes based on the data and puts them into an Arraylist.
     */
    public void createShapes(){
        String[] allShapes = {"circle", "triangle","rectangle", "square"};
        Circle c;
        Rectangle r;
        Square s;
        Triangle t;

        // Will not break if new shapes are added, but new shapes will need to be added to be put into list.
        // Each shapes constructor has different amounts of data so a shape is created by
        // using first item as kind of object and the rest are filled in by adding to loop counter variable,
        // then after the object is created increase for loop to next kind of object to be created.
        for(int i = 0; i < peices.size() - 1; i++){
            if(peices.get(i).toString().compareTo("circle") == 0){
                c = new Circle( peices.get(i).toString(),
                                Integer.parseInt(peices.get(i + 1).toString()),
                                peices.get(i + 3).toString(),
                                Integer.parseInt(peices.get(i + 2).toString()));
                shapes.add(c);
                i += 3;
                continue;
            }else if(peices.get(i).toString().compareTo("triangle") == 0){
                t = new Triangle(   peices.get(i).toString(),
                                    Integer.parseInt(peices.get(i + 1).toString()),
                                    peices.get(i + 5).toString(),
                                    Integer.parseInt(peices.get(i + 2).toString()),
                                    Integer.parseInt(peices.get(i + 3).toString()),
                                    Integer.parseInt(peices.get(i + 4).toString()));
                shapes.add(t);
                i += 5;
                continue;
            }else if(peices.get(i).toString().compareTo("square") == 0){
                s = new Square( peices.get(i).toString(),
                                Integer.parseInt(peices.get(i + 1).toString()),
                                peices.get(i + 3).toString(),
                                Integer.parseInt(peices.get(i + 2).toString()));
                shapes.add(s);
                i += 3;
                continue;
            }else if(peices.get(i).toString().compareTo("rectangle") == 0){
                r = new Rectangle(  peices.get(i).toString(),
                                    Integer.parseInt(peices.get(i + 1).toString()),
                                    peices.get(i + 4).toString(),
                                    Integer.parseInt(peices.get(i + 2).toString()),
                                    Integer.parseInt(peices.get(i + 3).toString()));
                shapes.add(r);
                i += 4;
                continue;

            }else{
                System.out.printf("Error!!! Didn't recognize '%sC'\n", peices.get(i));
                // Finds next shape kind because i fell on data that wasn't shape kind.
                boolean exit = false;
                for(int j = i; j < peices.size() - 1; j++){
                    for(int n = 0; n < allShapes.length; n++) {
                        if (peices.get(j).toString().compareTo(allShapes[n]) == 0) {
                            // Set index to next found recognized shape.
                            i = j - 1;
                            exit = true;
                        }
                    }
                    // Did this because break only works for one for loop...although I didn't even
                    // see if it would work in a nested loop.
                    if(exit){
                        break;
                    }
                }
                if(exit) {
                    System.out.printf("New shape found!\n");
                }else{
                    System.out.printf("No New shapes found...\n");
                }
            }
        }
    }
}
