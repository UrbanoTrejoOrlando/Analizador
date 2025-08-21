package codigo;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class Principal {

    public static void main(String[] args) throws Exception {
        String ruta1 = "/home/starlord/NetBeansProjects/AnalizadorLexico/src/codigo/Lexer.flex";
        String ruta2 = "/home/starlord/NetBeansProjects/AnalizadorLexico/src/codigo/LexerCup.flex";
        String[] rutas = {
            "-parser", "Sintax",
            "/home/starlord/NetBeansProjects/AnalizadorLexico/src/codigo/Sintax.cup"
        };
        generar(ruta1, ruta2, rutas);
    }

    public static void generar(String ruta1, String ruta2, String[] rutas) throws IOException, Exception {
        // Generar con JFlex
        JFlex.Main.generate(new File(ruta1));
        JFlex.Main.generate(new File(ruta2));

        // Generar con CUP
        java_cup.Main.main(rutas);

        // Asegurar directorio destino
        Path destino = Paths.get("/home/starlord/NetBeansProjects/AnalizadorLexico/src/codigo");
        Files.createDirectories(destino);

        // Mover sym.java
        Path rutaSymOrigen = Paths.get("/home/starlord/NetBeansProjects/AnalizadorLexico/sym.java");
        Path rutaSymDestino = destino.resolve("sym.java");
        if (Files.exists(rutaSymOrigen)) {
            Files.deleteIfExists(rutaSymDestino);
            Files.move(rutaSymOrigen, rutaSymDestino);
        }

        // Mover Sintax.java
        Path rutaSinOrigen = Paths.get("/home/starlord/NetBeansProjects/AnalizadorLexico/Sintax.java");
        Path rutaSinDestino = destino.resolve("Sintax.java");
        if (Files.exists(rutaSinOrigen)) {
            Files.deleteIfExists(rutaSinDestino);
            Files.move(rutaSinOrigen, rutaSinDestino);
        }
    }
}
