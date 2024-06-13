package org.example;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

// Пример работы со звуков в Java с сайта habr, автор raid
// https://habr.com/ru/articles/191422/

public class Main {
    public static void main(String[] args) {
        File soundFile = new File("snd.wav");
        System.out.println(soundFile.getAbsolutePath());

        // Получаем AudioInputStream.
        // Вот тут могут полететь IOException и UnsupportedAudioFileException.

        //Получаем реализацию интерфейса Clip.
        //Может выкинуть LineUnavailableException.
        try ( AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
              Clip clip = AudioSystem.getClip() ) {

            // Загружаем наш звуковой поток в Clip.
            // Может выкинуть IOException и LineUnavailableException.
            clip.open(ais);

            clip.setFramePosition(0); //устанавливаем указатель на старт.
            clip.start(); //Поехали!!!

            //Если не запущено других потоков, то стоит подождать, пока клип не закончится.
            //В GUI-приложениях следующие 3 строчки не понадобятся.
            Thread.sleep(clip.getMicrosecondLength()/1000);
            clip.stop(); //Останавливаем
        } catch (IOException |
                 UnsupportedAudioFileException |
                 LineUnavailableException |
                 InterruptedException exc) {
            exc.printStackTrace();
        }
    }
}