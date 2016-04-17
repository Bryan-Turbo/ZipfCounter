package Default;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Bryan on 15-4-2016.
 */
public class Main {
	List<String> uniqueWords = new ArrayList<>();
	List<Integer> uniqueWordsCount = new ArrayList<>();


	public void run(){
		String[] words = StringContainer.sentence.split(" ");
		for(String word : words){
			word = word.toLowerCase();
			try{
				word = word.replace("\n", "").replace(".", "").replace(",", "").replace(":", "").replace(";", "").replace("\"", "").replace("[", "").replace("]", "");
			}catch(Exception e){}

			if(!uniqueWords.contains(word)){
				if(Pattern.matches("[a-zA-Z]+", word)) {
					uniqueWords.add(word);
					uniqueWordsCount.add(1);
				}
			}else{
				int index = uniqueWords.indexOf(word);
				int value = uniqueWordsCount.get(index);

				uniqueWordsCount.set(index, value + 1);
			}
		}

		int CorrectOrder;
		do{
			CorrectOrder = 0;
			for(int i = 1; i < uniqueWords.size(); i++){
				int currentValue = uniqueWordsCount.get(i);
				int previousValue = uniqueWordsCount.get(i - 1);

				String currentWord = uniqueWords.get(i);
				String previousWord = uniqueWords.get(i - 1);
				if(currentValue > previousValue){
					uniqueWordsCount.set(i - 1, currentValue);
					uniqueWordsCount.set(i, previousValue);

					uniqueWords.set(i - 1, currentWord);
					uniqueWords.set(i, previousWord);
				}else{
					CorrectOrder++;
				}
			}
		}while(CorrectOrder != uniqueWords.size() - 1);


		for(int i = 0; i < uniqueWords.size(); i++){
			System.out.println(uniqueWordsCount.get(i) + "\t\t" + uniqueWords.get(i));
		}
	}

	public static void main(String[] args) {
		new Main().run();
	}
}