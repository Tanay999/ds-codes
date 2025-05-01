import ReverseModule.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;

public class ReverseServer {
    public static void main(String[] args) {
        try {
            // Initialize the ORB
            ORB orb = ORB.init(args, null);

            // Get reference to RootPOA and activate the POAManager
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            // Create the servant and register it with the ORB
            ReverseImpl reverseImpl = new ReverseImpl();
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(reverseImpl);
            Reverse href = ReverseHelper.narrow(ref);

            // Bind the object reference in the Naming Service
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            NameComponent path[] = ncRef.to_name("Reverse");
            ncRef.rebind(path, href);

            System.out.println("ReverseServer ready and waiting...");
            orb.run(); // Wait for client requests
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
