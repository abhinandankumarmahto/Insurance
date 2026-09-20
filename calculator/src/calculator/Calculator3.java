package calculator;

public class Calculator3 
{
	public void sum(int x, int y) 
	{
		int sum=x+y;
		System.out.println(sum); 
	}
	
	public void sub(int x, int y)
	{
		int sub= x-y;
		System.out.println(sub);
		
	}
	
	public static void main(String[] args) 
	{
	Calculator3 cal =new Calculator3();
	cal.sub(8, 5);
	cal.sum(9, 5);
	
		
	}
}
