package greybox.recorder

import jpcap.JpcapCaptor
import greybox.capture.LiveCapturer

object Recorder {

  def main(args: Array[String]) {
/*
    val devices = JpcapCaptor.getDeviceList
    for( device <- devices ) {
      printf("Device %s (%s)\n", device.name, device.description)
      printf("datalink: %s (%s)\n", device.datalink_name, device.datalink_description)
      for( address <- device.addresses ) {
        printf("IP address: %s %s %s\n", address.address, address.subnet, address.broadcast)
      }

      printf("\n")
    }
*/    
    val capturer = new LiveCapturer("wlan0")
    
    ()
  }

}
