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
                    "I thought I saw you in the battleship\n" +
                            "But it was only a look a like\n" +
                            "She was nothing but a vision trick\n" +
                            "Under the warning light\n" +
                            "She was close, close enough to be your ghost\n" +
                            "But my chances turned to toast\n" +
                            "When I asked her if I could call her your name\n" +
                            "I thought I saw you in the rusty hook\n" +
                            "Huddled up in wicker chair\n" +
                            "I wandered up for a closer look\n" +
                            "And kissed who ever was sitting there\n" +
                            "She was close, and she held me very tightly\n" +
                            "'Til I asked awfully politely, please\n" +
                            "Can I call you her name\n" +
                            "And I elongated my lift home,\n" +
                            "Yeah I let him go the long way round\n" +
                            "I smelt your scent on the seat belt\n" +
                            "And kept my shortcuts to myself\n" +
                            "I thought I saw you in the parrots beak\n" +
                            "Messing with the smoke alarm\n" +
                            "It was too loud for me to hear her speak\n" +
                            "And she had a broken arm\n" +
                            "It was close, so close that the walls were wet\n" +
                            "And she wrote it out in letraset\n" +
                            "No you can't call me her name\n" +
                            "Tell me where's your hiding place\n" +
                            "I'm worried I'll forget your face\n" +
                            "And I've asked everyone\n" +
                            "I'm beginning to think I imagined you all along\n" +
                            "I elongated my lift home\n" +
                            "Yeah I let him go the long way 'round\n" +
                            "I smelt your scent on the seat belt\n" +
                            "And kept my shortcuts to myself\n" +
                            "I saw your sister in the cornerstone\n" +
                            "On the phone to the middle man\n" +
                            "When I saw that she was on her own\n" +
                            "I thought she might understand\n" +
                            "She was close, well you couldn't get much closer\n" +
                            "She said I'm really not supposed to but yes,\n" +
                            "You can call me anything you want",
                    "Arctic Monkeys",
                    "Humbug"
                )
            )
            list.add(
                MusicPiece(
                    "Crying Lightning",
                    cover,
                    R.raw.am_crying_lightning,
                    "Outside the cafe by the cracker factory\n" +
                            "You were practicing a magic trick\n" +
                            "And my thoughts got rude, as you talked and chewed\n" +
                            "On the last of your pick and mix\n" +
                            "Said your mistaken if your thinking that I haven't been called cold before\n" +
                            "As you bit into your strawberry lace\n" +
                            "And then a flip in your attention in the form of a gobstopper\n" +
                            "Is all you have left and it was going to waste\n" +
                            "Your past-times, consisted of the strange\n" +
                            "And twisted and deranged\n" +
                            "And I love that little game you had called\n" +
                            "Crying lightning\n" +
                            "And how you like to aggravate the ice-cream man on rainy afternoons\n" +
                            "The next time that I caught my own reflection\n" +
                            "It was on it's way to meet you\n" +
                            "Thinking of excuses to postpone\n" +
                            "You never look like yourself from the side\n" +
                            "But your profile did not hide\n" +
                            "The fact you knew I was approaching your throne\n" +
                            "With folded arms you occupy the bench like toothache\n" +
                            "Stood and puff your chest out like you never lost a war\n" +
                            "And though I try so not to suffer the indignity of a reaction\n" +
                            "There was no cracks to grasp or gaps to claw\n" +
                            "And your past-times, consisted of the strange\n" +
                            "And twisted and deranged\n" +
                            "And I hate that little game you had called\n" +
                            "Crying lightning\n" +
                            "And how you like to aggravate the icky man on rainy afternoons\n" +
                            "Uninviting\n" +
                            "But not half as impossible as everyone assumes\n" +
                            "You are crying lightning\n" +
                            "Your past-times, consisted of the strange\n" +
                            "And twisted and deranged\n" +
                            "And I hate that little game you had called\n" +
                            "Crying lightning\n" +
                            "Crying lightning\n" +
                            "Crying lightning\n" +
                            "Crying lightning\n" +
                            "Your past-times, consisted of the strange\n" +
                            "And twisted and deranged\n" +
                            "And I hate that little game you had called\n" +
                            "Crying",
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
                    "TEST",
                    R.drawable.ic_launcher_foreground,
                    R.raw.test,
                    "-",
                    "-",
                    "-"
                )
            )
            list.add(
                MusicPiece(
                    "Fluorescent Adolescent",
                    R.drawable.am_fvm,
                    R.raw.am_fa,
                    "[Verse 1]\n" +
                            "You used to get it in your fishnets\n" +
                            "Now you only get it in your night dress\n" +
                            "Discarded all the naughty nights for niceness\n" +
                            "Landed in a very common crisis\n" +
                            "Everything's in order in a black hole\n" +
                            "Nothing seems as pretty as the past though\n" +
                            "That Bloody Mary's lacking in Tabasco\n" +
                            "Remember when you used to be a rascal?\n" +
                            "\n" +
                            "[Chorus 1]\n" +
                            "Oh, the boy's a slag, the best you ever had\n" +
                            "The best you ever had is just a memory and those dreams\n" +
                            "Weren't as daft as they seem, not as daft as they seem\n" +
                            "My love, when you dream them up\n" +
                            "\n" +
                            "[Verse 2]\n" +
                            "Flicking through a little book of sex tips\n" +
                            "Remember when the boys were all electric?\n" +
                            "Now when she's told she's gonna get it\n" +
                            "I'm guessing that she'd rather just forget it\n" +
                            "Clinging to not getting sentimental\n" +
                            "Said she wasn't going but she went still\n" +
                            "Likes her gentlemen not to be gentle\n" +
                            "Was it a Mecca dauber or a betting pencil?\n" +
                            "\n" +
                            "[Chorus 2]\n" +
                            "Oh, the boy's a slag, the best you ever had\n" +
                            "The best you ever had is just a memory and those dreams\n" +
                            "Weren't daft as they seem, not as daft as they seem\n" +
                            "My love, when you dream them up\n" +
                            "Oh, Flo, where did you go?\n" +
                            "Where did you go? Where did you go?\n" +
                            "\n" +
                            "[Bridge]\n" +
                            "You're falling about\n" +
                            "You took a left off Last Laugh Lane\n" +
                            "Just sounding it out\n" +
                            "But you're not coming back again\n" +
                            "You're falling about\n" +
                            "You took a left off Last Laugh Lane\n" +
                            "You were just sounding it out\n" +
                            "But you're not coming back again\n" +
                            "\n" +
                            "[Outro]\n" +
                            "You used to get it in your fishnets\n" +
                            "(Falling about)\n" +
                            "Now you only get it in your night dress\n" +
                            "Discarded all the naughty nights for niceness\n" +
                            "(You took a left off Last Laugh Lane)\n" +
                            "Landed in a very common crisis\n" +
                            "Everything's in order in a black hole\n" +
                            "(You were just sounding it out)\n" +
                            "Is anything as pretty in the past though?\n" +
                            "That Bloody Mary's lacking in Tabasco\n" +
                            "(You're not coming back again)\n" +
                            "Remember when you used to be a rascal?\n" +
                            "(Oh...)",
                    "Fluorescent Adolescent",
                    "Favourite Worst Nightmare"
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
    val size = list.size
    fun getAll():List<MusicPiece>{
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