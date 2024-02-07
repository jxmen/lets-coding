import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals

class HashTableCollisionTest {

    @Test
    fun `같은 해시 값을 리턴하더라도 linked list를 통해 충돌을 방지한다`() {
        val hashTable = HashTable<String, String>(10)
        hashTable.put("key1", "value1")
        hashTable.put("key2", "value2")
        hashTable.put("key3", "value3")
        hashTable.put("key4", "value4")
        hashTable.put("key5", "value5")

        listOf("key1", "key2", "key3", "key4", "key5").forEach { key ->
            assertEquals(0, hashTable.hash(key))
        }
        assertEquals(hashTable.get("key1"), "value1")
        assertEquals(hashTable.get("key2"), "value2")
        assertEquals(hashTable.get("key3"), "value3")
        assertEquals(hashTable.get("key4"), "value4")
        assertEquals(hashTable.get("key5"), "value5")
    }

}

data class KeyValueData<T, U> (val key: T, val value: U)

class HashTable<T, U>(size: Int) : HashAble<T> {
    private val hashTable : List<LinkedList<KeyValueData<T, U>>> = List(size) { LinkedList<KeyValueData<T, U>>() }

    fun put(key: T, value: U) {
        val hashed = hash(key)

        hashTable[hashed]
            .add(KeyValueData(key, value))
    }

    fun get(key: T): U {
       val hashed = hash(key)

        val find = hashTable[hashed]
            .find { it.key == key }

        return find!!.value
    }

    override fun hash(key: T): Int {
        // 극단적인 상황을 가정하여 해시가 항상 0을 리턴하도록 설정
        return 0
    }

}

interface HashAble<T> {
    fun hash(key: T): Int
}
