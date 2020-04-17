package com.example.recordingaudio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;
    private String fileName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fileName = Environment.getExternalStorageDirectory() + "/record.3gpp";
        buttonTapped();
    }

    public void buttonTapped(View view) {
        switch (view.getId()) {
            case R.id.start_btn:
                try {
                    beginRecording();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            case R.id.stop_btn:
                try {
                    stopRecording();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            case R.id.listen_btn:
                try {
                    playRecording();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            case R.id.stop_lis:
                try {
                    stopPlay();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void stopPlay() {
        if (mediaPlayer != null)
            mediaPlayer.stop();
    }

    private void playRecording() throws IOException {
        ditchMediaPlayer();
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setDataSource(fileName);
        mediaPlayer.prepare();
        mediaPlayer.start();
    }

    private void ditchMediaPlayer() {
        if (mediaRecorder != null) {
            try {
                mediaPlayer.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void stopRecording() {
        if (mediaRecorder != null)
            mediaRecorder.stop();
    }

    private void beginRecording() throws IOException {
        ditchMediaRecorder();
        File outfile = new File(fileName);
        if (outfile.exists())
            outfile.delete();
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mediaRecorder.setOutputFile(fileName);
        mediaRecorder.prepare();
        mediaRecorder.start();

    }

    private void ditchMediaRecorder() {
        if (mediaRecorder != null)
            mediaRecorder.release();
    }
}
