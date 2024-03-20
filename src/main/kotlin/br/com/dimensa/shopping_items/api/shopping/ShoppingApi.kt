package br.com.dimensa.shopping_items.api.shopping

import br.com.dimensa.shopping_items.data.models.ShoppingItem
import br.com.dimensa.shopping_items.data.repositories.ShoppingRepository
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@Service
@RestController
@RequestMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
class ShoppingApi(
    val shoppingRepository: ShoppingRepository
) {
    @GetMapping("/shopping/items")
    fun items() = shoppingRepository.findAll()

    @PostMapping("/shopping/item")
    fun createItem(@RequestBody item: ShoppingItem) = shoppingRepository.save(item)

    @DeleteMapping("/shopping/item/{id}")
    fun deleteItem(@PathVariable("id") id: Long) = shoppingRepository.delete(findItem(id))

    @PutMapping("/shopping/item/{id}")
    fun updateItem(
        @PathVariable("id") id: Long,
        @RequestBody item: ShoppingItem,
    ) = findItem(id).run {
        shoppingRepository.save(
            this.copy(
                name = item.name,
                quantity = item.quantity
            )
        )
    }

    private fun findItem(id: Long) = shoppingRepository.findById(id).orElseThrow {
        ResponseStatusException(HttpStatus.NOT_FOUND)
    }
}
