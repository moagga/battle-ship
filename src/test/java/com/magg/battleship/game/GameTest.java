package com.magg.battleship.game;

import java.nio.file.Path;
import java.nio.file.Paths;

import junit.framework.Assert;

import org.junit.Test;

import com.magg.battleship.model.Player;
import com.magg.battleship.provider.FileInputProvider;
import com.magg.battleship.provider.InputProvider;

/**
 * Test cases for {@link Game} class.
 * 
 * @author Mohit Aggarwal
 */
public class GameTest {

    @Test
    public void testDraw() throws Exception {
        Path path = Paths.get(Thread.currentThread().getContextClassLoader().getResource("draw.test.txt").toURI());
        InputProvider provider = new FileInputProvider(path.toString());

        Game game = new Game();
        game.setup(provider);
        Player winner = game.play();
        Assert.assertNull(winner);
    }

    @Test
    public void testWinnerInFirstShot() throws Exception {
        Path path = Paths.get(Thread.currentThread().getContextClassLoader().getResource("win.test.txt").toURI());
        InputProvider provider = new FileInputProvider(path.toString());

        Game game = new Game();
        game.setup(provider);
        Player winner = game.play();
        Assert.assertEquals("Player 1", winner.getName());
    }

}
