package greybox.capture

import org.slf4j.LoggerFactory

import greybox._
import greybox.flow._

class PacketReceiver extends jpcap.PacketReceiver {

  private val logger = LoggerFactory.getLogger(this.getClass)

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
    FlowManager.findFlowBetween( packet.source, packet.dest ) match {
      case None => 
        FlowManager.newFlowForPacket( packet )
      case Some(flow) =>
        flow.receivePacket( packet )
    }      
  }

}
