package src.org.calculator;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class CalculatorWS {

    @WebMethod
    public int add(int a, int b) {
        return a + b;
    }

    @WebMethod
    public int subtract(int a, int b) {
        return a - b;
    }

    @WebMethod
    public int multiply(int a, int b) {
        return a * b;
    }

    @WebMethod
    public int divide(int a, int b) {
        if (b == 0) throw new ArithmeticException("Divide by zero");
        return a / b;
    }
}
