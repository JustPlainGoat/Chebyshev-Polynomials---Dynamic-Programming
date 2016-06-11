package com.michaelmuinos.main;
import java.util.Scanner;

/**
 * The following program will calculate a Chebyshev Polynomial up the amount the user enters.
 */
public class Driver {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the Chebyshev Polynomial to calculate: ");
		int num = scanner.nextInt();
		calculatePolynomial(num).printPolynomial();
		scanner.close();
	}
	
	/**
	 * Calculate the Chebyshev Polynomial with a highest degree of n.
	 * Uses the recurrence relation: Tn+1(x) = 2x*Tn(x) - Tn-1(x)
	 * @param Highest degree polynomial to calculate up to.
	 * @return
	 */
	private static Polynomial calculatePolynomial(int n) {
		// Starting polynomials to build off of
		if(n == 0) {
			return new Polynomial(new int[] {1});
		} else if(n == 1) {
			return new Polynomial(new int[] {0,1});
		}
		
		Polynomial[] chebyshevPolynomials = new Polynomial[n + 1];
		chebyshevPolynomials[0] = new Polynomial(new int[] {1});
		chebyshevPolynomials[1] = new Polynomial(new int[] {0,1});
		
		for(int i = 2; i <= n; i++) {
			int[] coefficients = new int[i + 1];
			int[] secondCoefficients = new int[chebyshevPolynomials[i - 2].coefficients.length];
			
			// Multiply 2x by Tn(x)
			for(int j = 0; j < chebyshevPolynomials[i - 1].coefficients.length; j++) {
				coefficients[j + 1] = 2 * chebyshevPolynomials[i - 1].coefficients[j];
			}
			// Multiply -1 by Tn-1(x)
			for(int j = 0; j < chebyshevPolynomials[i - 2].coefficients.length; j++) {
				secondCoefficients[j] = -1 * chebyshevPolynomials[i - 2].coefficients[j];
			}
			
			// Add 2x*Tn(x) and Tn-1(x)
			int[] finalCoefficients = new int[i + 1];
			for(int j = 0; j < finalCoefficients.length; j++) {
				if(j > (secondCoefficients.length - 1)) {
					finalCoefficients[j] = coefficients[j];
				} else {
					finalCoefficients[j] = coefficients[j] + secondCoefficients[j];
				}
			}
			chebyshevPolynomials[i] = new Polynomial(finalCoefficients);
		}
		return chebyshevPolynomials[n];
	}
	
	/**
	 * The following helper class images a polynomial using an array in which
	 * the indices of the array are the exponent and the values are the coefficients.
	 */
	private static class Polynomial {
		public int[] coefficients;
		
		public Polynomial(int[] coefficients) {
			this.coefficients = coefficients;
		}

		public void printPolynomial() {
			if(coefficients.length == 1) {
				System.out.println("1");
				return;
			}
			if(coefficients.length == 2) {
				System.out.println("x");
				return;
			}

			String polynomial = coefficients[coefficients.length - 1] + "x^" + (coefficients.length - 1);
			for(int i = coefficients.length - 2; i > -1; i--) {
				if(coefficients[i] == 0) continue;
				else if(coefficients[i] > 0) polynomial += " + " + coefficients[i];
				else if(coefficients[i] < 0) polynomial += " - " + (-1 *coefficients[i]);
				if(i == 1) polynomial += "x";
				else if(i > 1) polynomial += "x^" + i;
			}
			System.out.println(polynomial);	
		}
	}
	
}
