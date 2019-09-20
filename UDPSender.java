import java.net.*;
import java.util.Scanner;

//Team w1
public class UDPSender {
private final static int PACKETSIZE = 100 ;
	public static void main(String[] args) 
   {
	      // Check the arguments
	      if( args.length != 3 )
	      {
	         System.out.println( "usage: java UDPSender host port" ) ;
	         return ;
	      }
	      DatagramSocket socket = null ;
              DatagramSocket echoSocket = null ;
	      try
	      {
	         // Convert the arguments first, to ensure that they are valid
	         InetAddress host = InetAddress.getByName( args[0] ) ;
	         int port         = Integer.parseInt( args[1] ) ;
		 int num          = Integer.parseInt( args[2] );
	         socket = new DatagramSocket() ;
                 echoSocket = new DatagramSocket(1001);
     
	         
	         String message = "Messages are cool";
	         while (true)
	         {
	        	 
	        	 if (message.length()==0) break;
	        	 for(int i = 0; i <= num; i++) {
                                String messageNum = message + Integer.toString(i);
			 	byte [] data = messageNum.getBytes() ;
	        	 	DatagramPacket packet = new DatagramPacket( data, data.length, host, port ) ;
	        	 	socket.send( packet ) ;

				DatagramPacket echoedPac = new DatagramPacket(new byte[PACKETSIZE], PACKETSIZE);
				echoSocket.receive(echoedPac);

				System.out.println( echoedPac.getAddress() + " " + echoedPac.getPort() + ": " + new String(echoedPac.getData()).trim() ) ;	
			 }
	         } 
	         System.out.println ("Closing down");
	      }
	      catch( Exception e )
	      {
	         System.out.println( e ) ;
	      }
	      finally
	      {
	         if( socket != null )
	            socket.close() ;
      }
   }
}
