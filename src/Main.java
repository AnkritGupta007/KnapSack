import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		List<ArrayList<Item>> subset=new ArrayList<ArrayList<Item>>();
		Scanner sc=new Scanner(System.in);

		ArrayList<Item> result=new ArrayList<>();
		double weightCapacity,sizeCapacity;
		System.out.print("Enter the weight and size capacity (separated by space: ");
		weightCapacity=sc.nextDouble();
		sizeCapacity=sc.nextDouble();
		sc.nextLine();

		int numberOfItems=sc.nextInt();

		double weights[]=new double[numberOfItems];
		double sizes[]=new double[numberOfItems];
		double values[]=new double[numberOfItems];

		System.out.print("Enter weights: ");
		for( int i=0;i<numberOfItems;i++) {
			weights[i]=sc.nextDouble();
		}
		sc.nextLine();

		System.out.print("Enter sizes: ");
		for( int i=0;i<numberOfItems;i++) {
			sizes[i]=sc.nextDouble();
		}
		sc.nextLine();

		System.out.print("Enter values: ");
		for( int i=0;i<numberOfItems;i++) {
			values[i]=sc.nextDouble();
		}
		sc.nextLine();

		Item[] items=new Item[numberOfItems];

		for(int i=0;i<numberOfItems;i++) {
			items[i]=new Item(weights[i],sizes[i],values[i]);
		}




		for(int i=0;i<numberOfItems;i++) {

			if(i==0) {
				ArrayList<Item> temp=new ArrayList<>();
				temp.add(items[0]);
				subset.add(temp);
			}
			else {


				ArrayList<ArrayList<Item>> tempSubset=new ArrayList<ArrayList<Item>>();

				for(ArrayList<Item> baha: subset) {

					//needed to do these steps because the same memory identity will  only
					//copy the reference, and act on the same objects collections
					ArrayList<Item> uncached=new ArrayList<>(baha);
					tempSubset.add(uncached);
				}

				for (ArrayList<Item> currentList : tempSubset) {
					currentList.add(items[i]);
				}
				ArrayList<Item> last=new ArrayList<Item>();
				last.add(items[i]);

				tempSubset.add(last);

				subset.addAll(tempSubset);

				tempSubset.clear();
			}
		}
		double resultValue[]=new double[3];
		for (ArrayList<Item> currentList : subset) {
			double size=0;
			double weight=0;
			double value=0;
			for(Item item:currentList) {
				size=size+item.getSize();
				weight+=item.getWeight();
				value+=item.getValue();
			}

			if(size==sizeCapacity && weight==weightCapacity) {
				if(value>resultValue[2]) {
					result=currentList;
					resultValue[0]=weight;
					resultValue[1]=size;
					resultValue[2]=value;

				}
			}
		}
		
		if(result.size()==0) {
			System.out.println("No packing is found");
		}
		else {
			System.out.println("Found an optimal packing: ");
			for(int i=0;i<result.size();i++) {
				System.out.printf("Item : %d %.2f, %.2f, %.2f ",i+1, result.get(i).getWeight(), result.get(i).getSize(),result.get(i).getValue());
				System.out.println();
			}
			System.out.printf("Total weight: %.2f",resultValue[0]);
			System.out.println();
			System.out.printf("Total size: %.2f", resultValue[1]);
			System.out.println();
			System.out.printf("Total value: %.2f", resultValue[2]);
		}




	}
}
