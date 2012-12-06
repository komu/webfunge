package webfunge.utils

val String.indices: IntRange
    get() = 0..this.length-1

fun Char.toCharCode() = "$this".charCodeAt(0)

native fun String.charCodeAt(n: Int): Int = noImpl
