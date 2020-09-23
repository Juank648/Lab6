package edu.eci.cvds.Faces;

import java.util.ArrayList;
import java.util.Random;
import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;


@ManagedBean(name = "calculadoraBean")
@ApplicationScoped
public class faces{
	private ArrayList<Double> datosIngresados;
	private double media;
	private double standardDesviation;
	private double variance;
	private double mode;
	public String cantidadNumeros;
	public double prueba;
	
	public faces(){
		datosIngresados = new ArrayList<Double>();
		media = 0;
		standardDesviation = 0;
		variance = 0;
		mode = 0;
		cantidadNumeros = "";
		prueba = 0;
	}
	
	public double getPrueba() {
		return prueba;
	}
	
	public void setDatosIngresados(ArrayList <Double> datosIngresados){
		this.datosIngresados = datosIngresados;
	}
	
	public ArrayList<Double> getDatosIngresados() {
		return datosIngresados;
	}
	
	public double getMedia(){
		return media;
	}
	public double getStandardDesviation(){
		return standardDesviation;
	}
	public double getVariance(){
		return variance;
	}
	public double getMode(){
		return mode;
	}
	
	
	public void setCantidadNumeros(String cantidadNumeros){
		this.cantidadNumeros = cantidadNumeros;
	}
	
	public String getCantidadNumeros() {
		return cantidadNumeros;
	}

	public  ArrayList<Double> convertirString() {
    	//ArrayList<Double> datosIngresados = new ArrayList<Double>();
		String pend = "";
		//System.out.println("Inicio");
		for(int i = 0; i < cantidadNumeros.length(); i++) {
			char t = ';';
			//System.out.println(cantidadNumeros.charAt(i));
			//System.out.println(i);
			if(cantidadNumeros.charAt(i) == t || i == cantidadNumeros.length()-1) {
				if(i == cantidadNumeros.length()-1) {
					pend +=  cantidadNumeros.charAt(i);
				}
				double doble = Double.parseDouble(pend);
				datosIngresados.add(doble);
				pend = "";
			}else {
				pend +=  cantidadNumeros.charAt(i);
			}
		}
		//System.out.println("Fin");
		return datosIngresados;
	}
	
	public double calculateMean(){
		convertirString();
		double res = 0; 
		for(int i = 0; i<datosIngresados.size();i++){
			res += datosIngresados.get(i);
		}
		res = res/datosIngresados.size();
		this.media = res;
		return res;
	}
	
	public double calculateStandardDeviation(){
		convertirString();
		double med = 0;
		med = calculateMean();
		double x = 0; 
		for(int i = 0; i<datosIngresados.size();i++){
			double y = 0;
			y = datosIngresados.get(i) - med;
			x += y*y;
		}
		x = Math.sqrt(x/datosIngresados.size());
		this.standardDesviation = x;
		return x;
	}
	
	public double calculateVariance(){
		convertirString();
		double med = 0;
		med = calculateMean();
		double x = 0; 
		for(int i = 0; i<datosIngresados.size();i++){
			double y = 0;
			y = datosIngresados.get(i) - med;
			x += y*y;
		}
		x = x/datosIngresados.size();
		this.variance = x;
		return x;
	}
	
	public double calculateMode(){
		convertirString();
		int maximoNumRepeticiones= 0;
        double moda= 0;
		for(int i=0; i<datosIngresados.size(); i++)
		{
			int numRepeticiones= 0;
			for(int j=0; j<datosIngresados.size(); j++)
			{
				if(datosIngresados.get(i).equals(datosIngresados.get(j)))
				{
					numRepeticiones++;
				}   //fin if
				if(numRepeticiones>maximoNumRepeticiones)
				{
					moda= datosIngresados.get(i);
					maximoNumRepeticiones= numRepeticiones;
				}
				
				//fin if
			}
		}   //fin for
		this.mode = moda;
		return moda;
	}
	public void restart(){
		
		this.datosIngresados = new ArrayList<Double>();
		this.mode = 0;
		this.standardDesviation = 0;
		this.media = 0;
		this.variance = 0;
		this.cantidadNumeros = "";
	}
	
}