package greybox.capture

import com.google.inject._

trait Capturer {
  
  @Inject
  var packetReceiver : PacketReceiver = _
  
  def capture( interfaceName : String )

}
