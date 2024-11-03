package br.mt.cba.ufmt.ic.si.alg3.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("/testea")
public class MainView extends VerticalLayout {

    public MainView() {
        Button button = new Button("Click me",
                event -> add(new Paragraph("Clicked!")));

        add(button);
    }
}
