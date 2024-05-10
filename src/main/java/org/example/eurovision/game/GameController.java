
package org.example.eurovision.game;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class GameController {
    private static final Logger logger = LoggerFactory.getLogger(GameController.class);
    private static final Question[] questions = {
            new Question(1, "Liar", "Silia Kapsis", "Cipro",
                    new String[]{"Cipro", "Serbia", "Lituania", "Montenegro", "Bulgaria"},
                    new String[]{"Liar", "Remonda", "Luktelk", "Secret", "Echo"},
                    new String[]{"Silia Kapsis", "Teya Dora", "Sylvester Belt", "Cassia Lope", "Mira Bel"}),
            new Question(2, "Remonda", "Teya Dora", "Serbia",
                    new String[]{"Serbia", "Regno Unito", "Irlanda", "Francia", "Germania"},
                    new String[]{"La Noia", "Remonda", "Doomsday Blue", "Tranquil", "Solitude"},
                    new String[]{"Silia Kapsis", "Teya Dora", "Bambie Thug", "Elara Sun", "Freya Mist"}),
            new Question(3, "Doomsday Blue", "Bambie Thug", "Irlanda",
                    new String[]{"Irlanda", "Regno Unito", "Polonia", "Norvegia", "Svezia"},
                    new String[]{"Doomsday Blue", "Dizzy", "The Tower", "Redemption", "Last Dance"},
                    new String[]{"Bambie Thug", "Olly Alexander", "Luna", "Eliot Vass", "Nora Kite"}),
            new Question(4, "Dizzy", "Olly Alexander", "Regno Unito",
                    new String[]{"Regno Unito", "Irlanda", "Ucraina", "Grecia", "Italia"},
                    new String[]{"Dizzy", "Teresa & Maria", "Rim Tim Tagi Dim", "Vortex", "Skyline"},
                    new String[]{"Olly Alexander", "Alyona Alyona & Jerry Heil", "Baby Lasagna", "Mara Zest", "Levi Stan"}),
            new Question(5, "The Tower", "Luna", "Polonia",
                    new String[]{"Polonia", "Ucraina", "Croazia", "Slovenia", "Ungheria"},
                    new String[]{"The Tower", "Teresa & Maria", "Rim Tim Tagi Dim", "Orbit", "Nightfall"},
                    new String[]{"Luna", "Alyona Alyona & Jerry Heil", "Baby Lasagna", "Tina Ray", "Opal Line"}),
            new Question(6, "Teresa & Maria", "Alyona Alyona & Jerry Heil", "Ucraina",
                    new String[]{"Ucraina", "Islanda", "Slovenia", "Lettonia", "Estonia"},
                    new String[]{"Teresa & Maria", "Scared of Heights", "Veronika", "Lost Cause", "End Game"},
                    new String[]{"Alyona Alyona & Jerry Heil", "Hera Björk", "Raiven", "Kira Moon", "Hans Volt"}),
            new Question(7, "Rim Tim Tagi Dim", "Baby Lasagna", "Croazia",
                    new String[]{"Croazia", "Islanda", "Slovenia", "Finlandia", "Norvegia"},
                    new String[]{"Rim Tim Tagi Dim", "Scared of Heights", "Veronika", "Eclipse", "Revival"},
                    new String[]{"Baby Lasagna", "Hera Björk", "Raiven", "Lila Fern", "Toby Stark"}),
            new Question(8, "Scared of Heights", "Hera Björk", "Islanda",
                    new String[]{"Islanda", "Slovenia", "Finlandia", "Svezia", "Danimarca"},
                    new String[]{"Scared of Heights", "Veronika", "No Rules!", "Beyond the Horizon", "Frostbite"},
                    new String[]{"Hera Björk", "Raiven", "Windows95Man", "Sola Mi", "Ivy Gold"}),
            new Question(9, "Veronika", "Raiven", "Slovenia",
                    new String[]{"Slovenia", "Finlandia", "Moldavia", "Romania", "Bulgaria"},
                    new String[]{"Veronika", "No Rules!", "In The Middle", "Windswept", "Timeless"},
                    new String[]{"Raiven", "Windows95Man", "Natalia Barbu", "Milo Vent", "Cara Stone"}),
            new Question(10, "In The Middle", "Natalia Barbu", "Moldavia",
                    new String[]{"Moldavia", "Azerbaigian", "Australia", "Croazia", "Lituania"},
                    new String[]{"In The Middle", "Özünlə Apar", "One Milkali (One Blood)", "Echoes", "Crossroads"},
                    new String[]{"Natalia Barbu", "Fahree feat. Ilkin Dovlatov", "Electric Fields", "Skye Peak", "Arlo Quench"}),
            new Question(11, "Özünlə Apar", "Fahree feat. Ilkin Dovlatov", "Azerbaigian",
                    new String[]{"Azerbaigian", "Australia", "Portogallo", "Grecia", "Italia"},
                    new String[]{"Özünlə Apar", "One Milkali (One Blood)", "Grito", "Serene", "Rapture"},
                    new String[]{"Fahree feat. Ilkin Dovlatov", "Electric Fields", "Iolanda", "Max Roar", "Lena Dream"}),
            new Question(12, "One Milkali (One Blood)", "Electric Fields", "Australia",
                    new String[]{"Australia", "Portogallo", "Lussemburgo", "Irlanda", "Regno Unito"},
                    new String[]{"One Milkali (One Blood)", "Grito", "Fighter", "Whirlwind", "Ascend"},
                    new String[]{"Electric Fields", "Iolanda", "Tali", "Nessa Ray", "Jonas Blue"}),
            new Question(13, "Grito", "Iolanda", "Portogallo",
                    new String[]{"Portogallo", "Lussemburgo", "Malta", "Spagna", "Francia"},
                    new String[]{"Grito", "Fighter", "Loop", "Solace", "Rise"},
                    new String[]{"Iolanda", "Tali", "Sarah Bonnici", "Evan Greer", "Mara Jade"}),
            new Question(14, "Fighter", "Tali", "Lussemburgo",
                    new String[]{"Lussemburgo", "Malta", "Albania", "Francia", "Belgio"},
                    new String[]{"Fighter", "Loop", "Titan", "Reckoning", "Thunder"},
                    new String[]{"Tali", "Sarah Bonnici", "Besa", "Corey Fox", "Elise Duran"}),
            new Question(15, "Loop", "Sarah Bonnici", "Malta",
                    new String[]{"Malta", "Albania", "Grecia", "Cipro", "Moldavia"},
                    new String[]{"Loop", "Titan", "Zari", "Vortex", "Horizon"},
                    new String[]{"Sarah Bonnici", "Besa", "Marina Satti", "Liam Gale", "Nora Eve"}),
            new Question(16, "Titan", "Besa", "Albania",
                    new String[]{"Albania", "Grecia", "Svizzera", "Macedonia", "Armenia"},
                    new String[]{"Titan", "Zari", "The Code", "Overture", "Legacy"},
                    new String[]{"Besa", "Marina Satti", "Nemo", "Diana Lore", "Felix Crown"}),
            new Question(17, "Zari", "Marina Satti", "Grecia",
                    new String[]{"Grecia", "Svizzera", "Repubblica Ceca", "Bulgaria", "Romania"},
                    new String[]{"Zari", "The Code", "Pedestal", "Mystic", "Reverie"},
                    new String[]{"Marina Satti", "Nemo", "Aiko", "Lyra Cole", "Timo Vant"}),
            new Question(18, "The Code", "Nemo", "Svizzera",
                    new String[]{"Svizzera", "Repubblica Ceca", "Austria", "Germania", "Italia"},
                    new String[]{"The Code", "Pedestal", "We Will Rave", "Cipher", "Enigma"},
                    new String[]{"Nemo", "Aiko", "Kaleen", "Casper Holt", "Elina Moon"}),
            new Question(19, "Pedestal", "Aiko", "Repubblica Ceca",
                    new String[]{"Repubblica Ceca", "Austria", "Danimarca", "Polonia", "Moldavia"},
                    new String[]{"Pedestal", "We Will Rave", "Sand", "Elevation", "Monolith"},
                    new String[]{"Aiko", "Kaleen", "Saba", "Rico Blunt", "Maya Soleil"}),
            new Question(20, "We Will Rave", "Kaleen", "Austria",
                    new String[]{"Austria", "Danimarca", "Armenia", "Ucraina", "Slovenia"},
                    new String[]{"We Will Rave", "Sand", "Jako", "Festival", "Pulse"},
                    new String[]{"Kaleen", "Saba", "Ladaniva", "Viktor Lake", "Stella Mar"}),
            new Question(21, "Sand", "Saba", "Danimarca",
                    new String[]{"Danimarca", "Armenia", "Lettonia", "Svezia", "Norvegia"},
                    new String[]{"Sand", "Jako", "Hollow", "Dunes", "Shoreline"},
                    new String[]{"Saba", "Ladaniva", "Dons", "Erica Vorn", "Miles Deep"}),
            new Question(22, "Jako", "Ladaniva", "Armenia",
                    new String[]{"Armenia", "Lettonia", "San Marino", "Georgia", "Cipro"},
                    new String[]{"Jako", "Hollow", "11:11", "Twilight", "Dawn"},
                    new String[]{"Ladaniva", "Dons", "Megara", "Ara Neva", "Talin Moor"}),
            new Question(23, "Hollow", "Dons", "Lettonia",
                    new String[]{"Lettonia", "San Marino", "Georgia", "Estonia", "Lituania"},
                    new String[]{"Hollow", "11:11", "Fire Fighter", "Void", "Echo"},
                    new String[]{"Dons", "Megara", "Nutsa Buzaladze", "Sol Runa", "Kai Storm"}),
            new Question(24, "11:11", "Megara", "San Marino",
                    new String[]{"San Marino", "Georgia", "Belgio", "Italia", "Portogallo"},
                    new String[]{"11:11", "Fire Fighter", "Before the Party's Over", "Wish", "Midnight"},
                    new String[]{"Megara", "Nutsa Buzaladze", "Mustii", "Lev Paros", "Ciri Zen"}),
            new Question(25, "Fire Fighter", "Nutsa Buzaladze", "Georgia",
                    new String[]{"Georgia", "Belgio", "Estonia", "Ucraina", "Moldavia"},
                    new String[]{"Fire Fighter", "Before the Party's Over", "(Nendest) narkootikumidest ei tea me (küll) midagi", "Blaze", "Rescue"},
                    new String[]{"Nutsa Buzaladze", "Mustii", "5miinust e i Puuluup", "Igor Plan", "Hana Song"}),
            new Question(26, "Before the Party's Over", "Mustii", "Belgio",
                    new String[]{"Belgio", "Estonia", "Israele", "Spagna", "Germania"},
                    new String[]{"Before the Party's Over", "(Nendest) narkootikumidest ei tea me (küll) midagi", "Hurricane", "Twilight", "Evening Star"},
                    new String[]{"Mustii", "5miinust e i Puuluup", "Eden Golan", "Livia Nair", "Markus Frei"}),
            new Question(27, "(Nendest) narkootikumidest ei tea me (küll) midagi", "5miinust e i Puuluup", "Estonia",
                    new String[]{"Estonia", "Israele", "Norvegia", "Lettonia", "Svezia"},
                    new String[]{"(Nendest) narkootikumidest ei tea me (küll) midagi", "Hurricane", "Ulveham", "Silent Waves", "Northern Lights"},
                    new String[]{"5miinust e i Puuluup", "Eden Golan", "Gåte", "Sara Kivi", "Niklas Storm"}),
            new Question(28, "Hurricane", "Eden Golan", "Israele",
                    new String[]{"Israele", "Norvegia", "Paesi Bassi", "Turchia", "Grecia"},
                    new String[]{"Hurricane", "Ulveham", "Europapa", "Stormbreaker", "Celestial"},
                    new String[]{"Eden Golan", "Gåte", "Joost Klein", "Tara Mil", "Helen Voss"}),
            new Question(29, "Ulveham", "Gåte", "Norvegia",
                    new String[]{"Norvegia", "Paesi Bassi", "Cipro", "Finlandia", "Danimarca"},
                    new String[]{"Ulveham", "Europapa", "Liar", "Frost Gate", "Viking Saga"},
                    new String[]{"Gåte", "Joost Klein", "Silia Kapsis", "Erik Red", "Mia Holm"}),
            new Question(30, "Europapa", "Joost Klein", "Paesi Bassi",
                    new String[]{"Paesi Bassi", "Cipro", "Serbia", "Austria", "Ungheria"},
                    new String[]{"Europapa", "Liar", "Remonda", "Windmill", "Tulip Fever"},
                    new String[]{"Joost Klein", "Silia Kapsis", "Teya Dora", "Hans Berger", "Liza Korn"}),
            new Question(31, "La Noia", "Angelina Mango", "Italia",
                    new String[]{"Italia", "Francia", "Germania", "Svizzera", "Austria"},
                    new String[]{"La Noia", "Mon amour", "Always on the Run", "Dolce Vita", "Serenade"},
                    new String[]{"Angelina Mango", "Slimane", "Isaak", "Giovanni Lido", "Maria Rosa"}),
            new Question(32, "Mon amour", "Slimane", "Francia",
                    new String[]{"Francia", "Italia", "Germania", "Belgio", "Lussemburgo"},
                    new String[]{"Mon amour", "La Noia", "Always on the Run", "Rue de l'Amour", "Belle Époque"},
                    new String[]{"Slimane", "Angelina Mango", "Isaak", "Claude Monet", "Sophie Durant"}),
            new Question(33, "Always on the Run", "Isaak", "Germania",
                    new String[]{"Germania", "Francia", "Italia", "Olanda", "Polonia"},
                    new String[]{"Always on the Run", "Mon amour", "La Noia", "Autobahn", "Night Drive"},
                    new String[]{"Isaak", "Slimane", "Angelina Mango", "Fritz Bauer", "Helena Kraft"}),
            new Question(34, "Luktelk", "Silvester Belt", "Lituania",
                    new String[]{"Lituania", "Francia", "Italia", "Lettonia", "Estonia"},
                    new String[]{"Luktelk", "Mon amour", "La Noia", "Baltic Mist", "Forest Whisper"},
                    new String[]{"Silvester Belt", "Slimane", "Angelina Mango", "Rasa Jukneviciene", "Egidijus Balandis"}),
            new Question(35, "Remonda", "Teya Dora", "Serbia",
                    new String[]{"Serbia", "Paesi Bassi", "Cipro", "Montenegro", "Bosnia Erzegovina"},
                    new String[]{"Remonda", "Europapa", "Liar", "Crossroads", "Balkan Nights"},
                    new String[]{"Teya Dora", "Joost Klein", "Silia Kapsis", "Milos Radivojevic", "Zara Petrovic"}),
            new Question(36, "Nebulossa", "Zorra", "Spagna",
                    new String[]{"Spagna", "Cipro", "Irlanda", "Portogallo", "Francia"},
                    new String[]{"Liar", "Remonda", "Doomsday Blue", "Flamenco Fire", "Sierra Echo"},
                    new String[]{"Silia Kapsis", "Teya Dora", "Bambie Thug", "Carmen Ruiz", "Pablo Fernández"}),
    };

    @GetMapping("/question")
    public ResponseEntity<QuestionResponse> getRandomQuestion(HttpSession session) {
        Random random = new Random();
        String category = (String) session.getAttribute("category");
        String difficulty = (String) session.getAttribute("difficulty");
        List<Question> filteredQuestions = filterQuestionByDifficultyandCategory(difficulty, category);

        if (filteredQuestions.isEmpty()) {
            return ResponseEntity.badRequest().body(new QuestionResponse(0, "Nessuna domanda disponibile per questa categoria.", null, new String[0]));
        }

        Question question = filteredQuestions.get(random.nextInt(filteredQuestions.size()));
        QuestionType type = QuestionType.valueOf(category != null ? category : QuestionType.values()[random.nextInt(QuestionType.values().length)].name());
        session.setAttribute("currentQuestion", question);
        session.setAttribute("currentQuestionType", type);


        List<String> shuffledOptions = Arrays.asList(question.getOptions(type));
        Collections.shuffle(shuffledOptions);

        QuestionResponse response = new QuestionResponse(
                question.getId(),
                generateQuestionText(question, type),
                type,
                shuffledOptions.toArray(new String[0])
        );

        return ResponseEntity.ok(response);
    }


    private String generateQuestionText(Question question, QuestionType type) {
        return switch (type) {
            case SONG_ARTIST -> "Chi canta la canzone '" + question.getSong() + "'?";
            case SONG_COUNTRY -> "La canzone '" + question.getSong() + "' gareggia per quale nazione?";
            case ARTIST_SONG -> "Che canzone canta '" + question.getArtist() + "'?";
            case ARTIST_COUNTRY -> "Per quale nazione gareggia '" + question.getArtist() + "'?";
            case COUNTRY_ARTIST -> "Chi gareggia per la nazione '" + question.getCorrectCountry() + "'?";
        };
    }

    @PostMapping("/answer")
    public ResponseEntity<?> submitAnswer(HttpSession session, @RequestBody Answer answer) {
        if (answer.getType() == null) {
            return ResponseEntity.badRequest().body("Missing question type in the request.");
        }
        logger.info("Submitting answer - Session ID: {}", session.getId());

        Question currentQuestion = findQuestionById(answer.getQuestionId());
        if (currentQuestion == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question not found");
        }

        String correctAnswer = currentQuestion.getCorrectAnswer(answer.getType());
        boolean isCorrect = correctAnswer != null && correctAnswer.equals(answer.getSelectedOption());

        logger.info("Answer submitted - Session ID: {}, Question ID: {}, Correct: {}", session.getId(), answer.getQuestionId(), isCorrect);

        AnswerResult result = new AnswerResult(isCorrect, correctAnswer);
        return ResponseEntity.ok(result);
    }


//    private List<Question> filterQuestionsByCategory(String category) {
//        if (category == null || category.isEmpty()) {
//            return Arrays.asList(questions);
//        }
//        return Arrays.stream(questions)
//                .filter(q -> q.getType().name().equals(category))
//                .collect(Collectors.toList());
//    }

    private Question findQuestionById(int questionId) {
        for (Question question : questions) {
            if (question.getId() == questionId) {
                return question;
            }
        }
        return null;
    }

    private List<Question> filterQuestionByDifficultyandCategory(String difficulty, String category){
        if (category == null || category.isEmpty() || difficulty == null || difficulty.isEmpty()){
            return Arrays.asList(questions);
        }

        QuestionType type = QuestionType.valueOf(category.toUpperCase());

        return Arrays.stream(questions)
                .filter(q -> q.getType() == type)
                .filter(q -> q.getOptions(type).length == Integer.parseInt(difficulty))
                .collect(Collectors.toList());
    }


    @PostMapping("/setCategory")
    public ResponseEntity<?> setCategory(HttpSession session, @RequestBody String category) {
        session.setAttribute("category", category);
        session.setAttribute("score", 0);
        session.setAttribute("questionCount", 0);
        return ResponseEntity.ok("Categoria impostata su: " + category);
    }

    @PostMapping("/setQuizLength")
    public ResponseEntity<?> setQuizLength(HttpSession session, @RequestBody int length) {
        session.setAttribute("quizLength", length);
        return ResponseEntity.ok("Quiz length set to: " + length);
    }

    @PostMapping("/setDifficulty")
    public ResponseEntity<?> setDifficulty(HttpSession session, @RequestBody String difficulty) {
        session.setAttribute("difficulty", difficulty);
        return ResponseEntity.ok("Difficulty set to: " + difficulty);
    }

}


