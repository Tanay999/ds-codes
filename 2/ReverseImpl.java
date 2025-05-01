import ReverseModule.ReversePOA;

public class ReverseImpl extends ReversePOA {
    @Override
    public String reverse_string(String str) {
        // Reverse the input string
        StringBuilder reversed = new StringBuilder(str).reverse();
        return "Server sends: " + reversed.toString();
    }
}
