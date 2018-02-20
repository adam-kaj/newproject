import java.util.Arrays;
import java.util.List;

import Steps.GoogleStep;
import org.jbehave.core.embedder.Embedder;

public class SimpleJBehave {

    private static Embedder embedder = new Embedder();
    private static List<String> storyPaths = Arrays.asList("Stories/GoogleSearch.story");

    public static void main(String[] args) {
        embedder.candidateSteps().add(new GoogleStep());
        embedder.runStoriesAsPaths(storyPaths);
    }
}
