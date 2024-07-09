/* This program will compare Huge Integers for equality. It will also be capable of adding and
 * Subtracting Huge Integers stored in an int[] array. 
 */
import java.util.Scanner;

public class HugeInteger{
    private static final int NUM_DIGITS = 40; 
    private int digits[] = new int[NUM_DIGITS]; 
    private boolean positve;  

    //Create a huge integer from int. First element contains least significant digit.
    public HugeInteger(int num){
        for(int i=0; i<NUM_DIGITS; i++)
        {
            digits[i] = (int) num % 10;
            num /= 10;
            if(num == 0)
            {
                i = 0;
                break;
            }
        }
    }

    //Create huge integer from string
    public HugeInteger(String s){
        int length = s.length();
        for(int i=0; i<NUM_DIGITS; i++)
        {
            if(i < length)
            {
                digits[i] = Character.digit(s.charAt(length-i-1), 10);
            }
            else
            {
                i=0;
                break;
            }
        }
    }

    //Convert to string
    public String toString(){
        boolean Zero = true;
        StringBuffer s = new StringBuffer();
        //MSD to LSD
        for(int i=NUM_DIGITS-1; i>=0; i--)
        {
            if(digits[i] > 0 || !Zero)
            {
                s.append(Character.forDigit(digits[i], 10));
                Zero = false;
            }
        }
        return s.toString();
    }

    //Huge integer constructor
    public HugeInteger(int[] digits, boolean positve){
        this.positve = positve;
        System.arraycopy(digits, 0, this.digits, 0, NUM_DIGITS);
    }
    
    //Comparison of two huge integers
    public int compareTo(HugeInteger hi){
        if(this.positve && !hi.positve)
            return -1;
        if(!this.positve && hi.positve)
            return 1;

        for(int i=0; i<NUM_DIGITS; i++)
        {
            if(this.digits[i] < hi.digits[i])
                return -1;
            if(this.digits[i] > hi.digits[i])
                return 1;
        }
        return 0;
    }

    //Checks equality of huge integers
    public boolean isEqualTo(HugeInteger hi){
        return (this.compareTo(hi) == 0);
    }

    //Checks if huge integers are not equal
    public boolean isNotEqualTo(HugeInteger hi){
        return (this.compareTo(hi) != 0);
    }

    //Checks if one huge integer is greater than another
    public boolean isGreaterThan(HugeInteger hi){
        return (this.compareTo(hi) > 0);
    }

    //Checks if one huge integer is greater than or equal to another
    public boolean isGreaterThanOrEqualTo(HugeInteger hi){
        return (this.compareTo(hi) >= 0);
    }

    //Checks if one huge integer is less than another
    public boolean isLessThan(HugeInteger hi){
        return (this.compareTo(hi) < 0);
    }

    //Checks if one huge integer is less than or equal to another
    public boolean isLessThanOrEqualTo(HugeInteger hi){
        return (this.compareTo(hi) <= 0);
    }

    //Returns the absolute value
    public HugeInteger abs(){
        return new HugeInteger(this.digits, false);
    }

    //Negates huge integer
    public HugeInteger negate(){
        return new HugeInteger(this.digits, !this.positve);
    }

    //Adds two huge integers
    public HugeInteger add(HugeInteger hi){
        if(this.positve && !hi.positve)
            return hi.subtract(this.abs());
        if(!this.positve && hi.positve)
            return this.subtract(hi.abs());

        int carry = 0;
        int result[] = new int[NUM_DIGITS];
        for(int i=0; i<NUM_DIGITS; i++)
        {
            int sum = this.digits[i] + hi.digits[i] + carry;
            carry = sum / 10;
            result[i] = sum % 10;
        }
        return new HugeInteger(result, this.positve && hi.positve);
    }

    //Subtracts two huge integers
    public HugeInteger subtract(HugeInteger hi){
        if(this.positve && hi.positve)
            return this.add(hi.abs()); 
        if(this.positve && !hi.positve)
            return this.abs().add(hi).negate();
        if(!this.positve && hi.positve)
            return this.add(hi.abs());

        //Subtract the smaller integer from the larger
        boolean thisBigger = this.isGreaterThan(hi);
        HugeInteger bigger  = thisBigger ? this : hi;
        HugeInteger smaller = thisBigger ? hi : this;

        int borrow = 0;
        int result[] = new int[NUM_DIGITS];
        for(int i=0; i<NUM_DIGITS; i++)
        {
            int diff = bigger.digits[i] - smaller.digits[i] - borrow;
            if(diff < 0)
            {
                borrow = 1;
                result[i] = diff + 10;
            }
            else
            {
                borrow = 0;
                result[i] = diff;
            }
        }
        return new HugeInteger(result, !thisBigger);
    }
    
    public static void main(String args[]){
    	HugeInteger h1, h2;
    	String num;
        @SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
         
         
        System.out.print("Please enter the first huge integer (h1): ");
 		num=scan.nextLine();
 		h1=new HugeInteger(num);
 		
 		System.out.print("Please enter the second huge integer (h2): ");
 		num=scan.nextLine();
 		h2=new HugeInteger(num);
         
 		if(h1.isEqualTo(h2)){
			System.out.println("h1 is equal to h2.");
		}
		if(h1.isNotEqualTo(h2)){
			System.out.println("h1 is not equal to h2.");
		}
		if(h1.isGreaterThan(h2)){
			System.out.println("h1 is greater than h2.");
		}
		if(h1.isLessThan(h2)){
			System.out.println("h1 is less than h2.");
		}
		if(h1.isGreaterThanOrEqualTo(h2)){
			System.out.println("h1 is greater than or equal to h2.");
		}
		if(h1.isLessThanOrEqualTo(h2)){
			System.out.println("h1 is less than or equal to h2.");
		} 
		
		System.out.printf("h1+h2=%s\n",h2.add(h1).toString());
		System.out.printf("h1-h2=%s\n",h2.subtract(h1).toString());
		//h1.add(h2);
		//h1.multiply(h2);
		//System.out.printf("h1*h2=%s\n",h1);
        
    }
}