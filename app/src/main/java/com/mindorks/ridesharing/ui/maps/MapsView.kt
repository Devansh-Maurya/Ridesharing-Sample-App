package com.mindorks.ridesharing.ui.maps

import com.google.android.gms.maps.model.LatLng

/**
 * Created by Devansh on 1/5/20
 */

interface MapsView {

    fun showNearbyCabs(latLngList: List<LatLng>)

    fun informCabBooked()

}