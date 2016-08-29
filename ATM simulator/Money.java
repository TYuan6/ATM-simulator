
package bankemployapplication;
/**
 *
 * @author Benjamin Berman
 */
public class Money {
	private int dollars;
	private int cents;
	
	public void setDollars(int dollars) {
		this.dollars = dollars;
	}
	
	public void setCents(int cents) {
		this.cents = cents;
	}
	
	public int getDollars() {
		return dollars;
	}
	
	public int getCents() {
		return cents;
	}
	
	//Precondition: non-negative dollars and cents.  Cents must be less than 100
	public Money(int dollars, int cents) {
		this.dollars = dollars;
		this.cents = cents;
	}
	
	//precondition: non-negative dollars
	public Money(int dollars) {
		this.dollars = dollars;
		cents = 0;
	}
	
	public Money() {
		dollars = 0;
		cents = 0;
	}
	
	public int compareTo(Money other) {
		if (dollars < other.dollars)
			return -1;
		else if (dollars > other.dollars)
			return 1;
		else { //in the case where dollars == other.dollars
			if (cents < other.cents)
				return -1;
			else if (cents > other.cents)
				return 1;
			else
				return 0; //dollars == other.dollars && cents == other.cents
		}
	}
	
	public boolean equals(Money other) {
		return compareTo(other) == 0;
		
		//return dollars == other.dollars && cents == other.cents;
	}
        
        public boolean lessThan(Money other) {
            return compareTo(other) < 0;
        }
	
	public String toString() {
                //return "$"+dollars+"."+cents;
		return cents < 10 ? "$"+dollars+".0"+cents : "$"+dollars+"."+cents;
	}
	
	public Money add(Money other) {
		return add(this, other); //call to static method
	}
	
	public Money subtract(Money other) {
		return subtract(this, other); //call to static method
	}
	
	public void addV2(Money other) {
		Money temp = this.add(other);  //call to instance method
		this.dollars = temp.dollars;
		this.cents = temp.cents;
	}
	
	public void subtractV2(Money other) {
		Money temp = subtract(other);  //call to instance method
		dollars = temp.dollars;
		cents = temp.cents;
	}
        
        //public static int adds = 0;

	public static Money add(Money m1, Money m2) {
          //      adds++;
            
		Money newMoney = new Money();
		
//		newMoney.dollars = m1.dollars + m2.dollars;
//		newMoney.cents = m1.cents + m2.cents;
//		if (newMoney.cents >= 100) {
//			newMoney.cents = newMoney.cents - 100;
//			newMoney.dollars++;
//		}
		
		newMoney.dollars = m1.dollars + m2.dollars + (m1.cents + m2.cents)/100;
		newMoney.cents = (m1.cents + m2.cents) % 100;
		
		return newMoney;
	}
	
        public static int subtracts = 0;
	
	public static Money subtract(Money bigger, Money smaller) {
		if (bigger.compareTo(smaller) == -1) //bigger is smaller than smaller
			return new Money(0,0);
		
		Money newMoney = new Money();
		
		newMoney.setDollars(bigger.getDollars() - smaller.getDollars());
		
		if (bigger.getCents() < smaller.getCents()) {
                        newMoney.setDollars(newMoney.getDollars() - 1);
			newMoney.setCents(100 + bigger.getCents() - smaller.getCents()) ;
		}
		
		return newMoney;
	}
	
	
	
}
