package solution_market_CRM.terminal;

import java.text.AttributedCharacterIterator.Attribute;
import java.util.Map;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.AttributedStyle;
import org.jline.utils.AttributedString;
import org.springframework.stereotype.Service;
import org.jline.reader.impl.completer.StringsCompleter;

@Service
public class TerminalMenuService {

    public void runMenu() throws Exception 
    {
            // Reset color
        final String RESET = "\u001B[0m";

            // Text colors
        final String BLACK = "\u001B[30m";
        final String RED = "\u001B[31m";
        final String GREEN = "\u001B[32m";
        final String YELLOW = "\u001B[33m";
        final String BLUE = "\u001B[34m";
        final String MAGENTA = "\u001B[35m";
        final String CYAN = "\u001B[36m";
        final String WHITE = "\u001B[37m";

            // Background colors
        final String BACKGROUND_BLACK = "\u001B[40m";
        final String BACKGROUND_RED = "\u001B[41m";
        final String BACKGROUND_GREEN = "\u001B[42m";
        final String BACKGROUND_YELLOW = "\u001B[43m";
        final String BACKGROUND_BLUE = "\u001B[44m";
        final String BACKGROUND_MAGENTA = "\u001B[45m";
        final String BACKGROUND_CYAN = "\u001B[46m";
        final String BACKGROUND_WHITE = "\u001B[47m";

        final String BOLD = "\u001B[1m";
        final String UNDERLINED = "\u001B[4m";
    
        Terminal terminal = TerminalBuilder.terminal();
        
        LineReader reader = LineReaderBuilder.builder()
                .terminal(terminal)
                .completer(new StringsCompleter("1","2", "3"))
                .build();

        String option = "";
        while (!option.equals("3")) {
            terminal.writer().println(BOLD +"\n=== MENÚ PRINCIPAL ===");
            terminal.writer().println(UNDERLINED +"1. Listar Clientes");
            terminal.writer().println(BACKGROUND_MAGENTA +"2. Listar Empleados");
            terminal.writer().println(YELLOW +"3. Salir");

            option = reader.readLine("Seleccione una opción: ");

            switch (option) {
                case "1":
                    terminal.writer().println("Clientes:");
                    break;
                case "2":
                    terminal.writer().println("Empleados:");
                    break;
                case "3":
                    terminal.writer().println("Saliendo de la aplicación...");
                    break;
                default:
                    terminal.writer().println("Opción no válida.");
            }
        }
        terminal.flush();
    }
}
