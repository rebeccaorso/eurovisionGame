
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
                    new String[]{"Cipro", "Serbia", "Lituania"},
                    new String[]{"Liar", "Remonda", "Luktelk"},
                    new String[]{"Silia Kapsis", "Teya Dora", "Sylvester Belt"}),
            new Question(2, "Remonda", "Teya Dora", "Serbia",
                    new String[]{"Serbia", "Regno Unito", "Irlanda"},
                    new String[]{"La Noia", "Remonda", "Doomsday Blue"},
                    new String[]{"Silia Kapsis", "Teya Dora", "Bambie Thug"}),
            new Question(3, "Doomsday Blue", "Bambie Thug", "Irlanda",
                    new String[]{"Irlanda", "Regno Unito", "Polonia"},
                    new String[]{"Doomsday Blue", "Dizzy", "The Tower"},
                    new String[]{"Bambie Thug", "Olly Alexander", "Luna"}),
            new Question(4, "Dizzy", "Olly Alexander", "Regno Unito",
                    new String[]{"Regno Unito", "Irlanda", "Ucraina"},
                    new String[]{"Dizzy", "Teresa & Maria", "Rim Tim Tagi Dim"},
                    new String[]{"Olly Alexander", "Alyona Alyona & Jerry Heil", "Baby Lasagna"}),
            new Question(5, "The Tower", "Luna", "Polonia",
                    new String[]{"Polonia", "Ucraina", "Croazia"},
                    new String[]{"The Tower", "Teresa & Maria", "Rim Tim Tagi Dim"},
                    new String[]{"Luna", "Alyona Alyona & Jerry Heil", "Baby Lasagna"}),
            new Question(6, "Teresa & Maria", "Alyona Alyona & Jerry Heil", "Ucraina",
                    new String[]{"Ucraina", "Islanda", "Slovenia"},
                    new String[]{"Teresa & Maria", "Scared of Heights", "Veronika"},
                    new String[]{"Alyona Alyona & Jerry Heil", "Hera Björk", "Raiven"}),
            new Question(7, "Rim Tim Tagi Dim", "Baby Lasagna", "Croazia",
                    new String[]{"Croazia", "Islanda", "Slovenia"},
                    new String[]{"Rim Tim Tagi Dim", "Scared of Heights", "Veronika"},
                    new String[]{"Baby Lasagna", "Hera Björk", "Raiven"}),
            new Question(8, "Scared of Heights", "Hera Björk", "Islanda",
                    new String[]{"Islanda", "Slovenia", "Finlandia"},
                    new String[]{"Scared of Heights", "Veronika", "No Rules!"},
                    new String[]{"Hera Björk", "Raiven", "Windows95Man"}),
            new Question(9, "Veronika", "Raiven", "Slovenia",
                    new String[]{"Slovenia", "Finlandia", "Moldavia"},
                    new String[]{"Veronika", "No Rules!", "In The Middle"},
                    new String[]{"Raiven", "Windows95Man", "Natalia Barbu"}),
            new Question(37, "No Rules!", "Windows95Man", "Finlandia",
                    new String[]{"Finlandia", "Moldavia", "Azerbaigian"},
                    new String[]{"No Rules!", "In The Middle", "Özünlə Apar"},
                    new String[]{"Windows95Man", "Natalia Barbu", "Fahree feat. Ilkin Dovlatov"}),
            new Question(10, "In The Middle", "Natalia Barbu", "Moldavia",
                    new String[]{"Moldavia", "Azerbaigian", "Australia"},
                    new String[]{"In The Middle", "Özünlə Apar", "One Milkali (One Blood)"},
                    new String[]{"Natalia Barbu", "Fahree feat. Ilkin Dovlatov", "Electric Fields"}),
            new Question(11, "Özünlə Apar", "Fahree feat. Ilkin Dovlatov", "Azerbaigian",
                    new String[]{"Azerbaigian", "Australia", "Portogallo"},
                    new String[]{"Özünlə Apar", "One Milkali (One Blood)", "Grito"},
                    new String[]{"Fahree feat. Ilkin Dovlatov", "Electric Fields", "Iolanda"}),
            new Question(12, "One Milkali (One Blood)", "Electric Fields", "Australia",
                    new String[]{"Australia", "Portogallo", "Lussemburgo"},
                    new String[]{"One Milkali (One Blood)", "Grito", "Fighter"},
                    new String[]{"Electric Fields", "Iolanda", "Tali"}),
            new Question(13, "Grito", "Iolanda", "Portogallo",
                    new String[]{"Portogallo", "Lussemburgo", "Malta"},
                    new String[]{"Grito", "Fighter", "Loop"},
                    new String[]{"Iolanda", "Tali", "Sarah Bonnici"}),
            new Question(14, "Fighter", "Tali", "Lussemburgo",
                    new String[]{"Lussemburgo", "Malta", "Albania"},
                    new String[]{"Fighter", "Loop", "Titan"},
                    new String[]{"Tali", "Sarah Bonnici", "Besa"}),
            new Question(15, "Loop", "Sarah Bonnici", "Malta",
                    new String[]{"Malta", "Albania", "Grecia"},
                    new String[]{"Loop", "Titan", "Zari"},
                    new String[]{"Sarah Bonnici", "Besa", "Marina Satti"}),
            new Question(16, "Titan", "Besa", "Albania",
                    new String[]{"Albania", "Grecia", "Svizzera"},
                    new String[]{"Titan", "Zari", "The Code"},
                    new String[]{"Besa", "Marina Satti", "Nemo"}),
            new Question(17, "Zari", "Marina Satti", "Grecia",
                    new String[]{"Grecia", "Svizzera", "Repubblica Ceca"},
                    new String[]{"Zari", "The Code", "Pedestal"},
                    new String[]{"Marina Satti", "Nemo", "Aiko"}),
            new Question(18, "The Code", "Nemo", "Svizzera",
                    new String[]{"Svizzera", "Repubblica Ceca", "Austria"},
                    new String[]{"The Code", "Pedestal", "We Will Rave"},
                    new String[]{"Nemo", "Aiko", "Kaleen"}),
            new Question(19, "Pedestal", "Aiko", "Repubblica Ceca",
                    new String[]{"Repubblica Ceca", "Austria", "Danimarca"},
                    new String[]{"Pedestal", "We Will Rave", "Sand"},
                    new String[]{"Aiko", "Kaleen", "Saba"}),
            new Question(20, "We Will Rave", "Kaleen", "Austria",
                    new String[]{"Austria", "Danimarca", "Armenia"},
                    new String[]{"We Will Rave", "Sand", "Jako"},
                    new String[]{"Kaleen", "Saba", "Ladaniva"}),
            new Question(21, "Sand", "Saba", "Danimarca",
                    new String[]{"Danimarca", "Armenia", "Lettonia"},
                    new String[]{"Sand", "Jako", "Hollow"},
                    new String[]{"Saba", "Ladaniva", "Dons"}),
            new Question(22, "Jako", "Ladaniva", "Armenia",
                    new String[]{"Armenia", "Lettonia", "San Marino"},
                    new String[]{"Jako", "Hollow", "11:11"},
                    new String[]{"Ladaniva", "Dons", "Megara"}),
            new Question(23, "Hollow", "Dons", "Lettonia",
                    new String[]{"Lettonia", "San Marino", "Georgia"},
                    new String[]{"Hollow", "11:11", "Fire Fighter"},
                    new String[]{"Dons", "Megara", "Nutsa Buzaladze"}),
            new Question(24, "11:11", "Megara", "San Marino",
                    new String[]{"San Marino", "Georgia", "Belgio"},
                    new String[]{"11:11", "Fire Fighter", "Before the Party's Over"},
                    new String[]{"Megara", "Nutsa Buzaladze", "Mustii"}),
            new Question(25, "Fire Fighter", "Nutsa Buzaladze", "Georgia",
                    new String[]{"Georgia", "Belgio", "Estonia"},
                    new String[]{"Fire Fighter", "Before the Party's Over", "(Nendest) narkootikumidest ei tea me (küll) midagi"},
                    new String[]{"Nutsa Buzaladze", "Mustii", "5miinust e i Puuluup"}),
            new Question(26, "Before the Party's Over", "Mustii", "Belgio",
                    new String[]{"Belgio", "Estonia", "Israele"},
                    new String[]{"Before the Party's Over", "(Nendest) narkootikumidest ei tea me (küll) midagi", "Hurricane"},
                    new String[]{"Mustii", "5miinust e i Puuluup", "Eden Golan"}),
            new Question(27, "(Nendest) narkootikumidest ei tea me (küll) midagi", "5miinust e i Puuluup", "Estonia",
                    new String[]{"Estonia", "Israele", "Norvegia"},
                    new String[]{"(Nendest) narkootikumidest ei tea me (küll) midagi", "Hurricane", "Ulveham"},
                    new String[]{"5miinust e i Puuluup", "Eden Golan", "Gåte"}),
            new Question(28, "Hurricane", "Eden Golan", "Israele",
                    new String[]{"Israele", "Norvegia", "Paesi Bassi"},
                    new String[]{"Hurricane", "Ulveham", "Europapa"},
                    new String[]{"Eden Golan", "Gåte", "Joost Klein"}),
            new Question(29, "Ulveham", "Gåte", "Norvegia",
                    new String[]{"Norvegia", "Paesi Bassi", "Cipro"},
                    new String[]{"Ulveham", "Europapa", "Liar"},
                    new String[]{"Gåte", "Joost Klein", "Silia Kapsis"}),
            new Question(30, "Europapa", "Joost Klein", "Paesi Bassi",
                    new String[]{"Paesi Bassi", "Cipro", "Serbia"},
                    new String[]{"Europapa", "Liar", "Remonda"},
                    new String[]{"Joost Klein", "Silia Kapsis", "Teya Dora"}),
            new Question(31, "La Noia", "Angelina Mango", "Italia",
                    new String[]{"Italia", "Francia", "Germania"},
                    new String[]{"La Noia", "Mon amour", "Always on the Run"},
                    new String[]{"Angelina Mango", "Slimane", "Isaak"}),
            new Question(32, "Mon amour", "Slimane", "Francia",
                    new String[]{"Francia", "Italia", "Germania"},
                    new String[]{"Mon amour", "La Noia", "Always on the Run"},
                    new String[]{"Slimane", "Angelina Mango", "Isaak"}),
            new Question(33, "Always on the Run", "Isaak", "Germania",
                    new String[]{"Germania", "Francia", "Italia"},
                    new String[]{"Always on the Run", "Mon amour", "La Noia"},
                    new String[]{"Isaak", "Slimane", "Angelina Mango"}),
            new Question(34, "Luktelk", "Silvester Belt", "Lituania",
                    new String[]{"Lituania", "Francia", "Italia"},
                    new String[]{"Luktelk", "Mon amour", "La Noia"},
                    new String[]{"Silvester Belt", "Slimane", "Angelina Mango"}),
            new Question(35, "Remonda", "Teya Dora", "Serbia",
                    new String[]{"Paesi Bassi", "Cipro", "Serbia"},
                    new String[]{"Europapa", "Liar", "Remonda"},
                    new String[]{"Joost Klein", "Silia Kapsis", "Teya Dora"}),
            new Question(36, "Nebulossa", "Zorra", "Spagna",
                    new String[]{"Spagna", "Cipro", "Irlanda"},
                    new String[]{"Liar", "Remonda", "Doomsday Blue"},
                    new String[]{"Silia Kapsis", "Teya Dora", "Bambie Thug"}),
    };

    @GetMapping("/question")
    public ResponseEntity<QuestionResponse> getRandomQuestion(HttpSession session) {
        Random random = new Random();
        String category = (String) session.getAttribute("category");
        List<Question> filteredQuestions = filterQuestionsByCategory(category);

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


    private List<Question> filterQuestionsByCategory(String category) {
        if (category == null || category.isEmpty()) {
            return Arrays.asList(questions);
        }
        return Arrays.stream(questions)
                .filter(q -> q.getType().name().equals(category))
                .collect(Collectors.toList());
    }

    private Question findQuestionById(int questionId) {
        for (Question question : questions) {
            if (question.getId() == questionId) {
                return question;
            }
        }
        return null;
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


}


