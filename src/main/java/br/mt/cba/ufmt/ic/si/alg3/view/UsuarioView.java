package br.mt.cba.ufmt.ic.si.alg3.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import br.mt.cba.ufmt.ic.si.alg3.model.Usuario;
import br.mt.cba.ufmt.ic.si.alg3.repository.UsuarioRepository;

import java.util.List;

@Route("usuarios")
public class UsuarioView extends VerticalLayout {

  private UsuarioRepository usuarioRepository = new UsuarioRepository();
  private Grid<Usuario> grid = new Grid<>(Usuario.class);
  private TextField filterText = new TextField();

  public UsuarioView() {
    addClassName("usuario-view");
    setSizeFull();
    configureGrid();

    Button addUsuarioButton = new Button("Adicionar Usuário");
    addUsuarioButton.addClickListener(e -> {
      // Lógica para adicionar usuário (pode abrir um formulário)
    });

    HorizontalLayout toolbar = new HorizontalLayout(filterText, addUsuarioButton);
    toolbar.addClassName("toolbar");
    add(toolbar, grid);

    updateGrid();
  }

  private void configureGrid() {
    grid.addClassName("usuario-grid");
    grid.setSizeFull();
    grid.setColumns("id", "nome", "login", "nivelAcesso");
    grid.getColumnByKey("senha").setVisible(false); // Ocultar senha
  }

  private void updateGrid() {
    List<Usuario> usuarios = usuarioRepository.listarTodos();
    grid.setItems(usuarios);
  }
}
