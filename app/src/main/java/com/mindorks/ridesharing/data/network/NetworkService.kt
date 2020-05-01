package com.mindorks.ridesharing.data.network

import com.mindorks.ridesharing.simulator.WebSocket
import com.mindorks.ridesharing.simulator.WebSocketListener

/**
 * Created by Devansh on 1/5/20
 */

class NetworkService {

    fun creatwWebSocket(webSocketListener: WebSocketListener): WebSocket {
        return WebSocket(webSocketListener)
    }

}