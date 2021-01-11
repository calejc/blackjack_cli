package cjc;

import cjc.service.PlayService;

public class Main {

    public static PlayService playService = new PlayService();

    public static void main(String[] args) throws InterruptedException {
        playService.play();
    }

}
