public class Customer {
    int number,areaCode;
    String company,lastName,firstName,role,phone1,phone2,address, city,state,country;

    public Customer(int number, String company, String lastName, String firstName, String role, String phone1, String phone2, String address, String city, String state, int areaCode, String country){
        this.number = number;
        this.company = company;
        this.lastName = lastName;
        this.firstName = firstName;
        this.role = role;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
        this.areaCode = areaCode;
    }

    public String toString(){
        return this.number + " " + this.company + " " + this.lastName + " " + this.firstName +
                    " " + this.role + " " + this.phone1 + " " + this.phone2 + " " + this.address + " " +
                    this.city + " " + this.state + " " + this.areaCode + " " + this.country;
    }
}
