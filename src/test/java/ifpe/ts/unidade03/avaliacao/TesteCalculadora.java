package ifpe.ts.unidade03.avaliacao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

class TesteCalculadora {

	ChromeDriver driver;

	@BeforeEach
	void setUp() throws Exception {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://www.calculadoraonline.com.br/basica");
	}

	@AfterEach
	void tearDown() throws Exception {
		driver.quit();
	}

	/*
	 * Para testar se a funcionalidade de exponenciação da calculadora funciona com
	 * base e expoente, ambos positivos.
	 */
	@Test
	void testaBasePositivaExpoentePositivo() {
		int resposta = Integer.parseInt(inserirValoresDaExponenciacaoNaCalculadora("8", "2"));
		assertEquals(64, resposta);
	}

	/*
	 * Para testar se a funcionalidade resolve exponenciações com o expoente nulo, o
	 * que deve sempre retornar 1 como resposta.
	 */
	@Test
	void testaExpoenteNulo() {
		int resposta = Integer.parseInt(inserirValoresDaExponenciacaoNaCalculadora("512", "0"));
		assertEquals(1, resposta);
	}

	/*
	 * Para testar se a funcionalidade resolve exponenciações com a base nula, o que
	 * deve sempre retornar 0 como resposta.
	 */
	@Test
	void testaBaseNula() {
		int resposta = Integer.parseInt(inserirValoresDaExponenciacaoNaCalculadora("0", "6"));
		assertEquals(0, resposta);
	}

	/*
	 * Para testar se a funcionalidade resolve exponenciações com o expoente
	 * negativo e se o resultado condiz com o esperado.
	 */
	@Test
	void testaExpoenteNegativo() {
		String resposta = inserirValoresDaExponenciacaoNaCalculadora("5", "-4");
		assertEquals("0,0016", resposta);
	}

	/*
	 * Para testar se a funcionalidade resolve exponenciações com a base negativa e
	 * o expoente ímpar, o que deve sempre retornar um número negativo como
	 * resposta.
	 */
	@Test
	void testaBaseNegativaExpoenteImpar() {
		int resposta = Integer.parseInt(inserirValoresDaExponenciacaoNaCalculadora("-5", "3"));
		assertEquals(-125, resposta);

	}

	/*
	 * Para testar se a funcionalidade resolve exponenciações com a base negativa e
	 * o expoente par, o que deve sempre retornar um número positivo como resposta.
	 */
	@Test
	void testaBaseNegativaExpoentePar() {
		int resposta = Integer.parseInt(inserirValoresDaExponenciacaoNaCalculadora("-3", "2"));
		assertEquals(9, resposta);
	}

	/*
	 * Para testar se a funcionalidade resolve exponenciações com o expoente não
	 * inteiro.
	 */
	@Test
	void testaExpoenteNaoInteiro() {
		String resposta = inserirValoresDaExponenciacaoNaCalculadora("2", "2.2");
		assertEquals("4,59479341998814", resposta);
	}
	
	/*
	 * Para testar se a funcionalidade aceita letra como base.E se ela retorna
	 * o adequado que é "NaN" (sigla de Not a Number).
	 * 
	 */
	@Test
	void testaBaseLetra() {
		String resposta = inserirValoresDaExponenciacaoNaCalculadora("a", "5");
		assertEquals("NaN", resposta);
	}
	
	/*
	 * Para testar se a funcionalidade aceita letra como expoente.E se ela retorna
	 * o adequado que é "NaN" (sigla de Not a Number).
	 * 
	 */
	@Test
	void testaExpoenteLetra() {
		String resposta = inserirValoresDaExponenciacaoNaCalculadora("3", "b");
		assertEquals("NaN", resposta);
	}

	/*
	 * Função criada para não estar repetindo esse trecho de código em todos os casos de teste.
	 */
	String inserirValoresDaExponenciacaoNaCalculadora(String base, String expoente) {
		driver.findElement(By.id("b31")).click();
		driver.findElement(By.id("cx31_0")).sendKeys("" + base);
		driver.findElement(By.id("cx31_1")).sendKeys("" + expoente);
		driver.findElement(By.cssSelector("button.uk-button.uk-button-default")).click();
		String resposta = driver.findElement(By.id("TIExp")).getAttribute("value");
		return resposta;
	}

}
