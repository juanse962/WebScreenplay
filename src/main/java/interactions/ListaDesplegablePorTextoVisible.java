package interactions;


import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


/* Solo para listas desplegables de tipo <ul> <li>
*
* Uso de esta interaccion -> ListaDesplegablePorTextoVisible(Target, TextoEsperado)
*
* */

public class ListaDesplegablePorTextoVisible implements Interaction {

    private final Target element;
    private final String string;

    public ListaDesplegablePorTextoVisible(Target element, String string) {
        this.element = element;
        this.string = string;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        try {
            WebElement listLocation = element.resolveFor(actor);
            List<WebElement> options = listLocation.findElements(By.tagName("li"));
            for (WebElement i : options) {
                if (i.getText().contains(string)) {
                    i.click();
                    break;
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println("El texto no se encuentra en la lista: " + e);
        } catch (WebDriverException e) {
            System.out.println("Error de interaccion con WebDriver: " + e);
        } catch (Exception e) {
            System.out.println("error inesperado: " + e);
        }

    }

    public static ListaDesplegablePorTextoVisible on(Target element, String string) {
        return Instrumented.instanceOf(ListaDesplegablePorTextoVisible.class).withProperties(element, string);
    }
}
