import ReverseModule.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ReverseClient {
    public static void main(String[] args) {
        try {
            // Initialize the ORB
            ORB orb = ORB.init(args, null);

            // Get a reference to the Naming Service
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // Resolve the object reference
            String name = "Reverse";
            Reverse reverseImpl = ReverseHelper.narrow(ncRef.resolve_str(name));

            // Take user input
            System.out.println("Enter a string to reverse:");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input = br.readLine();

            // Invoke the reverse_string method
            String result = reverseImpl.reverse_string(input);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
