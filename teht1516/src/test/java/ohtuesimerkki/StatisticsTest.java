package ohtuesimerkki;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import ohtuesimerkki.Player;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class StatisticsTest {

    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }

    @Test
    public void topScorersNegatiivinenParametri() {
        List<Player> topLista = stats.topScorers(-1);
        assertTrue(topLista.isEmpty());
    }

    @Test
    public void topScorersOikeaMaara() {
        List<Player> topLista = stats.topScorers(3);
        assertEquals(4, topLista.size(), 0);
    }

    @Test
    public void searchLoytaaNimen() {
        Player haettu = stats.search("Yzerman");
        assertNotNull(haettu);
    }

    @Test
    public void searchEiLoydaNimea() {
        Player haettu = stats.search("Salmela");
        assertNull(haettu);
    }

    @Test
    public void teamPalauttaaOikeanMaaran() {
        List<Player> tiimi = stats.team("EDM");

        assertEquals(3,tiimi.size(), 0);
    }
}