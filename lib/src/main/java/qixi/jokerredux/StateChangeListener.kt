package qixi.jokerredux

interface StateChangeListener<State, Action> {
    fun onStateChanged(state: State, action: Action)
}