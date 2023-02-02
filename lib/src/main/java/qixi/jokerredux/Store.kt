package qixi.jokerredux

open class Store<State, Action>(private val reducer: Reducer<State, Action>, override val state: State) :
    Cursor<State, Action>, Dispatcher<Action> {

    private val subscribers = HashSet<StateChangeListener<State, Action>>()
    private val pendingAdditions = HashSet<StateChangeListener<State, Action>>()
    private val pendingRemovals = HashSet<StateChangeListener<State, Action>>()

    override fun subscribe(subscriber: StateChangeListener<State, Action>): Cancelable {
        pendingAdditions.add(subscriber)
        return object : Cancelable {
            override fun cancel() {
                pendingRemovals.add(subscriber)
            }
        }
    }

    override fun unsubscribe(subscriber: StateChangeListener<State, Action>) {
        pendingRemovals.add(subscriber)
    }

    override fun dispatch(action: Action) {
        val state = reduce(action)
        if (state != null) {
            emitStoreChange(state, action)
        }
    }

    private fun reduce(action: Action): State? {
        return reducer.reduce(state, action)
    }

    private fun emitStoreChange(state: State, action: Action) {
        commitUpdates()
        for (subscriber in subscribers) {
            subscriber.onStateChanged(state, action)
        }
    }

    private fun commitUpdates() {
        if (pendingAdditions.size > 0 || pendingRemovals.size > 0) {
            println("commitUpdates subscribers=${subscribers.size} +${pendingAdditions.size} -${pendingRemovals.size}")
        }
        if (pendingAdditions.isNotEmpty()) {
            subscribers.addAll(pendingAdditions)
            pendingAdditions.clear()
        }
        if (pendingRemovals.isNotEmpty()) {
            subscribers.removeAll(pendingRemovals)
            pendingRemovals.clear()
        }
    }

}