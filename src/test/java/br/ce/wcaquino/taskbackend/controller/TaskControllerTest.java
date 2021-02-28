package br.ce.wcaquino.taskbackend.controller;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;

public class TaskControllerTest {
	
	private static final String MENSAGEM = "Não deveria passar por aqui";
	
	/**
	 O objeto:
	 "@Autowired
	 private TaskRepo todoRepo;"
	 será substituido pelo objeto abaixo
	 Isto é mock. A simulação de objeto. Isso permite que o teste não precise do Spring e um BD para a execução dos testes.
	 Isso é importante para deixar o teste mais rápido e isolado de dependêcias externas
	 */

	@Mock
	private TaskRepo taskRepo;
	
	// O objeto mock "TaskRepo taskRepo" é injetado na classe abaixo substituindo o original 
	@InjectMocks
	private TaskController controller;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void naoDeveSalvarTarefaSemDescricao() {
		Task todo = new Task();
		todo.setDueDate(LocalDate.now());
		try {
			controller.save(todo);
			Assert.fail(MENSAGEM);
		} catch (ValidationException e) {
			Assert.assertEquals("Fill the task description", e.getMessage());
		}
	}

	@Test
	public void naoDeveSalvarTarefaSemData() {
		Task todo = new Task();
		todo.setTask("Descrição");
		try {
			controller.save(todo);
			Assert.fail(MENSAGEM);
		} catch (ValidationException e) {
			Assert.assertEquals("Fill the due date", e.getMessage());
		}
	}

	@Test
	public void naoDeveSalvarTarefaSemDataPassada() {
		Task todo = new Task();
		todo.setTask("Descrição");
		todo.setDueDate(LocalDate.of(1970, 04, 18));
		try {
			controller.save(todo);
			Assert.fail(MENSAGEM);
		} catch (ValidationException e) {
			Assert.assertEquals("Due date must not be in past", e.getMessage());
		}
	}

	@Test
	public void deveSalvarTarefaComSucesso() throws ValidationException {
		Task todo = new Task();
		todo.setTask("Descrição");
		todo.setDueDate(LocalDate.now());
		controller.save(todo);
		
		Mockito.verify(taskRepo).save(todo);
	}

}
