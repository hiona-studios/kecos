package com.hiona.kecos


class Entity {

    private val components = mutableListOf<Component<*>>()

    operator fun <T> get(component: Component<T>): T? = component at this

    operator fun <T> set(component: Component<T>, value: T?) {
        component[this] = value
    }

    fun <T> remove(component: Component<T>) {
        component[this] = null
    }

    override fun toString(): String {
        val componentsStr = components.fold("") { str, comp -> "$str, ${comp::class.java.simpleName} = ${comp at this}" }
        return "${hashCode()} { $componentsStr }"
    }
}

fun entity(init: Entity.() -> Unit) = Entity().apply { init(this) }