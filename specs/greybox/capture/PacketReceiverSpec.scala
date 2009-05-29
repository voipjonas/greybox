package greybox.capture

import org.specs._
import greybox._

object PacketReceiverSpec extends Specification {

  var packetReceiver : PacketReceiver = new PacketReceiver 
  
  "Receive new packet and start new flow" in {
    packetReceiver.receivePacket( new TransportLayerPacket( EndPointMaker.newTcp(123),
                                EndPointMaker.newTcp(456),
                                "Hello".getBytes ) )
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
