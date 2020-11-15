package com.aliakberaakash.boston_hacks.core

import android.view.View

fun View.makeItVisible() {
    visibility = View.VISIBLE
}

fun View.makeItGone() {
    visibility = View.GONE
}

fun View.toggleVisibility(){
    visibility = if(visibility == View.VISIBLE)  View.GONE else View.VISIBLE
}