package s7

data class Photo(val name: String)

class PhotoCollection(private val photos: List<Photo>) {
    fun iterator(): Iterator<Photo> = CyclicIterator(photos)
}

class CyclicIterator(private val items: List<Photo>) : Iterator<Photo> {
    private var currentIndex = 0

    override fun hasNext(): Boolean = items.isNotEmpty()

    override fun next(): Photo {
        if (currentIndex >= items.size) currentIndex = 0
        return items[currentIndex++]
    }
}

fun main() {
    val photos = listOf(Photo("Фото 1"), Photo("Фото 2"), Photo("Фото 3"))
    val photoCollection = PhotoCollection(photos)

    val photoIterator = photoCollection.iterator()
    repeat(10) {
        if (photoIterator.hasNext()) {
            println(photoIterator.next())
        }
    }
}
