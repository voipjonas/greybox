package greybox.capture

import jpcap.packet.Packet
import org.slf4j.LoggerFactory

class PacketReceiver( capturer_ : Capturer ) extends jpcap.PacketReceiver {

  private val logger = LoggerFactory.getLogger(this.getClass)
  
  def receivePacket( packet : Packet ) {
    logger.trace("Got packet")
  }

}
