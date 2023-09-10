package ru.itmo.ctd.parsers.lab3;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.codehaus.plexus.util.IOUtil;
import ru.itmo.ctd.parsers.lab3.grammar.gen.KekLexer;
import ru.itmo.ctd.parsers.lab3.grammar.gen.KekParser;
import ru.itmo.ctd.parsers.lab3.translation.KekContextManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Main {
    private static final Function<List<Integer>, Long> kek = list -> 5L;



    public static void main(String[] args) throws IOException {
        System.out.println(kek.apply(new ArrayList<>()));
    }
}
