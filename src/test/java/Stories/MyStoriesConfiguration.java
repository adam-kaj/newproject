package Stories;

import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.embedder.StoryControls;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.SilentStepMonitor;
import org.jbehave.core.steps.spring.SpringApplicationContextFactory;
import org.jbehave.core.steps.spring.SpringStepsFactory;
import org.jbehave.web.selenium.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.*;

public abstract class MyStoriesConfiguration extends JUnitStories {

    private SeleniumContext seleniumContext = new SeleniumContext();
    private ContextView contextView = new LocalFrameContextView().sized(500, 100);
    private final ApplicationContext context = createDefaultApplicationContext();


    public MyStoriesConfiguration() {
       configureEmbedderControls();
           }

    @Override
    public Configuration configuration() {
        Class<? extends Embeddable> embeddableClass = this.getClass();
        return new SeleniumConfiguration().useSeleniumContext(seleniumContext)
                .useStoryControls(createDefaultStoryControls())
                .useStepMonitor(new SeleniumStepMonitor(contextView, seleniumContext, new SilentStepMonitor()))
                .useStoryLoader(new LoadFromClasspath(embeddableClass))
                .useStoryReporterBuilder(new StoryReporterBuilder()
                        .withCodeLocation(codeLocationFromClass(embeddableClass))
                        .withDefaultFormats()
                        .withFormats(CONSOLE, TXT, HTML, XML));
    }
    private void configureEmbedderControls() {
        configuredEmbedder().embedderControls()
                .useStoryTimeouts("10000")
                .useThreads(1)
                .doIgnoreFailureInStories(false);
    }
    private ConfigurableApplicationContext createDefaultApplicationContext() {
        return new SpringApplicationContextFactory("steps.xml").createApplicationContext();
    }
    private StoryControls createDefaultStoryControls() {
        return new StoryControls()
                .doResetStateBeforeScenario(false)
                .doResetStateBeforeStory(true)
                .doSkipScenariosAfterFailure(true);
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new SpringStepsFactory(configuration(),context);
    }
}
