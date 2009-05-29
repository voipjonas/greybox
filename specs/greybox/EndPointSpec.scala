package greybox

import java.net.InetAddress
import org.specs._

object EndPointSpec extends Specification {

  "Two EndPoint objects with the same params should be equal" in {
     val ep1 = new EndPoint( InetAddress.getByName("192.168.1.1"), Proto.TCP, 123 )
     val ep2 = new EndPoint( InetAddress.getByName("192.168.1.1"), Proto.TCP, 123 )
     ep1 must be_==(ep2)
  }

  "Two EndPoint objects with different protocol should not be equal" in {
     val ep1 = new EndPoint( InetAddress.getByName("192.168.1.1"), Proto.TCP, 123 )
     val ep2 = new EndPoint( InetAddress.getByName("192.168.1.1"), Proto.UDP, 123 )
     ep1 must be_!=(ep2)
  }

}

/* Magical incantantion for our specs to run in JUnit */
import org.specs.runner.JUnit4
class EndPointSpecTest extends JUnit4(EndPointSpec)
