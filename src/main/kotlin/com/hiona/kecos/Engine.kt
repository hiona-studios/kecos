package com.hiona.kecos

class Engine {

    private val _entities = mutableListOf<Entity>()

    val entities: List<Entity>
        get() = _entities

    fun add(entity: Entity) {
        _entities.add(entity)
    }
    fun remove(entity: Entity) {
        _entities.remove(entity)
    }


    private val _systems = mutableListOf<System>()

    val systems: List<System>
        get() = _systems

    fun add(system: System) {
        _systems.add(system)
    }
    fun remove(system: System) {
        _systems.remove(system)
    }

    fun update(delta: Float) {
        _systems.forEach { it.update(delta) }
    }

}