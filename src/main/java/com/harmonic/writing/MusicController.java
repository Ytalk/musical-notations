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
        Lyrics lyrics1 = new Lyrics("We're no strangers to love\n" +
                                    "You know the rules and so do I\n" +
                                    "A full commitment's what I'm thinking of\n" +
                                    "You wouldn't get this from any other guy\n" +
                                    "\n" +
                                    "I just wanna tell you how I'm feeling\n" +
                                    "Gotta make you understand\n" +
                                    "\n" +
                                    "Never gonna give you up\n" +
                                    "Never gonna let you down\n" +
                                    "Never gonna turn around and desert you\n" +
                                    "Never gonna make you cry\n" +
                                    "Never gonna say goodbye\n" +
                                    "Never gonna tell a lie and hurt you\n" +
                                    "\n" +
                                    "We've known each other for so long\n" +
                                    "Your heart's been aching\n" +
                                    "But you're too shy to say it\n" +
                                    "Inside we both know what's been going on\n" +
                                    "We know the game and we're gonna play it\n" +
                                    "\n" +
                                    "And if you ask me how I'm feeling\n" +
                                    "Don't tell me you're too blind to see\n" +
                                    "\n" +
                                    "Never gonna give you up\n" +
                                    "Never gonna let you down\n" +
                                    "Never gonna turn around and desert you\n" +
                                    "Never gonna make you cry\n" +
                                    "Never gonna say goodbye\n" +
                                    "Never gonna tell a lie and hurt you\n" +
                                    "\n" +
                                    "Never gonna give you up\n" +
                                    "Never gonna let you down\n" +
                                    "Never gonna turn around and desert you\n" +
                                    "Never gonna make you cry\n" +
                                    "Never gonna say goodbye\n" +
                                    "Never gonna tell a lie and hurt you\n" +
                                    "\n" +
                                    "Ooh (give you up)\n" +
                                    "Ooh (give you up)\n" +
                                    "Never gonna give, never gonna give (ooh, give you up)\n" +
                                    "Never gonna give, never gonna give (ooh, give you up)\n" +
                                    "\n" +
                                    "We've known each other for so long\n" +
                                    "Your heart's been aching\n" +
                                    "But you're too shy to say it\n" +
                                    "Inside we both know what's been going on\n" +
                                    "We know the game and we're gonna play it\n" +
                                    "\n" +
                                    "I just wanna tell you how I'm feeling\n" +
                                    "Gotta make you understand\n" +
                                    "\n" +
                                    "Never gonna give you up\n" +
                                    "Never gonna let you down\n" +
                                    "Never gonna turn around and desert you\n" +
                                    "Never gonna make you cry\n" +
                                    "Never gonna say goodbye\n" +
                                    "Never gonna tell a lie and hurt you\n" +
                                    "\n" +
                                    "Never gonna give you up\n" +
                                    "Never gonna let you down\n" +
                                    "Never gonna turn around and desert you\n" +
                                    "Never gonna make you cry\n" +
                                    "Never gonna say goodbye\n" +
                                    "Never gonna tell a lie and hurt you\n" +
                                    "\n" +
                                    "Never gonna give you up\n" +
                                    "Never gonna let you down\n" +
                                    "Never gonna turn around and desert you\n" +
                                    "Never gonna make you cry\n" +
                                    "Never gonna say goodbye\n" +
                                    "Never gonna tell a lie and hurt you");
        Music music1 = new Music("Never Gonna Give You Up", "Rick Astley");
        music1.addNotation(lyrics1);

        Lyrics lyrics2 = new Lyrics("Apaga luz\n" +
                                    "Enquanto sente meu toque\n" +
                                    "Enquanto o chão se dissolve\n" +
                                    "Enquanto tudo me aborrece\n" +
                                    "Demônios testam minha fé\n" +
                                    "\n" +
                                    "Ela quer me hipnotizar\n" +
                                    "Medusa\n" +
                                    "Vem me seduzir\n" +
                                    "Tudo me acusa\n" +
                                    "\n" +
                                    "Se eu ouço Purple ah, ah\n" +
                                    "Todas as vezes eu te olho Purple ah, ah\n" +
                                    "Tudo a minha volta ficou Purple ah, ah\n" +
                                    "Sim, a noite eu vejo tudo Purple ah, ah\n" +
                                    "\n" +
                                    "Minha volta gira em volta\n" +
                                    "Tiras meus pés do chão (Do chão)\n" +
                                    "Vou pra outra dimensão\n" +
                                    "\n" +
                                    "Purple ah, ah, uh, ye\n" +
                                    "Purple ah, ah\n" +
                                    "\n" +
                                    "Baby, eu não me encaixo\n" +
                                    "Ela me bota pra baixo\n" +
                                    "Tudo me soa desprezo\n" +
                                    "Canto minha dor no compasso\n" +
                                    "Ela me bota pra baixo\n" +
                                    "Livre, mas me sinto preso\n" +
                                    "\n" +
                                    "Ela quer me priorizar\n" +
                                    "Baby, me ajuda\n" +
                                    "Sua autoestima\n" +
                                    "Não vale a minha\n" +
                                    "\n" +
                                    "(Ey) Desce, (Ey) Sobe bem devagar\n" +
                                    "(Ey) Bad, (Ey) Pode te dominar\n" +
                                    "\n" +
                                    "Se eu ouço Purple ah, ah\n" +
                                    "Toda noite eu te vejo Purple ah, ah\n" +
                                    "Sim, a noite eu vejo tudo Purple\n" +
                                    "\n" +
                                    "Tira meus pés do chão\n" +
                                    "Vou pra outra dimensão\n" +
                                    "Toda noite eu te olho Purple ah, ah\n" +
                                    "Tudo gira em volta e fica Purple ah, ah\n" +
                                    "\n" +
                                    "Ey, ey\n" +
                                    "Sei que isso tudo é stress, isso só me entristece\n" +
                                    "Ey, ey\n" +
                                    "Lembro de você a noite, isso não me aquece\n" +
                                    "Ey, ey");
        Music music2 = new Music("Purple", "VMZ");
        music2.addNotation(lyrics2);

        musicDatabase.put("never-gonna-give-you-up", music1);
        musicDatabase.put("purple", music2);

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