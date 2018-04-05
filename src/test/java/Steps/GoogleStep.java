package Steps;

import PageFactory.PageFactory;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.When;

public class GoogleStep {

    private PageFactory factory;

    public GoogleStep(PageFactory factory)
    {
        this.factory = factory;
    }

    public GoogleStep() {

    }

    @Given("web '$parameter'")
    public void openWeb(@Named("parameter") String url) throws InterruptedException {
    factory.getGooglePage().openWeb(url);
 }
 @When("enter and search '$text'")
    public void getSearchText(@Named("text") String text) throws InterruptedException {
    factory.getGooglePage().setSearchText(text);
 }
 @When("choose googlelist element contains '$text'")
    public void clickGoogleListElement(@Named("text") String text) throws InterruptedException {
    factory.getGooglePage().clickGoogleListElement(text);
    }
 }

