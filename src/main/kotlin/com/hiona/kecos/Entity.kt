package com.hiona.kecos

import kotlinx.serialization.Serializable

@Serializable
class Entity {

    val id = nextId++

    operator fun <T> get(component: Component<T>): T? = component[this]

    operator fun <T> set(component: Component<T>, value: T?) {
        component[this] = value
    }

    fun <T> remove(component: Component<T>) {
        component[this] = null
    }

    infix fun isA(tag: TagComponent) = this in tag
    infix fun tagAs(tag: TagComponent) = tag.tag(this)

    override fun hashCode(): Int = id
    override fun equals(other: Any?): Boolean = when (other) {
        is Entity -> id == other.id
        else -> false
    }

    companion object {
        private var nextId = 0
    }
}

fun entity(init: Entity.() -> Unit) = Entity().apply { init(this) }