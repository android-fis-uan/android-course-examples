package co.edu.uan.hearthstonedex

data class Card(
    val id: String,
    val slug: String,
    val name: String,
    val image: String,
    val cropImage: String? = "https://d15f34w2p8l1cc.cloudfront.net/hearthstone/603c6da3133b0bfcdc267898cabda4167402e4c8ca7a4ab43d7c1e5466a7ebf8.jpg",
    val text: String
)

data class CardList(
    val cards: List<Card>
)