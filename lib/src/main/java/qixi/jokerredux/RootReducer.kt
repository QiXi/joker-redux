package qixi.jokerredux

abstract class RootReducer<State, Action> : Reducer<State, Action> {

    private val map: MutableMap<Action, MutableList<Reducer<State, Action>>> = mutableMapOf()

    override fun reduce(state: State, action: Action): State {
        val list = map[action]
        if (list == null) {
            println("no reducer for action:$action")
            return state
        }
        for (reducer in list) {
            reducer.reduce(state, action)
        }
        return state
    }

    protected fun addReducer(action: Action, reducer: Reducer<State, Action>) {
        var list = map[action]
        if (list == null) {
            list = mutableListOf()
            map[action] = list
        }
        if (!list.contains(reducer)) {
            list.add(reducer)
        }
    }

    protected fun removeReducer(action: Action, reducer: Reducer<State, Action>) {
        val list = map[action]
        if (list != null && list.contains(reducer)) {
            list.remove(reducer)
        }
    }

}