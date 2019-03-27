package com.hiona.kecos

import org.junit.Before
import org.junit.Test

class EngineTest {

    lateinit var engine: Engine

    @Before
    fun setUp() {
        engine = Engine()
    }

    @Test
    fun add_remove_entity() {
        val entity = Entity()
        engine.add(entity)
        assert(engine.entities.contains(entity))
        engine.remove(entity)
        assert(!engine.entities.contains(entity))
    }

    @Test
    fun add_remove_system() {
        val system = object : System {
            override fun update(delta: Float) {
            }
        }
        engine.add(system)
        assert(engine.systems.contains(system))
        engine.remove(system)
        assert(!engine.systems.contains(system))
    }

    @Test
    fun update() {
    }
}