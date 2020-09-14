package com.kakadurf.newProject

class Cat(_name: String): Pet(_name) {
    constructor(_name: String, _age: Int,_isAMale : Boolean): this(_name){
        age = _age
        isAMale = _isAMale
    }
    constructor(_name: String, _age: Int,_isAMale : Boolean,_size: Int): this(_name,_age,_isAMale){
        size = _size
    }
    override fun makeASound(): String = "meow"
    override fun makeAFunnySound(): String = "mrrrmhhhh"
}