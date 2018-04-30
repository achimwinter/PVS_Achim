package assignment5.exercise3;

import java.rmi.Naming;

public class RMIClient {

    private static final String ADRESS = "rmi://localhost/" + IFacService.IFAC_SERVICE_NAME;

    public static void main(String... args){
        try {
            IFacService facService = (IFacService) Naming.lookup(ADRESS);

            System.out.println(facService.fac(5));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
