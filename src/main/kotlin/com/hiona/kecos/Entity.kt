package com.hiona.kecos

class Entity {

    private val components = mutableMapOf<Component<*>, Any>()

    @Suppress("UNCHECKED_CAST")
    operator fun <T> get(component: Component<T>): T? = components[component] as T?

    operator fun <T> set(component: Component<T>, value: T?) {
        when (value) {
            null -> components.remove(component)
            else -> components[component] = value as Any
        }
    }

    fun remove(component: Component<*>) = components.remove(component)

    override fun toString(): String {
        return "${hashCode()} $components"
    }
}