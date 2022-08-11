
package timevalueofmoney;

import java.text.NumberFormat;
import java.util.Scanner;

/**
 *
 * @author amadou
 */
public class TimeValueOfMoney {

    static Scanner sc = new Scanner(System.in);
    static NumberFormat Curr = NumberFormat.getCurrencyInstance();
    static NumberFormat Pct = NumberFormat.getPercentInstance();
    public static void main(String[] args) {
      int choice;
      System.out.println("Welcome to the Financial Calculator");
      Pct.setMaximumFractionDigits(3);
      Pct.setMinimumFractionDigits(3);
    
      choice = getChoice();
      
      while(choice != 0) {
        if (choice == 1) {
            doPV();
        }
        else if (choice == 2) {
            doFV();
        }
        
        else if (choice == 3) {
            doFVA();
        }
        
        else {
        System.out.println("Unknown Operator" + choice);
        }
        
        System.out.println();
        choice = getChoice();
        
      }// End of While
      System.out.println("Thanks for using the financial Calculator ");
    }// End of Main
    
    public static int getChoice() {
        int c = -1 ;
        
        do {
     try {
      System.out.print("Financial Operation: 1= PV  2 = FV  3 = FVA  0 = Quit ");
      c = sc.nextInt();
      if ( c < 0 || c > 3) {
      System.out.println("Error entries : 0-3 only ");
      }
      
         
     } catch(Exception e) {
      System.out.println("Unknown Operation ");
      sc.nextLine();
      c = -1;
        
     }
         
        } while (c < 0 || c > 3);
        
        return c;
    }// End of getChoice;
   
    public static void doPV() {
        
        double amount, rate, pv;
        int term;
        
        amount = getValue("Amount to be received in the future");
        rate = getValue("Annual Interest Rate (6.5% = 6.5): ");
           if (rate < 1.0 || rate > 25.0) {
          System.out.println(" Rate is out of bounds");
          rate = getValue("Annual Interest Rate (6.5% = 6.5): ");
           }
        term = getTerm();
        
        pv = amount / Math.pow((1+rate /100.0/12.0), term);
        
    System.out.println( Curr.format(amount) 
            + " received in 24 months discounted at an annual rate of : "
            + Pct.format(rate/100.0) + " has a value today of " + Curr.format(pv));
    System.out.println("The discount applied was " + Curr.format(amount -pv));
                
                
        
    }
        

        
    public static void doFV() {
        double amount, rate, fv;
        int term;
        
        amount = getValue("Amount to be deposited today? ");
        rate = getValue("Annual Interest Rate (6.5% = 6.5): ");
           while (rate < 1.0 || rate > 25.0) {
          System.out.println(" Rate is out of bounds");
          rate = getValue("Annual Interest Rate (6.5% = 6.5): ");
           }
        term = getTerm();
        
        fv = amount * Math.pow((1+rate /100.0/12.0), term);
        
        System.out.println("In " + Curr.format(term)
                + " months an amount of : " + Curr.format(amount) 
                + "deposited today and earning interest at an annual rate of: "
                + Pct.format(rate/100.0) + " will have a value of: " + Curr.format(fv));
        System.out.println(" This includes interest of: " + Curr.format(amount-fv));
        
    }
    
    public static void doFVA() {
      double amount, rate, fva;
      int term;
      
      amount = getValue("Amount to be deposited monthly ");
      rate = getValue("Annuel Interest rate 6.5% = 6.5 ");
      while (rate < 1.0 || rate > 25.0) {
          System.out.println(" Rate is out of bounds. Please re-enter");
          rate = getValue("Annuel Interest rate 6.5% = 6.5 ");
          
          
      }
      term = getTerm();
      
      fva = 0;
      
    for ( int i=1; i<=term; i++)  {
        double intearn = (fva + amount) * (rate/100.0/12.0);
        fva += (intearn + amount);
    }
    
    System.out.println(" An annuity of " + Curr.format(amount) + 
                 " per month at annual rate of " + Pct.format(rate/100.0) +
                " after " + term + " will have a value of: " + Curr.format(fva));
    
  System.out.println("That includes interest of: " + Curr.format((fva) - (amount*term)));
       
  }
    
   public static double getValue(String prompt) {
       double v = 0;
    do {
       try {
    System.out.println(prompt);
    v = sc.nextDouble();
    if (v <= 0) {
    System.out.println("Positive Values Only Please");
    }
    
       }catch (Exception e) {
   System.out.println("Illegal values : " + e.getMessage());
   sc.nextLine();
           
   v = -1;
           
       } 
       
    } while(v <= 0);
       
    return v;   
   
   }// End of getValue
   
   public static int getTerm() {
       int t = 0;
    do {
       try {
    System.out.println(" Annuel Monthly term");
    t = sc.nextInt();
    
        if (t < 0) {
    System.out.println("Term must be positive ");
    }
     
       } catch (Exception e) {
    System.out.println("Illegal entries" + e.getMessage());
    sc.nextLine();
    t = -1;
           
       }
        
    } while( t < 0);
    
    return t;
   }
}
