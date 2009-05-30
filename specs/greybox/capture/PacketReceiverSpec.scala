package greybox.capture

import greybox._
import greybox.flow._

import com.google.inject._
import org.specs._
import org.specs.mock.Mockito

object PacketReceiverSpec extends Specification with Mockito {
  
  "Receive new packet and start new flow" in {
    val mockFlowManager = mock[FlowManager]
    val injector = Guice.createInjector( new AbstractModule {
	    override def configure {	      
	      bind(classOf[FlowManager]).
	        toInstance(mockFlowManager)
	      bind(classOf[PacketReceiver]).
	        to(classOf[PacketReceiverImpl])
	    }
    } )
    val packetReceiver = injector.getInstance(classOf[PacketReceiver])
    val ep1 = EndPointMaker.newTcp(123)
    val ep2 = EndPointMaker.newTcp(456)
    val packet = new TransportLayerPacket( ep1, ep2, "Hello".getBytes )  
    mockFlowManager.findFlowBetween(ep1, ep2) returns None
    packetReceiver.receivePacket( packet ) 
    mockFlowManager.newFlowForPacket(packet) was called
  }

}

object EndPointMaker {
  def newTcp( port : Int ) : EndPoint = {
    new EndPoint( java.net.InetAddress.getByName("192.168.1.1"), Proto.TCP, port)
  }
}

/* Magical incantantion for our specs to run in JUnit */
import org.specs.runner.JUnit4
class PacketReceiverSpecTest extends JUnit4(PacketReceiverSpec)
