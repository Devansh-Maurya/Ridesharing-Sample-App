package com.mindorks.ridesharing.ui.maps

import com.google.android.gms.maps.model.LatLng

/**
 * Created by Devansh on 1/5/20
 */

interface MapsView {

    fun showNearbyCabs(latLngList: List<LatLng>)

    fun informCabBooked()

    fun showPath(latLngList: List<LatLng>)

    fun updateCabLocationLatLng(latLng: LatLng)

    fun informCabIsArriving()

    fun informCabArrived()

    fun informTripStart()

    fun informTripEnd()

    fun showRoutesNotAvailableError()

    fun showDirectionApiFailedError(error: String)
}