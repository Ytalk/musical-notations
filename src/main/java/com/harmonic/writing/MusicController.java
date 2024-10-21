package com.harmonic.writing;

import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/music")
public class MusicController {

    private static final Logger logger = LoggerFactory.getLogger(MusicController.class);

    private static Map<String, Music> musicDatabase = new HashMap<>();
    private static Playlist fixedPlaylist;


    static {
        // Adiciona músicas ao "banco de dados"
        Lyrics lyrics1 = new Lyrics("Never gonna give you up\n" +
                "Never gonna let you down\n" +
                "Never gonna turn around and desert you\n" +
                "Never gonna make you cry\n" +
                "Never gonna say goodbye\n" +
                "Never gonna tell a lie and hurt you\n");

        Music music1 = new Music("Never Gonna Give You Up", "Rick Astley");
        music1.addNotation(lyrics1);

        Lyrics lyrics2 = new Lyrics("Here comes the sun...");
        Music music2 = new Music("Here Comes the Sun", "The Beatles");
        music2.addNotation(lyrics2);

        musicDatabase.put("never-gonna-give-you-up", music1);
        musicDatabase.put("here-comes-the-sun", music2);

        // Cria uma playlist fixa
        fixedPlaylist = new Playlist("Playlist Test");
        fixedPlaylist.addMusic(music1);
        fixedPlaylist.addMusic(music2);
    }

    @GetMapping("/{title}")
    public Music getMusic(@PathVariable String title) {
        logger.info("Título recebido no endpoint: {}", title);

        Music foundMusic = musicDatabase.getOrDefault(title.toLowerCase(), null);
        if (foundMusic == null) {
            logger.warn("Música '{}' não encontrada no banco de dados.", title);
            return new Music("Música não encontrada", "");
        }
        return foundMusic;
    }

    @GetMapping("/playlist")
    public Playlist getFixedPlaylist() {
        return fixedPlaylist;
    }

}