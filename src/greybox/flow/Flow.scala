package greybox.flow

trait Flow {

  def fromEndPoint : EndPoint
  def toEndPoint   : EndPoint

  def receivePacket( packet : TransportLayerPacket )

  def isBetween( endpointA : EndPoint, endpointB : EndPoint ) = {
    (fromEndPoint == endpointA && toEndPoint == endpointB) ||
      (fromEndPoint == endpointB && toEndPoint == endpointA) 
  }
  
  override def toString = 
    "(" + fromEndPoint.toString + " <==> " + toEndPoint.toString + ")"

}
