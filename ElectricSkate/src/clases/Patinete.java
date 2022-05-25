package clases;

public class Patinete {
	
	//Atributos
	
	private String marca;
	private String modelo;
	private String color;
	private double kmRecorridos;
	private int numeroSerie;
	
	
	//Constructor
	public Patinete(String marca, String modelo, String color, double kmRecorridos, int numeroSerie) {
		super();
		this.marca = marca;
		this.modelo = modelo;
		this.color = color;
		this.kmRecorridos = kmRecorridos;
		this.numeroSerie = numeroSerie;
	}
	
	//Getters & Setters

	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	public String getModelo() {
		return modelo;
	}


	public void setModelo(String modelo) {
		this.modelo = modelo;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public double getKmRecorridos() {
		return kmRecorridos;
	}


	public void setKmRecorridos(double kmRecorridos) {
		this.kmRecorridos = kmRecorridos;
	}


	public int getNumeroSerie() {
		return numeroSerie;
	}


	public void setNumeroSerie(int numeroSerie) {
		this.numeroSerie = numeroSerie;
	}
	
}
