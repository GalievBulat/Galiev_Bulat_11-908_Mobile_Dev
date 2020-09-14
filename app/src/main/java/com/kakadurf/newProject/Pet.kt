package com.kakadurf.newProject

abstract class Pet(_name: String) {
    private val name: String = _name
    protected var age: Int = 0
    protected var size: Int = 0
    protected var isAMale: Boolean = true
    override fun toString(): String {
        return (this.javaClass.toString() +
                " name: %s, age: %s,size: %s, is a male: %b".format(name, age, size, isAMale))
    }
    fun ageUp(){
        age++
        size+=10
    }
    abstract fun makeASound(): String
    abstract fun makeAFunnySound(): String
}