import java.io.*;
import java.util.ArrayList;

public class Data {
    ArrayList peices = new ArrayList();
    ArrayList<Customer> customers = new ArrayList<>();

    public Data(){
        String fileName = "Customers.csv";
        try(FileInputStream is = new FileInputStream(fileName)){
            InputStreamReader ir = new InputStreamReader(is);
            BufferedReader rdr = new BufferedReader(ir);
            String line = rdr.readLine();

            while(line != null){
                String[] parts = line.split(",");
                for(String p: parts){
                    if(p.length() > 0 && p.charAt(0) == '"'){
                        peices.add(p.substring(1, p.length() - 1));
                    }else if(p.length() > 0){
                        peices.add(p);
                    }else{
                        //System.out.printf("%s\n", p);
                    }
                }
                line = rdr.readLine();
            }
        }
        catch (Exception ex){
            System.out.printf("Failed for %s\n", fileName);
            System.out.println(ex.getMessage());
        }
    }

    public void customerFill(){
        Customer c;
        for(int i = 0; i < peices.size(); i+=12){
            c = new Customer(Integer.parseInt(peices.get(i).toString()),(String)peices.get(i + 1),(String)peices.get(i + 2),(String)peices.get(i + 3),(String)peices.get(i + 4),(String)peices.get(i + 5),(String)peices.get(i + 6),(String)peices.get(i + 7),(String)peices.get(i + 8),(String)peices.get(i + 9),Integer.parseInt(peices.get(i + 10).toString()),(String)peices.get(i + 11));
            customers.add(c);
        }
    }

    public int indexSearch(int id){
        for(int i = 0; i < customers.size(); i++){
            if(customers.get(i).number == id){
                return i;
            }
        }
        System.out.println("Error!! Id not found!");
        return -1;
    }
}
