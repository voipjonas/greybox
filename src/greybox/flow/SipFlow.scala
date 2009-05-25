package greybox.flow

import jpcap.packet.Packet

class SipFlow( fromEndPoint_ : EndPoint,
               toEndPoint_   : EndPoint ) extends Flow {

  def fromEndPoint = fromEndPoint_
  def toEndPoint   = toEndPoint_

  def acceptsPacket( packet : Packet ) = {
    true
  }
  
}
