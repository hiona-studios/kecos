package com.hiona.kecos

class Component<T> {

    private val values = mutableMapOf<Entity, T>()

    operator fun get(entity: Entity): T? = values[entity]

    operator fun set(entity: Entity, value: T?) {
        when (value) {
            null -> values.remove(entity)
            else -> values[entity] = value
        }
    }

    infix fun at(entity: Entity): T? = values[entity]

    fun forEachEntity(action: (Entity) -> Unit) {
        values.keys.forEach(action)
    }

    fun forEachValue(action: (T) -> Unit) {
        values.values.forEach(action)
    }

    fun forEach(action: (Entity, T) -> Unit) {
        values.asSequence().forEach{ (e, v) -> action(e, v) }
    }

    fun <R> map(transform: (Entity, T) -> R): List<R> = values.map { (e, v) -> transform(e, v) }

}

class TagComponent {
    private val entities = mutableSetOf<Entity>()

    fun tag(entity: Entity) {
        entities.add(entity)
    }

    fun untag(entity: Entity) {
        entities.remove(entity)
    }

    operator fun contains(entity: Entity) = entity in entities

    fun forEach(action: (Entity) -> Unit) {
        entities.forEach(action)
    }
    fun <R> map(transform: (Entity) -> R): List<R> = entities.map(transform)
}