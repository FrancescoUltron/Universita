import java.time.LocalDate;
import java.time.LocalTime;

public class Date implements Comparable{
	
	private int day;
	private int month;
	private int year;
	
	
	public Date() {
		this.day = LocalDate.now().getDayOfMonth();
		this.month = LocalDate.now().getMonthValue();
		this.year = LocalDate.now().getYear();
	}
	
	public Date(int day, int month, int year) throws DataNonConformeException {
		super();
		if(month > 12)
			throw new DataNonConformeException("mese: " + month);
		if(day > 31)
			throw new DataNonConformeException("mese: " + month);
		this.day = day;
		this.month = month;
		this.year = year;
	}
	public int getDay() {
		return day;
	}
	public int getMonth() {
		return month;
	}
	public int getYear() {
		return year;
	}
	@Override
	public String toString() {
		return "Date [day=" + day + ", month=" + month + ", year=" + year + "]";
	}
	
	public boolean equals(Date data) {
		if(this.getDay() == data.getDay() && this.getMonth() == data.getMonth() && this.getYear() == data.getYear())
			return true;
		else return false;
	}
	
	public boolean dopoDi(int anno) {
		if(this.year < anno)
			return false;
		else return true;
	}

	@Override
	public int compareTo(Object o) {
		if(this.year > ((Date) o).getYear() || (this.year == ((Date) o).getYear() && this.month > ((Date) o).getMonth()) ||
				(this.year == ((Date) o).getYear() && this.month == ((Date) o).getMonth() && this.day > ((Date) o).getDay()))
			return 1;
		else if(this.year == ((Date) o).getYear() && this.month == ((Date) o).getMonth() && this.day == ((Date) o).getDay())
			return 0;
		else return -1;
	}
	
	
	
	
}
