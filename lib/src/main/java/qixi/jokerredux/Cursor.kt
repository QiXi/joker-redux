package qixi.jokerredux

interface Cursor<State, Action> {
    val state: State
    fun subscribe(subscriber: StateChangeListener<State, Action>): Cancelable
    fun unsubscribe(subscriber: StateChangeListener<State, Action>)
}