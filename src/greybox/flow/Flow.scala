package greybox.flow

import jpcap.packet.Packet

trait Flow {

  def fromEndPoint : EndPoint
  def toEndPoint   : EndPoint

  def acceptsPacket( packet : Packet ) : Boolean;

}
