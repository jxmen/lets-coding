fun doSomethingWithCollection(collection: Collection<String>): Collection<String>? {

    /**
     *     val groupsByLength = mutableMapOf<Int, MutableList<String>>()
     *     for (s in collection) {
     *         var strings: MutableList<String>? = groupsByLength[s.length]
     *         if (strings == null) {
     *             strings = mutableListOf()
     *             groupsByLength[s.length] = strings
     *         }
     *         strings.add(s)
     *     }
     */
    val groupsByLength = collection.groupBy { s -> s.length }

    /**
     *     var maximumSizeOfGroup = 0
     *     for (group in groupsByLength.values) {
     *         if (group.size > maximumSizeOfGroup) {
     *             maximumSizeOfGroup = group.size
     *         }
     *     }
      */
    val maximumSizeOfGroup = groupsByLength.values.map { group -> group.size }.maxOrNull()

    /**
     *     for (group in groupsByLength.values) {
     *         if (group.size == maximumSizeOfGroup) {
     *             return group
     *         }
     *     }
     *     return null
     */
    return groupsByLength.values.firstOrNull { group -> group.size == maximumSizeOfGroup }
}
