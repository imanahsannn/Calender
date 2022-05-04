package ahsan_culm;

public class Calender {
    
    //fields
    private String month;
    private int day;
    private String info;
    
    //arg constructor
    public Calender (String m, int d, String i) {
        month = m;
        day = d;
        info = i;
    }
    
    //mutators
    public void setMonth (String m) {
        month = m;
    }
    
    public void setDay (int d) {
        day = d;
    }
    public void setInfo (String i) {
        info  = i;
    }
    
    //accessors 
    public String getMonth () {
        return month;
    }
    
    public int getDay () {
        return day;
    }
    
    public String getInfo () {
        return info;
    }
    
    /**
     * Method Name: equals
     * Description: checks to see if two elements in array list are equal
     * @param obj - second element to compare with
     * @return status - true if equal, false is not equal
     */
    public boolean equals (Calender obj) {
        
        boolean status;
        
        if (month.equals(obj.month) && day == obj.day && info.equals(obj.info))
            status = true;
        else 
            status = false;
        
        return status;
    } //end of equals
    
    /**
     * Method Name: toString
     * Description: creates a String representing object state 
     * @return state of the object
     */
    public String toString () {
        return ("On " + month + " the " + day + ", you have " + info);
    } //end of toString
} //end of class
