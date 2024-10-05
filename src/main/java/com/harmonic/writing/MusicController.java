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

    static {
        // Adiciona uma música ao "banco de dados"
        Lyrics lyrics = new Lyrics("Never gonna give you up\n" +
                "Never gonna let you down\n" +
                "Never gonna turn around and desert you\n" +
                "Never gonna make you cry\n" +
                "Never gonna say goodbye\n" +
                "Never gonna tell a lie and hurt you\n");

        Music music = new Music("Never Gonna Give You Up", "Rick Astley");
        music.addNotation(lyrics);

        musicDatabase.put("never-gonna-give-you-up", music);
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
}