package stepdefinitions;

import io.cucumber.datatable.DataTable;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Constantes;
import net.serenitybdd.screenplay.GivenWhenThen;

import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.ensure.Ensure;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import questions.ValidarBooleano;
import questions.ValidarTexto;
import tasks.*;
import models.setdata.*;
import net.serenitybdd.screenplay.actors.OnStage;

import static userinterfaces.CheckoutPage.*;

public class SwagLabsStepDefinitions {

    @Given("el actor ingresa a saucedemo.com")
    public void elActorIngresaASauceDemoCom(DataTable table) {
        OnStage.theActorCalled(Constantes.ACTOR).wasAbleTo(Open.url(AbrirLoombokData.setData(table).get(0).getUrl()));
    }

    @When("el actor se autentica con la siguiente informacion")
    public void elActorSeAutenticaConLaSiguienteInformacion(DataTable table) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                Autenticarse.conCredenciales(AutenticarseLoombokData.setData(table).get(0))
        );
    }

    @When("agrega el producto al carrito y hace el checkout")
    public void agregaElProductoAlCarritoYHaceElCheckout(DataTable table) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                Agregar.alCarrito(AgregarAlCarritoLoombokData.setData(table).get(0))
        );
    }

    @Then("valida que el producto se compro de manera exitosa")
    public void validaQueElProductoSeComproDeManeraExitosa(DataTable table) {
        OnStage.theActorInTheSpotlight().should(GivenWhenThen.seeThat(ValidarTexto.delElemento(TXT_CONFIRMAR), Matchers.containsString(FinalizarCompraLoombokData.setData(table).get(0).getMensajeExitoso())));
        OnStage.theActorInTheSpotlight().should(GivenWhenThen.seeThat(ValidarBooleano.delElemento(TXT_CONFIRMAR)));
        Ensure.thatTheAnswerTo(ValidarTexto.delElemento(TXT_CONFIRMAR)).contains(FinalizarCompraLoombokData.setData(table).get(0).getMensajeExitoso());
        Ensure.thatTheAnswerTo(ValidarBooleano.delElemento(TXT_CONFIRMAR)).isTrue();
        Ensure.that(TXT_CONFIRMAR).textContent().contains(FinalizarCompraLoombokData.setData(table).get(0).getMensajeExitoso());
    }
}
