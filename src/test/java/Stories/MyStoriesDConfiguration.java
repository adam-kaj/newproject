package Stories;

import java.util.List;

import static java.util.Arrays.asList;

public class MyStoriesDConfiguration extends MyStoriesConfiguration {

    @Override
    protected List<String> storyPaths(){
        return asList(
                "Stories/GoogleSearch.story"
        );
    }
}
