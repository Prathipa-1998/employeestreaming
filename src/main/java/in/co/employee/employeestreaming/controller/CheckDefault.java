package in.co.employee.employeestreaming.controller;

public interface CheckDefault {
default void myAbstractMethod()
{
	System.out.println("hi i am adeafukt method");
}
 static public void testStatic()
{
	System.out.println("cannot change me");
}
}
