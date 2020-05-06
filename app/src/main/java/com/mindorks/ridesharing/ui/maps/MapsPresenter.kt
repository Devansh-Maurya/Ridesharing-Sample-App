package com.mindorks.ridesharing.ui.maps

import android.util.Log
import com.google.android.gms.maps.model.LatLng
import com.mindorks.ridesharing.data.network.NetworkService
import com.mindorks.ridesharing.simulator.WebSocket
import com.mindorks.ridesharing.simulator.WebSocketListener
import com.mindorks.ridesharing.utils.Constants
import org.json.JSONObject

/**
 * Created by Devansh on 1/5/20
 */

class MapsPresenter(private val networkService: NetworkService): WebSocketListener {

    companion object {
        private const val TAG = "MapsPresenter"
    }

    private var view: MapsView? = null
    private lateinit var webSocket: WebSocket

    fun onAttach(view: MapsView) {
        this.view = view
        webSocket = networkService.createWebSocket(this)
        webSocket.connect()
    }

    fun onDetach() {
        webSocket.disconnect()
        view = null
    }

    override fun onConnect() {
        Log.d(TAG, "onConnect")
    }

    override fun onMessage(data: String) {
        Log.d(TAG, "onMessage data : $data")
        val jsonObject = JSONObject(data)

        when(jsonObject.getString(Constants.TYPE)) {
            Constants.NEARBY_CABS -> {
                handleOnMessageNearbyCabs(jsonObject)
            }
            Constants.CAB_BOOKED -> {
                view?.informCabBooked()
            }
            Constants.PICKUP_PATH -> {
                val jsonArray = jsonObject.getJSONArray("path")
                val pickupPath = arrayListOf<LatLng>()

                for (i in 0 until jsonArray.length()) {
                    val location = jsonArray.get(i) as JSONObject
                    val lat = location.getDouble(Constants.LAT)
                    val lng = location.getDouble(Constants.LNG)
                    pickupPath.add(LatLng(lat, lng))
                }
                view?.showPath(pickupPath)
            }
        }
    }

    override fun onDisconnect() {
        Log.d(TAG, "onDisconnect")
    }

    override fun onError(error: String) {
        Log.d(TAG, "onError : $error")
    }

    fun requestNearbyCabs(latLng: LatLng) {
        val jsonObject = JSONObject().apply {
            put(Constants.TYPE, Constants.NEARBY_CABS)
            put(Constants.LAT, latLng.latitude)
            put(Constants.LNG, latLng.longitude)
        }
        webSocket.sendMessage(jsonObject.toString())
    }

    fun requestCab(pickupLatLng: LatLng, dropLatLng: LatLng) {
        val jsonObject = JSONObject().apply {
            put(Constants.TYPE, Constants.REQUEST_CAB)
            put("pickUpLat", pickupLatLng.latitude)
            put("pickUpLng", pickupLatLng.longitude)
            put("dropLat", dropLatLng.latitude)
            put("dropLng", dropLatLng.longitude)
        }
        webSocket.sendMessage(jsonObject.toString())
    }

    private fun handleOnMessageNearbyCabs(jsonObject: JSONObject) {
        val nearbyCabLocations = arrayListOf<LatLng>()
        val jsonArray = jsonObject.getJSONArray(Constants.LOCATIONS)

        for (i in 0 until jsonArray.length()) {
            val location = jsonArray.get(i) as JSONObject
            val lat = location.getDouble(Constants.LAT)
            val lng = location.getDouble(Constants.LNG)

            nearbyCabLocations.add(LatLng(lat, lng))
        }
        view?.showNearbyCabs(nearbyCabLocations)
    }
}