package com.yoochangwonspro.usedtradeproject.chatdetail

data class ChatItem(
    val senderId: String,
    val message: String
) {
    constructor() : this("", "")
}
