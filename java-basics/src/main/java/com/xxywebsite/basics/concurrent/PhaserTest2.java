package com.xxywebsite.basics.concurrent;

import java.util.concurrent.Phaser;

/**
 * @author xuxiaoyin
 * @since 2022/3/30
 **/
public class PhaserTest2 {
    public static void main(String[] args) throws InterruptedException {
        Phaser parentPhaser = new Phaser(0);
        System.out.println(parentPhaser.getUnarrivedParties());
        new Phaser(parentPhaser, 9);
        new Phaser(parentPhaser, 7);
        new Phaser(parentPhaser, 0);
        System.out.println(parentPhaser.getUnarrivedParties());

    }
}
