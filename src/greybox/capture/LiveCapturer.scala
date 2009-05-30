package greybox.capture

import jpcap.JpcapCaptor
import org.slf4j.LoggerFactory

class LiveCapturer extends Capturer {
  
  private val logger = LoggerFactory.getLogger(this.getClass) 
  
  def capture( interfaceName : String ) {

    val device = JpcapCaptor.getDeviceList.find(_.name == interfaceName)
    if( device == None ) {
      throw new IllegalArgumentException( String.format("Interface %s not found", interfaceName))
    }
    val jpcapCaptor = JpcapCaptor.openDevice(device.get, 1500, true, 500)
    logger.info("Opened pcap handle for interface {}", interfaceName)
    jpcapCaptor.loopPacket(-1, packetReceiver)
  }
  
}
