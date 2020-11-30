
package com.kakadurf.newProject;
import com.kakadurf.newProject.MListener;
interface Aidl {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    /*void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);*/
    boolean stop();
    void resume();
//    void next(int id);
//    void prev(int id);
    void pause();
    void pass(in MusicPiece composition);
    int getPid();
    void onMusicComplete(MListener listener);
}

parcelable MusicPiece;