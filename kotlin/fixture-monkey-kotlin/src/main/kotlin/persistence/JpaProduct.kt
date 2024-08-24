package org.example.persistence

import javax.persistence.Entity

@Entity
class JpaProduct(
    val name: String,
    val price: Int
) : BaseEntity() {

    override fun toString(): String {
        return "JpaProduct(" +
                "id=${super.id}" +
                "name='$name'," +
                "price=$price," +
                "createdAt=${super.createdAt}," +
                "updatedAt=${super.updatedAt}"
    }
}
