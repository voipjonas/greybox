package greybox.flow

import org.slf4j.LoggerFactory

object FlowManager {

  private val logger = LoggerFactory.getLogger(this.getClass)

  var flows : Set[Flow] = Set();

  def findFlowBetween( endpointA : EndPoint, endpointB : EndPoint ) = {
    flows.find( _.isBetween(endpointA, endpointB) )
  }
  
  def addFlow( flow : Flow ) {
    logger.info("Starting new flow {}", flow)
    flows += flow
  }
  
  def newFlowForPacket( packet : TransportLayerPacket ) {
    var flow = new SipFlow(packet.source, packet.dest)
    addFlow(flow)
  }
    
}
