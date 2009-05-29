package greybox

import java.net.InetAddress

/**
 * Transport protocol
 */
object Proto extends Enumeration {
  val TCP = Value("TCP")
  val UDP = Value("UDP")
}

/**
 * An end point (IP host + port) for a flow of data
 * between two IP devices
 */
class EndPoint( host_ : InetAddress, proto_ : Proto.Value, port_ : Int ) {

  require(port_ > 0)
  
  val host  = host_
  val proto = proto_
  val port  = port_
  
  override def toString =
    String.format("%s,%s,%s", host.getHostAddress, proto.toString, port.toString)

  override def equals(other : Any) : Boolean = {
    other match {
      case otherEndPoint : EndPoint =>
        this.host == otherEndPoint.host &&
          this.proto == otherEndPoint.proto &&
          this.port == otherEndPoint.port
        
      case _ =>
        false
    }
  }
  
  override def hashCode = {
    41 * (
      41 * (
        41 + host.hashCode
      ) + proto.hashCode
    ) + port.hashCode
  }
}
