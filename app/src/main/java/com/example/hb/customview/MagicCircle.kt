package com.example.hb.customview

// NOTE les data class sont generalement utilisees pour les objets graphiques ou qui représentent des données d'une base ...
data class MagicCircle(var maxX: Float, var maxY: Float) {

    var cx: Float = 50F
    var cy: Float = 50F
    var rad: Float = 40F
    var delta = 10

    // sens de déplacement du cercle
    var dx = delta
    var dy = delta

    fun move(){
        // NOTE when comme un switch/case évolué
        when {
            cx !in 0F..maxX -> dx = -dx
            cy !in 0F..maxY -> dy = -dy
        }

        cx += dx
        cy += dy

    }
}