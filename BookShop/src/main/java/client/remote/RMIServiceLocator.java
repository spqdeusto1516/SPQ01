package client.remote;

import java.rmi.RMISecurityManager;

import server.remote.*;

public class RMIServiceLocator {
	
	 private IRemote service;

	    public RMIServiceLocator(){ 
	     
	    }

	    public void setService(String ip, String puerto, String nombre) {
	        
	        if (System.getSecurityManager() == null) {
	                        System.setSecurityManager(new RMISecurityManager());
	                }

	        try {
	        		//PRUEBA
	        		//String url="//127.0.0.1:1099/BetAgainServer";
	        		//EL QUE UTILIZAREMOS
	                String url = "//" + ip + ":" + puerto + "/" + nombre;
	                
	                //String nameUserS = "//" + args[0] + ":" + args[1] + "/" + args[3];
	                //String nameSongS = "//" + args[0] + ":" + args[1] + "/" + args[3];
	                System.out.println(url);
	                        service = (IRemote) java.rmi.Naming.lookup(url);
	                        System.out.println("OBTENIDO EL SERVICIO");
	                } catch (Exception e) {
	                        System.err.println("- Excepción al ejecutar el cliente: " + e.getMessage());
	                        e.printStackTrace();}
	    }
	    
	    

	    public IRemote getService() {       
	        return service;
	    }


}
