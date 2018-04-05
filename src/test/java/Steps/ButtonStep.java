package Steps;

import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.When;
import PageFactory.PageFactory;

public class ButtonStep {

    private PageFactory factory;

    public ButtonStep(PageFactory factory) {
        this.factory = factory;
    }

    @When("choose button by id '$id'")
    public void clickButtonById(@Named("id") String id) throws InterruptedException {
        factory.getButtonPage().clickButtonById(id);
    }

    @When("choose button contains id '$id'")
    public void clickButtonContainsId(@Named("id") String id) throws InterruptedException {
        factory.getButtonPage().clickButtonContainsId(id);
    }

    @When("choose button contains name '$resourceKey'")
    public void clickButtonContainsName(@Named("elementName") String resourceKey) throws InterruptedException {
        factory.getButtonPage().clickButtonContainsName(resourceKey);
    }
}