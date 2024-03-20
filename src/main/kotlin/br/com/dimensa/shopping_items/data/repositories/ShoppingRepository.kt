package br.com.dimensa.shopping_items.data.repositories

import br.com.dimensa.shopping_items.data.models.ShoppingItem
import org.springframework.data.jpa.repository.JpaRepository

interface ShoppingRepository : JpaRepository<ShoppingItem, Long>