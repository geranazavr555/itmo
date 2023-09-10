import exception.ParseException;
import parser.Parser;
import visualization.Visualizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final String USAGE = """
            Arguments: [-s] output_svg_path
            """;

    public static void main(String[] args) throws IOException, ParseException {
        String path;
        boolean showBrowser = false;
        if (args.length == 0 || args.length > 2) {
            System.err.println(USAGE);
            System.exit(1);
            return;
        } else if (args.length == 1) {
            path = args[0];
        } else {
            path = args[1];
            showBrowser = true;
        }

        var parser = new Parser(new BufferedReader(new InputStreamReader(System.in)));
        Visualizer.visualize(parser.parse(), path);

        if (showBrowser) {
            String url = "file:///" + path.replace('\\', '/');
            Runtime.getRuntime().exec("python -m webbrowser -n \"" + url + "\"");
        }
    }
}
