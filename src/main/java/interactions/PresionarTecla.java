package interactions;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

/* uso de la clase Actions para presionar teclas
 *
 * en esta interaccion en la linea "action.sendKeys(Keys.ENTER).build().perform();"
 * se presiona la tecla ENTER. En caso de requerir otro tipo de acciones aqui encontraras mas informacion
 *
 * https://www.browserstack.com/guide/action-class-in-selenium
 *
 * */

public class PresionarTecla implements Interaction {

    WebDriver driver;
    @Override
    public <T extends Actor> void performAs(T actor) {
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ENTER).build().perform();

    }

    public static PresionarTecla onScreen(){
        return Instrumented.instanceOf(PresionarTecla.class).withProperties();
    }
}
