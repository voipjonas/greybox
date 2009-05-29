package greybox.flow

import greybox.EndPoint

class SipFlow( fromEndPoint_ : EndPoint,
               toEndPoint_   : EndPoint ) extends Flow {

  def fromEndPoint = fromEndPoint_
  def toEndPoint   = toEndPoint_

  def receivePacket( packet : TransportLayerPacket ) = {
    
  }
  
}
