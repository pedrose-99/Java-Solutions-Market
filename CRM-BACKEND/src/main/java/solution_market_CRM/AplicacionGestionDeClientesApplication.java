package solution_market_CRM;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import solution_market_CRM.model.Client;
import solution_market_CRM.service.ClientService;
import solution_market_CRM.terminal.TerminalMenuService;

@SpringBootApplication
public class AplicacionGestionDeClientesApplication implements CommandLineRunner{

	@Autowired
    public TerminalMenuService terminalMenuService;
	public static void main(String[] args) {
		SpringApplication.run(AplicacionGestionDeClientesApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		terminalMenuService.runMenu();
	}

}
