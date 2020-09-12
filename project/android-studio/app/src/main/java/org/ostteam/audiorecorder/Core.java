package org.ostteam.audiorecorder;

public class Core {

    private long _core = 0;      //c-pointer managed at JNI

    public Core(){
        //
    }

    public native boolean       engineInit();
    public native void		    engineRelease();

    //Must be manually called few times per second (>=10 times per second)
    public native void		    tick();

    public native boolean       start(String wavFilepath, int buffersPerSec, boolean keepAvgsOfSamples);
    public native boolean       pause();
    public native boolean       resume();
    public native boolean       stop();
    public native boolean       reset();

    public native boolean       isActive(); //recording or paused
    public native boolean       isRecording();
    public native boolean       isPaused();

    //current or last recording
    public native long          samplesCount();
    public native long          samplesBytes();
    public native float         samplesSecs();

    //Avg of samples from -1 to 1 (warning: overflows if not manually reset preiodically)
    public native float          samplesRelAvg(boolean resetAvg);

    //player

    public native boolean       playerLoad(String filepath, int buffersPerSec);
    public native boolean       playerPlay();
    public native boolean       playerPause();
    public native boolean       playerStop();
    public native boolean       playerIsLoaded();
    public native boolean       playerIsPaused();
    public native boolean       playerSeekRelPos(float relPos);
    public native float         playerRelProgress();
    public native float         playerSecsTotal();

    //encoder

    public native boolean       encoderStart(String filepathIn, String filepathOut, String formatId);
    public native boolean       encoderIsLoaded();
    public native float         encoderRelProgress(); //never 1.0f unless done
    public native boolean       encoderFinish();

}
