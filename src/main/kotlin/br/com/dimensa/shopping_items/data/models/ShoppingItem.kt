package br.com.dimensa.shopping_items.data.models

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
data class ShoppingItem(
    @Id
    @GeneratedValue
    val id: Long? = null,
    val name: String,
    val quantity: Int,
)
