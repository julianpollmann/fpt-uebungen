package problem4;

public class Balance {

	//Bubblesort zum Ordnen der Kassen in der Bilanz - Kasse mit größtem Umsatz soll auf Position 0
	public static int[] bubblesort(int[] zusortieren) {
		int temp;
		for(int i=1; i<zusortieren.length; i++) {
			for(int j=0; j<zusortieren.length-i; j++) {
				if(zusortieren[j]<zusortieren[j+1]) {
					temp=zusortieren[j];
					zusortieren[j]=zusortieren[j+1];
					zusortieren[j+1]=temp;
				}

			}
		}
		return zusortieren;
	}


	//Test zum Sortieren
	public static void main(String[] args) {

		int arrayList[]={200,500,3000,20,50};
		int[] sortiert=bubblesort(arrayList);

		for (int i = 0; i<sortiert.length; i++) {
			System.out.print(sortiert[i] + ", ");
		}

	}
	 //benutze PriorityBlockingQueue hier
}


