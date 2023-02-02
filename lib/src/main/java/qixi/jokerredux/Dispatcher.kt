package qixi.jokerredux

interface Dispatcher<Action> {
    fun dispatch(action: Action)
}