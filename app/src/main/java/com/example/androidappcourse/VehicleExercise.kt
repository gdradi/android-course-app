package com.example.androidappcourse

/*
    Creare un modello di dominio che rappresenti:
    1. Vehicle    (numero di ruote, turnOn())
    2. Car   (numero di porte)
    3. Moto

    creare un oggetto Car, uno Moto, invocare su ciascuno
    il metodo turnOn
 */

abstract class Vechicle(val wheels: Int) {
    abstract fun turnOn();
}

class Car(val doors: Int) : Vechicle(4) {
    init {
        assert(doors == 2 || doors == 4);
    }

    override fun turnOn() {
        println("Brum brum");
    }
}

class Motorbike : Vechicle(2) {
    override fun turnOn() {
        println("MEEEEH MEEEEEEEEEEEEEEEHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH")
    }
}
