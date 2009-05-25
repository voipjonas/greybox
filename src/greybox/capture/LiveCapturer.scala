package greybox.capture

import jpcap.JpcapCaptor
import org.slf4j.LoggerFactory

class LiveCapturer( intf_name_ : String ) extends Capturer {

  private val logger = LoggerFactory.getLogger(this.getClass) 
  
  private val device = JpcapCaptor.getDeviceList.find(_.name == intf_name_)
  if( device == None ) {
    throw new IllegalArgumentException( String.format("Interface %s not found", intf_name_))
  }
  private val jpcapCaptor = JpcapCaptor.openDevice(device.get, 1500, true, 500)
  logger.info("Opened pcap handle for interface {}", intf_name_)
  jpcapCaptor.loopPacket(-1, new PacketReceiver(this))
  
}
