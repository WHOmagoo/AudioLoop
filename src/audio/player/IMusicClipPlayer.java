package audio.player;


public interface IMusicClipPlayer {

    /**
     * Plays music
     */
    void play();

    /**
     * Stops music and when play is called, it will resume exactly where it was
     */
    void pause();

    /**
     * Stops music and next time play is called, it will start at the beginning
     */
    void stop();

    /**
     * @return The length of the clip in milliseconds
     */
    long getLength();

//    /**
//     * Resize the length of the clip
//     * @param newLength the new length of the clip in milliseconds
//     */
//    void resize(long newLength);

    /**
     * @return whether or not the current player is playing
     */
    boolean isPlaying();

    void requestNotificationOnFinish(Notifiable obj);

//    void start();
//    void pauseNow();
//    void pauseAtRepeat();
//    void stopNow();
//    void stopAtRepeat();
//
//    double getLength();
//    boolean isPlaying();
//    boolean isLooping();
//    void setLoopLength(double length);
//
//    //A value of 0 is for infinitely looping, greater than 0 will play the clip that many times.
//    void setPlayCount(int timesToLoop);
//
//    void resizeToFitLoop(boolean resize);
//    void setIgnoreLoopLength(boolean ignoreLoopLength);
//
//    //Each of these are mutually exclusive to each other the last
//    void setResizeToLoopLength(boolean resizeToLoopLength);
//    void setPlayNTimesPerLoop(int nTimes);
}
