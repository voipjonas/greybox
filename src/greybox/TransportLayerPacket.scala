package greybox

import jpcap.packet._

class TransportLayerPacket( from_ : EndPoint, to_ : EndPoint, data_ : Array[Byte] ) {

  val source = from_
  val dest   = to_
  val data   = data_
  
  def this( tcpPacket : TCPPacket ) =
    this( new EndPoint( tcpPacket.src_ip, Proto.TCP, tcpPacket.src_port ),
          new EndPoint( tcpPacket.dst_ip, Proto.TCP, tcpPacket.dst_port ),
          tcpPacket.data )

  def this( udpPacket : UDPPacket ) =
    this( new EndPoint( udpPacket.src_ip, Proto.UDP, udpPacket.src_port ),
          new EndPoint( udpPacket.dst_ip, Proto.UDP, udpPacket.dst_port ),
          udpPacket.data )
}
