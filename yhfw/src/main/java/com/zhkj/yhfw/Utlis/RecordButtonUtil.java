package com.zhkj.yhfw.Utlis;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.io.IOException;

public class RecordButtonUtil {
    public static final String AUDOI_DIR = Environment
            .getExternalStorageDirectory().getAbsolutePath() + "/oschina/audio"; // 录音音频保存根路径

    private boolean mIsRecording;// 是否正在录音
    private boolean mIsPlaying;// 是否正在播放
    private MediaRecorder mRecorder;
    private MediaPlayer mPlayer;
    private File soundFile;
    private String mAudioPath; // 要播放的声音的路径
    private OnPlayListener listener;

    // 初始化 录音器
    private void initRecorder() {
        if( mRecorder == null) {
            File dir = new File(Environment.getExternalStorageDirectory(), "sounds");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            //存储到SD卡当然也可上传到服务器
            soundFile = new File(dir, System.currentTimeMillis() + ".amr");
            if (!soundFile.exists()) {
                try {
                    soundFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mRecorder.setOutputFile(mAudioPath);
        mIsRecording = true;
    }

    /** 开始录音，并保存到文件中 */
    public void recordAudio() {
        initRecorder();
        try {
            mRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mRecorder.start();
    }

    /** 停止录音 */
    public void stopRecord() {
        if (mRecorder != null) {
            mRecorder.stop();
            mRecorder.release();
            mRecorder = null;
            mIsRecording = false;
        }
    }

    /** 获取音量值，只是针对录音音量 */
    public int getVolumn() {
        int volumn = 0;
        // 录音
        if (mRecorder != null && mIsRecording) {
            volumn = mRecorder.getMaxAmplitude();
            if (volumn != 0)
                volumn = (int) (10 * Math.log(volumn) / Math.log(10)) / 7;
        }
        return volumn;
    }

    public void startPlay(String audioPath) {
        if (!mIsPlaying) {
            if (!TextUtils.isEmpty(audioPath)) {
                mPlayer = new MediaPlayer();
                try {
                    mPlayer.setDataSource(audioPath);
                    mPlayer.prepare();
                    mPlayer.start();
                    if (listener != null) {
                        listener.starPlay();
                    }
                    mIsPlaying = true;
                    mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            if (listener != null) {
                                listener.stopPlay();
                            }
                            mp.release();
                            mPlayer = null;
                            mIsPlaying = false;
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {

            }
        } // end playing
    }
    public interface OnPlayListener {
        /** 播放声音结束时调用 */
        void stopPlay();

        /**  播放声音开始时调用 */
        void starPlay();
    }
}
