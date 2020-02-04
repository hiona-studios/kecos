package com.hiona.kecos

import kotlinx.serialization.*
import kotlinx.serialization.internal.IntDescriptor
import kotlin.math.max

//TODO use inline once it is serializable
@Serializable
class Entity private constructor(val id: Int) {

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

    @Serializer(forClass = Entity::class)
    companion object : KSerializer<Entity> {

        private var nextId = 0

        private val instances = mutableMapOf<Int, Entity>()

        internal fun create(id: Int = nextId++) = instances[id] ?: Entity(id).also {
            instances[id] = it
            nextId = max(nextId, id)
        }

        override val descriptor: SerialDescriptor = IntDescriptor.withName("Entity")

        override fun serialize(encoder: Encoder, obj: Entity) = encoder.encodeInt(obj.id)

        override fun deserialize(decoder: Decoder): Entity = create(decoder.decodeInt())
    }
}

fun entity(init: Entity.() -> Unit) = Entity.create().apply { init(this) }