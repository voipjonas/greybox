package greybox.recorder

import greybox.capture._
import greybox.flow._

import jpcap.JpcapCaptor
import com.google.inject._

class RecorderGuiceModule extends AbstractModule {
  override def configure {
    bind(classOf[Capturer]).
      to(classOf[LiveCapturer])
    
    bind(classOf[FlowManager]).
      to(classOf[FlowManagerImpl])
      
    bind(classOf[PacketReceiver]).
      to(classOf[PacketReceiverImpl])
  }
}

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
    val injector = Guice.createInjector( new RecorderGuiceModule )
    val capturer = injector.getInstance(classOf[Capturer]) 
    capturer.capture("wlan0")
    
    ()
  }

}
