/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serveur;

import client.ServiceJoueurCorba;
import javax.naming.InitialContext;
import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

/**
 *
 * @author Goldwing
 */
public class Tier2_Corba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ORB orb = ORB.init(args,null);
            org.omg.CORBA.Object poaRef = orb.resolve_initial_references("RootPOA");
            POA rootpoa = POAHelper.narrow(poaRef);
            rootpoa.the_POAManager().activate();
            
            ServiceJoueurCorba service = new ServiceJoueurCorba();
            
            InitialContext ctxt = new InitialContext();
            ctxt.rebind("CorbaServer",  rootpoa.servant_to_reference(service));
            
            orb.run();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
