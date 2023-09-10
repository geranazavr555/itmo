package dijkstra.messages

sealed class Message

data class DistMessage(val dist: Long) : Message()
data class  DistAckMessage(val activated: Boolean) : Message()
object CutMessage : Message()
