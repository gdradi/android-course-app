package com.example.androidappcourse

interface ICounter {
    fun inc(): Unit
    fun reset(): Unit
    fun print(): Unit
}

interface IDisplay {
    fun print(str: String)
}


open class SimpleCounter(protected var value: Int = 0): ICounter {

    override fun inc() {
        value += 1
    }

    override fun reset() {
        value = 0
    }

    override fun print() {
        println("value = $value")
    }
}

class CounterWithDisplay(val d: IDisplay) : SimpleCounter(0) {
    override fun print() {
        d.print("CounterWithDisplay - value: $value")
    }
}

class ConsoleDisplay : IDisplay {
    override fun print(str: String) {
        println(str)
    }
}
