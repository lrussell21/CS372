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
        if(getDays(month_, year_) >= day_ && month_ <= 12 && month_ >= 1){
            day = day_;
            month = month_;
            year = year_;
            //System.out.println("Date entry succeeded.");
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
        System.out.printf("Today is %d/%d/%d\n", localDate.getMonthValue(), localDate.getDayOfMonth(), localDate.getYear());
    }

    public int difference(Date d){
        int d1 = this.day, d2 = d.getDay(), num, testyear;
        // Sets year to where days counted starts.
        if(this.year == d.getYear()){
            testyear = this.year;
        }
        else if(this.year < d.getYear()){
            testyear = this.year - 1;
        }else{
            testyear = d.getYear() - 1;
        }

        // Counts the days between 1/1/testyear and the day entered for this.
        for(int di = this.year - 1; di >= testyear; di--){
            for(int dj = 1; dj <= 12; dj++){
                d1 += this.getDays(dj, di);
            }
        }
        for(int dm = this.month - 1; dm > 0; dm--){
            d1 += this.getDays(dm, this.year);
        }

        // Counts the days between 1/1/testyear and the day entered for d.
        for(int i = d.getYear() - 1; i >= testyear; i--){
            for(int j = 1; j <= 12; j++){
                d2 += d.getDays(j, i);
            }
        }
        for(int m = d.getMonth() - 1; m > 0; m--){
            d2 += d.getDays(m, d.getYear());
        }

        // Finds differences in both dates and if number is negative it is negated then returned.
        num = d1 - d2;
        if(num < 0){
            num *= -1;
        }
        return num;
    }

    public Date newFutureDate(int days){
        Date ret;
        int d = this.day, m = this.month, y = this.year, togo = days;
        if(getDays(this.month, this.year) + togo <= getDays(this.month, this.year)){
            d = this.day + togo;
            togo = 0;
        }
        do{
            if(d + 1 > getDays(m, y)){
                if(m + 1 > 12){
                    y++;
                    m = 1;
                    d = 1;
                }else{
                    m++;
                    d = 1;
                }
            }else{
                d += 1;
            }
            togo--;
        }while(togo != 0);
        ret = new Date(m,d,y);
        return ret;
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
