
package com.kakadurf.newProject;

interface Aidl {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    /*void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);*/
    void stop();
    void play();
    void next();
    void prev();
    void pause();
    oneway void pass(in MusicPiece composition);
    int getPid();
}

parcelable MusicPiece;