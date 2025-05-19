package service;

import models.Form;
import models.Question;
import models.Response;
import models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormManager {
    private Map<String, Form> forms = new HashMap<>();
    private Map<String, List<Response>> responses = new HashMap<>();
    private Map<String, User> users = new HashMap<>();

    public User registerUser(String name, String email) {
        User user = new User(name, email);
        users.put(user.getUserId(), user);
        return user;
    }

    public String createForm(User user, String title) {
        Form form = new Form(title);
        forms.put(form.getFormId(), form);
        user.createForm(form);
        return form.getFormId();
    }

    public void addQuestion(String formId, Question question, User user) {
        Form form = forms.get(formId);
        if (form != null && user.getOwnedForms().contains(form)) {
            form.addQuestion(question);
        } else {
            throw new SecurityException("User is not the owner of the form");
        }
    }

    public void submitResponse(String formId, Response response) {
        if (!forms.containsKey(formId)) throw new IllegalArgumentException("Form does not exist.");
        responses.computeIfAbsent(formId, k -> new ArrayList<>()).add(response);
    }

    public List<Response> getResponses(String formId) {
        return responses.getOrDefault(formId, new ArrayList<>());
    }
}
