package com.kakadurf.newProject

class Fox(_name: String): Pet(_name), Stealing {
    constructor(_name: String, _age: Int,_isAMale : Boolean): this(_name){
        age = _age
        isAMale = _isAMale
    }
    constructor(_name: String, _age: Int,_isAMale : Boolean,_size: Int): this(_name,_age,_isAMale){
        size = _size
    }
    override fun makeASound(): String = "woof"
    override fun makeAFunnySound(): String = "hrfhfrfr"
    override fun steal(p: Pet?): Pet? {
        return null
    }

    override fun sayThatHeHadVanished(): String = "gfgfghhr"

}