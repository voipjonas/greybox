package greybox

import java.net.InetAddress

/**
 * An end point (IP host + port) for a flow of data
 * between two IP devices
 */
class EndPoint( host_ : InetAddress, port_ : Short ) {

  require(port_ > 0)
  
  val host = host_
  val port = port_
  
  override def toString =
    String.format("EndPoint: %s,%s", host.toString, port.toString)

}
