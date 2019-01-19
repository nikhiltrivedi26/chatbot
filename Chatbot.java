import java.util.*;
import java.io.*;

public class Chatbot{
	private static String filename = "./WARC201709_wid.txt";
	private static ArrayList<Integer> readCorpus(){
		ArrayList<Integer> corpus = new ArrayList<Integer>();
		try{
			File f = new File(filename);
			Scanner sc = new Scanner(f);
			while(sc.hasNext()){
				if(sc.hasNextInt()){
					int i = sc.nextInt();
					corpus.add(i);
				}
				else{
					sc.next();
				}
			}
		}
		catch(FileNotFoundException ex){
			System.out.println("File Not Found.");
		}
		return corpus;
	}
	static public void main(String[] args){
		ArrayList<Integer> corpus = readCorpus();
		int flag = Integer.valueOf(args[0]);

		if(flag == 100){
			int w = Integer.valueOf(args[1]);
			int count = 0;

			//My code starts
			for(int i = 0; i < corpus.size(); i++) {
				if(corpus.get(i).equals(w)) {
					count++;
				}
			}
			//My code ends

			System.out.println(count);
			System.out.println(String.format("%.7f",count/(double)corpus.size()));
		}
		else if(flag == 200){
			int n1 = Integer.valueOf(args[1]);
			int n2 = Integer.valueOf(args[2]);

			//My code starts
			int arrFreq[] = new int[4700];
			for(int i = 0; i < corpus.size(); i++) {
				(arrFreq[corpus.get(i)])++;
			}

			double r = ((double) n1 / (double) n2);
			double currRP = 0;
			double prevRP = 0;

			for(int j = 0; j < 4700; j++) {
				prevRP = currRP;
				currRP = currRP + (double) ((double) arrFreq[j] / (double) 228548);
				if(r <= currRP) {
					System.out.println(j);
					System.out.println(String.format("%.7f", prevRP));
					System.out.println(String.format("%.7f", currRP));
					break;
				}
			}
			//My code ends

		}
		else if(flag == 300){
			int h = Integer.valueOf(args[1]);
			int w = Integer.valueOf(args[2]);
			int count = 0;
			ArrayList<Integer> words_after_h = new ArrayList<Integer>();

			//My code starts
			for(int i = 0; i < 228548; i++) {
				if(corpus.get(i).equals(h)) {
					words_after_h.add(corpus.get(i + 1)); //FIXME: Possible error
				}
			}

			for(int i = 0; i < 228548; i++) {
				if(corpus.get(i).equals(h) && corpus.get(i + 1).equals(w)) {
					count++;
				}
			}
			//My code ends

			//output 
			System.out.println(count);
			System.out.println(words_after_h.size());
			System.out.println(String.format("%.7f",count/(double)words_after_h.size()));
		}
		else if(flag == 400){
			int n1 = Integer.valueOf(args[1]);
			int n2 = Integer.valueOf(args[2]);
			int h = Integer.valueOf(args[3]);

			//My code starts
			double r = (double) ((double) n1 / (double) n2);
			int arrFreqAfter_h[] = new int[4700];
			int hCount = 0;

			for(int i = 0; i < 228548; i++) { //FIXME: error same thing as above
				if(corpus.get(i).equals(h)) {
					hCount++;
					arrFreqAfter_h[corpus.get(i + 1)]++;
				}
			}

			double currRP = 0;
			double prevRP = 0;

			for(int j = 0; j < 4700; j++) {
				prevRP = currRP;
				currRP = currRP + (double) ((double) arrFreqAfter_h[j] / (double) hCount);
				if(r <= currRP) {
					System.out.println(j);
					System.out.println(String.format("%.7f", prevRP));
					System.out.println(String.format("%.7f", currRP));
					break;
				}
			}
			//My code ends

		}
		else if(flag == 500){
			int h1 = Integer.valueOf(args[1]);
			int h2 = Integer.valueOf(args[2]);
			int w = Integer.valueOf(args[3]);

			//My code start
			int arrFreqAfter_h1Andh2[] = new int[4700];
			int h1h2count = 0;

			for(int i = 0; i < 228548; i++) { //FIXME: error same thing as above
				if(corpus.get(i).equals(h1) && corpus.get(i + 1).equals(h2)) {
					h1h2count++;
					arrFreqAfter_h1Andh2[corpus.get(i + 2)]++;
				}
			}

			//output 			
			boolean allZeros = true;
			for(int s = 0; s < 4700; s++) {
				if(arrFreqAfter_h1Andh2[s] != 0) {
					allZeros = false;
					break;
				}
			}

			System.out.println(arrFreqAfter_h1Andh2[w]);
			System.out.println(h1h2count);
			if(allZeros)
				System.out.println("undefined");
			else
				System.out.println(String.format("%.7f",arrFreqAfter_h1Andh2[w]/(double) h1h2count));
		}
		else if(flag == 600){
			int n1 = Integer.valueOf(args[1]);
			int n2 = Integer.valueOf(args[2]);
			int h1 = Integer.valueOf(args[3]);
			int h2 = Integer.valueOf(args[4]);

			//Start
			double r = (double) ((double) n1 / (double) n2);
			int h1h2count = 0;
			int arrFreqAfter_h1Andh2[] = new int[4700];

			for(int i = 0; i < 228548; i++) { //FIXME: error same thing as above
				if(corpus.get(i).equals(h1) && corpus.get(i + 1).equals(h2)) {
					h1h2count++;
					arrFreqAfter_h1Andh2[corpus.get(i + 2)]++;
				}
			}

			double currRP = 0;
			double prevRP = 0;

			if(h1h2count == 0) {												//FIXME: Junky code
				System.out.println("undefined");
			} else {
				if(r == 0.0) {
					for(int j = 0; j < 4700; j++) {
						prevRP = currRP;
						currRP = currRP + (double) ((double) arrFreqAfter_h1Andh2[j] / (double) h1h2count);
						if(r < currRP) { //FIXME: had to delete = could be error
							System.out.println(j);
							System.out.println(String.format("%.7f", prevRP));
							System.out.println(String.format("%.7f", currRP));
							break;
						}
					}
				} else {
					for(int j = 0; j < 4700; j++) {
						prevRP = currRP;
						currRP = currRP + (double) ((double) arrFreqAfter_h1Andh2[j] / (double) h1h2count);
						if(r <= currRP) { //FIXME: had to delete = could be error
							System.out.println(j);
							System.out.println(String.format("%.7f", prevRP));
							System.out.println(String.format("%.7f", currRP));
							break;
						}
					}
				}
			}
		}
		else if(flag == 700) {
            int seed = Integer.valueOf(args[1]);
            int t = Integer.valueOf(args[2]);
            int h1=0,h2=0;

            Random rng = new Random();
            if (seed != -1) rng.setSeed(seed);

            if(t == 0){
                // TODO Generate first word using r
                double r = rng.nextDouble();
                
                //My code starts
				int wordFreqGivenCorpus[] = new int[4700];
				for(int i = 0; i < corpus.size(); i++) {
					(wordFreqGivenCorpus[corpus.get(i)])++;
				}
				
				double currRP = 0;

				for(int j = 0; j < 4700; j++) {
					currRP = currRP + (double) ((double) wordFreqGivenCorpus[j] / (double) 228548);
					if(r <= currRP) {
						h1 = j;
						break;
					}
				}
                //My code end
				
                System.out.println(h1);
                if(h1 == 9 || h1 == 10 || h1 == 12){
                    return;
                }

 //               System.exit(1);
                
                // TODO Generate second word using r
                r = rng.nextDouble();
                
				int historyWordCount = 0;
				int wordFreqGiven_h1[] = new int[4700];
				for(int i = 0; i < corpus.size(); i++) {
					if(corpus.get(i).equals(h1)) {
						historyWordCount++;
						(wordFreqGiven_h1[corpus.get(i + 1)])++;
					}
				}
                
				currRP = 0;

				for(int j = 0; j < 4700; j++) {
					currRP = currRP + (double) ((double) wordFreqGiven_h1[j] / (double) historyWordCount);
					if(r <= currRP) {
						h2 = j;
						break;
					}
				}
				
                System.out.println(h2);
            }
            else if(t == 1){
                h1 = Integer.valueOf(args[3]);
                
                // TODO Generate second word using r
                double r = rng.nextDouble();
                int h1Count = 0;
                
				int wordFreqGiven_h1[] = new int[4700];
				for(int i = 0; i < corpus.size(); i++) {
					if(corpus.get(i).equals(h1)) {
						h1Count++;
						(wordFreqGiven_h1[corpus.get(i + 1)])++;
					}
				}
				
				double currRP = 0;

				for(int j = 0; j < 4700; j++) {
					currRP = currRP + (double) ((double) wordFreqGiven_h1[j] / (double) h1Count);
					if(r <= currRP) {
						h2 = j;
						break;
					}
				}
                
                System.out.println(h2);
            }
            else if(t == 2){
                h1 = Integer.valueOf(args[3]);
                h2 = Integer.valueOf(args[4]);
                
                double r = rng.nextDouble();
                int h1_h2Count = 0;
                
				int wordFreqGiven_h1_h2[] = new int[4700];
				for(int i = 0; i < corpus.size(); i++) {
					if(corpus.get(i).equals(h1) && corpus.get(i + 1).equals(h2)) {
						h1_h2Count++;
						(wordFreqGiven_h1_h2[corpus.get(i + 2)])++;
					}
				}
				
				double currRP = 0;

				for(int j = 0; j < 4700; j++) {
					currRP = currRP + (double) ((double) wordFreqGiven_h1_h2[j] / (double) h1_h2Count);
					if(r <= currRP) {
						h2 = j;
						break;
					}
				}
                
                System.out.println(h2);
            }

            while(h2 != 9 && h2 != 10 && h2 != 12){
                double r = rng.nextDouble();
                int w  = 0;
                
                // TODO Generate new word using h1,h2
                int h1_h2WordCount = 0;
				int arr[] = new int[4700];
				for(int i = 0; i < corpus.size(); i++) {
					if(corpus.get(i).equals(h1) && corpus.get(i + 1).equals(h2)) {
						h1_h2WordCount++;
						(arr[corpus.get(i + 2)])++;
					}
				}
				
				double currRP = 0;
				
				for(int j = 0; j < 4700; j++) {
					currRP = currRP + (double) ((double) arr[j] / (double) h1_h2WordCount);
					if(r <= currRP) {
						w = j;
						break;
					}
				}
                
                System.out.println(w);
                h1 = h2;
                h2 = w;
            }
        }

		return;
	}
}
