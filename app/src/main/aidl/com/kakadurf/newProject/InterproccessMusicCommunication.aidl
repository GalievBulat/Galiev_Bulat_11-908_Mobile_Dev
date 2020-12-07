
package com.kakadurf.newProject;
import com.kakadurf.newProject.MListener;
interface InterproccessMusicCommunication {
    boolean stop();
    void resume();
    void pause();
    boolean pass(in MusicPiece composition);
    int getPid();
    boolean onMusicComplete(MListener listener);
}

parcelable MusicPiece;