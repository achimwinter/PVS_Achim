package assignment5.exercise3;

import java.rmi.Naming;

public class RMIServer {

    public static void main(String... args){
        try {
            IFacService facService = new FacServiceImpl();
            Naming.rebind("FacService", facService);
            System.out.println("Server is running");
        }catch (Exception e){
            e.printStackTrace();
        }

    }



}
