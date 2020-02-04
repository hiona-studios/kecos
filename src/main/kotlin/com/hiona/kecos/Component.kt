package com.hiona.kecos

import kotlinx.serialization.Serializable

@Serializable
class Component<T> : Iterable<Map.Entry<Entity, T>> {

    private val values = mutableMapOf<Entity, T>()

    operator fun get(entity: Entity): T? = values[entity]

    operator fun set(entity: Entity, value: T?) {
        when (value) {
            null -> values.remove(entity)
            else -> values[entity] = value
        }
    }

    infix fun at(entity: Entity): T? = values[entity]

    override fun iterator(): Iterator<Map.Entry<Entity, T>> = values.iterator()

    fun forEach(action: (Entity, T) -> Unit) = values.forEach(action)

    fun forEachEntity(action: (Entity) -> Unit) = values.keys.forEach(action)

    fun forEachValue(action: (T) -> Unit) = values.values.forEach(action)

    fun clear() = values.clear()
}

@Serializable
class TagComponent : Iterable<Entity> {
    private val entities = mutableSetOf<Entity>()

    fun tag(entity: Entity) {
        entities.add(entity)
    }

    fun untag(entity: Entity) {
        entities.remove(entity)
    }

    operator fun contains(entity: Entity) = entity in entities

    override fun iterator() = entities.iterator()
}