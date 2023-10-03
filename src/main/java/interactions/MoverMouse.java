package interactions;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/* uso de la clase Actions para presionar teclas
 *
 * en esta interaccion en la linea "action.sendKeys(Keys.ENTER).build().perform();"
 * se presiona la tecla ENTER. En caso de requerir otro tipo de acciones aqui encontraras mas informacion
 *
 * https://www.browserstack.com/guide/action-class-in-selenium
 *
 * */

public class MoverMouse implements Interaction {

    @Override
    public <T extends Actor> void performAs(T actor) {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }

// Indicando coordenadas en pantalla y moviendo el raton a la pocision indicada
        int x = 285;
        int y = 165;
        robot.mouseMove(x, y);
// Ejecutando un click
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
// Algunas veces, podrias necesitas presionar "Enter" tambien
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    public static MoverMouse ahora() {
        return Instrumented.instanceOf(MoverMouse.class).withProperties();
    }
}
