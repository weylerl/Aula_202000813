import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Banco {

	static String clientes[] = new String[20];
	static String enderecos[] = new String[20];
	static double saldos[] = new double[20];

	public static void main(String[] args) throws IOException {

		//	criaConta(1, "Fulano de Tal", "Rua Sem Fim Algum XX");

		carregaClientes();
		carregaSaldos();

		imprimeArrays();
		quantidadeContas();

//		criaConta(7, "Onofre", "Rua da Casa");
//		deposita(7, 100.20);
	
//		excluiConta(3);
		
		gravaClientes();
		gravaSaldos();
		

	}

	static void criaConta (int numConta, String nomeCliente, String endereco) {
		if (clientes[numConta] != null)
			System.out.println("Número de conta já utilizado!!");
		else {
			clientes[numConta] = nomeCliente;
			enderecos[numConta] = endereco;
			System.out.println("Conta "+ numConta + " incluida com sucesso!!");
		} 
	} 

	static void excluiConta (int numConta) {
		if (clientes[numConta] == null)
			System.out.println("Número de conta inexistente!!");
		else {
			clientes[numConta] = null;
			saldos[numConta] = 0.00;
			System.out.println("Conta "+ numConta + " excluída com sucesso!!");
		} 
	} 


	static void deposita (int numConta, double valor) {
		if (clientes[numConta] == null) 
			System.out.println("Número de conta inexistente!!");
		else {
			saldos[numConta] = saldos[numConta] + valor;
			System.out.println("Depósito efetuado com sucesso na conta " + numConta );
		}
	} 

	static void saque (int numConta, double valor) {
		if (clientes[numConta] == null) 
			System.out.println("Número de conta inexistente!!");
		else {
			if (saldos[numConta] < valor)
				System.out.println("Saldo insuficiente na conta " + numConta );
			else {	
				saldos[numConta] = saldos[numConta] - valor;
				System.out.println("Saque efetuado com sucesso na conta " + numConta );
			}
		}
	} 

	static void transferencia (int numContaOrigem, int numContaDestino, double valor) {
		if ((clientes[numContaOrigem] == null) ||  (clientes[numContaDestino] == null)){
			System.out.println("Número de conta origem ou destino inexistente!!");
			return;
		}
		if (saldos[numContaOrigem] < valor)
			System.out.println("Saldo insuficiente na conta de origem " + numContaOrigem );
		else {	
			saldos[numContaOrigem] = saldos[numContaOrigem] - valor;
			saldos[numContaDestino] = saldos[numContaDestino] + valor;
			System.out.println("Transferência efetuada com sucesso da conta " + numContaOrigem +
					" para " +  numContaDestino );
		}
	}


	static void consultaSaldo (int numConta) {
		if (clientes[numConta] == null) 
			System.out.println("Número de conta inexistente!!");
		else {
			System.out.println("Saldo da conta " + numConta + ": " + saldos[numConta]);
		}
	} 

	static void quantidadeContas () {
		int cont = 0;
		for (int i = 0; i < clientes.length; i++)
			if (clientes[i] != null) 
				cont++;
		System.out.println("Quantidade de contas:  " + cont);
	} 

	public static void carregaClientes() throws FileNotFoundException  {
		Scanner sc = new Scanner(new BufferedReader(new FileReader("clientes")));
		int cont = 0;
		while(sc.hasNextLine()) {
			String[] linha = sc.nextLine().trim().split(":");
			for (int i=0; i < linha.length; i++) {
				if (linha[i].equals("null"))
					clientes[cont] = null;
				else 
					clientes[cont] = linha[i];
				cont++;
			}
		}
		sc.close();
//		System.out.println(Arrays.deepToString(clientes));
	}

	public static void carregaSaldos() throws FileNotFoundException  {
		Scanner sc = new Scanner(new BufferedReader(new FileReader("saldos")));
		int cont = 0;
		while(sc.hasNextLine()) {
			String[] linha = sc.nextLine().trim().split(":");
			for (int i=0; i < linha.length; i++) {
				saldos[cont] = Double.parseDouble(linha[i]);
				cont++;
			}
		}
		sc.close();
		//	System.out.println(saldos.toString());
	}

	public static void gravaClientes() throws IOException  {
		FileWriter writer = new FileWriter("clientes");
		int cont = 0;
		for (int i = 0; i < (clientes.length - 1); i++) {
			if (cont == 4) { // quebra a linha quando a quantidade de valores por linha for alcançada.
				cont = 0;
				writer.write(clientes[i] + "");
				writer.write("\n");
			}
			else {
				writer.write(clientes[i] + ":");
				cont++;
			}
		}
		writer.write(clientes[clientes.length - 1] + "");
		writer.close();
	}

	
	public static void gravaSaldos() throws IOException  {
		FileWriter writer = new FileWriter("saldos");
		int cont = 0;
		for (int i = 0; i < (saldos.length - 1); i++) {
			if (cont == 4) { // quebra a linha quando a quantidade de valores por linha for alcançada.
				cont = 0;
				writer.write(saldos[i] + "");
				writer.write("\n");
			}
			else {
				writer.write(saldos[i] + ":");
				cont++;
			}
		}
		writer.write(saldos[saldos.length - 1] + "");
		writer.close();
	}


	static void imprimeArrays() {
		for (String c: clientes) {
			System.out.print(c + ":");
		}
		System.out.println();
		for (double s: saldos) {
			System.out.print(s + ":");
		}
		System.out.println();
	}
}
