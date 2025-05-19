import models.Question;
import models.QuestionFactory;
import models.Response;
import models.User;
import service.FormManager;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FormManager manager = new FormManager();

        // Register user
        User user = manager.registerUser("Alice", "alice@example.com");

        // Create form
        String formId = manager.createForm(user, "Job Application Form");

        // Add questions using factory
        Question q1 = QuestionFactory.createQuestion("text", "What is your name?", null);
        Question q2 = QuestionFactory.createQuestion("mcq", "Which language do you prefer?", Arrays.asList("Java", "Python", "C++"));
        manager.addQuestion(formId, q1, user);
        manager.addQuestion(formId, q2, user);

        // Submit a response
        Response response = new Response(formId);
        response.addAnswer("John Doe");
        response.addAnswer("Java");
        manager.submitResponse(formId, response);

        // Print submitted responses
        List<Response> submittedResponses = manager.getResponses(formId);
        for (Response res : submittedResponses) {
            System.out.println("Response for Form ID: " + res.getFormId());
            for (String ans : res.getAnswers()) {
                System.out.println("Answer: " + ans);
            }
        }
    }
}