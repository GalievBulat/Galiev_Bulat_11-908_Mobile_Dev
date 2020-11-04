package com.kakadurf.newProject

class UserRepository(){
    companion object {
        private val list = ArrayList<User>()
    }
    init {
        with(list){
            add(User(1, "Kostyan","rer","a hobo"))
            add(User(2,"Andrey","rerree","IT giant"))
            add(User(3, "Sadam","No","lawnmower"))
            add(User(4,"Ivan","no more rerer","farmer"))
        }
    }
    fun getAll():List<User> {
        return list
    }
    fun findById(id:Int):User?{
        if(list.size<id || id<1)
            return null
        return list[id-1]
    }
    fun add(user:User){
        list.add(user)
        list.sortBy { u: User->u.id }
    }
    fun size() = list.size

}