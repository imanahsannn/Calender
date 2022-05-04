package ahsan_culm;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Ahsan_culm {
    
    public static Scanner scanN = new Scanner (System.in);
    public static Scanner scanS = new Scanner (System.in);
    public static ArrayList <Calender> events = new ArrayList <>(); //hold events

    public static void main(String[] args) throws IOException, 
            InterruptedException {
        
        int menuChoice, searchChoice; 
        
        System.out.println("Welcome to your own personal calender");
        
        readEvents(); //read from text file
        sortEvents(); //sort the days
        Thread.sleep(500);
        
        do {
            menuChoice = mainMenu(); //user chooses an option
        
            switch (menuChoice) 
            {
                case 1: //add new event
                    getData(); 
                    sortEvents();
                    break;
                
                case 2: //search for event
                    do 
                    {
                        System.out.println("Which would you like to search for: "
                                + "\n1. Event's name \n2. Event's date");
                        searchChoice = scanN.nextInt();
                    } while (searchChoice < 1 || searchChoice > 2); //error trap
                    if (searchChoice == 1)
                        searchInfo(); 
                    else
                        searchDate();
                    break;
                
                case 3: //view all events
                    for (int i = 0; i < events.size(); i++) 
                        System.out.println(events.get(i));
                    break;
                
                case 4: //edit or remove an event
                    editEvent();
                    break;
                    
                case 5: //exit
                    System.out.println("Exiting now...");
                    break;
            }
            Thread.sleep(1000);
            
        } while (menuChoice != 5); //while they dont want to exit

        printEvents(); //print to text file
        
    } //end of main
    
    /**
     * Method Name: readEvents
     * Description: puts the events in the text file in the array list
     * @throws IOException 
     */
    public static void readEvents () throws IOException {
     
        File myFile = new File ("/Users/iman/Documents/4U NetBeansProjects/files/"
                + "Ahsan_culm.txt");
        Scanner readFile = new Scanner (myFile);
        
        while (readFile.hasNext())
        {
            String[] seperate = readFile.nextLine().split(":"); //tokenize
            String month = seperate[0].toLowerCase();
            int day = Integer.parseInt(seperate[1]); //parse
            if (!(month.equals("january") || month.equals("february") || 
                month.equals("march") || month.equals("april") || 
                month.equals("may") || month.equals("june") || 
                month.equals("july") || month.equals("august") ||
                month.equals("september") || month.equals("october") ||
                month.equals("november") | month.equals("december")) || 
                day < 1 || ((month.equals("january") || 
                month.equals("march") || month.equals("may") ||
                month.equals("july") || month.equals("august") ||
                month.equals("october") || month.equals("december")) && 
                day > 31) || ((month.equals("april") ||
                month.equals("june") || month.equals("september") ||
                month.equals("november")) && day > 30) || 
                (month.equals("february") && day > 29))
                System.out.println(seperate[0].toLowerCase() + " " + 
                        Integer.parseInt(seperate[1]) + " is not a valid date "
                                + "so it is not added to your calender");
            else
                events.add(new Calender(month, day, seperate[2])); //new element
        }
    } //end of readEvents
    
    /**
     * Method Name: sortEvents
     * Description: sorts events days using selection sort
     */
    public static void sortEvents () {
        
        int minIndex, minDay;
        String minMonth, minInfo;
        
        for (int i = 0; i < events.size() - 1; i++)
        {
            minDay = events.get(i).getDay(); 
            minMonth = events.get(i).getMonth();
            minInfo = events.get(i).getInfo();
            minIndex = i;
            
            for (int j = i + 1; j < events.size(); j++)
            {
                if (events.get(i).equals(events.get(j)))
                {
                    System.out.println("There are 2 identical events on " + 
                            events.get(i).getMonth() + " the " + 
                            events.get(i).getDay());
                    events.remove(j);
                }
                else if (events.get(j).getDay() < minDay) //compare two day ints
                {
                    minDay = events.get(j).getDay();
                    minMonth = events.get(j).getMonth();
                    minInfo = events.get(j).getInfo();
                    minIndex = j;
                }
            }
            
            events.get(minIndex).setDay(events.get(i).getDay()); //swap them
            events.get(minIndex).setMonth(events.get(i).getMonth());
            events.get(minIndex).setInfo(events.get(i).getInfo());
            
            events.get(i).setDay(minDay);
            events.get(i).setMonth(minMonth);
            events.get(i).setInfo(minInfo);
        }
    } //end of sortEvents
        
    /**
     * Method Name: mainMenu
     * Description: allows user to pick what they want to do
     * @return choice - users choice 
     */
    public static int mainMenu () {
        
        int choice;
        String[] options = {"Add New Event", "Search for Event", 
            "View All Events", "Edit/Remove an Event", "Exit"}; 
        
        System.out.println("What would you like to do?");
        for (int i = 0; i < options.length; i++) 
            System.out.println((i + 1) + ". " + options[i]); //print options
        
        do {
            System.out.println("\nEnter a number from 1 to " + options.length);
            choice = scanN.nextInt();
        } while (choice < 1 || choice > options.length); //error trap
        
        return choice; 
        
    } //end of mainMenu
    
    /**
     * Method Name: getData
     * Description: collects data about an events from user
     */
    public static void getData () {
        
        String info, month;
        int day, maxDays;
        
        do {
            System.out.println("In which month will the event take place?");
            month = scanS.nextLine().toLowerCase(); 
        } while (!(month.equals("january") || month.equals("february") || 
                month.equals("march") || month.equals("april") || 
                month.equals("may") || month.equals("june") || 
                month.equals("july") || month.equals("august") ||
                month.equals("september") || month.equals("october") ||
                month.equals("november") | month.equals("december")));//error trap
        
        //find maximum days each month can have
        if (month.equals("april") || month.equals("june") || 
                month.equals("september") || month.equals("november"))
            maxDays = 30; 
        else if (month.equals("february"))
            maxDays = 29;
        else
            maxDays = 31;
        
        do {
            System.out.println("On which day will the event take place? (1-" + 
                    maxDays + ")");
            day = scanN.nextInt(); 
        } while (day < 1 || day > maxDays); //error trap
         
        System.out.println("What do you want to call the event?");
        info = scanS.nextLine();
        
        //go through array lists to check if an element w/ same contents exists
        for (int i = 0; i < events.size(); i++) 
            if (events.get(i).getMonth().equals(month) && 
                    events.get(i).getDay() == day && 
                    events.get(i).getInfo().equals(info)) 
            {
                System.out.println("That event already exists");
                return; //don't add the new event in array list
            }
        
        events.add(new Calender (month, day, info)); //otherwise add new element
       
    } //end of getData
    
    /**
     * Method Name: searchInfo
     * Description: checks all elements in array list for specific event name
     */
    public static void searchInfo () {
        
        int count = 0; //number of times an element with same info exists
        
        System.out.println("What is the event called?");
        String name = scanS.nextLine();
         
        for (int i = 0; i < events.size(); i++) 
            if (events.get(i).getInfo().equals(name)) 
            {
                System.out.println(events.get(i));
                count++;
            }
        
        if (count == 0) //no element has the same name
            System.out.println("Sorry that event does not exist. "
                        + "Check to see if you mispelled any of the words");
        
    } //end of searchInfo
    
    /**
     * Method Name: searchDate
     * Description: use binary search to find specifc date
     */
    public static void searchDate () {
        
        int first = 0, last = events.size() - 1, middle = 0, desiredDay, maxDays;
        String desiredMonth;
        boolean flag = false;
        
        do {
            System.out.println("In which month is/was the event held?");
            desiredMonth = scanS.nextLine().toLowerCase(); 
        } while (!(desiredMonth.equals("march") || desiredMonth.equals("may") || 
                desiredMonth.equals("january") || desiredMonth.equals("april") || 
                desiredMonth.equals("february") || desiredMonth.equals("june") || 
                desiredMonth.equals("october") || desiredMonth.equals("august")||
                desiredMonth.equals("september") || desiredMonth.equals("july")||
                desiredMonth.equals("november") || 
                desiredMonth.equals("december"))); //error trap
        
        //find maximum days each month can have
        if (desiredMonth.equals("april") || desiredMonth.equals("november") || 
                desiredMonth.equals("september") || desiredMonth.equals("june"))
            maxDays = 30;
        else if (desiredMonth.equals("february"))
            maxDays = 29;
        else
            maxDays = 31;
        
        do {
            System.out.println("On what day is/was the event held? (1-" + 
                    maxDays + ")");
            desiredDay = scanN.nextInt(); 
        } while (desiredDay < 1 || desiredDay > maxDays); //error trap
        
        while (!flag && first <= last) //binary search
        {
            middle = (first + last) / 2;
                
            if (desiredDay == events.get(middle).getDay() && 
                    desiredMonth.equals(events.get(middle).getMonth())) 
            {
                flag = true; 
                System.out.println(events.get(middle)); //print event
                
                while (desiredDay == events.get(middle - 1).getDay() && 
                        desiredMonth.equals(events.get(middle - 1).getMonth()) 
                        && middle > 0) //check events before middle
                {
                    System.out.println(events.get(middle - 1)); 
                    middle--;
                } 
                
                middle = (first + last) / 2;
                while (desiredDay == events.get(middle + 1).getDay() && 
                        desiredMonth.equals(events.get(middle + 1).getMonth())
                        && middle < (events.size() - 1)) //events after middle
                {
                    System.out.println(events.get(middle + 1));
                    middle++;
                }
            }
                
            else if (desiredDay > events.get(middle).getDay())
                first = middle + 1; //adjust first 
                
            else if (desiredDay < events.get(middle).getDay())
                last = middle - 1; //adjust last
            
            else //days matches but the month dont match
                flag = true;
        }
        
        if (desiredDay != events.get(middle).getDay() || 
                !desiredMonth.equals(events.get(middle).getMonth()))
            System.out.println("There is no event on " + desiredMonth + 
                    " " + desiredDay);
        
    } //end of searchDate
    
    /**
     * Method Name: editEvent
     * Description: allows user to edit their desired event
     */
    public static void editEvent () {
        
        String info, month, newMonth;
        int day, choice, maxDays, newDay, count = 0;
                
        do {
            System.out.println("In which month did/will the event you want to "
                    + "edit/remove took/take place");
            month = scanS.nextLine().toLowerCase(); 
        } while (!(month.equals("january") || month.equals("february") || 
                month.equals("march") || month.equals("april") || 
                month.equals("may") || month.equals("june") || 
                month.equals("july") || month.equals("august") ||
                month.equals("september") || month.equals("october") ||
                month.equals("november") | month.equals("december")));//error trap
        
        //find maximum days each month can have
        if (month.equals("april") || month.equals("june") || 
                month.equals("september") || month.equals("november"))
            maxDays = 30;
        else if (month.equals("february"))
            maxDays = 29;
        else
            maxDays = 31;
        
        do {
            System.out.println("On which day did/will the event you want to "
                    + "edit/remove took/take place? (1-" + maxDays + ")");
            day = scanN.nextInt(); 
        } while (day < 1 || day > maxDays); //error trap
        
        System.out.println("What was the description of the event");
        info = scanS.nextLine();
        
        for (int i = 0; i < events.size(); i++) 
            //element matches all three inputs 
            if (events.get(i).getMonth().equals(month) && 
                    events.get(i).getDay() == day && 
                    events.get(i).getInfo().equals(info)) 
            {
                do 
                {
                System.out.println("Which would you like to edit: "
                        + "\n1. Event's month \n2. Event's day \n3. Event's info"
                        + "\n4. Remove Event");
                choice = scanN.nextInt();
                } while (choice < 1 || choice > 4); //error trap
                
                switch (choice) 
                {
                    case 1: //edit event's month
                        do {
                            System.out.println("In which month would you like "
                                    + "the event to be in?");
                            newMonth = scanS.nextLine().toLowerCase(); 
                        } while (!(newMonth.equals("january") || 
                            newMonth.equals("february")||newMonth.equals("may")||
                            newMonth.equals("march")|| newMonth.equals("april")|| 
                            newMonth.equals("june") || newMonth.equals("july") || 
                            newMonth.equals("august") ||
                            newMonth.equals("september") || 
                            newMonth.equals("october") ||
                            newMonth.equals("november") || 
                            newMonth.equals("december"))); //error trap
                        
                        events.get(i).setMonth(newMonth); 
                        break;
                        
                    case 2: //edit event's day
                        do {
                            System.out.println("On which day would you like the"
                                    + " event to be on? (1-" + maxDays + ")");
                            newDay = scanN.nextInt(); 
                        } while (newDay < 1 || newDay > maxDays); //error trap
                        
                        events.get(i).setDay(newDay);
                        break;
                        
                    case 3: //edit event's info
                        System.out.println("What would you like to change the"
                                + " description to?");
                        events.get(i).setInfo(scanS.nextLine());
                        break;
                        
                    case 4: //remove event
                        events.remove(i);
                        break;
                } //end of switch 
                count++; //how many times an event appeared with same values
            } 
        if (count == 0) 
            System.out.println("Sorry that event does not exist. "
                    + "Check to see if you mispelled any of the words");
        
    } //end of editEvent
    
    /**
     * Method Name: printEvents
     * Description: print array list onto text file
     * @throws IOException 
     */
    public static void printEvents () throws IOException {
        
        FileWriter fwriter = new FileWriter ("/Users/iman/Desktop/"
                + "4U NetBeansProjects/files/Ahsan_culm.txt", false);
        PrintWriter outFile = new PrintWriter (fwriter);
        
        for (int i = 0; i < events.size(); i++) //print to text file
            outFile.println(events.get(i).getMonth() + ":" +  
                    events.get(i).getDay() + ":" + events.get(i).getInfo());
        
        outFile.close();
        
    } //end of printEvents
} //end of class
