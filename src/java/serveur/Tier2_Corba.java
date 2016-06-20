/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serveur;

import Database.RemoteServer;
import Database.Server;
import SynchronisationClient.RemoteSynchronisationClient;
import SynchronisationClient.SynchronisationClient;
import client.ServiceJoueurCorba;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
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
        
        Registry registry = null;
        try {
            registry = LocateRegistry.createRegistry(1099);
        } catch (RemoteException ex) {
            try {
                registry = LocateRegistry.getRegistry();
            } catch (RemoteException ex1) {
                ex1.printStackTrace();
            }
        }
        try {
            SynchronisationClient obj = new SynchronisationClient();
            RemoteSynchronisationClient stub = (RemoteSynchronisationClient) UnicastRemoteObject.exportObject(obj, 0);
            registry.bind("Synchro", stub);
            System.err.println("Server ready");
        } 
        catch (java.rmi.AlreadyBoundException e)
        {
            //its okay
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
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
    
    private Registry GetSafeRegistry()
    {
        Registry registry = null;
        try {
            registry = LocateRegistry.createRegistry(1099);
        } catch (RemoteException ex) {
            try {
                registry = LocateRegistry.getRegistry();
            } catch (RemoteException ex1) {
                return null;
            }
        }
        return registry;
    }
    
}
