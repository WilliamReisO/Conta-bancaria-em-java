package conta.controller;

import java.util.ArrayList;

import conta.model.Conta;
import conta.repository.ContaRepository;

public class ContaController implements ContaRepository {

	private ArrayList<Conta> listaContas = new ArrayList<Conta>();
	int numero = 0;

	@Override
	public void procurarPorNumero(int numero) {
		var conta = BuscarNaCollection(numero);
		if (conta != null)
			conta.visualizar();
		else
			System.out.println("\nA Conta número:  " + numero + "não foi encontrada!");

	}

	@Override
	public void listarTodas() {
		for (var conta : listaContas) {
			conta.visualizar();
		}
	}

	@Override
	public void cadastrar(Conta conta) {
		listaContas.add(conta);
		System.out.println("\nA Conta número : " + conta.getNumero() + " foi criada com sucesso !");
	}

	@Override
	public void atualizar(Conta conta) {
		var buscaConta = BuscarNaCollection(conta.getNumero());

		if (buscaConta != null) {
			listaContas.set(listaContas.indexOf(buscaConta), conta);
			System.out.println("\nA Conta numero: " + conta.getNumero() + " foi atualizada com sucesso ! ");

		} else
			System.out.println("\nA Conta numero: " + conta.getNumero() + " não foi encontrada ! ");
	}

	@Override
	public void deletar(int numero) {
		var conta = BuscarNaCollection(numero);
		
		if (conta != null) {
			if (listaContas.remove(conta)== true);
				System.out.println("\nA Conta numero: " + numero + "foi deletada com sucesso! ");	
		}else
			System.out.println("\nA Conta numero: " + numero +" não foi encontrada!");
	}

	@Override
	public void sacar(int numero, float valor) {
		var conta = BuscarNaCollection(numero);
		
		if (conta != null) {
			
			if(conta.sacar(valor) == true)
				System.out.println("\n O Saque na Conta numero: "+ numero +" foi efetuado co sucesso !");
		}else
			System.out.println("\nA Conta numero: " + numero + "não fou encontrada !");

	}

	@Override
	public void depositar(int numero, float valor) {
		var conta = BuscarNaCollection(numero);
		
		if(conta != null) {
			conta.depositar(valor);
			System.out.println("\nO Depósito na Conta numero : "+ numero +"foi efetuado com sucesso!");
		}else {
			System.out.println("\nA Conta numero : "+ numero + "não foi encontarda ou a Conta destino não é uma Conta Corrente!");;
		}

	}

	@Override
	public void transferir(int numeroOrigem, int numeroDestino, float valor) {
		var contaOrigem = BuscarNaCollection(numeroOrigem);
		var contaDestino = BuscarNaCollection(numeroDestino);
		
		if (contaOrigem != null && contaDestino != null) {
			if (contaOrigem.sacar(valor)==true) {
				System.out.println("\n Transferência foi efetuada com sucesso! ");
			}
			
		}else 
			System.out.println("\nA Conta de Origem e/ou Destino não foram encontradas !");

	}

	public int gerarNumero() {
		return ++numero;
	}

	public Conta BuscarNaCollection(int numero) {
		for (var conta : listaContas) {
			if (conta.getNumero() == numero) {
				return conta;
			}
		}
		return null;
	}

	public int retornaTipo(int numero) {
		for (var conta : listaContas) {
			if (conta.getNumero() == numero) {
				return conta.getTipo();

			}
		}
		return 0;
	}

}
