package me.oguzhanozer.musfer.views;

import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import me.oguzhanozer.musfer.base.ui.MainLayout;

import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.component.UI;

@Route(value = "login", layout = MainLayout.class)
@Menu(order = 1, icon = "vaadin:sign-in", title = "Sign in")
@PageTitle("Sign in | Musfer")
public class LoginView extends VerticalLayout {

    public LoginView() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        LoginForm loginForm = new LoginForm();
        loginForm.setAction("login"); // Optional if using Spring Security

        loginForm.addLoginListener(e -> {
            if (authenticate(e.getUsername(), e.getPassword())) {
                // Navigate to home after successful login
                UI.getCurrent().navigate("home");
            } else {
                loginForm.setError(true);
            }
        });

        // Optional: customize labels
        LoginI18n i18n = LoginI18n.createDefault();
        i18n.setHeader(new LoginI18n.Header());
        i18n.getHeader().setTitle("Musfer Login");
        i18n.getHeader().setDescription("Please enter your credentials");
        loginForm.setI18n(i18n);

        add(loginForm);
    }

    private boolean authenticate(String username, String password) {
        // Basic example — replace with real authentication
        return "admin".equals(username) && "secret".equals(password);
    }
}
