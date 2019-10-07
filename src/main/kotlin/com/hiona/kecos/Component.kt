package com.hiona.kecos

abstract class Component<T> {

    private val values = mutableMapOf<Entity, T>()

    operator fun get(entity: Entity): T? = values[entity]
    operator fun set(entity: Entity, value: T?) {
        when (value) {
            null -> values.remove(entity)
            else -> values[entity] = value
        }
    }

    infix fun at(entity: Entity): T? = values[entity]
}