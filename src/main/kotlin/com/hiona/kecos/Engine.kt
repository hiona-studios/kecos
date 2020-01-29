package com.hiona.kecos

object Engine {

    private var gameLoop = listOf<System>()

    fun loadGameLoop(aGameLoop: List<System>) {
        gameLoop = aGameLoop
    }

    fun update(delta: Float) {
        gameLoop.forEach { it.update(delta) }
    }

}