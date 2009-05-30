package greybox.capture

import org.slf4j.LoggerFactory
import com.google.inject._

import greybox._
import greybox.flow._

trait PacketReceiver extends jpcap.PacketReceiver {
  
  def receivePacket( packet : TransportLayerPacket )
  
} 

class PacketReceiverImpl extends PacketReceiver {

  private val logger = LoggerFactory.getLogger(this.getClass)

  @Inject
  var flowManager : FlowManager = _  

  /** 
   * Match on the packet type and transform it into a more abstract representation,
   * the TransportLayerPacket
   */
  def receivePacket( packet : jpcap.packet.Packet ) {
    packet match {
      case tcpPacket : jpcap.packet.TCPPacket =>
        receivePacket( new TransportLayerPacket(tcpPacket) )
      
      case udpPacket : jpcap.packet.UDPPacket =>
        receivePacket( new TransportLayerPacket(udpPacket) )

      case _ =>
        logger.trace("Ignoring packet that is neither TCP nor UDP")
    }    
  }  
  
  def receivePacket( packet : TransportLayerPacket ) {
    flowManager.findFlowBetween( packet.source, packet.dest ) match {
      case None => 
        flowManager.newFlowForPacket( packet )
      case Some(flow) =>
        flow.receivePacket( packet )
    }      
  }

}
