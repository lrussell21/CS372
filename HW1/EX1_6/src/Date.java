import java.time.LocalDate;

public class Date {
    private int day,month,year;
    public Date(){
        LocalDate localDate = LocalDate.now();
        day = localDate.getDayOfMonth();
        month = localDate.getMonthValue();
        year = localDate.getYear();
    }

    public Date(int month_, int day_, int year_){
        LocalDate localDate = LocalDate.now();
        if(getDays(month_, year_) >= day_ && month_ <= 12 && month_ >= 0){
            day = day_;
            month = month_;
            year = year_;
            System.out.println("Date entry succeeded.");
        }else{
            System.out.println("Error! Date not possible.");
        }
    }

    public boolean leapYearCheck(int year){
        if(year % 400 == 0){
            return true;
        }else if(year % 4 == 0 && !(year % 100 == 0)){
            return true;
        }else{
            return false;
        }
    }

    public int getDays(int month, int year){
        if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            return 31;
        }else if(month == 2){
            if(leapYearCheck(year)){
                return 29;
            }else{
                return 28;
            }
        }else{
            return 30;
        }
    }

    public void today(){
        LocalDate localDate = LocalDate.now();
        System.out.printf("Today is %d/%d/%d", localDate.getMonthValue(), localDate.getDayOfMonth(), localDate.getYear());
    }

    public int difference(Date d){
        int num = 0;
        int d1,d2;
        d1 = this.day;
        for(int i = this.month - 1; i > 0; i--){
            d1 += getDays(i, this.year);
        }
        d2 = d.getDay();
        for(int j = d.getMonth() - 1; j > 0; j--){
            d2 += getDays(j, d.getYear());
        }

        return d1 - d2;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
