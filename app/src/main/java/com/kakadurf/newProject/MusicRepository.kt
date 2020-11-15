package com.kakadurf.newProject

class MusicRepository {
    private val list:ArrayList<MusicPiece> = ArrayList(16)
    init {
        val cover:Int = R.drawable.am_humbug
        list.add( MusicPiece("Cornerstone",cover,0,"","Arctic Monkeys","Humbug"))
        list.add(MusicPiece("Crying Lightning",cover,0,"","Arctic Monkeys","Humbug"))
        list.add(MusicPiece("Dance Little Liar",cover,0,"","Arctic Monkeys","Humbug"))
        list.add(MusicPiece("Dangerous Animals",cover,0,"","Arctic Monkeys","Humbug"))
        list.add(MusicPiece("Fire And The Thud",cover,0,"","Arctic Monkeys","Humbug"))
        list.add(MusicPiece("My Propeller",cover,0,"","Arctic Monkeys","Humbug"))
        list.add(MusicPiece("Potion Approaching",cover,0,"","Arctic Monkeys","Humbug"))
        list.add(MusicPiece("Pretty Visitors",cover,0,"","Arctic Monkeys","Humbug"))
        list.add(MusicPiece("Secret Door",cover,0,"","Arctic Monkeys","Humbug"))
        list.add(MusicPiece("The Jeweller's Hands",cover,0,"","Arctic Monkeys","Humbug"))
    }

    public fun getAll():List<MusicPiece>{
        return list
    }
}