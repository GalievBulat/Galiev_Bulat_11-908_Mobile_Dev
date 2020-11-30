package com.kakadurf.newProject

class MusicRepository {
    companion object {

        private val list: ArrayList<MusicPiece> = ArrayList(16)
        private const val cover: Int = R.drawable.am_humbug

        init {


            list.add(
                MusicPiece(
                    "Cornerstone",
                    cover,
                    R.raw.am_cornerstone,
                    "",
                    "Arctic Monkeys",
                    "Humbug"
                )
            )
            list.add(
                MusicPiece(
                    "Crying Lightning",
                    cover,
                    R.raw.am_crying_lightning,
                    "",
                    "Arctic Monkeys",
                    "Humbug"
                )
            )
            list.add(
                MusicPiece(
                    "Dance Little Liar",
                    cover,
                    R.raw.am_dance_little_liar,
                    "",
                    "Arctic Monkeys",
                    "Humbug"
                )
            )
            list.add(
                MusicPiece(
                    "Dangerous Animals",
                    cover,
                    R.raw.test,
                    "",
                    "Arctic Monkeys",
                    "Humbug"
                )
            )

            /*
       list.add(MusicPiece("Fire And The Thud",cover,0,"","Arctic Monkeys","Humbug"))
       list.add(MusicPiece("My Propeller",cover,0,"","Arctic Monkeys","Humbug"))
       list.add(MusicPiece("Potion Approaching",cover,0,"","Arctic Monkeys","Humbug"))
       list.add(MusicPiece("Pretty Visitors",cover,0,"","Arctic Monkeys","Humbug"))
       list.add(MusicPiece("Secret Door",cover,0,"","Arctic Monkeys","Humbug"))
       list.add(MusicPiece("The Jeweller's Hands",cover,0,"","Arctic Monkeys","Humbug"))*/

        }
    }
    public fun getAll():List<MusicPiece>{
        return list
    }
    fun get(int: Int):MusicPiece{
        return list[int]
    }
    fun findNext(musicPiece: MusicPiece): MusicPiece{
        if (list.isNotEmpty()) {
            val ind = list.indexOf(musicPiece)
            return if (ind == list.size - 1)
                list[0]
            else
                list[ind + 1]
        }else throw RuntimeException("empty list")
    }
    fun findNext(int: Int): MusicPiece{
        if (list.isNotEmpty()) {
            return if (int == list.size - 1)
                list[0]
            else
                list[int + 1]
        }else throw RuntimeException("empty list")
    }
    fun findPrev(musicPiece: MusicPiece): MusicPiece{
        if (list.isNotEmpty()) {
            val ind = list.indexOf(musicPiece)
            if (ind == -1)
                throw RuntimeException("no element")
            return if (ind == 0)
                list[list.size-1]
            else
                list[ind - 1]
        }else throw RuntimeException("empty list")
    }
    fun findPrev(int: Int): MusicPiece{
        if (list.isNotEmpty()) {
            if (int == -1)
                throw RuntimeException("no element")
            return if (int == 0)
                list[list.size-1]
            else
                list[int - 1]
        }else throw RuntimeException("empty list")
    }
    fun find(musicPiece: MusicPiece):Int =
        list.indexOf(musicPiece)

}