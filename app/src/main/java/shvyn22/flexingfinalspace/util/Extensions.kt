package shvyn22.flexingfinalspace.util

fun <E> MutableList<E>.removeAndAdd(
    items: Collection<E>
) {
    this.clear()
    this.addAll(items)
}