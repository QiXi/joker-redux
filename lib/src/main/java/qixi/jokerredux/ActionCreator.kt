package qixi.jokerredux

abstract class ActionCreator<Action>(protected val dispatcher: Dispatcher<Action>)