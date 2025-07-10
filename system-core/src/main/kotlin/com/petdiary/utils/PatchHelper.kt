package com.petdiary.utils

import java.util.Optional
import kotlin.reflect.KMutableProperty1

class PatchHelper <T> (
    val target: T
) {
    fun <V> patch (value: V, prop: KMutableProperty1<T, V>) {
        if(value != null) {
            prop.set(target, value)
        }
    }

    fun <V> patch (optional: Optional<V>, prop: KMutableProperty1<T, V?>) {
        if(optional.isPresent) {
            prop.set(target, optional.get())
        } else {
            prop.set(target, null)
        }
    }
}