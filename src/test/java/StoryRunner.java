import Stories.MyStoriesDConfiguration;
import org.junit.Test;


public class StoryRunner {

    @Test
    public void runStoriesAsJUnit() throws Throwable {
        final MyStoriesDConfiguration story = new MyStoriesDConfiguration();
         story.run();
}

}