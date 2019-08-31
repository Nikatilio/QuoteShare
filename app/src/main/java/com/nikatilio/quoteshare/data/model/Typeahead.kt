package com.nikatilio.quoteshare.data.model

import androidx.room.Entity

@Entity
data class Typeahead(
    val authors: Array<Item>,
    val tags: Array<Item>,
    val users: Array<Item>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Typeahead

        if (!authors.contentEquals(other.authors)) return false
        if (!tags.contentEquals(other.tags)) return false
        if (!users.contentEquals(other.users)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = authors.contentHashCode()
        result = 31 * result + tags.contentHashCode()
        result = 31 * result + users.contentHashCode()
        return result
    }
}

data class Item(
    val count: Int,
    val permalink: String,
    val name: String
)